package com.calc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CalculatorPanel extends JPanel implements ActionListener {

    JButton hexDisableButton[], decimalDisableButton[], octDisableButton[],
            binDisableButton[];
    static JButton statDisableButton[];
    JRadioButton hex, dec, oct, bin;
    static JRadioButton degrees, radians, grads;
    JPanel panel2, panel21;
    static JTextField display;
    static JLabel c1, c2;
    static char mode = 'D';
    static int h = 0, d = 1, o = 0, b = 0;
    static boolean transition = false;
    static boolean backFlag = true;
    static JCheckBox inv, hyp;
    static boolean expState = true;
    static boolean pointState = true;
    static boolean c_status = false;
    static boolean feFlag = false;
    static boolean statWindow = false;

    CalculatorPanel(JTextField display) {

        this.display = display;

        Border etched = BorderFactory.createEtchedBorder();
        Action listener1 = new ActionSeparate(display);
        ActionListener listener2 = new OneStepCalculation(display);

        setLayout(null);
        hexDisableButton = new JButton[7];
        decimalDisableButton = new JButton[6];
        octDisableButton = new JButton[2];
        binDisableButton = new JButton[6];
        statDisableButton = new JButton[4];

        JPanel panel1 = new JPanel();
        panel1.setBorder(etched);
        panel1.setLayout(new GridLayout(1, 4));

        hex = new JRadioButton("Hex");
        hex.addActionListener(this);
        dec = new JRadioButton("Dec", true);
        dec.addActionListener(this);
        oct = new JRadioButton("Oct");
        oct.addActionListener(this);
        bin = new JRadioButton("Bin");
        bin.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(hex);
        group.add(dec);
        group.add(oct);
        group.add(bin);
        panel1.add(hex);
        panel1.add(dec);
        panel1.add(oct);
        panel1.add(bin);

        panel2 = new JPanel();
        panel2.setBorder(etched);
        panel2.setLayout(new GridLayout(1, 3));

        degrees = new JRadioButton("Degrees", true);
        radians = new JRadioButton("Radians");
        grads = new JRadioButton("Grads");

        ButtonGroup group2 = new ButtonGroup();
        group2.add(degrees);
        group2.add(radians);
        group2.add(grads);
        panel2.add(degrees);
        panel2.add(radians);
        panel2.add(grads);

        panel21 = new JPanel();
        panel21.setBorder(etched);
        panel21.setLayout(new GridLayout(1, 4));
        ButtonGroup group3 = new ButtonGroup();
        JRadioButton qword = new JRadioButton("Qword", true);
        JRadioButton dword = new JRadioButton("Dword");
        JRadioButton word = new JRadioButton("Word");
        JRadioButton b_byte = new JRadioButton("Byte");
        group3.add(qword);
        group3.add(dword);
        group3.add(word);
        group3.add(b_byte);
        panel21.add(qword);
        panel21.add(dword);
        panel21.add(word);
        panel21.add(b_byte);

        JPanel panel3 = new JPanel();
        panel3.setBorder(etched);
        panel3.setLayout(new GridLayout(1, 4));

        inv = new JCheckBox("Inv");
        panel3.add(inv);
        hyp = new JCheckBox("Hyp");
        panel3.add(hyp);

        Font f = new Font("Times in new roman", Font.BOLD, 14);
        c1 = new JLabel();
        c1.setFont(f);
        panel3.add(c1);
        c2 = new JLabel();
        c2.setFont(f);
        panel3.add(c2);
        c1.setHorizontalTextPosition(JLabel.RIGHT);
        c2.setHorizontalTextPosition(JLabel.RIGHT);
        Border br1 = BorderFactory.createLoweredBevelBorder();

        c1.setBorder(br1);
        c2.setBorder(br1);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1, 3));

        JButton backspace = new JButton("Back");
        backspace.setForeground(Color.red);
        panel4.add(backspace);
        backspace.addActionListener(listener1);
        JButton ce = new JButton("CE");
        ce.setForeground(Color.red);
        panel4.add(ce);
        ce.addActionListener(listener1);
        JButton c = new JButton("C.");
        c.setForeground(Color.red);
        panel4.add(c);

        c.addActionListener(listener1);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayout(5, 1));
        JButton sta = new JButton("Sta");
        sta.addActionListener(new StatisticOperation());
        sta.setForeground(Color.blue);

        JButton ave = new JButton("Ave");
        ave.addActionListener(new StatisticOperation());
        ave.setEnabled(false);
        statDisableButton[0] = ave;
        ave.setForeground(Color.blue);
        JButton sum = new JButton("Sum");
        sum.addActionListener(new StatisticOperation());
        sum.setEnabled(false);
        statDisableButton[1] = sum;
        sum.setForeground(Color.blue);
        JButton s = new JButton("s");
        s.addActionListener(new StatisticOperation());
        s.setEnabled(false);
        statDisableButton[2] = s;
        s.setForeground(Color.blue);
        JButton dat = new JButton("Dat");
        dat.setEnabled(false);
        statDisableButton[3] = dat;
        dat.setForeground(Color.blue);

        panel5.add(sta);
        panel5.add(ave);
        panel5.add(sum);
        panel5.add(sum);
        panel5.add(s);
        panel5.add(dat);

        JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayout(5, 3));

        JButton f_e = new JButton("F-E");
        f_e.addActionListener(listener2);
        hexDisableButton[0] = f_e;
        f_e.setForeground(new Color(255, 0, 255));
        panel6.add(f_e);
        JButton o_br = new JButton("(");
        o_br.setForeground(new Color(255, 0, 255));
        panel6.add(o_br);
        JButton c_br = new JButton(")");
        c_br.setForeground(new Color(255, 0, 255));
        panel6.add(c_br);
        JButton dms = new JButton("dms");
        dms.addActionListener(listener2);
        hexDisableButton[1] = dms;
        dms.setForeground(new Color(255, 0, 255));
        panel6.add(dms);
        JButton exp = new JButton("Exp");
        exp.addActionListener(listener2);
        hexDisableButton[2] = exp;
        exp.setForeground(new Color(255, 0, 255));
        panel6.add(exp);
        JButton ln = new JButton("ln");
        ln.addActionListener(listener2);
        ln.setForeground(new Color(255, 0, 255));
        panel6.add(ln);
        JButton sin = new JButton("sin");
        sin.addActionListener(new TrigonometryOperation());
        hexDisableButton[3] = sin;
        sin.setForeground(new Color(255, 0, 255));
        panel6.add(sin);
        JButton x_p_y = new JButton("x^y");
        x_p_y.addActionListener(listener1);
        x_p_y.setForeground(new Color(255, 0, 255));
        panel6.add(x_p_y);
        JButton log = new JButton("log");
        log.addActionListener(listener2);
        log.setForeground(new Color(255, 0, 255));
        panel6.add(log);
        JButton cos = new JButton("cos");
        cos.addActionListener(new TrigonometryOperation());
        hexDisableButton[4] = cos;
        cos.setForeground(new Color(255, 0, 255));
        panel6.add(cos);
        JButton x_p_3 = new JButton("x^3");
        x_p_3.addActionListener(listener2);
        x_p_3.setForeground(new Color(255, 0, 255));
        panel6.add(x_p_3);
        JButton n_f = new JButton("n!");
        n_f.setForeground(new Color(255, 0, 255));
        panel6.add(n_f);
        n_f.addActionListener(listener2);
        JButton tan = new JButton("tan");
        tan.addActionListener(new TrigonometryOperation());
        hexDisableButton[5] = tan;
        tan.setForeground(new Color(255, 0, 255));
        panel6.add(tan);
        JButton x_p_2 = new JButton("x^2");
        x_p_2.addActionListener(listener2);
        x_p_2.setForeground(new Color(255, 0, 255));
        panel6.add(x_p_2);
        JButton x_i = new JButton("1/x");
        x_i.setForeground(new Color(255, 0, 255));
        panel6.add(x_i);
        x_i.addActionListener(listener2);

        JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayout(5, 1));

        JButton mc = new JButton("MC");
        mc.setForeground(Color.red);
        panel7.add(mc);
        mc.addActionListener(listener2);
        JButton mr = new JButton("MR");
        mr.setForeground(Color.red);
        panel7.add(mr);
        mr.addActionListener(listener2);
        JButton ms = new JButton("MS");
        ms.setForeground(Color.red);
        panel7.add(ms);
        ms.addActionListener(listener2);
        JButton m_plus = new JButton("M+");
        m_plus.setForeground(Color.red);
        panel7.add(m_plus);
        m_plus.addActionListener(listener2);
        JButton pi = new JButton("pi");
        pi.addActionListener(listener2);

        hexDisableButton[6] = pi;
        pi.setForeground(Color.blue);
        panel7.add(pi);

        JPanel panel8 = new JPanel();

        final JButton bgroup[] = new JButton[30];
        panel8.setLayout(new GridLayout(5, 6));
        String str[] = { "7", "8", "9", "/", "Md", "And", "4", "5", "6", "*",
                "Or", "Xor", "1", "2", "3", "-", "Lsh", "Not", "0", "+/-", ".",
                "+", "=", "Int", "A", "B", "C", "D", "E", "F" };

        String keyList1[] = { "7", "8", "9", "/", "%", "&", "4", "5", "6", "*",
                "|", "^", "1", "2", "3", "-", "<", "~", "0", "KeyEvent.VK_F9",
                ".", "KeyEvent.VK_SHIFT.PLUS", "KeyEvent.VK_ENTER", ";", "A",
                "B", "C", "D", "E", "F" };

        int keyList[] = { 55, 56, 57, 111, 123, 150, 52, 53, 54, 106 };

        for (int i = 0; i < 30; i++) {
            bgroup[i] = new JButton(str[i]);
            bgroup[i].addActionListener(listener1);
            addKeyStroke(keyList1[i], listener1, bgroup[i]);
            panel8.add(bgroup[i]);

            if (i >= 24) {
                decimalDisableButton[i - 24] = bgroup[i];
                bgroup[i].setEnabled(false);
            }
            if (i == 1 || i == 2)
                octDisableButton[i - 1] = bgroup[i];
            if (i == 0)
                binDisableButton[i] = bgroup[i];
            if (i == 6)
                binDisableButton[1] = bgroup[i];
            if (i == 7)
                binDisableButton[2] = bgroup[i];
            if (i == 8)
                binDisableButton[3] = bgroup[i];
            if (i == 13)
                binDisableButton[4] = bgroup[i];
            if (i == 14)
                binDisableButton[5] = bgroup[i];
        }

        String blueButton[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "0", "+/-", ".", "A", "B", "C", "D", "E", "F" };
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 30; j++)
                if (blueButton[i].equals(bgroup[j].getText()))
                    bgroup[j].setForeground(Color.blue);
        }

        String redButton[] = { "/", "Md", "And", "*", "Or", "Xor", "-", "Lsh",
                "Not", "+", "=", "Int" };

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 30; j++)
                if (redButton[i].equals(bgroup[j].getText()))
                    bgroup[j].setForeground(Color.red);
        }

        panel1.setBounds(10, 20, 200, 25);
        add(panel1);
        panel2.setBounds(210, 20, 230, 25);
        add(panel2);
        panel21.setBounds(210, 20, 270, 25);
        add(panel21);
        panel21.setVisible(false);
        panel3.setBounds(10, 45, 190, 25);
        add(panel3);
        panel4.setBounds(200, 45, 260, 25);
        add(panel4);
        panel5.setBounds(5, 85, 60, 200);
        add(panel5);
        panel6.setBounds(65, 85, 177, 200);
        add(panel6);
        panel7.setBounds(243, 85, 55, 200);
        add(panel7);
        panel8.setBounds(299, 85, 345, 200);
        add(panel8);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (getText().equals("Cannot divide by zero.")
                        || getText().equals("Invalid input for function."))
                    Toolkit.getDefaultToolkit().beep();
                else {
                    StatisticOperation.dataCount++;
                    StatisticOperation.listModel.addElement("" + getText());
                    StatisticOperation.label.setText("n="
                            + StatisticOperation.dataCount);
                    StatisticOperation.list
                            .setSelectedIndex(StatisticOperation.dataCount - 1);
                    StatisticOperation.list
                            .ensureIndexIsVisible(StatisticOperation.dataCount - 1);
                    setTransition(true);
                    setBackWard(true);
                }
            }
        };
        dat.addActionListener(actionListener);

    }

    public void actionPerformed(ActionEvent e) {
        String st1 = display.getText();
        String st = e.getActionCommand();
        if (st1.equals("Cannot divide by zero.")
                || st1.equals("Invalid input for function.")) {
            if (mode == 'H')
                hex.setSelected(true);
            if (mode == 'D')
                dec.setSelected(true);
            if (mode == 'O')
                oct.setSelected(true);
            if (mode == 'B')
                bin.setSelected(true);
            Toolkit.getDefaultToolkit().beep();
        } else {

            if (st.equals("Hex")) {

                if (h == 0)
                    display.setText(""
                            + Integer.toHexString((int) getNumber())
                            .toUpperCase());
                this.panel2.setVisible(false);
                this.panel21.setVisible(true);
                hexDisable(false);
                decDisable(true);
                octDisable(true);
                binDisable(true);
                if (h == 0)
                    setTransition(true);
                else
                    setTransition(false);
                mode = 'H';
                h = 1;
                d = 0;
                o = 0;
                b = 0;
            }
            if (st.equals("Dec")) {
                if (d == 0)
                    display.setText("" + (int) getNumber());

                this.panel2.setVisible(true);
                this.panel21.setVisible(false);
                decDisable(false);
                hexDisable(true);
                octDisable(true);
                binDisable(true);
                if (d == 0)
                    setTransition(true);
                else
                    setTransition(false);
                mode = 'D';
                h = 0;
                d = 1;
                o = 0;
                b = 0;
            }
            if (st.equals("Oct")) {
                if (o == 0)
                    display.setText(""
                            + Integer.toOctalString((int) getNumber()));
                this.panel2.setVisible(false);
                this.panel21.setVisible(true);
                hexDisable(false);
                decDisable(false);
                octDisable(false);
                binDisable(true);
                if (o == 0)
                    setTransition(true);
                else
                    setTransition(false);
                mode = 'O';
                h = 0;
                d = 0;
                o = 1;
                b = 0;
            }

            if (st.equals("Bin")) {

                if (b == 0)
                    display.setText(""
                            + Integer.toBinaryString((int) getNumber()));
                this.panel2.setVisible(false);
                this.panel21.setVisible(true);
                hexDisable(false);
                decDisable(false);
                octDisable(false);
                binDisable(false);
                if (b == 0)
                    setTransition(true);
                else
                    setTransition(false);
                mode = 'B';
                h = 0;
                d = 0;
                o = 0;
                b = 1;
            }
        }
    }// end action method

    public void hexDisable(Boolean flag) {

        for (int i = 0; i < 7; i++)
            this.hexDisableButton[i].setEnabled(flag);
    }

    public void decDisable(Boolean flag) {
        for (int i = 0; i < 6; i++)
            this.decimalDisableButton[i].setEnabled(flag);
    }

    public void octDisable(Boolean flag) {
        for (int i = 0; i < 2; i++)
            this.octDisableButton[i].setEnabled(flag);
    }

    public void binDisable(Boolean flag) {
        for (int i = 0; i < 6; i++)
            this.binDisableButton[i].setEnabled(flag);
    }

    public static void enableStat() {
        for (int i = 0; i < 4; i++)
            statDisableButton[i].setEnabled(true);

    }

    public static void disableStat() {
        for (int i = 0; i < 4; i++)
            statDisableButton[i].setEnabled(false);

    }

    public static String getText() {
        return display.getText();
    }

    public static void setTransition(Boolean flag) {
        transition = flag;
    }

    public static boolean getTransition() {
        return transition;
    }

    public static void setCommandStatus(boolean flag) {
        c_status = flag;
    }

    public static boolean getCommandStatus() {
        return c_status;
    }

    public static char getMode() {
        return mode;
    }

    public static void setLabel(char c) {
        c2.setText("" + c);
    }

    public static void clearLabel() {
        c2.setText("");
    }

    public static void setBackWard(boolean flag) {
        backFlag = flag;
    }

    public static boolean getBackWard() {
        return backFlag;
    }

    public static boolean getInvState() {
        return inv.isSelected();
    }

    public static void setInvState(Boolean flag) {
        inv.setSelected(flag);
    }

    public static boolean getHyp() {
        return hyp.isSelected();
    }

    public static void setHyp(Boolean flag) {
        hyp.setSelected(flag);
    }

    public static boolean getExpState() {
        return expState;
    }

    public static void setExpState(boolean flag) {
        expState = flag;
    }

    public static void setPointState(boolean flag) {
        pointState = flag;
    }

    public static boolean getPointState() {
        return pointState;
    }

    public static void setFeFlag(boolean flag) {
        feFlag = flag;
    }

    public static boolean getFeFlag() {
        return feFlag;
    }

    public static void setStatWindow(boolean flag) {
        statWindow = flag;
    }

    public static boolean getStatWindow() {
        return statWindow;
    }

    public static String getTrigonMode() {
        String str = "";
        if (degrees.isSelected())
            str = "degree";
        if (radians.isSelected())
            str = "radian";
        if (grads.isSelected())
            str = "grade";
        return str;
    }

    public void addKeyStroke(String str, Action action, JComponent component) {

        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(str), "pressed");
        component.getActionMap().put("pressed", action);

        /*
         * System.out.println("Add button is called:-"+str); String
         * actionKey="pressed";
         *
         * KeyStroke stroke= KeyStroke.getKeyStroke(str); InputMap inputMap =
         * component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
         * inputMap.put(stroke,actionKey); ActionMap
         * actionMap=component.getActionMap(); actionMap.put(actionKey,action);
         */

    }

    public static void displayResult(double result) {
        if (mode == 'H')
            display.setText(""
                    + Integer.toHexString((int) result).toUpperCase());
        else if (mode == 'B')
            display.setText("" + Integer.toBinaryString((int) result));
        else if (mode == 'O')
            display.setText("" + Integer.toOctalString((int) result));
        else {
            if (checkDecimal("" + result))
                display.setText("" + result);
            else
                display.setText("" + (int) result);
        }
    }

    public static boolean checkDecimal(String string1) {
        boolean flag = false;
        for (int i = 0; i < string1.length(); i++) {

            if (string1.charAt(i) == '.') {
                for (int j = 1; j < string1.length() - i; j++) {
                    if (string1.charAt(j + i) != '0')
                        flag = true;
                }
            }
        }

        return flag;
    }

    public static double getNumber() {
        double number = 0;
        try {
            if (mode == 'H')
                number = Integer.parseInt(display.getText(), 16);
            else if (mode == 'O')
                number = Integer.parseInt(display.getText(), 8);
            else if (mode == 'B')
                number = Integer.parseInt(display.getText(), 2);
            else
                number = Double.parseDouble(display.getText());

        } catch (Exception e) {
        }
        return number;
    }

}
