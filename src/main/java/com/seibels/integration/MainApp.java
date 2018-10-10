package com.seibels.integration;

import com.seibels.integration.badstuff.MakeThingsRouteBuilder;
import com.seibels.integration.cluster.ClusterRouteBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.camel.component.ActiveMQConfiguration;
import org.apache.camel.CamelContext;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.camel.component.mail.MailComponent;
import org.apache.camel.main.Main;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new MyOtherRouteBuilder());
//        main.addRouteBuilder(new ClusterRouteBuilder());

        CamelContext camelContext = main.getOrCreateCamelContext();

//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61617");
//        ActiveMQConfiguration configuration = new ActiveMQConfiguration();
//        configuration.setConnectionFactory(activeMQConnectionFactory);
//        camelContext.addComponent("activemq", new ActiveMQComponent(configuration));
        camelContext.addComponent("activemq", activeMQComponent("failover:(tcp://localhost:61616, tcp://localhost:61617)?randomize=false"));
        main.run(args);
    }

}

