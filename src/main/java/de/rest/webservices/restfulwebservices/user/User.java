package de.rest.webservices.restfulwebservices.user;

import java.util.Date;

public class User {

    private String id;
    private String name;
    private Date birthDate;

    // Ohne Default-Konstruktor HTTP 500 Error
    public User() {}

    public User(String id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
