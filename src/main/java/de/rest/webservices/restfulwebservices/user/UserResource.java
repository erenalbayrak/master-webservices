package de.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userService;

    @GetMapping(path = "/all-users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/user/{id}")
    public User getUserBy(@PathVariable String id) {
        return userService.findByID(id);
    }

    /*
    @PostMapping(path = "/add-user")
    public ResponseEntity<Object> addUser(@RequestBody User user) {

        User newAddedUser = userService.addUser(user);

        // ResponseEntity<Object>.created(location)
    }
    */
}
