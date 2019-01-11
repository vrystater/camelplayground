package com.seibels.integration;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

public class FTPRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file:/home/qg/tmp?fileName=test.txt&delay=3000&move=success&move&moveFailed=failed")
                .log("ding")
                .setHeader("destination", constant("BLEGH"))
                .setHeader("destination_path", constant("/meh"))
                .log("going to queue")
                .to(ExchangePattern.InOnly, "activemq:queue:ftp.out");

    }


}
