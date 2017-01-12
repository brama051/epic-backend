package main.models;

import java.util.Date;

/**
 * Created by brama on 1/12/17.
 */
public class Sequence {
    public int sequenceNumber;
    public String byUser;
    public String purpose;
    public Date date;

    public Sequence() {
        this.sequenceNumber = -1;
        this.byUser = "";
        this.purpose = "";
        this.date = new Date();
    }

    public Sequence(int sequenceNumber, String byUser, String purpose, Date date) {
        this.sequenceNumber = sequenceNumber;
        this.byUser = byUser;
        this.purpose = purpose;
        this.date = date;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getByUser() {
        return byUser;
    }

    public void setByUser(String byUser) {
        this.byUser = byUser;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
