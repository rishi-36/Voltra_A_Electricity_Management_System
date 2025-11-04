package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class View_Information extends JFrame implements ActionListener {
    String meterNo;
    JButton cancel;

    View_Information(String meterNo) {
        this.meterNo = meterNo;

        setTitle("Customer Details");
        setBounds(150, 80, 1150, 600);
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(null);

        Font headingFont = new Font("Cambria", Font.BOLD, 26);
        Font labelFont = new Font("Cambria", Font.PLAIN, 18);
        Font dataFont = new Font("Cambria", Font.BOLD, 18);

        // Image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/view_1.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(380, 500, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaledImage));
        imgLabel.setBounds(30, 50, 380, 480);
        add(imgLabel);

        // Heading
        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(450, 20, 500, 35);
        heading.setFont(headingFont);
        heading.setForeground(new Color(30, 90, 140));
        add(heading);

        // Name
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(450, 80, 150, 25);
        lblName.setFont(labelFont);
        add(lblName);

        JLabel valName = new JLabel();
        valName.setBounds(600, 80, 400, 25);
        valName.setFont(dataFont);
        add(valName);

        // Meter Number
        JLabel lblMeter = new JLabel("Meter Number:");
        lblMeter.setBounds(450, 130, 150, 25);
        lblMeter.setFont(labelFont);
        add(lblMeter);

        JLabel valMeter = new JLabel();
        valMeter.setBounds(600, 130, 400, 25);
        valMeter.setFont(dataFont);
        add(valMeter);

        // Address
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(450, 180, 150, 25);
        lblAddress.setFont(labelFont);
        add(lblAddress);

        JLabel valAddress = new JLabel();
        valAddress.setBounds(600, 180, 400, 25);
        valAddress.setFont(dataFont);
        add(valAddress);

        // City
        JLabel lblCity = new JLabel("City:");
        lblCity.setBounds(450, 230, 150, 25);
        lblCity.setFont(labelFont);
        add(lblCity);

        JLabel valCity = new JLabel();
        valCity.setBounds(600, 230, 400, 25);
        valCity.setFont(dataFont);
        add(valCity);

        // State
        JLabel lblState = new JLabel("State:");
        lblState.setBounds(450, 280, 150, 25);
        lblState.setFont(labelFont);
        add(lblState);

        JLabel valState = new JLabel();
        valState.setBounds(600, 280, 400, 25);
        valState.setFont(dataFont);
        add(valState);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(450, 330, 150, 25);
        lblEmail.setFont(labelFont);
        add(lblEmail);

        JLabel valEmail = new JLabel();
        valEmail.setBounds(600, 330, 400, 25);
        valEmail.setFont(dataFont);
        add(valEmail);

        // Phone
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(450, 380, 150, 25);
        lblPhone.setFont(labelFont);
        add(lblPhone);

        JLabel valPhone = new JLabel();
        valPhone.setBounds(600, 380, 400, 25);
        valPhone.setFont(dataFont);
        add(valPhone);

        // Fetch data
        try {
            Database c = new Database();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM new_customer WHERE meter_no = '" + meterNo + "'");
            if (rs.next()) {
                valName.setText(rs.getString("name"));
                valMeter.setText(rs.getString("meter_no"));
                valAddress.setText(rs.getString("address"));
                valCity.setText(rs.getString("city"));
                valState.setText(rs.getString("state"));
                valEmail.setText(rs.getString("email"));
                valPhone.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.setBounds(600, 440, 140, 35);
        cancel.setFont(new Font("Cambria", Font.BOLD, 16));
        cancel.setBackground(new Color(70, 130, 180));
        cancel.setForeground(Color.WHITE);
        cancel.setFocusPainted(false);
        cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new View_Information(""); // for test
    }
}
