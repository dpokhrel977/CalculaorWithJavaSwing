package com.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class ActionSeparate extends AbstractAction {

    JTextField display;

    ActionSeparate(JTextField display) {
        this.display = display;
    }

    public void actionPerformed(ActionEvent e) {
        String st = e.getActionCommand();

        if (st.equals("1") || st.equals("2") || st.equals("3")
                || st.equals("4") || st.equals("5") || st.equals("6")
                || st.equals("7") || st.equals("8") || st.equals("9")
                || st.equals("0") || st.equals(".") || st.equals("A")
                || st.equals("B") || st.equals("C") || st.equals("D")
                || st.equals("E") || st.equals("F") || st.equals("a")
                || st.equals("b") || st.equals("c") || st.equals("d")
                || st.equals("e") || st.equals("f")) {
            CalculatorPanel.setBackWard(false);
            CalculatorPanel.setFeFlag(true);
            new InsertOperation(st, display);
            CalculatorPanel.setCommandStatus(false);

        }

        if (st.equals("+") || st.equals("-") || st.equals("*")
                || st.equals("/") || st.equals("=") || st.equals("C.")
                || st.equals("CE") || st.equals("x^y") || st.equals("Md")
                || st.equals("And") || st.equals("Or") || st.equals("Xor")
                || st.equals("Lsh") || st.equals("Not") || st.equals("Int")) {
            CalculatorPanel.setBackWard(true);
            CalculatorPanel.setFeFlag(true);
            CommandOperation command = new CommandOperation(st, display);
            CalculatorPanel.setCommandStatus(true);

        }

        if (st.equals("Back")) {
            moveBack();
        }

        if (st.equals("+/-")) {
            String str = display.getText();
            if (str.equals("Cannot divide by zero.")
                    || str.equals("Invalid input for function."))
                Toolkit.getDefaultToolkit().beep();
            else {
                if (!str.equals("0")) {
                    if (str.startsWith("-"))
                        str = str.substring(1, str.length());
                    else
                        str = "-" + str;
                    display.setText("" + str);
                }
            }
        }
    }

    public void moveBack() {

        if (CalculatorPanel.getBackWard())
            Toolkit.getDefaultToolkit().beep();
        else {

            String orginal = display.getText();
            if (orginal.equals("Cannot divide by zero.")
                    || orginal.equals("Invalid input for function."))
                Toolkit.getDefaultToolkit().beep();
            else {
                String newText = "";
                int length = orginal.length();
                if (length > 0) {
                    for (int i = 0; i < length - 1; i++) {
                        if (orginal.charAt(Math.abs(length - 3)) == 'e') {
                            if (orginal.charAt(length - 1) == '0') {
                                newText = orginal.substring(0, length - 3);
                                CalculatorPanel.setExpState(true);
                            }
                            if (orginal.charAt(length - 1) != '0')
                                newText = orginal.substring(0, length - 1)
                                        + "0";
                        } else
                            newText += orginal.charAt(i);
                    }
                    if (newText.equals(""))
                        display.setText("0");

                    else
                        display.setText("" + newText);
                }
            }
        }
    }

}
