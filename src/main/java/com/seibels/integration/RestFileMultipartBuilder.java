package com.seibels.integration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.Map;

public class RestFileMultipartBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().host("0.0.0.0").port(1234);

        rest().post("/email")
                .consumes("multipart/form-data")
                .to("direct:here");

        from("direct:here")
//                .log("${body}")
                .process(exchange -> {
                    System.out.println("exchange = " + exchange.getIn().getBody().getClass().getName());
                    Object body = exchange.getIn().getBody();
//                    log.info(exchange.getIn().getBody(String.class));
                })
                .unmarshal().mimeMultipart()
                .log("${headers}")
                .process(exchange -> {
                    log.info(exchange.getIn().getBody(String.class));
                    log.info(exchange.getIn().getAttachments().size() + "");
                })
                .log("Hi");
    }
}
