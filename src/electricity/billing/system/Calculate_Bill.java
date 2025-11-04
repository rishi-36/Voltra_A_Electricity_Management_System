package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class Calculate_Bill extends JFrame implements ActionListener {
    Choice meternumCh, monthCh;
    JLabel nameText, addressText;
    JTextField unitText;
    JButton submit, cancel;

    Calculate_Bill() {
        super("Voltra/ calculate_bill");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 230, 255));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(150, 10, 350, 30);
        heading.setFont(new Font("Cambria", Font.BOLD, 22));
        heading.setForeground(new Color(60, 40, 90));
        panel.add(heading);

        JLabel meternum = new JLabel("Meter Number");
        meternum.setFont(new Font("Cambria", Font.PLAIN, 14));
        meternum.setBounds(50, 70, 120, 25);
        panel.add(meternum);

        meternumCh = new Choice();
        meternumCh.setFont(new Font("Cambria", Font.PLAIN, 14));
        meternumCh.setBounds(180, 70, 150, 25);
        panel.add(meternumCh);

        try {
            Database obj = new Database();
            ResultSet resultSet = obj.statement.executeQuery("select * from new_customer");
            while (resultSet.next()) {
                meternumCh.add(resultSet.getString("meter_no"));
            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }

        JLabel name = new JLabel("Name");
        name.setFont(new Font("Cambria", Font.PLAIN, 14));
        name.setBounds(50, 110, 100, 25);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setFont(new Font("Cambria", Font.PLAIN, 14));
        nameText.setBounds(180, 110, 200, 25);
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setFont(new Font("Cambria", Font.PLAIN, 14));
        address.setBounds(50, 150, 100, 25);
        panel.add(address);

        addressText = new JLabel("");
        addressText.setFont(new Font("Cambria", Font.PLAIN, 14));
        addressText.setBounds(180, 150, 300, 25);
        panel.add(addressText);

        meternumCh.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Database obj = new Database();
                    ResultSet resultSet = obj.statement.executeQuery("select * from new_customer where meter_no = '" + meternumCh.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        nameText.setText(resultSet.getString("name"));
                        addressText.setText(resultSet.getString("address"));
                    }
                } catch (Exception E) {
                    System.out.println(E.getMessage());
                }
            }
        });

        JLabel unitconsumed = new JLabel("Unit Consumed");
        unitconsumed.setFont(new Font("Cambria", Font.PLAIN, 14));
        unitconsumed.setBounds(50, 190, 120, 25);
        panel.add(unitconsumed);

        unitText = new JTextField();
        unitText.setFont(new Font("Cambria", Font.PLAIN, 14));
        unitText.setBounds(180, 190, 150, 25);
        panel.add(unitText);

        JLabel month = new JLabel("Month");
        month.setFont(new Font("Cambria", Font.PLAIN, 14));
        month.setBounds(50, 230, 100, 25);
        panel.add(month);

        monthCh = new Choice();
        monthCh.setFont(new Font("Cambria", Font.PLAIN, 14));
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String m : months) monthCh.add(m);
        monthCh.setBounds(180, 230, 150, 25);
        panel.add(monthCh);

        submit = new JButton("Submit");
        submit.setFont(new Font("Cambria", Font.BOLD, 14));
        submit.setBackground(new Color(60, 40, 90));
        submit.setForeground(Color.WHITE);
        submit.setBounds(100, 280, 100, 30);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Cambria", Font.BOLD, 14));
        cancel.setBackground(new Color(100, 100, 100));
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(220, 280, 100, 30);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH); // image quality enhance kr diya
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledIcon);
        add(imageLabel, "East");

        setSize(750, 400);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String smeterNo = meternumCh.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthCh.getSelectedItem();

            int totalBill = 0;
            int unit = Integer.parseInt(sunit);
            String query_tax = "select * from tax";

            try {
                Database c = new Database();
                ResultSet resultSet = c.statement.executeQuery(query_tax);
                while (resultSet.next()) {
                    totalBill += unit * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("swachh_bharat_tax"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }
            } catch (Exception E) {
                E.printStackTrace();
            }

            String query_total_bill = "insert into bill values ('" + smeterNo + "', '" + smonth + "', '" + sunit + "', '" + totalBill + "', 'Not Paid')";
            try {
                Database obj = new Database();
                obj.statement.executeUpdate(query_total_bill);

                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Calculate_Bill();
    }
}
