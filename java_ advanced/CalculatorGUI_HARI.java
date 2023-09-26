import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JTextField inputField;
    private JLabel resultLabel;
    private String currentInput = "";
    private double currentResult = 0.0;

    public CalculatorGUI() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        JPanel logoPanel = new JPanel();
        JLabel logoLabel = new JLabel("HARIOM");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 40));
        logoLabel.setForeground(Color.WHITE);
        logoPanel.add(logoLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setHorizontalAlignment(JTextField.RIGHT);

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.setFont(new Font("Arial", Font.PLAIN, 24));
            btn.addActionListener(new ButtonClickListener());
            panel.add(btn);
        }
        frame.add(logoPanel, BorderLayout.WEST);
        frame.add(inputField, BorderLayout.NORTH);
        frame.add(resultLabel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if ("0123456789".contains(command)) {
                currentInput += command;
                inputField.setText(currentInput);
            } else if (command.equals("C")) {
                currentInput = "";
                currentResult = 0.0;
                inputField.setText("");
                resultLabel.setText("Result: ");
            } else if (command.equals("=")) {
                try {
                    currentResult = evaluateExpression(currentInput);
                    resultLabel.setText("Result: " + currentResult);
                } catch (Exception ex) {
                    resultLabel.setText("Error");
                }
                currentInput = "";
            } else {
                currentInput += " " + command + " ";
                inputField.setText(currentInput);
            }
        }
    }

    private double evaluateExpression(String expression) {
        try {
            String[] parts = expression.split(" ");
            double num1 = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double num2 = Double.parseDouble(parts[2]);

            switch (operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    if (num2 != 0) {
                        return num1 / num2;
                    } else {
                        throw new ArithmeticException("Division by zero");
                    }
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid expression");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorGUI();
        });
    }
}
//add gradident blue color and add logo of "hariom" 