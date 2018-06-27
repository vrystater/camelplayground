package com.seibels.integration;

import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;

import java.io.FileNotFoundException;

public class PollEnrichRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        onException(FileNotFoundException.class)
                .handled(true)
                .to("activemq:queue:filenotfound")
                .log("oh hi");

        from("activemq:queue:testfiles?acknowledgementModeName=CLIENT_ACKNOWLEDGE")
                .log("hi")
                .setHeader("mehmeh", constant("test.txt"))
                .pollEnrich().simple("file:/home/qg/tmp?fileName=${header.mehmeh}&move=mehmeh").timeout(0)
                .aggregationStrategy((timerexchange, fileexchange) -> {
                    System.out.println("is Tony Tony = " + (fileexchange == null));
                    if (fileexchange == null) {
                        System.out.println("m = " + timerexchange.getIn().getHeader("mehmeh"));
                        timerexchange.setException(new FileNotFoundException("meh"));
                        return timerexchange;
                    }
                    Object body = fileexchange.getIn().getBody();
                    System.out.println("body = " + body);

                    timerexchange.getIn().setBody(body);
                    return timerexchange;
                })

                .log("${body}");
    }
}
