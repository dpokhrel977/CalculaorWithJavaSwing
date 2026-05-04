package com.calc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class MenuClass {

    public MenuClass(Calculator cals) {
        JMenuBar bar = new JMenuBar();

        JMenu edit = new JMenu("Edit");
        JMenu view = new JMenu("View");
        JMenu help = new JMenu("Help");

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                InputEvent.CTRL_MASK));

        copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Copy is in process...");
            }
        });

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                InputEvent.CTRL_MASK));
        edit.add(copy);
        edit.add(paste);

        JRadioButtonMenuItem standard = new JRadioButtonMenuItem("Standard");
        JRadioButtonMenuItem scientific = new JRadioButtonMenuItem("Scientific");

        ButtonGroup group1 = new ButtonGroup();
        group1.add(standard);
        group1.add(scientific);

        JRadioButtonMenuItem hex = new JRadioButtonMenuItem("Hex");
        hex.addActionListener(new CalculatorPanel(cals.display));
        JRadioButtonMenuItem decimal = new JRadioButtonMenuItem("Decimal");
        JRadioButtonMenuItem octal = new JRadioButtonMenuItem("Octal");
        JRadioButtonMenuItem binaray = new JRadioButtonMenuItem("Binaray");
        JRadioButtonMenuItem degree = new JRadioButtonMenuItem("Degree");
        JRadioButtonMenuItem radian = new JRadioButtonMenuItem("Radian");
        JRadioButtonMenuItem grad = new JRadioButtonMenuItem("Grade");
        JRadioButtonMenuItem digitgroup = new JRadioButtonMenuItem(
                "Digit Grouping");

        ButtonGroup group2 = new ButtonGroup();
        group2.add(hex);
        group2.add(decimal);
        group2.add(octal);
        group2.add(binaray);

        view.add(standard);
        view.add(scientific);
        view.addSeparator();
        view.add(hex);
        view.add(decimal);
        view.add(octal);
        view.add(binaray);
        view.addSeparator();
        view.add(degree);
        view.add(radian);
        view.add(grad);
        view.addSeparator();
        view.add(digitgroup);

        JMenuItem helptopic = new JMenuItem("HelpTopics");
        JMenuItem aboutCalc = new JMenuItem("About Calculator");

        help.add(helptopic);
        help.add(aboutCalc);
        // paste.add(ok);
        bar.add(edit);
        bar.add(view);
        bar.add(help);
        cals.setJMenuBar(bar);

    }
}
