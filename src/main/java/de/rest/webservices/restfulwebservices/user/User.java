package de.rest.webservices.restfulwebservices.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

// http://localhost:8080/h2-console
// JDBC URL: jdbc:h2:mem:testdb
@ApiModel(description = "All details about the user.")
@Entity
public class User {

    @Id
    private String id;

    @Size(min = 2, message = "Name muss mindestends 2 Zeichen lang sein.")
    @ApiModelProperty(notes = "Name have to contain at least 2 characters.")
    private String name;

    @Past
    @ApiModelProperty(notes = "Birth date have to be in the past.")
    private Date birthDate;

    // Ohne Default-Konstruktor HTTP 500 Error
    public User() {}

    public User(String id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @PrePersist
    private void prePersist() {
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        }
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
