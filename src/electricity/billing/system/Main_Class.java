package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Class extends JFrame implements ActionListener {
    String acctype;
    String meter_pass;

    Main_Class(String acctype, String meter_pass) {
        super("Voltra/ home");

        this.acctype = acctype;
        this.meter_pass = meter_pass;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/main_r5.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(
                Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                Image.SCALE_SMOOTH
        );
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(0, 0, getWidth(), getHeight());
        add(imageLabel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(189, 223, 247));
        setJMenuBar(menuBar);

        // Menu (Admin)
        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("Cambria", Font.BOLD, 18));
        menu.setForeground(Color.BLACK);

        JMenuItem newcustomer = new JMenuItem("New Customer");
        newcustomer.setFont(new Font("Cambria", Font.PLAIN, 16));
        newcustomer.setIcon(resizeIcon("icon/newcustomer.png"));
        newcustomer.setBackground(new Color(189, 223, 247));
        newcustomer.setForeground(Color.BLACK);
        newcustomer.setMargin(new Insets(5, 10, 5, 10));
        newcustomer.addActionListener(this);
        menu.add(newcustomer);

        JMenuItem customerdetails = new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("Cambria", Font.PLAIN, 16));
        customerdetails.setIcon(resizeIcon("icon/customerDetails.png"));
        customerdetails.setBackground(new Color(189, 223, 247));
        customerdetails.setForeground(Color.BLACK);
        customerdetails.setMargin(new Insets(5, 10, 5, 10));
        customerdetails.addActionListener(this);
        menu.add(customerdetails);

        JMenuItem depositdetails = new JMenuItem("Deposit Details");
        depositdetails.setFont(new Font("Cambria", Font.PLAIN, 16));
        depositdetails.setIcon(resizeIcon("icon/depositdetails.png"));
        depositdetails.setBackground(new Color(189, 223, 247));
        depositdetails.setForeground(Color.BLACK);
        depositdetails.setMargin(new Insets(5, 10, 5, 10));
        depositdetails.addActionListener(this);
        menu.add(depositdetails);

        JMenuItem calculatebill = new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("Cambria", Font.PLAIN, 16));
        calculatebill.setIcon(resizeIcon("icon/calculatorbills.png"));
        calculatebill.setBackground(new Color(189, 223, 247));
        calculatebill.setForeground(Color.BLACK);
        calculatebill.setMargin(new Insets(5, 10, 5, 10));
        calculatebill.addActionListener(this);
        menu.add(calculatebill);

        // Info (Customer)
        JMenu info = new JMenu("Information");
        info.setFont(new Font("Cambria", Font.BOLD, 18));
        info.setForeground(Color.BLACK);

        JMenuItem upinfo = new JMenuItem("Update Information");
        upinfo.setFont(new Font("Cambria", Font.PLAIN, 16));
        upinfo.setIcon(resizeIcon("icon/refresh.png"));
        upinfo.setBackground(new Color(189, 223, 247));
        upinfo.setForeground(Color.BLACK);
        upinfo.setMargin(new Insets(5, 10, 5, 10));
        upinfo.addActionListener(this);
        info.add(upinfo);

        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("Cambria", Font.PLAIN, 16));
        viewInfo.setIcon(resizeIcon("icon/information.png"));
        viewInfo.setBackground(new Color(189, 223, 247));
        viewInfo.setForeground(Color.BLACK);
        viewInfo.setMargin(new Insets(5, 10, 5, 10));
        viewInfo.addActionListener(this);
        info.add(viewInfo);

        // User (Customer)
        JMenu user = new JMenu("User");
        user.setFont(new Font("Cambria", Font.BOLD, 18));
        user.setForeground(Color.BLACK);

        JMenuItem paybill = new JMenuItem("Pay Bill");
        paybill.setFont(new Font("Cambria", Font.PLAIN, 16));
        paybill.setIcon(resizeIcon("icon/pay_bill3.png"));
        paybill.setBackground(new Color(189, 223, 247));
        paybill.setForeground(Color.BLACK);
        paybill.setMargin(new Insets(5, 10, 5, 10));
        paybill.addActionListener(this);
        user.add(paybill);

        JMenuItem billdetails = new JMenuItem("Bill Details");
        billdetails.setFont(new Font("Cambria", Font.PLAIN, 16));
        billdetails.setIcon(resizeIcon("icon/detail.png"));
        billdetails.setBackground(new Color(189, 223, 247));
        billdetails.setForeground(Color.BLACK);
        billdetails.setMargin(new Insets(5, 10, 5, 10));
        billdetails.addActionListener(this);
        user.add(billdetails);

        // Bill (Customer)
        JMenu bill = new JMenu("Bill");
        bill.setFont(new Font("Cambria", Font.BOLD, 18));
        bill.setForeground(Color.BLACK);

        JMenuItem genBill = new JMenuItem("Generate Bill");
        genBill.setFont(new Font("Cambria", Font.PLAIN, 16));
        genBill.setIcon(resizeIcon("icon/bill.png"));
        genBill.setBackground(new Color(189, 223, 247));
        genBill.setForeground(Color.BLACK);
        genBill.setMargin(new Insets(5, 10, 5, 10));
        genBill.addActionListener(this);
        bill.add(genBill);

        // Utility
        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("Cambria", Font.BOLD, 18));
        utility.setForeground(Color.BLACK);

        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("Cambria", Font.PLAIN, 16));
        notepad.setIcon(resizeIcon("icon/notepad.png"));
        notepad.setBackground(new Color(189, 223, 247));
        notepad.setForeground(Color.BLACK);
        notepad.setMargin(new Insets(5, 10, 5, 10));
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("Cambria", Font.PLAIN, 16));
        calculator.setIcon(resizeIcon("icon/calculator.png"));
        calculator.setBackground(new Color(189, 223, 247));
        calculator.setForeground(Color.BLACK);
        calculator.setMargin(new Insets(5, 10, 5, 10));
        calculator.addActionListener(this);
        utility.add(calculator);

        // Exit
        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("Cambria", Font.BOLD, 18));
        exit.setForeground(Color.BLACK);

        JMenuItem eexit = new JMenuItem("Exit");
        eexit.setFont(new Font("Cambria", Font.PLAIN, 16));
        eexit.setIcon(resizeIcon("icon/exit.png"));
        eexit.setBackground(new Color(189, 223, 247));
        eexit.setForeground(Color.BLACK);
        eexit.setMargin(new Insets(5, 10, 5, 10));
        eexit.addActionListener(this);
        exit.add(eexit);

        // ✅ Add menus based on account type
        if (acctype.trim().equalsIgnoreCase("Admin")) {
            menuBar.add(menu);
        } else {
            menuBar.add(user);
            menuBar.add(bill);
            menuBar.add(info);
//            menuBar.add(calculatebill);
        }
        menuBar.add(utility);
        menuBar.add(exit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    private ImageIcon resizeIcon(String path) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(path));
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        return new ImageIcon(img);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        switch (msg) {
            case "New Customer" -> new New_Customer();
            case "Customer Details" -> new Customer_Details();
            case "Deposit Details" -> new Deposit_Details();
            case "Calculate Bill" -> new Calculate_Bill();
            case "View Information" -> new View_Information(meter_pass);
            case "Update Information" -> new Update_Information(meter_pass);
            case "Bill Details" -> new Bill_Details(meter_pass);
            case "Pay Bill" -> new Pay_Bill(meter_pass);
            case "Generate Bill" -> new Generate_Bill(meter_pass);
            case "Calculator" -> {
                try {
                    Runtime.getRuntime().exec("calc.exe");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            case "Notepad" -> {
                try {
                    Runtime.getRuntime().exec("notepad.exe");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            case "Exit" -> {
                setVisible(false);
                new Login();
            }
        }
    }

    public static void main(String[] args) {
        new Main_Class("", "");
    }
}
