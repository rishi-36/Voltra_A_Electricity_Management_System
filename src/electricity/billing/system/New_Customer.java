package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class New_Customer extends JFrame implements ActionListener {
    JLabel heading, meternumText, customerName, meterNum, address, city, state, email, phone;
    JButton next, cancel;
    JTextField nameText, addressText, cityText, stateText, emailText, phoneText;

    // Constructor for the New_Customer frame
    New_Customer() {
        super("Voltra/ New Customer");  // Frame title
        setSize(800, 550); // Frame size
        setLocation(300, 100); // Frame location

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(null); // Null layout for custom positioning
        panel.setBackground(new Color(241, 232, 222)); // Soft background color
        add(panel); // Add panel to the frame

        // Heading label
        heading = new JLabel("Add New Customer");
        heading.setBounds(270, 10, 300, 40);
        heading.setFont(new Font("Cambria", Font.BOLD, 30)); // Font for heading
        heading.setForeground(new Color(40, 55, 71)); // Heading color
        panel.add(heading);

        // Customer Name label and text field
        customerName = new JLabel("Customer Name");
        customerName.setBounds(50, 100, 150, 30);
        customerName.setFont(new Font("Cambria", Font.PLAIN, 16));
        panel.add(customerName);

        nameText = new JTextField();
        nameText.setBounds(200, 100, 250, 30);
        nameText.setFont(new Font("Cambria", Font.PLAIN, 16)); // Font for text field
        panel.add(nameText);

        // Meter Number label and text field (random meter number generated)
        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50, 140, 150, 30);
        meterNum.setFont(new Font("Cambria", Font.PLAIN, 16));
        panel.add(meterNum);

        meternumText = new JLabel("");
        meternumText.setBounds(200, 140, 250, 30);
        meternumText.setFont(new Font("Cambria", Font.PLAIN, 16));
        panel.add(meternumText);

        // Generate random meter number
        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meternumText.setText("" + Math.abs(number));

        // Address label and text field
        address = new JLabel("Address");
        address.setBounds(50, 180, 150, 30);
        address.setFont(new Font("Cambria", Font.PLAIN, 16));
        panel.add(address);

        addressText = new JTextField();
        addressText.setBounds(200, 180, 250, 30);
        addressText.setFont(new Font("Cambria", Font.PLAIN, 16)); // Font for text field
        panel.add(addressText);

        // City label and text field
        city = new JLabel("City");
        city.setBounds(50, 220, 150, 30);
        city.setFont(new Font("Cambria", Font.PLAIN, 16));
        panel.add(city);

        cityText = new JTextField();
        cityText.setBounds(200, 220, 250, 30);
        cityText.setFont(new Font("Cambria", Font.PLAIN, 16)); // Font for text field
        panel.add(cityText);

        // State label and text field
        state = new JLabel("State");
        state.setBounds(50, 260, 150, 30);
        state.setFont(new Font("Cambria", Font.PLAIN, 16));
        panel.add(state);

        stateText = new JTextField();
        stateText.setBounds(200, 260, 250, 30);
        stateText.setFont(new Font("Cambria", Font.PLAIN, 16)); // Font for text field
        panel.add(stateText);

        // Email label and text field
        email = new JLabel("Email");
        email.setBounds(50, 300, 150, 30);
        email.setFont(new Font("Cambria", Font.PLAIN, 16));
        panel.add(email);

        emailText = new JTextField();
        emailText.setBounds(200, 300, 250, 30);
        emailText.setFont(new Font("Cambria", Font.PLAIN, 16)); // Font for text field
        panel.add(emailText);

        // Phone label and text field
        phone = new JLabel("Phone");
        phone.setBounds(50, 340, 150, 30);
        phone.setFont(new Font("Cambria", Font.PLAIN, 16));
        panel.add(phone);

        phoneText = new JTextField();
        phoneText.setBounds(200, 340, 250, 30);
        phoneText.setFont(new Font("Cambria", Font.PLAIN, 16)); // Font for text field
        panel.add(phoneText);

        // Next button
        next = new JButton("Next");
        next.setBounds(120, 400, 150, 40);
        next.setBackground(new Color(40, 55, 71)); // Button background color
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Cambria", Font.PLAIN, 16)); // Font for button
        next.addActionListener(this);
        panel.add(next);

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.setBounds(300, 400, 150, 40);
        cancel.setBackground(new Color(40, 55, 71)); // Cancel button color
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Cambria", Font.PLAIN, 16)); // Font for button
        cancel.addActionListener(this);
        panel.add(cancel);

        // Image setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image i2 = i1.getImage().getScaledInstance(350, 400, Image.SCALE_SMOOTH); // Scale the image
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLable = new JLabel(i3);
        imgLable.setBounds(450, 60, 350, 400); // Image positioning
        panel.add(imgLable);

        // Set the frame to visible
        setVisible(true);
    }

    // Action performed function for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            // Get input values from the form
            String sname = nameText.getText();
            String smeter = meternumText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String eemail = emailText.getText();
            String sphone = phoneText.getText();

            // Validate input (optional)
            if (sname.isEmpty() || saddress.isEmpty() || scity.isEmpty() || sstate.isEmpty() || eemail.isEmpty() || sphone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            // SQL query to insert the new customer details into the database
            String query_customer = "INSERT INTO new_customer (name, meter_no, address, city, state, email, phone) VALUES ('" + sname + "','" + smeter + "','" + saddress + "','" + scity + "','" + sstate + "','" + eemail + "','" + sphone + "')";
            String query_signup = "INSERT INTO Signup (meter_no, name) VALUES ('" + smeter + "','" + sname + "')";

            try {
                // Execute queries to store data in the database
                Database obj = new Database();
                obj.statement.executeUpdate(query_customer);
                obj.statement.executeUpdate(query_signup);

                // Success message
                JOptionPane.showMessageDialog(null, "Customer details added successfully.");
                setVisible(false); // Hide current frame
                new Meter_Info(smeter); // Open Meter Info frame for the new customer
            } catch (Exception ex) {
                ex.printStackTrace(); // Handle any exceptions
                JOptionPane.showMessageDialog(null, "Error occurred while adding customer.");
            }
        } else if (e.getSource() == cancel) {
            setVisible(false); // Close the form if Cancel button is clicked
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        new New_Customer(); // Display the New Customer form
    }
}
