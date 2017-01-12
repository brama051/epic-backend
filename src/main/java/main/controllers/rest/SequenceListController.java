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
@RequestMapping("/sequences")
public class SequenceListController {
    @RequestMapping(value = "", method = RequestMethod.GET)
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

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public SequenceList getSequencePage(@RequestParam(value = "token", defaultValue = "") String token,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "itemsPerPage", defaultValue = "1") int itemsPerpage,
                                        @RequestParam(value = "filter", defaultValue = "") String filter) {
        /**
         * Todo: Add totalPages, currentPage, itemsPerPage and filter to the response JSON
         */
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
}
