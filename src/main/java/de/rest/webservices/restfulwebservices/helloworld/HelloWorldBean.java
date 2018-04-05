package de.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {

    private String message;

    HelloWorldBean(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean " + message;
    }

    public String getMessage() {
        return message;
    }
}
