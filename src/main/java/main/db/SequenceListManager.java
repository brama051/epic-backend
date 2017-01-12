package main.db;

import main.models.Sequence;
import main.models.SequenceList;

/**
 * Created by brama on 1/12/17.
 */
public class SequenceListManager extends Database {
    public SequenceList getSequenceList() {
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

    public SequenceList getSequenceListPage(int page, int itemsPerPage, String filter) {
        SequenceList sequenceList = new SequenceList();
        try {
            String sql = "SELECT * FROM Sequences WHERE Sequences.by_user LIKE ? OR Sequences.purpose LIKE ? LIMIT ? OFFSET ?";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setString(1, "%" + filter + "%");
            this.preparedStatement.setString(2, "%" + filter + "%");
            this.preparedStatement.setInt(3, itemsPerPage);
            this.preparedStatement.setInt(4, (page - 1) * itemsPerPage);

            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                Sequence sequence = new Sequence();
                sequence.setSequenceNumber(this.resultSet.getLong("sequence_number"));
                sequence.setByUser(this.resultSet.getString("by_user"));
                sequence.setPurpose(this.resultSet.getString("purpose"));
                sequence.setDate(this.resultSet.getDate("date"));
                sequenceList.addSequence(sequence);
            }

            //Inserting page stat metrics
            sequenceList.totalPages = this.totalPages(itemsPerPage, filter);
            sequenceList.filter = filter;
            sequenceList.currentPage = page;
            sequenceList.itemsPerPage = itemsPerPage;

            return sequenceList;

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    private int totalPages(int itemsPerPage, String filter) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) as 'Count' FROM Sequences WHERE Sequences.by_user LIKE ? OR Sequences.purpose LIKE ?";
            this.preparedStatement = this.connect.prepareStatement(sql);
            this.preparedStatement.setString(1, "%" + filter + "%");
            this.preparedStatement.setString(2, "%" + filter + "%");

            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                count = this.resultSet.getInt("Count");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return (int) Math.floor(((double) count / (double) itemsPerPage)) + 1;

    }


}
