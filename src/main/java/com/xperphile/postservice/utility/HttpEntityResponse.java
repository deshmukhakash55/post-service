package com.xperphile.postservice.utility;

import org.springframework.http.HttpStatus;

public class HttpEntityResponse {

    private HttpStatus httpStatus;
    private byte[] content;

    public HttpEntityResponse() {
    }

    public HttpEntityResponse(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpEntityResponse(HttpStatus httpStatus, byte[] content) {
        this.httpStatus = httpStatus;
        this.content = content;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
