package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Customer_Details extends JFrame implements ActionListener {
    Choice searchMeterCho, searchNameCho;
    JTable table;
    JButton search, print, close;

    Customer_Details() {
        super("Voltra / Customer Details");
        getContentPane().setBackground(new Color(235, 230, 250));
        setSize(750, 550);
        setLocation(380, 180);
        setLayout(null);

        JLabel title = new JLabel("Customer Details");
        title.setBounds(260, 10, 250, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(75, 0, 130));
        add(title);

        JLabel searchMeter = new JLabel("Meter Number:");
        searchMeter.setBounds(30, 60, 120, 25);
        searchMeter.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(150, 60, 160, 25);
        searchMeterCho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(searchMeterCho);

        JLabel searchName = new JLabel("Customer Name:");
        searchName.setBounds(350, 60, 130, 25);
        searchName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(searchName);

        searchNameCho = new Choice();
        searchNameCho.setBounds(490, 60, 160, 25);
        searchNameCho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(searchNameCho);

        // Populate dropdowns with distinct values
        try {
            Database c = new Database();

            ResultSet rsMeter = c.statement.executeQuery("SELECT DISTINCT meter_no FROM new_customer ORDER BY meter_no");
            while (rsMeter.next()) {
                searchMeterCho.add(rsMeter.getString("meter_no"));
            }

            ResultSet rsName = c.statement.executeQuery("SELECT DISTINCT name FROM new_customer ORDER BY name");
            while (rsName.next()) {
                searchNameCho.add(rsName.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.setForeground(Color.DARK_GRAY);
        table.setBackground(new Color(250, 245, 255));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(180, 160, 230));
        header.setForeground(Color.BLACK);

        try {
            Database c = new Database();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 150, 700, 310);
        scrollPane.getViewport().setBackground(new Color(250, 245, 255));
        add(scrollPane);

        search = new JButton("Search");
        search.setBackground(new Color(130, 100, 200));
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Segoe UI", Font.BOLD, 14));
        search.setBounds(50, 110, 100, 30);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(new Color(100, 150, 230));
        print.setForeground(Color.WHITE);
        print.setFont(new Font("Segoe UI", Font.BOLD, 14));
        print.setBounds(180, 110, 100, 30);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(new Color(200, 80, 80));
        close.setForeground(Color.WHITE);
        close.setFont(new Font("Segoe UI", Font.BOLD, 14));
        close.setBounds(600, 110, 100, 30);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String meter = searchMeterCho.getSelectedItem();
            String name = searchNameCho.getSelectedItem();

            String query_search = "SELECT * FROM new_customer WHERE meter_no = '" + meter + "' AND name = '" + name + "'";
            try {
                Database c = new Database();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Customer_Details();
    }
}





