package main.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brama on 1/12/17.
 */
public class SequenceList {
    public List<Sequence> list = new ArrayList<>();
    public int listLength = 0;


    public int getListLength() {
        return this.listLength;
    }

    public void setListLength(int listLength) {
        this.listLength = this.list.size();
    }
}
