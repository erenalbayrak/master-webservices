package de.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(UUID.randomUUID().toString(), "Eren Albayrak", new Date()));
        users.add(new User(UUID.randomUUID().toString(), "Maggi Müller", new Date()));
        users.add(new User(UUID.randomUUID().toString(), "Carol Ann", new Date()));
        users.add(new User(UUID.randomUUID().toString(), "Ece", new Date()));
        users.add(new User(UUID.randomUUID().toString(), "Efe", new Date()));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User addUser(User user) {
        user.setId(UUID.randomUUID().toString());
        users.add(user);
        return user;
    }

    public User findByID(String id) {
        return users.stream()
                .filter( x -> x.getId().equals(id) )
                .findFirst()
                .orElse(null);
    }

    public boolean deleteByID(String id) {
        User user = users.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst().orElse(null);
        return user != null && users.remove(user);
    }
}
