package com.seibels.integration;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.InputStream;

public class HappyPlace4 extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:businesstime?repeatCount=1")
                .setHeader("documentId", constant("1693439"))
                .setHeader("groupCode", constant("GPCIC"))
//                .setHeader(Exchange.HTTP_URI, simple("http://localhost:8792/document/${header.groupCode}/${header.documentId}"))
                .toD("http://localhost:8792/document/${header.groupCode}/${header.documentId}")
//                .to("http://meh")
                .process(exchange -> {
                    System.out.println("exchange.getIn().getBody().getClass().getName() = " + exchange.getIn().getBody().getClass().getName());
                })
//                .to("restlet:http://localhost:8792/document/{groupCode}/{documentId}?streamRepresentation=true&autoCloseStream=true")
                .log("Found my happy place")
                .convertBodyTo(byte[].class)
                .process(exchange -> {
                    System.out.println("exchange = " + exchange.getIn().getBody().getClass().getName());
                    System.out.println("exchange length = " + exchange.getIn().getBody(byte[].class).length);
//                    System.out.println("ba.length = " + ba.);
                })
                .log("KKKKK")
                .process(exchange -> {
                    byte[] fileData = exchange.getIn().getBody(byte[].class);
//                    System.out.println("fileData.length = " + fileData.length);
                    int count = PDDocument.load(fileData).getNumberOfPages();

                    exchange.getIn().setHeader("pageCount", count);
                })
                .log("${header.pageCount}");
    }
}
