/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package Onsite;

import java.util.ArrayList;
import java.util.List;

public class TableSet {
    private List<Table> tables;
    private int numberOfTables;

    public TableSet(int numberOfTables) {
        this.tables = new ArrayList<>();
        this.numberOfTables = numberOfTables;

        for (int i = 0; i < this.numberOfTables; i++) {
            tables.add(new Table(i + 1));
        }
    }

    public List<Table> getTables() {
        return tables;
    }

    public int addTable(){
        this.numberOfTables++;
        tables.add(new Table(this.numberOfTables));
        return this.numberOfTables;
    }

    public boolean removeTable(int id){
        for(Table t : tables){
            if(t.getId() == id){
                return tables.remove(t);
            }
        }
        return false;
    }

}
