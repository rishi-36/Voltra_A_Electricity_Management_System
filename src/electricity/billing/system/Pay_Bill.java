package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.ResultSet;

public class Pay_Bill extends JFrame implements ActionListener {
    String meter_no;
    Choice searchmonthch;
    JButton pay, back;
    JLabel statusText;

    public Pay_Bill(String meter_no) {
        super("Voltra / Pay Bill");
        this.meter_no = meter_no;

        setSize(800, 500);  // Smaller frame size
        setLocation(350, 180);  // Adjusted frame location
        getContentPane().setBackground(new Color(210, 225, 245)); // Light steel blue
        setLayout(null);

        Font labelFont = new Font("Cambria", Font.PLAIN, 17);
        Font valueFont = new Font("Cambria", Font.BOLD, 16);

        JLabel heading = new JLabel("Pay Your Electricity Bill");
        heading.setFont(new Font("Cambria", Font.BOLD, 30));
        heading.setBounds(250, 20, 500, 40);
        heading.setForeground(new Color(50, 50, 150));
        add(heading);

        JLabel meterNumber = new JLabel("Meter Number:");
        meterNumber.setBounds(80, 90, 150, 30);
        meterNumber.setFont(labelFont);
        add(meterNumber);

        JLabel meterNumberText = new JLabel();
        meterNumberText.setBounds(250, 90, 200, 30);
        meterNumberText.setFont(valueFont);
        add(meterNumberText);

        JLabel name = new JLabel("Customer Name:");
        name.setBounds(80, 135, 150, 30);
        name.setFont(labelFont);
        add(name);

        JLabel nameText = new JLabel();
        nameText.setBounds(250, 135, 250, 30);
        nameText.setFont(valueFont);
        add(nameText);

        JLabel month = new JLabel("Select Month:");
        month.setBounds(80, 180, 150, 30);
        month.setFont(labelFont);
        add(month);

        searchmonthch = new Choice();
        searchmonthch.setFont(new Font("Cambria", Font.PLAIN, 15));
        searchmonthch.setBounds(250, 185, 180, 25);
        for (String m : new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"}) {
            searchmonthch.add(m);
        }
        add(searchmonthch);

        JLabel unit = new JLabel("Units Consumed:");
        unit.setBounds(80, 225, 150, 30);
        unit.setFont(labelFont);
        add(unit);

        JLabel unitText = new JLabel();
        unitText.setBounds(250, 225, 200, 30);
        unitText.setFont(valueFont);
        add(unitText);

        JLabel totalBill = new JLabel("Total Bill (₹):");
        totalBill.setBounds(80, 270, 150, 30);
        totalBill.setFont(labelFont);
        add(totalBill);

        JLabel totalBillText = new JLabel();
        totalBillText.setBounds(250, 270, 200, 30);
        totalBillText.setFont(valueFont);
        add(totalBillText);

        JLabel status = new JLabel("Payment Status:");
        status.setBounds(80, 315, 150, 30);
        status.setFont(labelFont);
        add(status);

        statusText = new JLabel();
        statusText.setBounds(250, 315, 200, 30);
        statusText.setFont(new Font("Cambria", Font.BOLD, 16));
        statusText.setForeground(Color.RED);
        add(statusText);

        // Pay and Back Buttons
        pay = new JButton("Pay Now");
        pay.setBounds(150, 370, 150, 40);
        pay.setBackground(new Color(30, 144, 255));
        pay.setForeground(Color.WHITE);
        pay.setFont(new Font("Cambria", Font.BOLD, 16));
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBounds(320, 370, 150, 40);
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Cambria", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        // Image - Modified size and position
        URL imageUrl = getClass().getClassLoader().getResource("icon/pay_bill2.png");
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaled = icon.getImage().getScaledInstance(260, 260, Image.SCALE_SMOOTH); // Increased image size
            JLabel imageLabel = new JLabel(new ImageIcon(scaled));
            imageLabel.setBounds(480, 100, 260, 260); // Adjusted position and size
            add(imageLabel);
        }

        // Fetching customer info
        try {
            Database obj = new Database();
            ResultSet rs = obj.statement.executeQuery("SELECT * FROM new_customer WHERE meter_no = '" + meter_no + "'");
            while (rs.next()) {
                meterNumberText.setText(rs.getString("meter_no"));
                nameText.setText(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Fetching bill info when month is selected
        searchmonthch.addItemListener(e -> {
            Database obj = new Database();
            try {
                ResultSet rs = obj.statement.executeQuery("SELECT * FROM bill WHERE meter_no = '" + meter_no + "' AND month = '" + searchmonthch.getSelectedItem() + "'");
                while (rs.next()) {
                    unitText.setText(rs.getString("unit"));
                    totalBillText.setText(rs.getString("total_bill"));
                    String statusVal = rs.getString("status");
                    statusText.setText(statusVal);
                    statusText.setForeground(statusVal.equalsIgnoreCase("Paid") ? new Color(0, 150, 0) : Color.RED);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pay) {
            try {
                Database obj = new Database();
                String selectedMonth = searchmonthch.getSelectedItem();
                int updated = obj.statement.executeUpdate("UPDATE bill SET status = 'Paid' WHERE meter_no = '" + meter_no + "' AND month = '" + selectedMonth + "'");
                if (updated > 0) {
                    statusText.setText("Paid");
                    statusText.setForeground(new Color(0, 150, 0));
                    setVisible(false);
                    new Bill_Payment_Done(meter_no);
                } else {
                    JOptionPane.showMessageDialog(this, "No bill found for selected month!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Pay_Bill(""); // Test with valid meter number
    }
}
