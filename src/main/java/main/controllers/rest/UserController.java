package main.controllers.rest;

import main.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UserController {

    @RequestMapping("/getUserDummy")
    public User getUser(@RequestParam(value = "name", defaultValue = "dummy") String name) {

        return new User(name, "<Todo: hashed_password>", "<Todo: token, if any>");
    }
}
