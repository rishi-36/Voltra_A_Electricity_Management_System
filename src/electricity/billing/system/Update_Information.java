package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Update_Information extends JFrame implements ActionListener {
    JLabel nameText, meterText;
    JTextField addressText, cityText, stateText, emailText, phoneText;
    JButton update, cancel;
    String meter_no;

    Update_Information(String meter_no) {
        this.meter_no = meter_no;

        setBounds(400, 120, 900, 500);
        getContentPane().setBackground(new Color(229, 255, 227));
        setLayout(null);

        Font labelFont = new Font("Cambria", Font.PLAIN, 16);
        Font headingFont = new Font("Cambria", Font.BOLD, 22);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(50, 20, 400, 30);
        heading.setFont(headingFont);
        add(heading);

        int labelX = 30, valueX = 160, width = 180, height = 25, gap = 40;
        int y = 70;

        JLabel name = new JLabel("Name");
        name.setBounds(labelX, y, 120, height);
        name.setFont(labelFont);
        add(name);

        nameText = new JLabel();
        nameText.setBounds(valueX, y, width, height);
        nameText.setFont(labelFont);
        add(nameText);

        y += gap;
        JLabel meterLabel = new JLabel("Meter Number");
        meterLabel.setBounds(labelX, y, 120, height);
        meterLabel.setFont(labelFont);
        add(meterLabel);

        meterText = new JLabel();
        meterText.setBounds(valueX, y, width, height);
        meterText.setFont(labelFont);
        add(meterText);

        y += gap;
        JLabel address = new JLabel("Address");
        address.setBounds(labelX, y, 120, height);
        address.setFont(labelFont);
        add(address);

        addressText = new JTextField();
        addressText.setBounds(valueX, y, width, height);
        addressText.setFont(labelFont);
        add(addressText);

        y += gap;
        JLabel city = new JLabel("City");
        city.setBounds(labelX, y, 120, height);
        city.setFont(labelFont);
        add(city);

        cityText = new JTextField();
        cityText.setBounds(valueX, y, width, height);
        cityText.setFont(labelFont);
        add(cityText);

        y += gap;
        JLabel state = new JLabel("State");
        state.setBounds(labelX, y, 120, height);
        state.setFont(labelFont);
        add(state);

        stateText = new JTextField();
        stateText.setBounds(valueX, y, width, height);
        stateText.setFont(labelFont);
        add(stateText);

        y += gap;
        JLabel email = new JLabel("Email");
        email.setBounds(labelX, y, 120, height);
        email.setFont(labelFont);
        add(email);

        emailText = new JTextField();
        emailText.setBounds(valueX, y, width, height);
        emailText.setFont(labelFont);
        add(emailText);

        y += gap;
        JLabel phone = new JLabel("Phone");
        phone.setBounds(labelX, y, 120, height);
        phone.setFont(labelFont);
        add(phone);

        phoneText = new JTextField();
        phoneText.setBounds(valueX, y, width, height);
        phoneText.setFont(labelFont);
        add(phoneText);

        try {
            Database c = new Database();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM new_customer WHERE meter_no = '" + meter_no + "'");
            if (rs.next()) {
                nameText.setText(rs.getString("name"));
                meterText.setText(rs.getString("meter_no"));
                addressText.setText(rs.getString("address"));
                cityText.setText(rs.getString("city"));
                stateText.setText(rs.getString("state"));
                emailText.setText(rs.getString("email"));
                phoneText.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        update = new JButton("Update");
        update.setBounds(labelX, y + 50, 120, 30);
        update.setBackground(new Color(33, 106, 145));
        update.setForeground(Color.WHITE);
        update.setFont(labelFont);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(valueX, y + 50, 120, 30);
        cancel.setBackground(new Color(33, 106, 145));
        cancel.setForeground(Color.WHITE);
        cancel.setFont(labelFont);
        cancel.addActionListener(this);
        add(cancel);

        // Update with high-quality image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update_1.jpg")); // Use high-quality image
        Image image = imageIcon.getImage().getScaledInstance(400, 450, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(image));
        imgLabel.setBounds(480, 10, 400, 450);
        add(imgLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String semail = emailText.getText();
            String sphone = phoneText.getText();

            try {
                Database c = new Database();
                c.statement.executeUpdate("UPDATE new_customer SET address ='" + saddress + "', city = '" + scity + "', state = '" + sstate + "', email = '" + semail + "', phone ='" + sphone + "' WHERE meter_no = '" + meter_no + "'");
                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Update_Information(""); // Pass a valid meter_no
    }
}
