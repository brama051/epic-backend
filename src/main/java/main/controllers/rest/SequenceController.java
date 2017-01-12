package main.controllers.rest;

import main.db.SequenceListManager;
import main.db.SequenceManager;
import main.db.UserAuthentication;
import main.models.Sequence;
import main.models.SequenceList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SequenceController {

    @RequestMapping("/sequence")
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

    @RequestMapping("/sequences")
    public SequenceList getSequenceList(@RequestParam(value = "token", defaultValue = "") String token) {
        //Token validation
        UserAuthentication userAuthentication = new UserAuthentication();
        if (userAuthentication.getUserByToken(token) == "") {
            return null;
        }

        SequenceListManager sequenceListManager = new SequenceListManager();
        SequenceList sequenceList = sequenceListManager.getSequenceList();
        sequenceListManager.close();

        return sequenceList;
    }

    @RequestMapping("/sequences/page")
    public SequenceList getSequencePage(@RequestParam(value = "token", defaultValue = "") String token,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "itemsPerPage", defaultValue = "1") int itemsPerpage,
                                        @RequestParam(value = "filter", defaultValue = "") String filter) {
        //Token validation
        UserAuthentication userAuthentication = new UserAuthentication();
        if (userAuthentication.getUserByToken(token) == "") {
            return null;
        }

        SequenceListManager sequenceListManager = new SequenceListManager();
        SequenceList sequenceList = sequenceListManager.getSequenceListPage(page, itemsPerpage, filter);
        sequenceListManager.close();

        return sequenceList;
    }

    @RequestMapping(value = "/sequence/new", method = RequestMethod.POST)
    public Sequence createSequence(@RequestParam(value = "token", defaultValue = "") String token,
                                   @RequestParam(value = "sequenceNumber", defaultValue = "") Long sequenceNumber,
                                   @RequestParam(value = "by_user", defaultValue = "") String by_user,
                                   @RequestParam(value = "purpose", defaultValue = "") String purpose,
                                   @RequestParam(value = "date", defaultValue = "") Date date) {
        //Token validation
        UserAuthentication userAuthentication = new UserAuthentication();
        if (userAuthentication.getUserByToken(token) == "") {
            return null;
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

    @RequestMapping(value = "/sequence/request", method = RequestMethod.POST)
    public Sequence requestSequence(@RequestParam(value = "token", defaultValue = "") String token) {
        //Token validation
        UserAuthentication userAuthentication = new UserAuthentication();
        String username = userAuthentication.getUserByToken(token);
        if (username == "") {
            return null;
        } else {
            SequenceManager sequenceManager = new SequenceManager();
            Long nextAvailableSequenceNumber = sequenceManager.getNextAvailableSequenceNumber();
            return new Sequence(nextAvailableSequenceNumber, username, "", new Date());
        }
    }

    @RequestMapping(value = "/sequence/", method = RequestMethod.DELETE)
    public String deleteSequence(@RequestParam(value = "token", defaultValue = "") String token,
                                 @RequestParam(value = "sequenceNumber", defaultValue = "0") Long sequenceNumber) {

        UserAuthentication userAuthentication = new UserAuthentication();
        String username = userAuthentication.getUserByToken(token);
        if (username == "") {
            return null;
        } else {
            SequenceManager sequenceManager = new SequenceManager();
            sequenceManager.deleteSequence(sequenceNumber);
            return
        }
    }


}
