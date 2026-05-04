package com.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class StatisticOperation implements ActionListener {
    static JDialog d;
    static JList list;
    Container content;

    static DefaultListModel listModel;
    static JScrollPane scrollPane;
    static JLabel label;
    static int dataCount = 0;

    StatisticOperation() {
        d = new JDialog((JFrame) null, JDialog.ModalityType.TOOLKIT_MODAL);
        d.setSize(267, 170);
        d.setVisible(false);
        d.setResizable(false);
        d.setTitle("Statistics Box");
        d.setLocation(150, 150);
        d.setModal(false);

        content = d.getContentPane();
        content.setLayout(null);

        JButton ret = new JButton("RET");
        JButton load = new JButton("LOAD");
        JButton cd = new JButton("CD");
        JButton cad = new JButton("CAD");
        label = new JLabel("n=0");

        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(list);

        scrollPane.setBounds(10, 10, 200, 60);
        ret.setBounds(10, 90, 57, 25);
        load.setBounds(73, 90, 67, 25);
        cd.setBounds(145, 90, 50, 25);
        cad.setBounds(200, 90, 58, 25);
        label.setBounds(130, 120, 40, 25);

        content.add(scrollPane);
        content.add(ret);
        content.add(load);
        content.add(cd);
        content.add(cad);
        content.add(label);

        ret.addActionListener(this);
        load.addActionListener(this);
        cd.addActionListener(this);
        cad.addActionListener(this);

        d.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                CalculatorPanel.disableStat();
                CalculatorPanel.setStatWindow(false);
                clearAll();
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        String string = CalculatorPanel.display.getText();
        if (string.equals("Cannot divide by zero.")
                || string.equals("Invalid input for function."))
            Toolkit.getDefaultToolkit().beep();
        else {
            if (str == "Sta") {
                CalculatorPanel.enableStat();
                CalculatorPanel.setStatWindow(true);
                d.setVisible(true);
            }

            if (str == "Sum") {
                CalculatorPanel.displayResult(sum());
            }
            if (str == "Ave") {
                CalculatorPanel.displayResult(ave());
            }
            if (str == "s") {
                CalculatorPanel.displayResult(sDevation());
            }
        }
        if (str == "RET")
            Calculator.moveFront();
        if (str == "LOAD") {
            loadToPanel();
        }
        if (str == "CD") {
            clearOne();
        }
        if (str == "CAD") {
            clearAll();
        }
    }

    public Double sum() {
        double sum = 0;
        int size = list.getModel().getSize();
        for (int i = 0; i < size; i++) {
            CalculatorPanel.display.setText(""
                    + list.getModel().getElementAt(i));
            if (CalculatorPanel.getInvState())
                sum += Math.pow(CalculatorPanel.getNumber(), 2);
            else
                sum += CalculatorPanel.getNumber();

        }
        CalculatorPanel.setInvState(false);
        return sum;
    }

    public Double ave() {
        double sum = 0;
        sum = sum();
        return sum / dataCount;
    }

    public Double sDevation() {
        double mean = 0, sum = 0, average = 0;
        Boolean inv = CalculatorPanel.getInvState();
        int size = list.getModel().getSize();
        CalculatorPanel.setInvState(false);
        mean = ave();

        for (int i = 0; i < size; i++) {
            CalculatorPanel.display.setText(""
                    + list.getModel().getElementAt(i));
            sum += Math.pow(CalculatorPanel.getNumber() - mean, 2);
        }
        if (inv)
            average = Math.sqrt(sum / dataCount);
        else
            average = Math.sqrt(sum / (dataCount - 1));
        return average;
    }

    public void loadToPanel() {
        if (list.getModel().getSize() > 0) {
            int index = list.getSelectedIndex();
            String str1 = "" + list.getModel().getElementAt(index);
            CalculatorPanel.display.setText(str1);
            CalculatorPanel.setBackWard(false);
            CalculatorPanel.setTransition(true);
        } else
            Toolkit.getDefaultToolkit().beep();
    }

    public void clearOne() {

        int size = list.getModel().getSize();
        if (size > 0) {
            int index = list.getSelectedIndex();
            listModel.remove(index);
            dataCount--;
            if (index + 1 == size)
                list.setSelectedIndex(index - 1);
            else
                list.setSelectedIndex(index);
            label.setText("n=" + dataCount);
        } else
            Toolkit.getDefaultToolkit().beep();

    }

    public void clearAll() {

        listModel.clear();
        dataCount = 0;
        label.setText("n=0");
    }

}
