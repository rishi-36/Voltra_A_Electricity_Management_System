package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class Bill_Payment_Done extends JFrame implements ActionListener {
    String meter_no;
    JButton phonePeBtn, gpayBtn, paytmBtn, back;

    Bill_Payment_Done(String meter_no) {
        super("Voltra / bill_payment");
        this.meter_no = meter_no;

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(500, 350);
        setLocation(500, 250);

        // heading label add kr diya font or color ke sath
        JLabel label = new JLabel("Choose a payment method:");
        label.setFont(new Font("Cambria", Font.BOLD, 18));
        label.setForeground(new Color(40, 40, 40));
        label.setBounds(130, 40, 300, 30);
        add(label);

        // PhonePe button add kiya original color (#5f259f - purple)
        phonePeBtn = new JButton("PhonePe");
        phonePeBtn.setFont(new Font("Cambria", Font.BOLD, 14));
        phonePeBtn.setBackground(new Color(95, 37, 159));
        phonePeBtn.setForeground(Color.WHITE);
        phonePeBtn.setBounds(40, 100, 120, 40);
        phonePeBtn.addActionListener(this);
        phonePeBtn.setFocusPainted(false);     // <-- isse focus wala border hat jaayega
        phonePeBtn.setBorderPainted(false);    // <-- isse normal border bhi hat gaya
        add(phonePeBtn);

        // GPay button add kiya original blue-ish color
        gpayBtn = new JButton("GPay");
        gpayBtn.setFont(new Font("Cambria", Font.BOLD, 14));
        gpayBtn.setBackground(new Color(66, 133, 244));
        gpayBtn.setForeground(Color.WHITE);
        gpayBtn.setBounds(190, 100, 120, 40);     // space diya beech me
        gpayBtn.addActionListener(this);
        add(gpayBtn);

        // Paytm button add kiya uske official light blue color (#00baf2)
        paytmBtn = new JButton("Paytm");
        paytmBtn.setFont(new Font("Cambria", Font.BOLD, 14));
        paytmBtn.setBackground(new Color(0, 186, 242));
        paytmBtn.setForeground(Color.WHITE);
        paytmBtn.setBounds(340, 100, 120, 40);     // space diya beech me
        paytmBtn.addActionListener(this);
        add(paytmBtn);

        // Back button same as before, thoda style diya
        back = new JButton("Back");
        back.setFont(new Font("Cambria", Font.BOLD, 14));
        back.setBounds(200, 200, 100, 35);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == phonePeBtn) {
                Desktop.getDesktop().browse(new URI("https://www.phonepe.com/"));
            } else if (e.getSource() == gpayBtn) {
                Desktop.getDesktop().browse(new URI("https://pay.google.com/"));
            } else if (e.getSource() == paytmBtn) {
                Desktop.getDesktop().browse(new URI("https://paytm.com/electricity-bill-payment"));
            } else if (e.getSource() == back) {
                setVisible(false);
                new Pay_Bill(meter_no);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unable to open the selected payment site.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Bill_Payment_Done("");
    }
}
