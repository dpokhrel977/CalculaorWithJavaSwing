package com.calc;

import javax.swing.*;
import java.awt.*;

class InsertOperation {
    int point = 0;
    String string1;
    static String lastPoint;
    static boolean insertFlag = true;

    InsertOperation(String st, JTextField display) {
        if (display.getText().equals("Cannot divide by zero.")
                || display.getText().equals("Invalid input for function."))
            Toolkit.getDefaultToolkit().beep();
        else if (CalculatorPanel.getMode() != 'D' && st.equals(".")) {
            st = "";
            Toolkit.getDefaultToolkit().beep();
        }

        else {
            insertFlag = true;

            if (CalculatorPanel.getCommandStatus()
                    || CalculatorPanel.getTransition()) {
                display.setText("0");
                CalculatorPanel.setTransition(false);
                lastPoint = "";

            } // check command status;

            if (display.getText().charAt(0) == '0' && st == ".")
                lastPoint = "0.";
            if (display.getText().charAt(0) != '0' && st == ".")
                lastPoint = st;
            if ((display.getText()).charAt(0) == '0'
                    && display.getText().length() == 1) {
                display.setText("");
            } // remove 0 frm right

            string1 = display.getText();

            for (int i = 0; i < string1.length(); i++) {
                // if(string1.charAt(i)=='e' && string1.charAt(i+2)=='0')
                // string1=string1.substring(0,i+2);

                if (string1.charAt(i) == 'e') {
                    if (string1.charAt(i + 2) == '0')
                        string1 = string1.substring(0, i + 2);

                    if (string1.substring(i + 2, string1.length()).length() == 4) {
                        st = "";
                        Toolkit.getDefaultToolkit().beep();
                    }
                }

            }

            for (int i = 0; i < string1.length(); i++) {
                if (string1.charAt(i) == '.')
                    point = 1;
            } // taking only one point

            if (point == 1 && st.equals(".")) {
                string1 = string1;
                Toolkit.getDefaultToolkit().beep();
            }

            else {
                if (lastPoint == "0." && st != "." && point == 0) {
                    string1 += lastPoint + st;
                    lastPoint = "";
                } else if (lastPoint == "." && st != "." && point == 0) {
                    string1 += lastPoint + st;
                    lastPoint = "";
                } else if (lastPoint == "0." && st == ".") {
                    string1 = "0";
                } else if (st != ".") {
                    string1 += st;
                }

            }
            display.setText(string1.toUpperCase());

        }

    }

    public static void setInsertState(Boolean flag) {
        insertFlag = flag;
    }

    public static boolean getInsertState() {
        return insertFlag;
    }

}
