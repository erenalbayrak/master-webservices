package de.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    /**
     * There is no perfect solution for versioning.
     * */
    // URI pollution
    @GetMapping(path = "/v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Carol Ann");
    }
    @GetMapping(path = "/v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Carol", "Ann"));
    }

    // URI pollution
    @GetMapping(path = "/person/param", params = "API-VERSION=1")
    public PersonV1 paramV1() {
        return new PersonV1("Carol Ann");
    }
    @GetMapping(path = "/person/param", params = "API-VERSION=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Carol", "Ann"));
    }

    // Bad for caching & using with browser.
    // More difficult to generate documentation.
    @GetMapping(path = "/person/header", headers = "API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("Carol Ann");
    }
    @GetMapping(path = "/person/header", headers = "API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Carol", "Ann"));
    }
}
