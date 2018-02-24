import javax.swing.*;
import java.sql.*;


public class Oeuvre {

    private Object[][] data;
    private Object[] column;
    private static ResultSet result;

    public void Oeuvre() {
    }

    public static JScrollPane CreateTab() {
        String sql = "SELECT id, titre, date_parution, note, commentaire FROM oeuvre";
        String sqlCount = "SELECT COUNT(*) FROM oeuvre";

        try {
            Connection conn = DbConnect.connect();
            Statement stmt  = conn.createStatement();
            int rowCount = stmt.executeQuery(sqlCount).getInt(1);
            result = stmt.executeQuery(sql);
            //On récupère les meta afin de récupérer le nom des colonnes
            ResultSetMetaData meta = result.getMetaData();
            //On initialise un tableau d'Object pour les en-têtes du tableau
            Object[] column = new Object[meta.getColumnCount()];

            for(int i = 1 ; i <= meta.getColumnCount(); i++)
                column[i-1] = meta.getColumnName(i);

            Object[][] data = new Object[rowCount][meta.getColumnCount()];

            //On revient au départ
            int j = 1;

            //On remplit le tableau d'Object[][]
            while(result.next()){
                for(int i = 1 ; i <= meta.getColumnCount(); i++)
                    data[j-1][i-1] = result.getObject(i);

                j++;
            }

            JScrollPane tableau = new JScrollPane(new JTable(data, column));
            return tableau;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*
    public static void insert(String name) {
        String sql = "INSERT INTO category(name) VALUES(?)";

        try (Connection conn = DbConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM category WHERE id = ?";

        try (Connection conn = DbConnect.connect();

             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update(int id, String name) {
        String sql = "UPDATE category SET name = ? " + "WHERE id = ?";

        try (Connection conn = DbConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/
}
