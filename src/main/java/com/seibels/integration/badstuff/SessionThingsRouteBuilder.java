package com.seibels.integration.badstuff;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sjms.SjmsComponent;

public class SessionThingsRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        SjmsComponent component = new SjmsComponent();
        component.setConnectionFactory(new ActiveMQConnectionFactory("tcp://localhost:61616"));
        getContext().addComponent("sjms", component);

        onCompletion()
                .log("I COMPLETED THINGS WTF");


        from("sjms:queue:thequeue?transacted=true&transactionBatchCount=3000&transactionBatchTimeout=500")
                .log("Got something ${body}");


    }
}

