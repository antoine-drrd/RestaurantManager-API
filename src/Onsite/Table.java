/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package Onsite;

public class Table {
    private int id;
    private TableStatus status = TableStatus.PENDING;

    public Table(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }
}
