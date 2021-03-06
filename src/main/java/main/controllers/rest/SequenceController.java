package main.controllers.rest;

import main.db.SequenceManager;
import main.db.UserAuthentication;
import main.models.Sequence;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/sequence")
public class SequenceController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Sequence getSequence(@RequestParam(value = "token", defaultValue = "") String token, @RequestParam(value = "sequenceNumber", defaultValue = "") Long sequenceNumber) {
        //Token validation
        UserAuthentication userAuthentication = new UserAuthentication();
        if (userAuthentication.getUserByToken(token).equals("") || token.equals("")) {
            userAuthentication.close();
            return new Sequence();

        }

        SequenceManager sequenceManager = new SequenceManager();
        Sequence sequence = sequenceManager.getSequence(sequenceNumber);
        sequenceManager.close();

        return sequence;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Sequence createSequence(@RequestParam(value = "token", defaultValue = "") String token, @RequestBody Sequence newSequence) {
        //return newSequence;

        Long sequenceNumber = newSequence.getSequenceNumber();
        String by_user = newSequence.byUser;
        String purpose = newSequence.purpose;
        Date date = newSequence.date;


        //Token validation
        UserAuthentication userAuthentication = new UserAuthentication();
        String actualUser = userAuthentication.getUserByToken(token);
        if (actualUser.equals("") || token.equals("")) {
            userAuthentication.close();
            return new Sequence();
        }
        //Check if user is the right one
        if (!newSequence.byUser.equals(actualUser)) {
            newSequence.byUser = actualUser;
            userAuthentication.close();
            return newSequence;
        }

        //If everything is correct, continue with checking the requested sequenceNumber
        SequenceManager sequenceManager = new SequenceManager();
        Long nextAvailableSequenceNumber = sequenceManager.getNextAvailableSequenceNumber();
        //Check if requested sequence number is not taken in the meantime.
        if (nextAvailableSequenceNumber.equals(sequenceNumber)) {
            // save the requested sequence
            Sequence savedSequence = sequenceManager.createSequence(newSequence);
            sequenceManager.close();
            return savedSequence;
        } else {
            //return a sequence with a new sequence number
            sequenceManager.close();
            return new Sequence(nextAvailableSequenceNumber, by_user, purpose, date);
        }

    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public Sequence requestSequence(@RequestParam(value = "token", defaultValue = "") String token) {
        //Token validation
        UserAuthentication userAuthentication = new UserAuthentication();
        String username = userAuthentication.getUserByToken(token);
        if (username.equals("") || token.equals("")) {
            userAuthentication.close();
            return new Sequence();
        } else {
            SequenceManager sequenceManager = new SequenceManager();
            Long nextAvailableSequenceNumber = sequenceManager.getNextAvailableSequenceNumber();
            sequenceManager.close();
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
