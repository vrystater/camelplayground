package com.seibels.integration;

import org.apache.camel.builder.RouteBuilder;

public class DurableTopicTestWhaaat extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:test?period=2000")
                .log("Hi")
                .process(exchange -> {
                    exchange.getIn().setBody(System.currentTimeMillis());
                })
                .to("activemq:topic:VirtualTopic.THING");

    }
}
