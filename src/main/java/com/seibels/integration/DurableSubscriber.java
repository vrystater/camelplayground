package com.seibels.integration;

import org.apache.camel.builder.RouteBuilder;

public class DurableSubscriber extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:queue:Consumer.BC.VirtualTopic.THING")
                .log("ONE BC: ${body}");

        from("activemq:queue:Consumer.BC.VirtualTopic.THING")
                .log("TWO BC: ${body}");

        from("activemq:queue:Consumer.PC.VirtualTopic.THING")
                .log("ONE PC: ${body}");

        from("activemq:queue:Consumer.PC.VirtualTopic.THING")
                .log("TWO PC: ${body}");

        from("activemq:queue:Consumer.AB.VirtualTopic.THING")
                .log("ONE AB: ${body}");

        from("activemq:queue:Consumer.AB.VirtualTopic.THING")
                .log("TWO AB: ${body}");
    }
}
