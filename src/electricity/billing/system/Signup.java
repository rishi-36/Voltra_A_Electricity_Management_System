package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    Choice loginASCho, meterChoice;
    JTextField EmployerText, userNameText, nameText, passwordText;
    JButton create, back;

    Signup() {
        super("Voltra / SignUp");
        getContentPane().setBackground(new Color(223, 238, 255));
        setLayout(null);

        Font labelFont = new Font("Cambria", Font.BOLD, 16);
        Font inputFont = new Font("Cambria", Font.PLAIN, 14);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(0, 0, 350, 450);
        formPanel.setBackground(new Color(223, 238, 255));
        add(formPanel);

        JLabel createAs = new JLabel("Create Account As");
        createAs.setFont(labelFont);
        createAs.setBounds(40, 50, 160, 25);
        formPanel.add(createAs);

        loginASCho = new Choice();
        loginASCho.add("Admin");
        loginASCho.add("Customer");
        loginASCho.setBounds(200, 50, 125, 30);
        formPanel.add(loginASCho);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setFont(labelFont);
        meterNo.setBounds(40, 90, 160, 25);
        meterNo.setVisible(false);
        formPanel.add(meterNo);

        meterChoice = new Choice();
        meterChoice.setFont(inputFont);
        meterChoice.setBounds(200, 90, 125, 25);
        meterChoice.setVisible(false);
        formPanel.add(meterChoice);

        meterChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String selectedMeter = meterChoice.getSelectedItem();
                try {
                    Database c = new Database();
                    ResultSet rs = c.statement.executeQuery("SELECT * FROM Signup WHERE meter_no = '" + selectedMeter + "'");
                    if (rs.next()) {
                        nameText.setText(rs.getString("name"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel Employer = new JLabel("Employer ID");
        Employer.setFont(labelFont);
        Employer.setBounds(40, 90, 160, 25);
        formPanel.add(Employer);

        EmployerText = new JTextField();
        EmployerText.setFont(inputFont);
        EmployerText.setBounds(200, 90, 120, 25);
        formPanel.add(EmployerText);

        JLabel userName = new JLabel("Username");
        userName.setFont(labelFont);
        userName.setBounds(40, 130, 160, 25);
        formPanel.add(userName);

        userNameText = new JTextField();
        userNameText.setFont(inputFont);
        userNameText.setBounds(200, 130, 120, 25);
        formPanel.add(userNameText);

        JLabel name = new JLabel("Name");
        name.setFont(labelFont);
        name.setBounds(40, 170, 160, 25);
        formPanel.add(name);

        nameText = new JTextField();
        nameText.setFont(inputFont);
        nameText.setBounds(200, 170, 120, 25);
        formPanel.add(nameText);

        JLabel password = new JLabel("Password");
        password.setFont(labelFont);
        password.setBounds(40, 210, 160, 25);
        formPanel.add(password);

        passwordText = new JTextField();
        passwordText.setFont(inputFont);
        passwordText.setBounds(200, 210, 120, 25);
        formPanel.add(passwordText);

        loginASCho.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String user = loginASCho.getSelectedItem();
                boolean isCustomer = user.equals("Customer");

                Employer.setVisible(!isCustomer);
                EmployerText.setVisible(!isCustomer);
                meterNo.setVisible(isCustomer);
                meterChoice.setVisible(isCustomer);
                nameText.setEditable(!isCustomer);

                if (isCustomer) {
                    meterChoice.removeAll();
                    try {
                        Database db = new Database();
                        ResultSet rs = db.statement.executeQuery("SELECT meter_no FROM Signup WHERE usertype IS NULL OR usertype = ''");
                        while (rs.next()) {
                            meterChoice.add(rs.getString("meter_no"));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        create = new JButton("Create");
        create.setFont(labelFont);
        create.setBackground(new Color(0, 102, 204));
        create.setForeground(Color.WHITE);
        create.setBounds(60, 280, 100, 35);
        create.addActionListener(this);
        formPanel.add(create);

        back = new JButton("Back");
        back.setFont(labelFont);
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.WHITE);
        back.setBounds(180, 280, 100, 35);
        back.addActionListener(this);
        formPanel.add(back);

        ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("icon/sign_r2.jpg"));
        Image img = imgIcon.getImage().getScaledInstance(400, 450, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setBounds(350, 0, 400, 450);
        add(imgLabel);

        setSize(750, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String sloginAs = loginASCho.getSelectedItem();
            String suserName = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeterNo = sloginAs.equals("Admin") ? EmployerText.getText() : meterChoice.getSelectedItem();

            try {
                Database obj = new Database();
                ResultSet rs = obj.statement.executeQuery("SELECT * FROM Signup WHERE username = '" + suserName + "'");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Redirecting to login.");
                    setVisible(false);
                    new Login();
                    return;
                }

                if (sloginAs.equals("Admin")) {
                    String query = "INSERT INTO Signup (meter_no, username, name, password, usertype) VALUES ('" + smeterNo + "', '" + suserName + "', '" + sname + "', '" + spassword + "', 'Admin')";
                    obj.statement.executeUpdate(query);
                } else {
                    rs = obj.statement.executeQuery("SELECT * FROM Signup WHERE meter_no = '" + smeterNo + "'");
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Meter Number not found!");
                        return;
                    }

                    rs = obj.statement.executeQuery("SELECT * FROM Signup WHERE meter_no = '" + smeterNo + "' AND username IS NOT NULL");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Account already exists for this meter.");
                        setVisible(false);
                        new Login();
                        return;
                    }

                    String query = "UPDATE Signup SET username = '" + suserName + "', password = '" + spassword + "', usertype = 'Customer' WHERE meter_no = '" + smeterNo + "'";
                    obj.statement.executeUpdate(query);
                }

                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
