package com.seibels.integration;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

public class ThatOtherRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:test?repeatCount=1")
                .log("ding")
                .setBody(constant("hi"))
                .process(exchange -> {
                    log.info(exchange.getPattern().name());
                })
                .to(ExchangePattern.InOut, "activemq:queue:ding?requestTimeout=10")
                .log("after queue")
                .process(exchange -> {
                    log.info(exchange.getPattern().name());
                });


/*
        from("activemq:queue:ding?acknowledgementModeName=CLIENT_ACKNOWLEDGE")
                .log("I am consuming: ${body}")
                .process(exchange -> {
                    log.info(exchange.getPattern().name());
                });
*/

    }
}
