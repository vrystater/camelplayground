package com.seibels.integration;

import com.seibels.integration.document.render.DocumentRenderStatus;
import com.seibels.integration.document.render.IndexInfoItem;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.util.ArrayList;
import java.util.List;

public class MyOtherOtherRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {


        from("timer:test?repeatCount=1")
                .log("ding")
                .delay(2000)
                .setHeader("Location", constant("test"))
                .setHeader("renderName", constant("GS-dev-quote-921-20180622082740"))
                .setHeader("status", constant(200))
                .setHeader("storeDocumentGroupCode", constant("GPCIC"))
                .setHeader("jobId", constant("12"))
                .setHeader("renderPriority", constant(8))
                .setHeader("SeibelsReplyTo", constant("walterisweirdsoweird"))
                .setHeader("documentId", constant("12"))
                .setHeader("message", constant("Crap broke oh noes"))
                .log("Beaning")
                .bean(DocumentRenderStatus.class)
                .log("Marshalling")
                .marshal().json(JsonLibrary.Gson)
                .log("Converting")
                .convertBodyTo(String.class)
                .log("walter");

    }

}
