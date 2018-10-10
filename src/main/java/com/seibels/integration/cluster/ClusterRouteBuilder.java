package com.seibels.integration.cluster;

import org.apache.camel.builder.RouteBuilder;

public class ClusterRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:name?period=500")
                .log("making")
                .setBody(constant("hi"))
                .to("activemq:queue:testThings");


        from("activemq:queue:testThings")
                .delay(1000)
                .log("taking");
    }
}
