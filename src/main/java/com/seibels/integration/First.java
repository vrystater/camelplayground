package com.seibels.integration;

import org.apache.camel.Body;
import org.apache.camel.Handler;

import java.util.List;

public class First extends DomainEntity{
    @Handler
    public First factory(@Body List<String> body){
        this.name = body.get(0);
        this.surname = body.get(1);
        this.name = body.get(2);
        return this;
    }
}

