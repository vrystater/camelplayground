package com.seibels.integration;

import com.seibels.integration.badstuff.SessionThingsRouteBuilder;
import com.seibels.integration.badstuff.WhenAmIDoneRouteBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.quartz2.QuartzComponent;
import org.apache.camel.component.sjms.SjmsComponent;
import org.apache.camel.main.Main;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

public class RunMe {

    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new SessionThingsRouteBuilder());

        CamelContext camelContext = main.getOrCreateCamelContext();
        SjmsComponent component = new SjmsComponent();
        component.setConnectionFactory(new ActiveMQConnectionFactory("tcp://localhost:61616", "admin", "admin"));
        camelContext.addComponent("sjms", component);
/*
        camelContext.addComponent("activemq", activeMQComponent("tcp://localhost:61616"));
        camelContext.addComponent("quartz2", new QuartzComponent());
*/
        main.run(args);
    }
}
