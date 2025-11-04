package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.ResultSet;

public class Bill_Details extends JFrame {
    String meter_no;

    Bill_Details(String meter_no) {
        super("Voltra/ bill_details");

        this.meter_no = meter_no;

        setSize(700, 650);
        setLocation(400, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JTable table = new JTable();

        try {
            Database obj = new Database();
            String query_bill = "SELECT * FROM bill WHERE meter_no = '" + meter_no + "'";
            ResultSet resultSet = obj.statement.executeQuery(query_bill);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception ee) {
            System.out.println(ee.getMessage());
        }

        // Customize table appearance
        table.setFont(new Font("Cambria", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setForeground(new Color(50, 50, 50));
        table.setGridColor(new Color(180, 180, 180));
        table.setSelectionBackground(new Color(220, 230, 241));
        table.setSelectionForeground(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 16));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 30));

        // Scroll pane
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 700, 650);
        sp.getViewport().setBackground(Color.WHITE);
        add(sp);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Bill_Details("");
    }
}
