package com.seibels.integration.badstuff;

import org.apache.camel.builder.RouteBuilder;

public class WhenAmIDoneRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {


        from("quartz2:test/test?cron=5+*+*+?+*+*")
                .log("Hi")
                .to("direct:dostuff");

        from("direct:dostuff")
                .log("######################################START##############################################")
                .process(new QueueBrowserProcessor())
                .log("#######################################DONE#############################################");

        from("direct:next")
                .log("Got it: ${body}");

    }
}
