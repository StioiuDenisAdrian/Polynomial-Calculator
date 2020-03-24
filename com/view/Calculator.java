package com.view;

import com.model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame {

    private JFrame mainFrame;
    private JTextField firstPolynomial = new JTextField();
    private JTextField secondPolynomial = new JTextField();

    private JLabel result = new JLabel(" ");
    private JButton addition = new JButton("+");
    private JButton substraction = new JButton("-");
    private JButton multiplication = new JButton("*");
    private JButton division = new JButton("/");
    private JButton derivation = new JButton("dx");
    private JButton undefinedIntegration = new JButton("Sdx");


    private AdditionSubstraction add;
    private AdditionSubstraction sub;
    private Integration uI;
    private Multiplication mul;
    private Derivation der;
    private Division div;


    public Calculator(AdditionSubstraction add, AdditionSubstraction sub, Integration uI, Multiplication mul, Derivation der, Division div) {

        this.add = add;
        this.sub = sub;
        this.uI = uI;
        this.mul = mul;
        this.der = der;
        this.div=div;

        mainFrame = new JFrame("Polynomial Calculator");

        firstPolynomial.setBounds(40, 10, 200, 30);
        secondPolynomial.setBounds(40, 50, 200, 30);

        result.setBounds(40, 90, 200, 30);

        addition.setBounds(40, 140, 60, 40);
        substraction.setBounds(110, 140, 60, 40);
        multiplication.setBounds(180, 140, 60, 40);
        division.setBounds(40, 210, 60, 40);
        derivation.setBounds(110, 210, 60, 40);
        undefinedIntegration.setBounds(180, 210, 60, 40);


        mainFrame.add(firstPolynomial);
        mainFrame.add(secondPolynomial);
        mainFrame.add(result);
        mainFrame.add(addition);
        mainFrame.add(substraction);
        mainFrame.add(multiplication);
        mainFrame.add(division);
        mainFrame.add(derivation);
        mainFrame.add(undefinedIntegration);

        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setSize(290, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
    }


    public String getFirstPolynomial() {
        return firstPolynomial.getText();
    }

    public String getSecondPolynomial() {
        return secondPolynomial.getText();
    }

    public void displayResult(String total) {
        result.setText(total);
    }

    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

    public void addAdditionListener(ActionListener aal) {
        addition.addActionListener(aal);
    }

    public void addSubstractionListener(ActionListener sal) {
        substraction.addActionListener(sal);
    }

    public void addMultiplicationListener(ActionListener mal) {
        multiplication.addActionListener(mal);
    }

    public void addDivisionListener(ActionListener dal) {
        division.addActionListener(dal);
    }

    public void addDerivationListener(ActionListener dxal) {
        derivation.addActionListener(dxal);
    }

    public void addUndefinedIntegrationListener(ActionListener uial) {
        undefinedIntegration.addActionListener(uial);
    }

}
