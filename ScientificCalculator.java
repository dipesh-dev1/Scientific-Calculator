import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.MathContext;

public class ScientificCalculator extends JFrame implements ActionListener {

    private static final String SQRT = "\u221A"; // the square root sign

    // 5 x 5 = 25 buttons, laid out row by row
    private static final String[] LABELS = {
        "sin", "cos", "tan", "log", "ln",
        SQRT,  "^",   "%",   "C",   "/",
        "7",   "8",   "9",   "*",   "-",
        "4",   "5",   "6",   "+",   ".",
        "1",   "2",   "3",   "0",   "="
    };

    private final JTextField display = new JTextField();   // current input / result
    private final JTextArea history = new JTextArea(6, 20); // read-only history

    private double num1 = 0;        // first operand
    private char operator = ' ';    // pending operator (' ' means none)
    private boolean startNew = false; // true after "=" or a function: next digit starts a new number

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // History panel (read-only)
        history.setEditable(false);
        history.setFont(new Font("Monospaced", Font.PLAIN, 13));
        add(new JScrollPane(history), BorderLayout.EAST);

        // Buttons
        JPanel panel = new JPanel(new GridLayout(5, 5, 5, 5));
        for (String label : LABELS) {
            JButton button = new JButton(label);
            button.setActionCommand(label);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.addActionListener(this);
            panel.add(button);
        }
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.length() == 1 && Character.isDigit(cmd.charAt(0))) {
            appendDigit(cmd);
            return;
        }

        switch (cmd) {
            case ".":
                appendDecimal();
                break;
            case "C":
                clearAll();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
                operatorPressed(cmd.charAt(0));
                break;
            case "=":
                equalsPressed();
                break;
            default:
                functionPressed(cmd); // sqrt, %, sin, cos, tan, log, ln
                break;
        }
    }

    // ---------- input handling ----------

    private void appendDigit(String digit) {
        if (startNew) {
            display.setText("");
            startNew = false;
        }
        display.setText(display.getText() + digit);
    }

    private void appendDecimal() {
        if (startNew) {
            display.setText("0.");
            startNew = false;
            return;
        }
        String text = display.getText();
        if (text.contains(".")) {
            return; // only one decimal point allowed
        }
        display.setText(text.isEmpty() ? "0." : text + ".");
    }

    private void clearAll() {
        display.setText("");
        num1 = 0;
        operator = ' ';
        startNew = false;
    }

    // Reads the display as a number. Shows a message and returns null if it is empty or invalid.
    private Double readDisplay() {
        String text = display.getText().trim();
        if (text.isEmpty()) {
            showError("Please enter a number first.");
            return null;
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException ex) {
            showError("Invalid number.");
            return null;
        }
    }

    // ---------- operations ----------

    private void operatorPressed(char op) {
        // If an operator is already waiting and nothing was typed yet, just change the operator
        if (display.getText().isEmpty() && operator != ' ') {
            operator = op;
            return;
        }
        Double value = readDisplay();
        if (value == null) {
            return;
        }
        num1 = value;
        operator = op;
        display.setText("");
        startNew = false;
    }

    private void equalsPressed() {
        if (operator == ' ') {
            return; // nothing to calculate
        }
        Double value = readDisplay();
        if (value == null) {
            return;
        }
        double num2 = value;
        double result = 0;

        switch (operator) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/':
                if (num2 == 0) {
                    showError("Cannot divide by zero!");
                    clearAll();
                    return;
                }
                result = num1 / num2;
                break;
            case '^': result = Math.pow(num1, num2); break;
        }

        if (Double.isNaN(result) || Double.isInfinite(result)) {
            showError("Math error: result is not a valid number.");
            clearAll();
            return;
        }

        String line = format(num1) + " " + operator + " " + format(num2) + " = " + format(result);
        display.setText(format(result));
        history.append(line + "\n");
        operator = ' ';
        num1 = result;
        startNew = true;
    }

    // Single-operand functions: sqrt, %, sin, cos, tan, log, ln
    private void functionPressed(String cmd) {
        Double value = readDisplay();
        if (value == null) {
            return;
        }
        double x = value;
        double result;
        String label;

        switch (cmd) {
            case SQRT:
                if (x < 0) {
                    showError("Cannot take the square root of a negative number!");
                    return;
                }
                result = Math.sqrt(x);
                label = SQRT + format(x);
                break;
            case "%":
                result = x / 100;
                label = format(x) + "%";
                break;
            case "sin":
                result = cleanTiny(Math.sin(Math.toRadians(x)));
                label = "sin(" + format(x) + ")";
                break;
            case "cos":
                result = cleanTiny(Math.cos(Math.toRadians(x)));
                label = "cos(" + format(x) + ")";
                break;
            case "tan":
                if (Math.abs(Math.cos(Math.toRadians(x))) < 1e-12) {
                    showError("tan is undefined for this angle.");
                    return;
                }
                result = cleanTiny(Math.tan(Math.toRadians(x)));
                label = "tan(" + format(x) + ")";
                break;
            case "log":
                if (x <= 0) {
                    showError("log is defined only for numbers greater than 0.");
                    return;
                }
                result = Math.log10(x);
                label = "log(" + format(x) + ")";
                break;
            case "ln":
                if (x <= 0) {
                    showError("ln is defined only for numbers greater than 0.");
                    return;
                }
                result = Math.log(x);
                label = "ln(" + format(x) + ")";
                break;
            default:
                return;
        }

        display.setText(format(result));
        history.append(label + " = " + format(result) + "\n");
        startNew = true;
    }

    // ---------- helpers ----------

    // Turns tiny rounding noise such as 1.2E-16 into 0
    private double cleanTiny(double value) {
        return Math.abs(value) < 1e-12 ? 0 : value;
    }

    // Formats a number without a useless ".0" and without long rounding errors
    private String format(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return String.valueOf(value);
        }
        return BigDecimal.valueOf(value)
                .round(new MathContext(12))
                .stripTrailingZeros()
                .toPlainString();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScientificCalculator::new);
    }
}
