package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator extends JFrame implements ActionListener {

    JTextField display;  // for current input/result
    JTextArea history;   // read-only history
    JButton[] numButtons = new JButton[10];
    JButton add, sub, mul, div, eq, clr, dec;
    JButton sqrt, power, percent, sin, cos, tan, log, ln;

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display for input/result
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // History panel (read-only)
        history = new JTextArea(6, 20);
        history.setEditable(false);
        JScrollPane scroll = new JScrollPane(history);
        add(scroll, BorderLayout.EAST);

        // Buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 5, 5, 5));

        // Number buttons 0-9
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
            panel.add(numButtons[i]);
        }

        // Decimal, clear, equals
        dec = new JButton("."); dec.addActionListener(this); panel.add(dec);
        clr = new JButton("C"); clr.addActionListener(this); panel.add(clr);
        eq = new JButton("="); eq.addActionListener(this); panel.add(eq);

        // Basic operators
        add = new JButton("+"); add.addActionListener(this); panel.add(add);
        sub = new JButton("-"); sub.addActionListener(this); panel.add(sub);
        mul = new JButton("*"); mul.addActionListener(this); panel.add(mul);
        div = new JButton("/"); div.addActionListener(this); panel.add(div);

        // Advanced
        sqrt = new JButton("√"); sqrt.addActionListener(this); panel.add(sqrt);
        power = new JButton("^"); power.addActionListener(this); panel.add(power);
        percent = new JButton("%"); percent.addActionListener(this); panel.add(percent);

        // Scientific
        sin = new JButton("sin"); sin.addActionListener(this); panel.add(sin);
        cos = new JButton("cos"); cos.addActionListener(this); panel.add(cos);
        tan = new JButton("tan"); tan.addActionListener(this); panel.add(tan);
        log = new JButton("log"); log.addActionListener(this); panel.add(log);
        ln = new JButton("ln"); ln.addActionListener(this); panel.add(ln);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Number buttons
        for(int i=0;i<10;i++){
            if(e.getSource()==numButtons[i]){
                display.setText(display.getText()+i);
            }
        }
        if(e.getSource()==dec) display.setText(display.getText()+".");

        // Clear
        if(e.getSource()==clr){ display.setText(""); num1=num2=result=0; }

        // Basic operations
        if(e.getSource()==add){ num1=Double.parseDouble(display.getText()); operator='+'; display.setText(""); }
        if(e.getSource()==sub){ num1=Double.parseDouble(display.getText()); operator='-'; display.setText(""); }
        if(e.getSource()==mul){ num1=Double.parseDouble(display.getText()); operator='*'; display.setText(""); }
        if(e.getSource()==div){ num1=Double.parseDouble(display.getText()); operator='/'; display.setText(""); }
        if(e.getSource()==power){ num1=Double.parseDouble(display.getText()); operator='^'; display.setText(""); }

        // Equals
        if(e.getSource()==eq){
            num2=Double.parseDouble(display.getText());
            switch(operator){
                case '+': result=num1+num2; break;
                case '-': result=num1-num2; break;
                case '*': result=num1*num2; break;
                case '/': 
                    if(num2==0){ JOptionPane.showMessageDialog(this,"Cannot divide by zero!"); display.setText(""); return; }
                    result=num1/num2; break;
                case '^': result=Math.pow(num1,num2); break;
            }
            display.setText(String.valueOf(result));
            history.append(num1+" "+operator+" "+num2+" = "+result+"\n");  // append to history
            num1=result;  // only for next calculation if user starts typing a new operator
        }

        // Single-operand advanced functions
        if(e.getSource()==sqrt){ num1=Double.parseDouble(display.getText()); result=Math.sqrt(num1); display.setText(String.valueOf(result)); history.append("√"+num1+" = "+result+"\n"); }
        if(e.getSource()==percent){ num1=Double.parseDouble(display.getText()); result=num1/100; display.setText(String.valueOf(result)); history.append(num1+"% = "+result+"\n"); }

        // Scientific functions
        if(e.getSource()==sin){ num1=Double.parseDouble(display.getText()); result=Math.sin(Math.toRadians(num1)); display.setText(String.valueOf(result)); history.append("sin("+num1+") = "+result+"\n"); }
        if(e.getSource()==cos){ num1=Double.parseDouble(display.getText()); result=Math.cos(Math.toRadians(num1)); display.setText(String.valueOf(result)); history.append("cos("+num1+") = "+result+"\n"); }
        if(e.getSource()==tan){ num1=Double.parseDouble(display.getText()); result=Math.tan(Math.toRadians(num1)); display.setText(String.valueOf(result)); history.append("tan("+num1+") = "+result+"\n"); }
        if(e.getSource()==log){ num1=Double.parseDouble(display.getText()); result=Math.log10(num1); display.setText(String.valueOf(result)); history.append("log("+num1+") = "+result+"\n"); }
        if(e.getSource()==ln){ num1=Double.parseDouble(display.getText()); result=Math.log(num1); display.setText(String.valueOf(result)); history.append("ln("+num1+") = "+result+"\n"); }
    }

    public static void main(String[] args){
        new ScientificCalculator();
    }
}
