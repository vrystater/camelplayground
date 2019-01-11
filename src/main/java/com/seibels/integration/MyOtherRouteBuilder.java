package com.seibels.integration;

import com.google.gson.Gson;
import com.seibels.integration.document.render.IndexInfoItem;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

import java.util.ArrayList;
import java.util.List;

public class MyOtherRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {


        from("file:/home/qg/tmp?fileName=test.pdf&delay=3000&move=success&move&moveFailed=failed")
                .log("ding")
                .setHeader("renderName", constant("test"))
                .setHeader("storeDocumentGroupCode", constant("TEST"))
                .setHeader("Location", constant("test"))
                .setHeader("jobId", constant("12"))
                .setHeader("renderPriority", constant(8))
//                .setHeader("CamelFileNameOnly", constant("test.pdf"))
                .process(exchange -> {
                    List<IndexInfoItem> stuff = makeIndexInfoItems();
                    exchange.getIn().setHeader("storeIndexItems", new Gson().toJson(stuff));
                })
                .log("going to queue")
                .convertBodyTo(byte[].class)
                .to(ExchangePattern.InOnly, "activemq:queue:DMSMessageBus")
                .log("Response in TEST THING: ${headers.documentID}")
                .log("Response in TEST THING: ${headers.status}")
                .log("Response in TEST THING: ${headers.message}");

/*
        from("timer:test?repeatCount=1")
                .log("ding")
                .setHeader("renderName", constant("GS-dev-quote-921-20180622082740"))
                .setHeader("storeDocumentGroupCode", constant("GPCIC"))
                .setHeader("Location", constant("test"))
                .setHeader("jobId", constant("12"))
                .setHeader("renderPriority", constant(8))
                .setHeader("SeibelsReplyTo", constant("walterisweirdsoweird"))
*/
/*                .process(exchange -> {
                    List<IndexInfoItem> stuff = makeIndexInfoItems();
                    exchange.getIn().setHeader("storeIndexItems", new Gson().toJson(stuff));
                })*//*

                .log("going to queue")
                .convertBodyTo(byte[].class)
                .to("activemq:queue:documentRenderStore");
*/
    }

    private List<IndexInfoItem> makeIndexInfoItems() {
//        return null;
        List<IndexInfoItem> stuff = new ArrayList<>();
        addInfoItem(stuff, "Account Number", "GSA00000001-LOCAL");
        addInfoItem(stuff, "Process Date", "2018-05-30");
        addInfoItem(stuff, "Document Type", "POL - System Documents");
        addInfoItem(stuff, "Document SubType", "QUOTE PROPOSAL");
        addInfoItem(stuff, "Description", "Quote (Submission GSQ00000001-LOCAL [Version #1]) Eff 2018-05-25");
        addInfoItem(stuff, "Document Title", "Quote (Submission GSQ00000001-LOCAL [Version #1]) Eff 2018-05-25");
        addInfoItem(stuff, "Company Code", "GPCIC");
        addInfoItem(stuff, "Document ID", "GS-local-401");
        addInfoItem(stuff, "Quote Number", "GSQ00000001-LOCAL");
        addInfoItem(stuff, "Process Type", "Policy Administration");
        return stuff;
    }

    private void addInfoItem(List<IndexInfoItem> stuff, String name, String value) {
        IndexInfoItem e = new IndexInfoItem();
        e.setName(name);
        e.setValue(value);
        stuff.add(e);
    }
}
