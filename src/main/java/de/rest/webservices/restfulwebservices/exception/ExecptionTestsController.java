package de.rest.webservices.restfulwebservices.exception;

import de.rest.webservices.restfulwebservices.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ExecptionTestsController {

    @GetMapping(path = "/fire-exception")
    public String fireNormalExeption() {

        throw new ArrayIndexOutOfBoundsException("bla bla bla");
        // return "XXX";
    }

    @PostMapping(path = "/fire-validation-exception")
    public String fireValidationException(@Valid @RequestBody User user) {
        // Request should look like this: {"name":"E","birthDate":"2015-04-23"}
        return "bla bla bla";
    }
}
