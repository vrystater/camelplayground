package com.seibels.integration.document.render;

import org.apache.camel.Handler;
import org.apache.camel.Header;

public class DocumentRenderStatus {
    private String documentId;
    private String jobId;
    private String location;
    private String renderName;
    private int renderPriority;
    private int status;
    private String message;

    @Handler
    public DocumentRenderStatus factory(@Header("Location") String location,
                                        @Header("renderName") String renderName,
                                        @Header("status") int status,
                                        @Header("documentId") String documentId,
                                        @Header("jobId") String jobId,
                                        @Header("renderPriority") int renderPriority,
                                        @Header("message") String message) {
        this.location = location;
        this.renderName = renderName;
        this.status = status;
        this.message = message;
        this.documentId = documentId;
        this.jobId = jobId;
        this.renderPriority = renderPriority;

        return this;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRenderName() {
        return renderName;
    }

    public void setRenderName(String renderName) {
        this.renderName = renderName;
    }

    public int getRenderPriority() {
        return renderPriority;
    }

    public void setRenderPriority(int renderPriority) {
        this.renderPriority = renderPriority;
    }

    public int getStatus() { return status; }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
