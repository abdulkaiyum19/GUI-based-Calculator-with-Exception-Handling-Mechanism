import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Calculator extends JFrame implements ActionListener {
 private JTextField display;
 private String operator;
 private double num1, num2, result;
 public Calculator() {
 setTitle("Calculator");
 setSize(280, 360);
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setLayout(null);
 setResizable(false);

 display = new JTextField();
 display.setFont(new Font("Arial", Font.BOLD, 20));
 display.setEditable(false);
 display.setBounds(10, 10, 260, 50);
 add(display);
 JButton clearButton = new JButton("Clear");
 clearButton.setFont(new Font("Arial", Font.BOLD, 14));
 clearButton.setBounds(10, 70, 125, 35);
 clearButton.addActionListener(this);
 add(clearButton);
 JButton backButton = new JButton("->Back");
 backButton.setFont(new Font("Arial", Font.BOLD, 14));
 backButton.setBounds(140, 70, 125, 35);
 backButton.addActionListener(this);
 add(backButton);
 String[] buttonLabels = {
 "1", "2", "3", "+",
 "4", "5", "6", "-",
 "7", "8", "9", "X",
 "0", "mod", "sqrt", "/",
 "."
 };
 int x = 10, y = 120;
 int width = 60, height = 40;
 int space = 5;
 for (int i = 0; i < buttonLabels.length; i++) {
 JButton button = new JButton(buttonLabels[i]);
 button.setFont(new Font("Arial", Font.BOLD, 12));
 button.setBounds(x, y, width, height);
 button.addActionListener(this);
 add(button);
 x += width + space;
 if ((i + 1) % 4 == 0) {
 x = 10;
 y += height + space;
 }
 }
 // Adjust the bounds for the "=" button
 JButton equalButton = new JButton("=");
 equalButton.setFont(new Font("Arial", Font.BOLD, 14));
 equalButton.setBounds(75, 300, 190, 40);
 equalButton.addActionListener(this);
 add(equalButton);
 }
 @Override
 public void actionPerformed(ActionEvent e) {
 String command = e.getActionCommand();
 try {
 if (command.chars().allMatch(Character::isDigit) || command.equals(".")) {
 display.setText(display.getText() + command);
 } else if (command.equals("Clear")) {
 display.setText("");
 } else if (command.equals("->Back")) {
 String currentText = display.getText();
 if (currentText.length() > 0) {
 display.setText(currentText.substring(0, currentText.length() - 1));
 }
 } else if (command.equals("=")) {
 num2 = Double.parseDouble(display.getText());
 switch (operator) {
 case "+" -> result = num1 + num2;
 case "-" -> result = num1 - num2;
 case "X" -> result = num1 * num2;
 case "/" -> {
 if (num2 == 0) {
 throw new ArithmeticException("Cannot divide by zero");
 }
 result = num1 / num2;
 }
 case "mod" -> result = num1 % num2;
 case "sqrt" -> result = Math.sqrt(num1);
 }
 display.setText(String.valueOf(result));
 } else {
 operator = command;
 num1 = Double.parseDouble(display.getText());
 display.setText("");
 }
 } catch (NumberFormatException ex) {
 JOptionPane.showMessageDialog(this, "Invalid number format", "Error", 
JOptionPane.ERROR_MESSAGE);
 display.setText("");
 } catch (ArithmeticException ex) {
 JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", 
JOptionPane.ERROR_MESSAGE);
 display.setText("");
 } catch (NullPointerException ex) {
 JOptionPane.showMessageDialog(this, "Null pointer exception occurred", "Error", 
JOptionPane.ERROR_MESSAGE);
 display.setText("");
 } catch (ArrayIndexOutOfBoundsException ex) {
 JOptionPane.showMessageDialog(this, "Array index out of bounds exception occurred", 
"Error", JOptionPane.ERROR_MESSAGE);
 display.setText("");
}catch (Exception ex) {
JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", 
JOptionPane.ERROR_MESSAGE);
  display.setText("");
 }
 }
 public static void main(String[] args) {
 SwingUtilities.invokeLater(() -> {
 Calculator calculator = new Calculator();
 calculator.setVisible(true);
 });
 }
}