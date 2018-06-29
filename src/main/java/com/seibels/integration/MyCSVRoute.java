package com.seibels.integration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;



public class MyCSVRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        DataFormat bindy = new BindyCsvDataFormat(Order.class);


        from("file:/home/qg/tmp?fileName=csv.txt&noop=true")
                .unmarshal(bindy)
                .split().body()
                .marshal().json(JsonLibrary.Gson)
                .toD("file:/home/qg/tmp/results?fileName=${exchange}.json")
                .log("Meh");

    }
}
