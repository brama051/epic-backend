package main.controllers.rest;

import main.db.SequenceListManager;
import main.db.SequenceManager;
import main.db.UserAuthentication;
import main.models.Sequence;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/sequence")
public class SequenceController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Sequence getSequence(@RequestParam(value = "token", defaultValue = "") String token, @RequestParam(value = "sequenceNumber", defaultValue = "") Long sequenceNumber) {
        //Token validation
        UserAuthentication userAuthentication = new UserAuthentication();
        if (userAuthentication.getUserByToken(token) == "") {
            return null;
        }

        SequenceManager sequenceManager = new SequenceManager();
        Sequence sequence = sequenceManager.getSequence(sequenceNumber);
        sequenceManager.close();

        return sequence;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Sequence createSequence(@RequestParam(value = "token", defaultValue = "") String token,
                                   @RequestParam(value = "sequenceNumber", defaultValue = "") Long sequenceNumber,
                                   @RequestParam(value = "by_user", defaultValue = "") String by_user,
                                   @RequestParam(value = "purpose", defaultValue = "") String purpose,
                                   @RequestParam(value = "date", defaultValue = "") Date date) {
        //Token validation
        UserAuthentication userAuthentication = new UserAuthentication();
        if (userAuthentication.getUserByToken(token) == "") {
            userAuthentication.close();
            return new Sequence();
        }

        SequenceManager sequenceManager = new SequenceManager();
        Long nextAvailableSequenceNumber = sequenceManager.getNextAvailableSequenceNumber();

        //Check if requested sequence number is not taken in the meantime.
        if (nextAvailableSequenceNumber > sequenceNumber) {
            //if it's taken, return a sequence with a new sequence number
            return new Sequence(nextAvailableSequenceNumber, by_user, purpose, date);
        } else {
            //else, save the requested sequence
            Sequence sequence;
            sequence = new Sequence(sequenceNumber, by_user, purpose, date);
            sequenceManager.createSequence(sequence);
            sequenceManager.close();
            return sequence;
        }

    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public Sequence requestSequence(@RequestParam(value = "token", defaultValue = "") String token) {
        //Token validation
        UserAuthentication userAuthentication = new UserAuthentication();
        String username = userAuthentication.getUserByToken(token);
        if (username == "") {
            userAuthentication.close();
            return new Sequence();
        } else {
            SequenceManager sequenceManager = new SequenceManager();
            Long nextAvailableSequenceNumber = sequenceManager.getNextAvailableSequenceNumber();
            return new Sequence(nextAvailableSequenceNumber, username, "", new Date());
        }
    }

    /*@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteSequence(@RequestParam(value = "token", defaultValue = "") String token,
                                 @RequestParam(value = "sequenceNumber", defaultValue = "0") Long sequenceNumber) {

        UserAuthentication userAuthentication = new UserAuthentication();
        String username = userAuthentication.getUserByToken(token);
        if (username == "") {
            userAuthentication.close();
            return "";
        } else {
            SequenceManager sequenceManager = new SequenceManager();

            return sequenceManager.deleteSequence(sequenceNumber);
        }
    }*/


}
