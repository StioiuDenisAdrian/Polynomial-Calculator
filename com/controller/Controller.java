package com.controller;

import com.model.*;
import com.view.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private AdditionSubstraction addition;
    private AdditionSubstraction substraction;
    private Integration undefinedIntegration;
    private Multiplication multiplication;
    private Division division;
    private Derivation derivation;
    private Calculator view;

    public Controller(AdditionSubstraction addition, AdditionSubstraction substraction, Integration undefinedIntegration, Multiplication multiplication, Derivation derivation, Division division, Calculator view) {
        this.addition = addition;
        this.substraction = substraction;
        this.undefinedIntegration = undefinedIntegration;
        this.multiplication = multiplication;
        this.derivation = derivation;
        this.division = division;
        this.view = view;

        view.addAdditionListener(new AdditionListener());
        view.addSubstractionListener(new SubstractionListener());
        view.addMultiplicationListener(new MultiplicationListener());
        view.addDivisionListener(new DivisionListener());
        view.addUndefinedIntegrationListener(new UndefinedIntegrationListener());
        view.addDerivationListener(new DerivationListener());
    }

    //We delimit each monomial with a blanc
    private String delimitMonomials(String p) {
        String delimited = "";
        if (p.charAt(0) != '+' && p.charAt(0) != '-')
            delimited += "+";
        else delimited += "-";
        for (int i = 0; i < p.length() - 1; i++) {
            if (p.charAt(i + 1) == '+' || p.charAt(i + 1) == '-')
                delimited += p.charAt(i) + " ";
            else delimited += p.charAt(i);
        }
        delimited += p.charAt(p.length() - 1);
        return delimited;
    }

    private boolean validPolynomial(String p) {
        if (p.length() == 0)
            return false;
        String delimited = delimitMonomials(p);
        Pattern pattern;
        Matcher matcher;
        String[] patterns = {"([-+])*(\\d+)*[x][\\^](\\d+)", "([-+])*[x][\\^](\\d+)", "([-+])*(\\d+)*[x]", "([-+])*(\\d)"};
        String[] split = delimited.split(" ");
        for (String i : split) {
            boolean valid = false;
            for (String j : patterns) {
                pattern = Pattern.compile(j);
                matcher = pattern.matcher(i);
                if (matcher.matches()) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                return false;
            }
        }
        return true;
    }

    class AdditionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2 = "";
            Polynomial p1 = new Polynomial(), p2 = new Polynomial(), sum = new Polynomial();
            Monomial m = new Monomial();
            try {
                userInput1 += view.getFirstPolynomial();
                userInput2 += view.getSecondPolynomial();
                if (validPolynomial(userInput1) && validPolynomial(userInput2)) {
                    String delimited1 = delimitMonomials(userInput1);
                    String delimited2 = delimitMonomials(userInput2);
                    String[] monomial1 = delimited1.split(" ");
                    String[] monomial2 = delimited2.split(" ");
                    int i = 0, j = 0;
                    while (i < monomial1.length) {
                        double c = m.convertFromString(monomial1[i]).getCoefficient();
                        int p = m.convertFromString(monomial1[i]).getPower();
                        if (c == 72 || c == -72) {
                            c /= 72;
                        }
                        Monomial aux = new Monomial(c, p);
                        p1.addTerm(aux);
                        i++;
                    }
                    p1.sortTerms();
                    p1.eliminateTermsWithSamePower();
                    while (j < monomial2.length) {
                        double c = m.convertFromString(monomial2[j]).getCoefficient();
                        int p = m.convertFromString(monomial2[j]).getPower();
                        if (c == 72 || c == -72) {
                            c /= 72;
                        }
                        Monomial aux = new Monomial(c, p);
                        p2.addTerm(aux);
                        j++;
                    }
                    p2.sortTerms();
                    p2.eliminateTermsWithSamePower();
                    sum = addition.add(p1, p2);
                    view.displayResult(sum.toStringInteger());
                } else throw new IllegalArgumentException();
            } catch (IllegalArgumentException ex) {
                view.showError("Bad input!");
            }
        }
    }

    class SubstractionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2 = "";
            Polynomial p1 = new Polynomial(), p2 = new Polynomial(), difference = new Polynomial();
            Monomial m = new Monomial();
            try {
                userInput1 += view.getFirstPolynomial();
                userInput2 += view.getSecondPolynomial();
                if (validPolynomial(userInput1) && validPolynomial(userInput2)) {
                    String[] monomial1 = delimitMonomials(userInput1).split(" ");
                    String[] monomial2 = delimitMonomials(userInput2).split(" ");
                    int i = 0, j = 0;
                    while (i < monomial1.length) {
                        double c = m.convertFromString(monomial1[i]).getCoefficient();
                        int p = m.convertFromString(monomial1[i]).getPower();
                        if (c == 72 || c == -72) {
                            c /= 72;
                        }
                        Monomial aux = new Monomial(c, p);
                        p1.addTerm(aux);
                        i++;
                    }
                    p1.sortTerms();
                    p1.eliminateTermsWithSamePower();
                    while (j < monomial2.length) {
                        double c = m.convertFromString(monomial2[j]).getCoefficient();
                        int p = m.convertFromString(monomial2[j]).getPower();
                        if (c == 72 || c == -72) {
                            c /= 72;
                        }
                        Monomial aux = new Monomial(c, p);
                        p2.addTerm(aux);
                        j++;
                    }
                    p2.sortTerms();
                    p2.eliminateTermsWithSamePower();
                    difference = substraction.sub(p1, p2);
                    view.displayResult(difference.toStringInteger());
                } else throw new IllegalArgumentException();
            } catch (IllegalArgumentException ex) {
                view.showError("Bad input!");
            }
        }
    }

    class MultiplicationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2 = "";
            Polynomial p1 = new Polynomial(), p2 = new Polynomial(), product = new Polynomial();
            Monomial m = new Monomial();
            try {
                userInput1 += view.getFirstPolynomial();
                userInput2 += view.getSecondPolynomial();
                if (validPolynomial(userInput1) && validPolynomial(userInput2)) {
                    String[] monomial1 = delimitMonomials(userInput1).split(" ");
                    String[] monomial2 = delimitMonomials(userInput2).split(" ");
                    int i = 0, j = 0;
                    while (i < monomial1.length) {
                        double c = m.convertFromString(monomial1[i]).getCoefficient();
                        int p = m.convertFromString(monomial1[i]).getPower();
                        if (c == 72 || c == -72) {
                            c /= 72;
                        }
                        Monomial aux = new Monomial(c, p);
                        p1.addTerm(aux);
                        i++;
                    }
                    p1.sortTerms();
                    p1.eliminateTermsWithSamePower();
                    while (j < monomial2.length) {
                        double c = m.convertFromString(monomial2[j]).getCoefficient();
                        int p = m.convertFromString(monomial2[j]).getPower();
                        if (c == 72 || c == -72) {
                            c /= 72;
                        }
                        Monomial aux = new Monomial(c, p);
                        p2.addTerm(aux);
                        j++;
                    }
                    p2.sortTerms();
                    p2.eliminateTermsWithSamePower();
                    product = multiplication.multiplication(p1, p2);
                    product = product.eliminateTermsWithSamePower();
                    view.displayResult(product.toStringInteger());
                } else throw new IllegalArgumentException();
            } catch (IllegalArgumentException ex) {
                view.showError("Bad input!");
            }
        }
    }

    class DivisionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2 = "";
            Polynomial p1 = new Polynomial(), p2 = new Polynomial();
            List<Polynomial> result = new ArrayList<>();
            Monomial m = new Monomial();
            try {
                userInput1 += view.getFirstPolynomial();
                userInput2 += view.getSecondPolynomial();
                if (validPolynomial(userInput1) && validPolynomial(userInput2)) {
                    String[] monomial1 = delimitMonomials(userInput1).split(" ");
                    String[] monomial2 = delimitMonomials(userInput1).split(" ");
                    int i = 0, j = 0;
                    while (i < monomial1.length) {
                        double c = m.convertFromString(monomial1[i]).getCoefficient();
                        int p = m.convertFromString(monomial1[i]).getPower();
                        if (c == 72 || c == -72) {
                            c /= 72;
                        }
                        Monomial aux = new Monomial(c, p);
                        p1.addTerm(aux);
                        i++;
                    }
                    p1.sortTerms();
                    p1.eliminateTermsWithSamePower();
                    while (j < monomial2.length) {
                        double c = m.convertFromString(monomial2[j]).getCoefficient();
                        int p = m.convertFromString(monomial2[j]).getPower();
                        if (c == 72 || c == -72) {
                            c /= 72;
                        }
                        Monomial aux = new Monomial(c, p);
                        p2.addTerm(aux);
                        j++;
                    }
                    p2.sortTerms();
                    p2.eliminateTermsWithSamePower();
                    result = division.division(p1, p2);
                    view.displayResult("Q:" + result.get(0).toStringDouble() + "R:" + result.get(1).toStringDouble());
                } else throw new IllegalArgumentException();
            } catch (IllegalArgumentException ex) {
                view.showError("Bad input!");
            }
        }

    }

    class DerivationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            Polynomial p1 = new Polynomial(), d = new Polynomial();
            Monomial m = new Monomial();
            try {
                userInput1 += view.getFirstPolynomial();
                if (validPolynomial(userInput1)) {
                    String delimited1 = delimitMonomials(userInput1);
                    String[] monomial1 = delimited1.split(" ");
                    int i = 0;
                    while (i < monomial1.length) {
                        double c = m.convertFromString(monomial1[i]).getCoefficient();
                        int p = m.convertFromString(monomial1[i]).getPower();
                        if (c == 72 || c == -72) {
                            c /= 72;
                        }
                        Monomial aux = new Monomial(c, p);
                        p1.addTerm(aux);
                        i++;
                    }
                    p1.sortTerms();
                    p1.eliminateTermsWithSamePower();
                    d = derivation.derivation(p1);
                    view.displayResult(d.toStringInteger());
                } else throw new IllegalArgumentException();
            } catch (IllegalArgumentException ex) {
                view.showError("Bad input!");
            }
        }
    }

    class UndefinedIntegrationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            Polynomial p1 = new Polynomial(), UI = new Polynomial();
            Monomial m = new Monomial();
            try {
                userInput1 += view.getFirstPolynomial();
                if (validPolynomial(userInput1)) {
                    String delimited1 = delimitMonomials(userInput1);
                    String[] monomial1 = delimited1.split(" ");
                    int i = 0;
                    while (i < monomial1.length) {
                        double c = m.convertFromString(monomial1[i]).getCoefficient();
                        int p = m.convertFromString(monomial1[i]).getPower();
                        if (c == 72 || c == -72) {
                            c /= 72;
                        }
                        Monomial aux = new Monomial(c, p);
                        p1.addTerm(aux);
                        i++;
                    }
                    p1.sortTerms();
                    p1.eliminateTermsWithSamePower();
                    UI = undefinedIntegration.undefined_integration(p1);
                    view.displayResult(UI.toStringDouble() + "+C");
                } else throw new IllegalArgumentException();
            } catch (IllegalArgumentException ex) {
                view.showError("Bad input!");
            }
        }
    }


}
