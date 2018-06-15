package com.seibels.integration;

import org.apache.camel.builder.RouteBuilder;

public class MyRestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:test?repeatCount=1")
                .log("ping")
                .setHeader("documentId", constant("1686802"))
                .setHeader("groupCode", constant("GPCIC"))
                .to("restlet:http://localhost:8791/document/{groupCode}/{documentId}?restletMethod=GET&synchronous=true&streamRepresentation=true")
                .convertBodyTo(byte[].class)
                .process(exchange -> {
                    System.out.println("FIle = " + exchange.getIn().getBody(byte[].class).length);
                })
                .to("file:/home/qg/tmp?fileName=walterisweird.pdf");

    }
}
