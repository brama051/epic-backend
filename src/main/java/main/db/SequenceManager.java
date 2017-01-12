package main.db;

import main.models.Sequence;
import main.models.SequenceList;

/**
 * Created by brama on 1/11/17.
 */
public class SequenceManager extends Database {

    public SequenceList getSequenceList() {
        /**
         * Todo: Implement paging
         */
        SequenceList sequenceList = new SequenceList();
        try {
            String sql = "SELECT * FROM Sequences";
            this.preparedStatement = this.connect.prepareStatement(sql);

            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                Sequence sequence = new Sequence();
                sequence.setSequenceNumber(this.resultSet.getLong("sequence_number"));
                sequence.setByUser(this.resultSet.getString("by_user"));
                sequence.setPurpose(this.resultSet.getString("purpose"));
                sequence.setDate(this.resultSet.getDate("date"));

                sequenceList.addSequence(sequence);
            }

            return sequenceList;

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public Sequence getSequence(Long sequenceNumber) {
        Sequence sequence = new Sequence();
        try {
            String sql = "SELECT * FROM Sequences WHERE Sequences.sequence_number = ?";
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

    public SequenceList getSequencePage(int page, int itemsPerPage, String filter) {
        SequenceList sequenceList = new SequenceList();
        try {
            String sql = "SELECT * FROM Sequences WHERE Sequences.by_user LIKE ? OR Sequences.purpose LIKE ?";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setString(1, filter);
            this.preparedStatement.setString(2, filter);

            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                Sequence sequence = new Sequence();
                sequence.setSequenceNumber(this.resultSet.getLong("sequence_number"));
                sequence.setByUser(this.resultSet.getString("by_user"));
                sequence.setPurpose(this.resultSet.getString("purpose"));
                sequence.setDate(this.resultSet.getDate("date"));
                sequenceList.addSequence(sequence);
            }

            return sequenceList;

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }

    }

}
