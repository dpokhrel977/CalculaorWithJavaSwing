package com.calc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TrigonometryOperation implements ActionListener {

    Boolean arc, hyp;
    double number = 0, number1 = 0, result = 0;
    String angleMode = "";

    public void actionPerformed(ActionEvent e) {

        String str = CalculatorPanel.display.getText();

        if (str.equals("Cannot divide by zero.")
                || str.equals("Invalid input for function."))
            Toolkit.getDefaultToolkit().beep();

        else {
            CalculatorPanel.setTransition(true);
            number1 = CalculatorPanel.getNumber();
            number = number1;
            String st = e.getActionCommand();
            angleMode = CalculatorPanel.getTrigonMode();
            arc = CalculatorPanel.getInvState();
            hyp = CalculatorPanel.getHyp();
            if (angleMode == "degree") {
                number = Math.toRadians(number1);
            }
            if (angleMode == "grade") {
                number = (Math.PI * 9 * number1) / (180 * 10);
            }
            if (st == "sin") {
                if (arc && hyp) {
                    result = Math.log(number1
                            + Math.sqrt(number1 * number1 + 1));
                    CalculatorPanel.setHyp(false);
                    CalculatorPanel.setInvState(false);
                }

                else if (arc) {
                    result = Math.asin(number1);
                    if (angleMode == "degree")
                        result = Math.toDegrees(result);
                    if (angleMode == "grade")
                        result = Math.toDegrees(result) * 10 / 9;
                    CalculatorPanel.setInvState(false);
                }

                else if (hyp) {
                    result = Math.sinh(number);
                    CalculatorPanel.setHyp(false);
                } else
                    result = Math.sin(number);
            }

            if (st == "cos") {
                if (arc && hyp) {
                    result = Math.log(number1
                            + Math.sqrt(number1 * number1 - 1));

                    CalculatorPanel.setHyp(false);
                    CalculatorPanel.setInvState(false);
                }

                else if (arc) {
                    result = Math.acos(number1);
                    if (angleMode == "degree")
                        result = Math.toDegrees(result);
                    if (angleMode == "grade")
                        result = Math.toDegrees(result) * 10 / 9;
                    CalculatorPanel.setInvState(false);
                }

                else if (hyp) {
                    result = Math.sinh(number);
                    CalculatorPanel.setHyp(false);
                } else
                    result = Math.cos(number);
            }

            if (st == "tan") {
                if (arc && hyp) {
                    result = Math.log((1 / number1 + 1) / (1 / number1 - 1)) / 2;
                    CalculatorPanel.setHyp(false);
                    CalculatorPanel.setInvState(false);
                }

                else if (arc) {
                    result = Math.atan(number1);
                    if (angleMode == "degree")
                        result = Math.toDegrees(result);
                    if (angleMode == "grade")
                        result = Math.toDegrees(result) * 10 / 9;
                    CalculatorPanel.setInvState(false);
                }

                else if (hyp) {
                    result = Math.tanh(number);
                    CalculatorPanel.setHyp(false);
                } else
                    result = Math.tan(number);
            }

            if (Double.isNaN(result))
                CalculatorPanel.display.setText("Invalid input for function.");
            else
                CalculatorPanel.displayResult(result);

        }

    }
}
