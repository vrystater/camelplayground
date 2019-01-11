package com.seibels.integration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import javax.jms.*;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;


public class SyncJMSTopicRoute extends RouteBuilder{

    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new SyncJMSTopicRoute());
        CamelContext camelContext = main.getOrCreateCamelContext();
        camelContext.addComponent("activemq", activeMQComponent("failover:(tcp://localhost:61616, tcp://localhost:61617)?randomize=false"));
        main.run(args);
    }

    @Override
    public void configure() throws Exception {
        from("timer:test?repeatCount=10&period=1000")
                .log("PING")
                .setBody(constant("START"))
                .process(this::moreWeirdThings)
                .log("STICK A FORK IN ME ${body}");


                // BC
        from("activemq:topic:search")
                .log("A1 got a message")
                .setBody(constant("A1"))
                .delay(2000);


        // IPX
        from("activemq:topic:search")
                .log("B1 got a message")
                .setBody(constant("B1"));
    }


    private void moreWeirdThings(Exchange exchange) throws JMSException, InterruptedException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Waiting for an answer
        Destination temporaryQueue = session.createTemporaryQueue();
        MessageConsumer consumer = session.createConsumer(temporaryQueue);
        log.info("THIS IS SO WEIRD: " + ((TemporaryQueue) temporaryQueue).getQueueName());

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage tMessage = (TextMessage) message;
                    try {
                        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                        log.info(tMessage.getText());
                        String body = exchange.getIn().getBody(String.class);
                        exchange.getIn().setBody(body + "-" + tMessage.getText());
                        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        // Asking a question
        Topic searchTopic = session.createTopic("search");
        MessageProducer producer = session.createProducer(searchTopic);

        TextMessage testMessage = session.createTextMessage("Test message");
        testMessage.setJMSReplyTo(temporaryQueue);
        producer.send(testMessage);
        producer.close();

        Thread.sleep(5000);
/*
        consumer.close();
        session.close();
        connection.close();*/
    }

}
