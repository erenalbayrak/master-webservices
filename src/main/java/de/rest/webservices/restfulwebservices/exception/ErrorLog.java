package de.rest.webservices.restfulwebservices.exception;

import java.util.Date;

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

    public String getUuid() {
        return uuid;
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public String getExceptionString() {
        return exceptionString;
    }

    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }
}
