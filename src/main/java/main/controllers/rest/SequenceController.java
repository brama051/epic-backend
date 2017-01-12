package main.controllers.rest;

import main.db.SequenceManager;
import main.models.Sequence;
import main.models.SequenceList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class SequenceController {

    @RequestMapping("/sequence")
    public Sequence getSequence(@RequestParam(value = "token", defaultValue = "") String token, @RequestParam(value = "sequenceNumber", defaultValue = "") Long sequenceNumber) {
        /**
         * Todo: Validate token first
         */

        SequenceManager sequenceManager = new SequenceManager();
        Sequence sequence = sequenceManager.getSequence(sequenceNumber);
        sequenceManager.close();

        return sequence;
    }

    @RequestMapping("/sequences")
    public SequenceList getSequenceList(@RequestParam(value = "token", defaultValue = "") String token) {
        /**
         * Todo: Validate token first
         */

        SequenceManager sequenceManager = new SequenceManager();
        SequenceList sequenceList = sequenceManager.getSequenceList();
        sequenceManager.close();

        return sequenceList;
    }

    @RequestMapping("/sequences/page")
    public SequenceList getSequencePage(@RequestParam(value = "token", defaultValue = "") String token,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "itemsPerPage", defaultValue = "1") int itemsPerpage,
                                        @RequestParam(value = "filter", defaultValue = "") String filter) {
        /**
         * Todo: Validate token first
         */

        SequenceManager sequenceManager = new SequenceManager();
        SequenceList sequenceList = sequenceManager.getSequencePage(page, itemsPerpage, filter);
        sequenceManager.close();

        return sequenceList;
    }
}
