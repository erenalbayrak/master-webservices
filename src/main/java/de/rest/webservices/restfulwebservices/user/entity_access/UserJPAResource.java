package de.rest.webservices.restfulwebservices.user.entity_access;

import de.rest.webservices.restfulwebservices.exception.ResourceNotFoundException;
import de.rest.webservices.restfulwebservices.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public ResponseEntity<Resource> getUserBy(@PathVariable String id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            // HATEOAS
            Resource<User> resource = new Resource<>(user.get());
            ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
            resource.add(link.withRel("all-users"));

            return new ResponseEntity<>(resource, HttpStatus.OK);
        } else
            throw new ResourceNotFoundException("User not found with ID: " + id);
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {

        User newAddedUser = userRepository.save(user);

        // Wir benutzen ServletUriComponentsBuilder um die URI http://asd.de:8080/users/X generisch zusammen zu stellen
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAddedUser.getId())
                .toUri();

        // Dadurch wird HTTP 201 mit der URI zurückgegeben. (201 bedeutet created.)
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUserBy(@PathVariable String id) {
        userRepository.deleteById(id);
    }
}
