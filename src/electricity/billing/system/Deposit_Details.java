package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Deposit_Details extends JFrame implements ActionListener {
    Choice searchMeterCh, searchMonthCh;
    JTable table;
    JButton search, print, close;

    Deposit_Details() {
        super("Voltra / Deposit Details");
        getContentPane().setBackground(new Color(235, 230, 250));
        setSize(800, 580); // Increased width for layout fix
        setLocation(350, 150);
        setLayout(null);

        // Title
        JLabel title = new JLabel("Deposit Details");
        title.setBounds(300, 10, 250, 30);
        title.setFont(new Font("Cambria", Font.BOLD, 22));
        title.setForeground(new Color(75, 0, 130));
        add(title);

        // Meter Number Label
        JLabel searchMeter = new JLabel("Meter Number:");
        searchMeter.setBounds(40, 60, 120, 25);
        searchMeter.setFont(new Font("Cambria", Font.PLAIN, 16));
        add(searchMeter);

        // Meter Choice
        searchMeterCh = new Choice();
        searchMeterCh.setFont(new Font("Cambria", Font.PLAIN, 14));
        searchMeterCh.setBounds(170, 60, 180, 25); // Fixed size and placement
        searchMeterCh.setBackground(Color.WHITE);
        searchMeterCh.setForeground(Color.DARK_GRAY);
        add(searchMeterCh);

        // Fill meter numbers
        try {
            Database obj = new Database();
            ResultSet resultSet = obj.statement.executeQuery("SELECT * FROM bill");
            while (resultSet.next()) {
                searchMeterCh.add(resultSet.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Month Label
        JLabel searchMonth = new JLabel("Select Month:");
        searchMonth.setBounds(400, 60, 120, 25);
        searchMonth.setFont(new Font("Cambria", Font.PLAIN, 16));
        add(searchMonth);

        // Month Choice
        searchMonthCh = new Choice();
        searchMonthCh.setFont(new Font("Cambria", Font.PLAIN, 14));
        searchMonthCh.setBounds(520, 60, 180, 25);
        searchMonthCh.setBackground(Color.WHITE);
        searchMonthCh.setForeground(Color.DARK_GRAY);
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        for (String month : months) {
            searchMonthCh.add(month);
        }
        add(searchMonthCh);

        // Table Setup
        table = new JTable();
        table.setFont(new Font("Cambria", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.setForeground(Color.DARK_GRAY);
        table.setBackground(new Color(250, 245, 255));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 16));
        header.setBackground(new Color(180, 160, 230));
        header.setForeground(Color.BLACK);

        // Load initial data
        try {
            Database c = new Database();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 150, 700, 310); // Aligned with content
        scrollPane.getViewport().setBackground(new Color(250, 245, 255));
        add(scrollPane);

        // Buttons
        search = new JButton("Search");
        search.setBackground(new Color(130, 100, 200));
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Cambria", Font.BOLD, 14));
        search.setBounds(60, 110, 100, 30);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(new Color(100, 150, 230));
        print.setForeground(Color.WHITE);
        print.setFont(new Font("Cambria", Font.BOLD, 14));
        print.setBounds(180, 110, 100, 30);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(new Color(200, 80, 80));
        close.setForeground(Color.WHITE);
        close.setFont(new Font("Cambria", Font.BOLD, 14));
        close.setBounds(640, 110, 100, 30);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String query = "SELECT * FROM bill WHERE meter_no = '" + searchMeterCh.getSelectedItem()
                    + "' AND month = '" + searchMonthCh.getSelectedItem() + "'";
            try {
                Database obj = new Database();
                ResultSet resultSet = obj.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Deposit_Details();
    }
}
