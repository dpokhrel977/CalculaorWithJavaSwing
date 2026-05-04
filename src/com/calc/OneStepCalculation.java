package com.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OneStepCalculation implements ActionListener {
    JTextField display;
    static double record = 0;
    static double input = 0;
    int zeroCount = 0, zeroCount1 = 0;
    static boolean feFlag = false;

    public OneStepCalculation(JTextField display) {
        this.display = display;
    }

    public void actionPerformed(ActionEvent e) {
        if (display.getText().equals("Cannot divide by zero.")
                || display.getText().equals("Invalid input for function."))
            Toolkit.getDefaultToolkit().beep();
        else {
            CalculatorPanel.setTransition(true);
            input = CalculatorPanel.getNumber();

            String st = e.getActionCommand();
            CalculatorPanel.setBackWard(true);
            if (st == "pi") {
                if (CalculatorPanel.getInvState()) {
                    display.setText("" + (2 * Math.PI));
                    CalculatorPanel.setInvState(false);
                } else
                    display.setText("" + Math.PI);
            }
            if (st == "1/x") {
                if (input == 0)
                    display.setText("Cannot divide by zero.");
                else
                    CalculatorPanel.displayResult(1 / input);

            }
            if (st == "M+") {
                if (input != 0)
                    CalculatorPanel.setLabel('M');
                record += input;
            }
            if (st == "MS") {
                if (input != 0)
                    CalculatorPanel.setLabel('M');
                record = input;
            }
            if (st == "MR")
                CalculatorPanel.displayResult(record);
            if (st == "MC") {
                CalculatorPanel.clearLabel();
                record = 0;
            }
            if (st == "n!") {
                long fact;
                fact = fact((long) input);
                CalculatorPanel.displayResult((double) fact);
            }

            if (st == "log") {
                double logValue = CalculatorPanel.getNumber();
                if (CalculatorPanel.getInvState()) {
                    CalculatorPanel.displayResult(Math.pow(10, input));
                    CalculatorPanel.setInvState(false);
                } else
                    CalculatorPanel.displayResult(Math.log10(input));
            }
            if (st == "ln") {
                if (CalculatorPanel.getInvState()) {
                    CalculatorPanel.displayResult(Math.exp(input));
                    CalculatorPanel.setInvState(false);
                } else
                    CalculatorPanel.displayResult(Math.log(input));

            }
            if (st == "x^2") {
                if (CalculatorPanel.getInvState()) {
                    CalculatorPanel.displayResult(Math.sqrt(input));
                    CalculatorPanel.setInvState(false);
                } else
                    CalculatorPanel.displayResult(Math.pow(input, 2));
            }
            if (st == "x^3") {
                if (CalculatorPanel.getInvState()) {
                    CalculatorPanel.displayResult(Math.cbrt(input));
                    CalculatorPanel.setInvState(false);
                } else
                    CalculatorPanel.displayResult(Math.pow(input, 3));

            }
            if (st == "Exp") {
                CalculatorPanel.setBackWard(false);
                CalculatorPanel.setTransition(false);
                if (InsertOperation.getInsertState()) {
                    if (CalculatorPanel.getExpState()) {
                        CalculatorPanel.setExpState(false);
                        CalculatorPanel.setPointState(false);
                        CalculatorPanel.setCommandStatus(false);
                        CalculatorPanel.setFeFlag(true);

                        if (CalculatorPanel
                                .checkDecimal("" + display.getText())
                                || display.getText().charAt(
                                display.getText().length() - 1) == '.')
                            display.setText("" + display.getText() + "e+0");
                        else
                            display.setText("" + display.getText() + ".e+0");
                    } else
                        Toolkit.getDefaultToolkit().beep();
                } else
                    Toolkit.getDefaultToolkit().beep();
            }
            if (st == "dms") {
                String[] store = new String[3];
                double diff = 0;
                if (CalculatorPanel.checkDecimal("" + input)) {
                    for (int i = 0; i < 3; i++) {
                        String sub1 = breakString("" + input);
                        store[i] = sub1;
                        double n1 = Double.parseDouble(sub1);
                        diff = input - n1;
                        input = diff * 60;
                    }

                    display.setText(store[0] + "." + store[1] + store[2]);

                }

            }

            if (st == "F-E") {
                if (CalculatorPanel.getFeFlag()) {
                    CalculatorPanel.setFeFlag(false);
                    CalculatorPanel.displayResult(input);
                } else {
                    CalculatorPanel.setFeFlag(true);
                    CalculatorPanel.setExpState(false);

                    if (CalculatorPanel.checkDecimal("" + input)) {
                        if (Double.toString(input).indexOf('E') != -1)
                            display.setText("" + input);
                        else {
                            String str1 = removeZeroFromLast(display.getText());
                            int len1 = str1.length();

                            int index = str1.indexOf('.');
                            if (str1.charAt(0) != '0' && str1.charAt(1) == '.')
                                display.setText("" + str1 + "e+0");
                            else if (str1.charAt(0) != '0'
                                    && str1.charAt(1) != '.')
                                display.setText("" + str1.charAt(0) + "."
                                        + str1.substring(1, index) + ""
                                        + str1.substring(index + 1, len1)
                                        + "e+" + (index - 1));

                            else
                                display.setText("" + str1.charAt(2) + "."
                                        + str1.substring(3, len1) + "e-"
                                        + (zeroCount - 3));

                        }
                    }

                    else {
                        String str1 = removeZeroFromLast(display.getText());
                        int len1 = str1.length();

                        display.setText("" + str1.charAt(0) + "."
                                + str1.substring(1, str1.length()) + "e"
                                + (len1 - 1 + zeroCount1));
                    }
                }
            }

        }
    }

    public String removeZeroFromLast(String st) {
        zeroCount = 0;
        zeroCount1 = 0;
        String str = st;
        int len = str.length();

        do {
            if (str.endsWith("0")) {
                str = str.substring(0, len - 1);
                zeroCount1++;
            }
            len--;

        } while (len != 0);

        zeroCount = str.length();
        if (str.charAt(0) == '0') {

            int len2 = 0;
            int i = 0;
            do {
                len2 = str.length();
                if (str.charAt(2) == '0') {
                    str = str.substring(3, len2);
                    str = "0." + str;
                }
                i++;
            } while (i < len2);
        }

        return str;
    }

    public String breakString(String st) {
        String sub1 = "0";
        for (int i = 0; i < st.length(); i++)
            if (st.charAt(i) == '.')
                sub1 = st.substring(0, i);
        if (sub1.length() == 1)
            sub1 = "0" + sub1;
        return sub1;
    }

    public long fact(long n) {
        if (n <= 1)
            return 1;
        else
            return n * fact(n - 1);
    }

}
