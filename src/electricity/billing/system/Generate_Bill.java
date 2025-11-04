package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Generate_Bill extends JFrame implements ActionListener {

    String meter;
    Choice searchmonthch;
    JTextArea area;
    JButton bill, print;

    public Generate_Bill(String meter_no) {
        super("Voltra / Generate Bill");

        this.meter = meter_no;

        setSize(650, 750);
        setLocation(420, 20);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 225, 250));

        // Top Panel
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        topPanel.setBackground(new Color(220, 210, 250));

        JLabel heading = new JLabel("Generate Electricity Bill", SwingConstants.CENTER);
        heading.setFont(new Font("Cambria", Font.BOLD, 22));
        heading.setForeground(new Color(60, 20, 110));

        // Fetching name with meter number
        String customerName = "";
        try {
            Database c = new Database();
            ResultSet rs = c.statement.executeQuery("SELECT name FROM new_customer WHERE meter_no = '" + meter + "'");
            if (rs.next()) {
                customerName = rs.getString("name");
            }
        } catch (Exception e) {
            customerName = "Unknown";
        }

        JLabel meterLabel = new JLabel("Meter No: " + meter + " | Name: " + customerName, SwingConstants.CENTER);
        meterLabel.setFont(new Font("Cambria", Font.PLAIN, 16));

        searchmonthch = new Choice();
        searchmonthch.setFont(new Font("Cambria", Font.PLAIN, 14));
        String[] months = { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
        for (String month : months) searchmonthch.add(month);

        JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        monthPanel.setBackground(new Color(220, 210, 250));
        monthPanel.add(new JLabel("Select Month: "));
        monthPanel.add(searchmonthch);

        topPanel.add(heading);
        topPanel.add(meterLabel);
        topPanel.add(monthPanel);
        add(topPanel, BorderLayout.NORTH);

        // Text area
        area = new JTextArea(40, 15);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);
        area.setMargin(new Insets(10, 15, 10, 15));
        area.setBackground(new Color(250, 245, 255));
        JScrollPane pane = new JScrollPane(area);
        add(pane, BorderLayout.CENTER);

        // Bottom panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(230, 225, 250));

        bill = new JButton("Generate Bill");
        bill.setFont(new Font("Cambria", Font.BOLD, 16));
        bill.setBackground(new Color(100, 80, 200));
        bill.setForeground(Color.WHITE);
        bill.addActionListener(this);
        buttonPanel.add(bill);

        print = new JButton("Print Bill");
        print.setFont(new Font("Cambria", Font.BOLD, 16));
        print.setBackground(new Color(80, 160, 120));
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        buttonPanel.add(print);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bill) {
            area.setText("");
            try {
                Database c = new Database();
                String smonth = searchmonthch.getSelectedItem();

                area.append("                 POWER LIMITED\n");
                area.append("          Electricity Bill - " + smonth + ", 2023\n");
                area.append("----------------------------------------------------\n\n");

                // Customer Info
                ResultSet rs = c.statement.executeQuery("SELECT * FROM new_customer WHERE meter_no = '" + meter + "'");
                if (rs.next()) {
                    area.append(String.format("%-20s: %s\n", "Customer Name", rs.getString("name")));
                    area.append(String.format("%-20s: %s\n", "Meter Number", rs.getString("meter_no")));
                    area.append(String.format("%-20s: %s\n", "Address", rs.getString("address")));
                    area.append(String.format("%-20s: %s\n", "City", rs.getString("city")));
                    area.append(String.format("%-20s: %s\n", "State", rs.getString("state")));
                    area.append(String.format("%-20s: %s\n", "Email", rs.getString("email")));
                    area.append(String.format("%-20s: %s\n", "Phone Number", rs.getString("phone")));
                } else {
                    area.append("No customer info found.\n");
                }

                area.append("\n---------------- Meter Information ----------------\n");

                rs = c.statement.executeQuery("SELECT * FROM meter_info WHERE meter_number = '" + meter + "'");
                if (rs.next()) {
                    area.append(String.format("%-20s: %s\n", "Meter Location", rs.getString("meter_location")));
                    area.append(String.format("%-20s: %s\n", "Meter Type", rs.getString("meter_type")));
                    area.append(String.format("%-20s: %s\n", "Phase Code", rs.getString("phase_code")));
                    area.append(String.format("%-20s: %s\n", "Bill Type", rs.getString("bill_type")));
                    area.append(String.format("%-20s: %s\n", "Days", rs.getString("days"))); // use lowercase if your DB uses it
                } else {
                    area.append("No meter info found.\n");
                }

                area.append("\n---------------- Tax Information ------------------\n");

                rs = c.statement.executeQuery("SELECT * FROM tax");
                if (rs.next()) {
                    area.append(String.format("%-20s: %s\n", "Cost Per Unit", rs.getString("cost_per_unit")));
                    area.append(String.format("%-20s: %s\n", "Meter Rent", rs.getString("meter_rent")));
                    area.append(String.format("%-20s: %s\n", "Service Charge", rs.getString("service_charge")));
                    area.append(String.format("%-20s: %s\n", "Service Tax", rs.getString("service_tax")));
                    area.append(String.format("%-20s: %s\n", "Swachh Bharat Tax", rs.getString("swachh_bharat_tax")));
                    area.append(String.format("%-20s: %s\n", "Fixed Tax", rs.getString("fixed_tax")));
                } else {
                    area.append("No tax data found.\n");
                }

                area.append("\n--------------- Current Bill Details --------------\n");

                rs = c.statement.executeQuery("SELECT * FROM bill WHERE meter_no = '" + meter + "' AND month = '" + smonth + "'");
                if (rs.next()) {
                    area.append(String.format("%-20s: %s\n", "Month", rs.getString("month")));
                    area.append(String.format("%-20s: %s\n", "Units Consumed", rs.getString("unit")));
                    area.append(String.format("%-20s: %s\n", "Total Charges", rs.getString("total_bill")));
                    area.append(String.format("%-20s: %s\n", "Payable Amount", rs.getString("total_bill")));
                } else {
                    area.append("No bill found for " + smonth + ".\n");
                }

                area.append("\n----------------------------------------------------\n");
                area.append("  Please pay your bill before due date.\n");
                area.append("  Thank you for using our service.\n");
                area.append("----------------------------------------------------\n");

            } catch (Exception ex) {
                ex.printStackTrace();
                area.setText("Error generating bill. Check console for details.");
            }
        }

        if (e.getSource() == print) {
            try {
                boolean complete = area.print();
                if (!complete) {
                    JOptionPane.showMessageDialog(this, "Printing was cancelled.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error during printing: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Generate_Bill("123456"); // Replace with a valid meter number
    }
}
