package com.seibels.integration;

import org.apache.camel.Body;
import org.apache.camel.Handler;

import java.util.List;

public class Second extends DomainEntity {
    @Handler
    public Second factory(@Body List<String> body){
        this.name =  body.get(2);
        this.surname =  body.get(1);
        this.name =  body.get(0);
        return this;
    }
}

