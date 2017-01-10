package main.controllers.rest;

import main.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class SequenceController {

    @RequestMapping("/getSequenceDummy")
    public User getSequence(@RequestParam(value = "name", defaultValue = "dummy") String name) {
        return null;
    }


}
