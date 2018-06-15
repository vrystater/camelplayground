package com.seibels.integration;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRKSRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {


        from("activemq:queue:expectedReceiptsService.in?selector=endpoint='BC'")
                .log("Got message with header ${header.endpoint}")
                .delay(1000)
                .log("ONE");

        from("activemq:queue:expectedReceiptsService.in?selector=endpoint='IPX'")
                .log("Got message with header ${header.endpoint}")
                .delay(2000)
                .setBody(constant("{\"matchType\": \"EXACT\"}"))
                .log("TWO");

/*        from("activemq:queue:expectedReceiptsService.in?selector=endpoint='SIPS'")
                .log("Got message with header ${header.endpoint}")
                .delay(3000)
                .setBody(constant("{\"matchType\": \"NONE\"}"))
                .log("THREE");*/


    }


}
