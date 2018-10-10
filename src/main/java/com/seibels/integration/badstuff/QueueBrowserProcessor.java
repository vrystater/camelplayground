package com.seibels.integration.badstuff;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.io.FileUtils;

import javax.jms.*;
import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class QueueBrowserProcessor implements Processor {
    private Connection _connection;
    private Session _session;
    private QueueBrowser _queueBrowser;
    private MessageConsumer _messageConsumer;

    @Override
    public void process(Exchange exchange) throws Exception {
            initActiveMQComponents(exchange, null);
            Enumeration msgsBrowser = _queueBrowser.getEnumeration();
            ProducerTemplate producerTemplate = exchange.getContext().createProducerTemplate();
            int count = 0;
            try {
                while (msgsBrowser.hasMoreElements()) {
                    TextMessage msg = (TextMessage) _messageConsumer.receiveNoWait();
                    producerTemplate.send("direct:next", tempExchange -> {
                        tempExchange.getIn().setBody(msg.getText());
                    });
                    msgsBrowser.nextElement();
                    count++;
                }
                System.out.println("I EATED THIS MANY = " + count);
                 _session.commit();
            } finally {
                dealocateActiveMQComponents();
            }
    }


    private void initActiveMQComponents(Exchange exchange, String additonalSelector) throws Exception {
        CamelContext context = exchange.getContext();
//        String companyCode = context.resolvePropertyPlaceholders("{{companyCode}}");
//        String selector = "Carrier ='" + companyCode + "' " + additonalSelector;
//        System.out.print(selector);
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory() {{
            setBrokerURL("tcp://localhost:61616");
            setPassword("admin");
            setUserName("admin");
        }};
        _connection = connectionFactory.createConnection();
        _session = _connection.createSession(true, Session.SESSION_TRANSACTED);
        Queue queue = _session.createQueue("thequeue");
        _queueBrowser = _session.createBrowser(queue);
        _messageConsumer = _session.createConsumer(queue);
        _connection.start();
    }


    private void dealocateActiveMQComponents() throws Exception {
        _queueBrowser.close();
        _messageConsumer.close();
        _session.close();
        _connection.close();
    }

}

