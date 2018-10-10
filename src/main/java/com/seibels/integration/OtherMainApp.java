package com.seibels.integration;

import org.apache.camel.CamelContext;
import org.apache.camel.main.Main;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

public class OtherMainApp {
    public static void main(String[] args) {
        try {
            new OtherMainApp().execute(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(String[] args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new DurableSubscriber());

        CamelContext camelContext = main.getOrCreateCamelContext();
        camelContext.addComponent("activemq", activeMQComponent("tcp://localhost:61616"));
        main.run(args);

    }
}
