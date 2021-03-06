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
            String sql = "SELECT * FROM sequences";
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

    public SequenceList getSequenceListPage(int page, int itemsPerPage, String filter, String orderBy) {
        SequenceList sequenceList = new SequenceList();
        try {
            String column = "";
            switch (orderBy) {
                case "byUser":
                    column = "sequences.by_user";
                    break;
                case "purpose":
                    column = "sequences.purpose";
                    break;
                case "date":
                    column = "sequences.date";
                    break;
                default:
                    column = "sequences.sequence_number";
                    break;
            }

            String sql = "SELECT * FROM Sequences WHERE Sequences.by_user LIKE ? OR Sequences.purpose LIKE ? ORDER BY " + column + " LIMIT ? OFFSET ?";
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

        if (count % itemsPerPage == 0) {
            return count / itemsPerPage;
        } else {
            return (int) Math.floor(((double) count / (double) itemsPerPage)) + 1;
        }


    }


}
