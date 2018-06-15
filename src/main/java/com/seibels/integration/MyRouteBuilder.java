package com.seibels.integration;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        from("timer:test?period=100000")
                .log("test")
                .setBody(constant(42d))
                .setHeader("Carrier", constant("gs"))
                .recipientList(method(Recipients.class)).parallelProcessing().aggregationStrategy((oldExchange, newExchange) -> {
                    if (oldExchange == null) {
                        return newExchange;
                    }
                    double oldNum = oldExchange.getIn().getBody(double.class);
                    double newNum = newExchange.getIn().getBody(double.class);
                    log.info("old = " + oldNum);
                    log.info("new = " + newNum);

                    if (oldNum >= newNum) {
                        return oldExchange;
                    } else {
                        return newExchange;
                    }

                })
                .end()
                .log("BODY and final result: ${body}");


        from("direct:BC")
                .setHeader("meh", constant("A"))
                .to(ExchangePattern.InOut, "activemq:queue:test?requestTimeout=5000");
        from("direct:IPX")
                .setHeader("meh", constant("B"))
                .to(ExchangePattern.InOut, "activemq:queue:test?requestTimeout=5000");
        from("direct:SIPS")
                .setHeader("meh", constant("C"))
                .to(ExchangePattern.InOut, "activemq:queue:test?requestTimeout=5000");
        from("direct:UNKNOWN")
                .log("WHO ARE YOU?");


        // THIS IS JUST FOR TESTING AND WILL HAPPEN IN BC, IPX or SIBS
        // THIS IS NOT PART OF THE ROUTE AND ONLY FOR TESTING

        from("activemq:queue:test?selector=meh='A'")
                .log("Got message with header ${header.meh}")
                .delay(1000)
                .setBody(constant(1d))
                .log("ONE");

/*        from("activemq:queue:test?selector=meh='B'")
                .log("Got message with header ${header.meh}")
                .delay(3000)
                .setBody(constant(2d))
                .log("TWO");

        from("activemq:queue:test?selector=meh='C'")
                .log("Got message with header ${header.meh}")
                .delay(5000)
                .setBody(constant(3d))
                .log("THREE");*/


    }


}
