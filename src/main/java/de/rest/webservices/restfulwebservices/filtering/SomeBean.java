package de.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SomeBean {

    private String fild1;
    private String fild2;

    // Filtering/Exclude:
    @JsonIgnore
    private String fild3;

    public SomeBean(String value1, String value2, String value3) {
        fild1 = value1;
        fild2 = value2;
        fild3 = value3;
    }

    public String getFild1() {
        return fild1;
    }

    public void setFild1(String fild1) {
        this.fild1 = fild1;
    }

    public String getFild2() {
        return fild2;
    }

    public void setFild2(String fild2) {
        this.fild2 = fild2;
    }

    public String getFild3() {
        return fild3;
    }

    public void setFild3(String fild3) {
        this.fild3 = fild3;
    }
}
