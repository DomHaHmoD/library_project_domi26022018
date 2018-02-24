import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class TableCreate extends JPanel {

public TableCreate(TableModel data) {
        JTable tableau = new JTable(data);
        tableau.setOpaque(true);
        tableau.setPreferredScrollableViewportSize(new Dimension(1550, 760));
        tableau.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tableau);
        add(scrollPane);
        }
}