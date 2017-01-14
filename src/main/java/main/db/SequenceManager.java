package main.db;

import main.models.Sequence;
import main.models.SequenceList;

import java.util.Date;

/**
 * Created by brama on 1/11/17.
 */
public class SequenceManager extends Database {


    public Sequence getSequence(Long sequenceNumber) {
        Sequence sequence = new Sequence();
        try {
            String sql = "SELECT * FROM sequences WHERE sequences.sequence_number = ?";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setLong(1, sequenceNumber);

            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                sequence.setSequenceNumber(this.resultSet.getLong("sequence_number"));
                sequence.setByUser(this.resultSet.getString("by_user"));
                sequence.setPurpose(this.resultSet.getString("purpose"));
                sequence.setDate(this.resultSet.getDate("date"));
            }

            return sequence;

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }

    }

    public Sequence createSequence(Sequence sequence) {

        if (this.getSequence(sequence.sequenceNumber).getSequenceNumber() > 0) {
            System.out.println("Sequence already exists");
            System.out.println(this.getSequence(sequence.sequenceNumber).toString());
            //Sequence with that sequencNumber already exists
            return this.getSequence(sequence.sequenceNumber);
        }

        try {
            String sql = "INSERT INTO sequences VALUES ( ?, ?, ?)";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setLong(1, sequence.getSequenceNumber());
            this.preparedStatement.setString(2, sequence.getByUser());
            this.preparedStatement.setString(3, sequence.getPurpose());
            //this.preparedStatement.setDate(4, new java.sql.Date(sequence.getDate().getTime()));

            this.preparedStatement.executeUpdate();
            return sequence;

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public Long getNextAvailableSequenceNumber() {
        Long nextSequence = new Long(0);
        try {
            String sql = "SELECT * FROM sequences ORDER BY sequences.sequence_number DESC LIMIT 1";
            this.preparedStatement = this.connect.prepareStatement(sql);

            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                nextSequence = this.resultSet.getLong("sequence_number");
                nextSequence++;
            }

            return nextSequence;

        } catch (Exception e) {
            System.out.println(e.toString());
            return nextSequence;
        }
    }

    /*public void updateSequence(Sequence sequence) {
        try {
            String sql = "UPDATE sequences SET  WHERE Users.name = ?";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setLong(1, sequence.getSequenceNumber());
            this.preparedStatement.setString(2, sequence.getByUser());
            this.preparedStatement.setString(3, sequence.getPurpose());
            this.preparedStatement.setDate(4, new java.sql.Date(sequence.getDate().getTime()));

            this.preparedStatement.executeUpdate();
            return sequence;

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }*/

    public String deleteSequence(Long sequenceNumber) {
        try {
            String sql = "DELETE FROM sequences WHERE sequences.sequence_number = ? LIMIT 1";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setLong(1, sequenceNumber);
            // It should delete a single row and return "1"
            return String.valueOf(this.preparedStatement.executeUpdate());

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "";
    }



}
