package main.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brama on 1/12/17.
 */
public class SequenceList {
    public List<Sequence> list;
    public int listLength;

    public SequenceList() {
        this.list = new ArrayList<>();
        this.listLength = 0;
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
