import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIBankBalanceApplication {
private static double balance = 0.0;
    
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Bank Account Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        
        // Create components
        JLabel balanceLabel = new JLabel("Current Balance: $0.00");
        JTextField amountField = new JTextField(10);
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");

        // Add components to the panel
        panel.add(balanceLabel);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(exitButton);
        
        // Add panel to frame
        frame.getContentPane().add(panel);
        
        // Action Listener for Deposit Button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0) {
                        balance += amount;
                        balanceLabel.setText(String.format("Current Balance: $%.2f", balance));
                        amountField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Amount must be positive", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount entered", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action Listener for Withdraw Button
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0 && amount <= balance) {
                        balance -= amount;
                        balanceLabel.setText(String.format("Current Balance: $%.2f", balance));
                        amountField.setText("");
                    } else if (amount > balance) {
                        JOptionPane.showMessageDialog(frame, "Insufficient funds", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Amount must be positive", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount entered", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action Listener for Exit Button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, String.format("Final Balance: $%.2f", balance), "Exiting", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

}
