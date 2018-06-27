package com.seibels.integration.document.render;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Header;

public class DocumentRenderResponse {
    private String code;

    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Handler
    public DocumentRenderResponse factory(@Header(Exchange.HTTP_RESPONSE_CODE) String code,
                                          @Body String message) {
        this.code = code;
        this.status = message;

        return this;
    }


}
