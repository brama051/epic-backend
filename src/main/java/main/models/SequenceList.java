package main.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brama on 1/12/17.
 */
public class SequenceList {
    public List<Sequence> list;
    public int listLength;
    public int totalPages;
    public int currentPage;
    public int itemsPerPage;
    public String filter;


    public SequenceList() {
        this.list = new ArrayList<>();
        this.listLength = 0;
        this.totalPages = 0;
        this.currentPage = 0;
        this.itemsPerPage = 0;
        this.filter = "";
    }

    public SequenceList(Sequence sequence) {
        this.list = new ArrayList<>();
        this.list.add(sequence);
        this.listLength = 1;
    }

    public int getListLength() {
        return this.listLength;
    }

    public void setListLength() {
        this.listLength = this.list.size();
    }

    public void addSequence(Sequence sequence) {
        this.list.add(sequence);
        this.setListLength();
    }

    public List<Sequence> getList() {
        return this.list;
    }


}
