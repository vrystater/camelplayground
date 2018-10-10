package com.seibels.integration.badstuff;

import org.apache.camel.builder.RouteBuilder;

public class MakeThingsRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:name?repeatCount=10000&period=1")
                .process(exchange -> {
                    exchange.getIn().setBody(System.currentTimeMillis());
                })
                .log("Made: ${body}")
                .convertBodyTo(String.class)
                .to("activemq:queue:thequeue?");

    }
}
