package com.calc;

import javax.swing.*;
import java.awt.*;

class CommandOperation {

    static String lastCommand = "N";

    String string1;

    static double number2, oldNumber;
    static double result = 0;
    static JTextField display;
    static boolean insertFlag;

    CommandOperation(String command, JTextField display) {
        this.display = display;
        string1 = display.getText();
        insertFlag = InsertOperation.getInsertState();
        InsertOperation.setInsertState(false);

        if (command.equals("CE"))
            this.display.setText("0");
        else if (command.equals("C."))
            clearAll();

        else if (string1.equals("Cannot divide by zero.")
                || string1.equals("Invalid input for function."))
            Toolkit.getDefaultToolkit().beep();
        else {
            number2 = CalculatorPanel.getNumber();

            calculate(number2, command);
        }

    }

    public void calculate(double number1, String command) {

        if (command == "Not" || command == "Int")
            this.lastCommand = command;
        if (this.lastCommand == "N")
            this.result = number1;
        if (this.lastCommand != "N") {
            if (!insertFlag)
                number1 = oldNumber;
            if (this.lastCommand != command && command != "=")
                this.lastCommand = command;

            if (this.lastCommand == "+" && insertFlag == true)
                add(number1);
            if (this.lastCommand == "+" && command == "="
                    && insertFlag == false)
                add(number1);

            if (this.lastCommand == "-" && insertFlag == true)
                subtract(number1);
            if (this.lastCommand == "-" && command == "="
                    && insertFlag == false)
                subtract(number1);

            if (this.lastCommand == "*" && insertFlag == true)
                multiply(number1);
            if (this.lastCommand == "*" && command == "="
                    && insertFlag == false)
                multiply(number1);

            if (this.lastCommand == "/" && insertFlag == true)
                divide(number1);
            if (this.lastCommand == "/" && command == "="
                    && insertFlag == false)
                divide(number1);

            if (lastCommand == "x^y" && insertFlag == true)
                raise(number1);
            if (this.lastCommand == "x^y" && command == "="
                    && insertFlag == false)
                raise(number1);

            if (this.lastCommand == "Md" && command == "=")
                modulus(number1);
            if (this.lastCommand == "And" && command == "=")
                bitwiseOperation(number1, lastCommand);
            if (this.lastCommand == "Or" && command == "=")
                bitwiseOperation(number1, lastCommand);
            if (this.lastCommand == "Xor" && command == "=")
                bitwiseOperation(number1, lastCommand);
            if (this.lastCommand == "Lsh" && command == "=")
                bitwiseOperation(number1, lastCommand);
            if (this.lastCommand == "Not" || this.lastCommand == "Int")
                bitwiseOperation(number1, lastCommand);
            oldNumber = number1;
        }
        if (insertFlag == false && lastCommand != "=")
            lastCommand = lastCommand;
        else if (command != "=")
            this.lastCommand = command;

    }

    public void add(double number1) {

        this.result += number1;

        CalculatorPanel.displayResult(result);

    }

    public void subtract(double number1) {
        this.result -= number1;
        CalculatorPanel.displayResult(result);
    }

    public void multiply(double number1) {
        this.result *= number1;
        CalculatorPanel.displayResult(result);
    }

    public void divide(double number1) {
        if (number1 != 0) {
            this.result /= number1;
            CalculatorPanel.displayResult(result);
        } else
            display.setText("Cannot divide by zero.");
    }

    public void raise(double number1) {
        result = Math.pow(result, number1);
        CalculatorPanel.displayResult(result);
    }

    public void modulus(double number1) {
        result = result % number1;
        CalculatorPanel.displayResult(result);
    }

    public void bitwiseOperation(double number1, String command) {
        if (command == "And")
            result = (int) result & (int) number1;
        if (command == "Or")
            result = (int) result | (int) number1;
        if (command == "Xor")
            result = (int) result ^ (int) number1;
        if (command == "Lsh")
            result = (int) result << (int) number1;
        if (command == "Not")
            result = ~(int) number1;
        if (command == "Int")
            result = (int) number1;
        CalculatorPanel.displayResult(result);
    }

    public static void clearAll()

    {
        number2 = 0;
        result = 0;
        display.setText("0");
        lastCommand = "N";
        CalculatorPanel.setExpState(true);
        CalculatorPanel.setPointState(false);
        InsertOperation.setInsertState(true);
    }
}
