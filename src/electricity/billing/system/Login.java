package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField userText;
    JPasswordField passwordText;
    Choice loginChoice;
    JButton loginButton, cancelButton, signupButton;

    Login() {
        super("Voltra / Login");

        getContentPane().setBackground(new Color(245, 250, 255));
        setLayout(null);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/login_r.jpg"));
        Image profileTwo = profileOne.getImage().getScaledInstance(450, 400, Image.SCALE_SMOOTH);
        ImageIcon fprofileOne = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(fprofileOne);
        profileLabel.setBounds(0, 0, 350, 400);
        add(profileLabel);

        Font labelFont = new Font("Cambria", Font.BOLD, 16);
        Font inputFont = new Font("Cambria", Font.PLAIN, 14);

        JLabel username = new JLabel("UserName");
        username.setFont(labelFont);
        username.setBounds(380, 50, 100, 25);
        add(username);

        userText = new JTextField();
        userText.setFont(inputFont);
        userText.setBounds(480, 50, 200, 25);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setFont(labelFont);
        password.setBounds(380, 100, 100, 25);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setFont(inputFont);
        passwordText.setBounds(480, 100, 200, 25);
        add(passwordText);

        JLabel login = new JLabel("Log in as");
        login.setFont(labelFont);
        login.setBounds(380, 150, 100, 25);
        add(login);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(480, 150, 200, 25);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(400, 210, 90, 35);
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(labelFont);
        loginButton.setFocusPainted(false);
        add(loginButton);
        loginButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(500, 210, 90, 35);
        cancelButton.setBackground(new Color(108, 117, 125));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(labelFont);
        cancelButton.setFocusPainted(false);
        add(cancelButton);
        cancelButton.addActionListener(this);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(600, 210, 90, 35);
        signupButton.setBackground(new Color(40, 167, 69));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(labelFont);
        signupButton.setFocusPainted(false);
        add(signupButton);
        signupButton.addActionListener(this);

        setSize(740, 400);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String susername = userText.getText();
            String spassword = new String(passwordText.getPassword());
            String suser = loginChoice.getSelectedItem();

            try {
                Database obj = new Database();

                String query = "SELECT * FROM Signup WHERE username = ? AND password = ? AND usertype = ?";
                PreparedStatement pstmt = obj.connection.prepareStatement(query);
                pstmt.setString(1, susername);
                pstmt.setString(2, spassword);
                pstmt.setString(3, suser);

                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    String meter_pass = resultSet.getString("meter_no");
                    setVisible(false);
                    new Main_Class(suser, meter_pass);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login Credentials");
                }
            } catch (SQLException ex) {
                System.err.println("SQL Exception: " + ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error occurred during login.");
            } catch (Exception ex) {
                System.err.println("An unexpected error occurred: " + ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An unexpected error occurred during login.");
            }

        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        } else if (e.getSource() == signupButton) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}