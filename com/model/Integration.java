package com.model;

import java.lang.Math;
import java.text.DecimalFormat;

public class Integration {
    public Polynomial undefined_integration(Polynomial p) {
        Polynomial integrated = new Polynomial();
        Monomial i;
        int j = 0;
        int nrP = p.numberOfTerms();
        while (j < nrP) {
            double c = p.getTerm(j).getCoefficient();
            int pow = p.getTerm(j).getPower();
            i = new Monomial(roundTwoDecimals(c / (pow + 1)), pow + 1);
            integrated.addTerm(i);
            j++;
        }
        return integrated;
    }

    public double defined_integration(Polynomial p, int a, int b) {
        double value, A = 0, B = 0;
        Polynomial aux = undefined_integration(p);
        A = aux.calculatePolynomial(a);
        B = aux.calculatePolynomial(b);
        value = B - A;
        return roundTwoDecimals(value);

    }

    public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

}