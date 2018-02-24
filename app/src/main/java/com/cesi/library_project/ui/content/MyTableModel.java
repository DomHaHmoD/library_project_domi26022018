import javax.swing.table.AbstractTableModel;
import java.sql.*;

class MyTableModel extends AbstractTableModel {

    private boolean DEBUG = false;
    private String[] columnNames;
    private Object[][] data;
    private static ResultSet result;
    private int rowCount;

    public MyTableModel() {

        String sql = "SELECT id, titre, date_parution, note, commentaire FROM oeuvre";
        String sqlCount = "SELECT COUNT(*) FROM oeuvre";
        
        try {
            Connection conn = DbConnect.connect();
            Statement stmt = conn.createStatement();

            rowCount = stmt.executeQuery(sqlCount).getInt(1);
            result = stmt.executeQuery(sql);
            //On récupère les meta afin de récupérer le nom des colonnes
            ResultSetMetaData meta = result.getMetaData();
            //On initialise un tableau d'Object pour les en-têtes du tableau
            //String[] columnNames = new String[meta.getColumnCount()];

            columnNames = new String[]{"First Name",
                    "Last Name",
                    "Sport",
                    "# of Years",
                    "Vegetarian"};

          //  for(int i = 1 ; i <= meta.getColumnCount(); i++)
            //    columnNames[i-1] = meta.getColumnName(i);

            Object[][] data = new Object[rowCount][meta.getColumnCount()];

            //On revient au départ
            int j = 1;

            //On remplit le tableau d'Object[][]
            while(result.next()){
                for(int i = 1 ; i <= meta.getColumnCount(); i++)
                    data[j-1][i-1] = result.getObject(i);
                j++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return rowCount;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                    + " to " + value
                    + " (an instance of "
                    + value.getClass() + ")");
        }

        data[row][col] = value;
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}