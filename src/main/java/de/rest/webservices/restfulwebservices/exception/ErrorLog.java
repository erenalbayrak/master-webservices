package de.rest.webservices.restfulwebservices.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorLog {

    private String uuid;
    private Date occurTime;
    private String exceptionString;
    private StackTraceElement[] stackTrace;

    public ErrorLog(String uuid, Exception exception) {
        this.uuid      = uuid;
        this.occurTime = new Date();
        this.exceptionString = exception.toString();
        this.stackTrace = exception.getStackTrace();
    }
}

