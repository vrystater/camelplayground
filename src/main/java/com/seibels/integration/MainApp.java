package com.seibels.integration;

import org.apache.camel.CamelContext;
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
//        main.addRouteBuilder(new MyRouteBuilder());
        main.addRouteBuilder(new MyOtherOtherRouteBuilder());
//        main.addRouteBuilder(new MyOtherRouteBuilder());
//        main.addRouteBuilder(new HappyPlace4());
//        main.addRouteBuilder(new PollEnrichRoute());

        CamelContext camelContext = main.getOrCreateCamelContext();
        camelContext.addComponent("activemq", activeMQComponent("tcp://localhost:61616"));
        main.run(args);
    }

}

