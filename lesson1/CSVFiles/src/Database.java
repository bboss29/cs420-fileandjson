import java.util.Arrays;
import java.util.InputMismatchException;

public class Database {
    private String[] colNames;
    private int numRows;
    private String[][] data;

    public String[] getColNames() {
        return colNames;
    }

    public void setColNames(String[] colNames) {
        this.colNames = colNames;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public Database(String contents) {/*
This constructor should take the contents of a CSV file and initialize the memember variables of the Database class.
*/
        String[] c = contents.split("\n");
        numRows = c.length - 1;
        colNames = c[0].split(",");
        data = new String[c.length][colNames.length];
        for (int i = 1; i < c.length; i++) {
            data[i - 1] = c[i].split(",");
        }
    }

    public String getValue(String columnName,int row){
        int col_ind = 0;
        for (col_ind = 0; col_ind < colNames.length; col_ind++) {
            if (columnName.equals(colNames[col_ind])) break;
        }
//        if (col_ind == 6)
        return data[row][col_ind];
    }

}


