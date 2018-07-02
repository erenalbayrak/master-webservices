package de.rest.webservices.restfulwebservices.user.entity_access;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.rest.webservices.restfulwebservices.user.User;

import javax.persistence.*;

// http://localhost:8080/h2-console
// JDBC URL: jdbc:h2:mem:testdb
@Entity
public class Post {

    @Id @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
