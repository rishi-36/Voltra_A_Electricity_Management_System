// Changes made to individually set position and size for each component
// Also improved image handling and overall layout

package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Meter_Info extends JFrame implements ActionListener {
    Choice meterLocCh, meterTypCh, phaseCodeCh, billtypCh;
    JButton submit;
    String meternumber;

    Meter_Info(String meternumber) {
        this.meternumber = meternumber;

        setTitle("Voltra / Meter Information");
        setLayout(null); // Changed to null layout for full control
        getContentPane().setBackground(new Color(230, 230, 255)); // Soft background

        // Heading
        JLabel heading = new JLabel("Meter Information");
        heading.setFont(new Font("Cambria", Font.BOLD, 24));
        heading.setForeground(new Color(75, 0, 130));
        heading.setBounds(280, 20, 250, 30); // Centered title
        add(heading);

        // Labels and Components with custom positions
        JLabel meterNumber = new JLabel("Meter Number:");
        meterNumber.setBounds(300, 80, 120, 25);
        meterNumber.setFont(new Font("Cambria", Font.PLAIN, 16));
        add(meterNumber);

        JLabel meterNumberText = new JLabel(meternumber);
        meterNumberText.setBounds(430, 80, 150, 25);
        meterNumberText.setFont(new Font("Cambria", Font.PLAIN, 16));
        add(meterNumberText);

        JLabel meterLoc = new JLabel("Meter Location:");
        meterLoc.setBounds(300, 120, 120, 25);
        meterLoc.setFont(new Font("Cambria", Font.PLAIN, 16));
        add(meterLoc);

        meterLocCh = new Choice();
        meterLocCh.setBounds(430, 120, 150, 25);
        meterLocCh.setFont(new Font("Cambria", Font.PLAIN, 14));
        meterLocCh.add("Outside");
        meterLocCh.add("Inside");
        add(meterLocCh);

        JLabel meterTyp = new JLabel("Meter Type:");
        meterTyp.setBounds(300, 160, 120, 25);
        meterTyp.setFont(new Font("Cambria", Font.PLAIN, 16));
        add(meterTyp);

        meterTypCh = new Choice();
        meterTypCh.setBounds(430, 160, 150, 25);
        meterTypCh.setFont(new Font("Cambria", Font.PLAIN, 14));
        meterTypCh.add("Electric Meter");
        meterTypCh.add("Solar Meter");
        meterTypCh.add("Smart Meter");
        add(meterTypCh);

        JLabel phaseCode = new JLabel("Phase Code:");
        phaseCode.setBounds(300, 200, 120, 25);
        phaseCode.setFont(new Font("Cambria", Font.PLAIN, 16));
        add(phaseCode);

        phaseCodeCh = new Choice();
        phaseCodeCh.setBounds(430, 200, 150, 25);
        phaseCodeCh.setFont(new Font("Cambria", Font.PLAIN, 14));
        for (int i = 1; i <= 9; i++) {
            phaseCodeCh.add("0" + i + i);
        }
        add(phaseCodeCh);

        JLabel billtyp = new JLabel("Bill Type:");
        billtyp.setBounds(300, 240, 120, 25);
        billtyp.setFont(new Font("Cambria", Font.PLAIN, 16));
        add(billtyp);

        billtypCh = new Choice();
        billtypCh.setBounds(430, 240, 150, 25);
        billtypCh.setFont(new Font("Cambria", Font.PLAIN, 14));
        billtypCh.add("Normal");
        billtypCh.add("Industrial");
        add(billtypCh);

        JLabel day = new JLabel("30 Days Billing Time");
        day.setBounds(300, 280, 280, 25);
        day.setFont(new Font("Cambria", Font.PLAIN, 15));
        add(day);

        JLabel note = new JLabel("Note:");
        note.setBounds(300, 320, 100, 25);
        note.setFont(new Font("Cambria", Font.BOLD, 15));
        add(note);

        JLabel note1 = new JLabel("By default, bill is calculated for 30 days.");
        note1.setBounds(340, 320, 300, 25);
        note1.setFont(new Font("Cambria", Font.PLAIN, 15));
        add(note1);

        // Submit Button
        submit = new JButton("Submit");
        submit.setBounds(370, 370, 120, 35);
        submit.setBackground(new Color(100, 80, 200));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Cambria", Font.BOLD, 15));
        submit.addActionListener(this);
        add(submit);

        // Image Panel (Higher quality, right size)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/meter_1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); // Increased quality
        JLabel imgLabel = new JLabel(new ImageIcon(i2));
        imgLabel.setBounds(20, 100, 250, 250);
        add(imgLabel);

        setSize(650, 470);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String smeterNum = meternumber;
            String smeterLoc = meterLocCh.getSelectedItem();
            String smeterTyp = meterTypCh.getSelectedItem();
            String sphaseCode = phaseCodeCh.getSelectedItem();
            String sbillTyp = billtypCh.getSelectedItem();
            String sday = "30";

            String query = "insert into meter_info values('" + smeterNum + "','" + smeterLoc + "','" + smeterTyp + "','" + sphaseCode + "','" + sbillTyp + "','" + sday + "')";

            try {
                Database obj = new Database();
                obj.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Meter Information Submitted Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Meter_Info("");
    }
}
