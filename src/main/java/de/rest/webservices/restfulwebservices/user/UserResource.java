package de.rest.webservices.restfulwebservices.user;

import de.rest.webservices.restfulwebservices.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userService;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUserBy(@PathVariable String id) {
        User user = userService.findByID(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else
            throw new ResourceNotFoundException("User not found.");
    }


    @PostMapping(path = "/users")
    public ResponseEntity<Object> addUser(@RequestBody User user) {

        User newAddedUser = userService.addUser(user);

        // Wir benutzen ServletUriComponentsBuilder um die URI http://asd.de:8080/users/X generisch zusammen zu stellen
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAddedUser.getId())
                .toUri();

        // Dadurch wird HTTP 201 mit der URI zurückgegeben. (201 bedeutet created.)
        return ResponseEntity.created(location).build();
    }
}
