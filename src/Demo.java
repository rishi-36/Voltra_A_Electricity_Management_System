import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.util.ArrayList;

class Device {
    String name;
    double power;
    int hours;

    Device(String name, double power, int hours) {
        this.name = name;
        this.power = power;
        this.hours = hours;
    }

    double getConsumption() {
        return power * hours;
    }
}

public class Demo extends JFrame {
    ArrayList<Device> devices = new ArrayList<>();
    JTextArea resultArea = new JTextArea();
    double limit = 50.0; // Threshold for alert

    Demo() {
        setTitle("Smart Electricity Usage Monitor");
        setSize(400, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField nameField = new JTextField(10);
        JTextField powerField = new JTextField(5);
        JTextField hourField = new JTextField(5);
        JButton addButton = new JButton("Add");
        JButton calcButton = new JButton("Calculate");

        add(new JLabel("Device Name:"));
        add(nameField);
        add(new JLabel("Power (kW):"));
        add(powerField);
        add(new JLabel("Hours Used:"));
        add(hourField);
        add(addButton);
        add(calcButton);
        add(new JScrollPane(resultArea));

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            try {
                double power = Double.parseDouble(powerField.getText());
                int hours = Integer.parseInt(hourField.getText());
                devices.add(new Device(name, power, hours));
                resultArea.append("Added: " + name + "\n");
                nameField.setText("");
                powerField.setText("");
                hourField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });

        calcButton.addActionListener(e -> {
            double total = 0;
            resultArea.setText("");
            for (Device d : devices)
            {
                double usage = d.getConsumption();
                resultArea.append(d.name + ": " + usage + " kWh\n");
                total += usage;
            }
            resultArea.append("\nTotal Usage: " + total + " kWh\n");
            if (total > limit) {
                resultArea.append("Alert: High usage!\n");
            }
        });
    }

    public static void main(String[] args) {
        new Demo().setVisible(true);
    }
}
