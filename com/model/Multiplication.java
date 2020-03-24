package com.model;

import java.awt.*;

public class Multiplication {
    public Polynomial multiplication(Polynomial p1, Polynomial p2) {
        Monomial m;
        Polynomial product = new Polynomial();
        Polynomial result;
        int i = 0;
        int j;
            int nrP1 = p1.numberOfTerms();
            int nrP2 = p2.numberOfTerms();
            while (i < nrP1) {
                double c1 = p1.getTerm(i).getCoefficient();
                int pow1 = p1.getTerm(i).getPower();
                i++;
                j = 0;
                while (j < nrP2) {
                    double c2 = p2.getTerm(j).getCoefficient();
                    int pow2 = p2.getTerm(j).getPower();
                    m = new Monomial(c1 * c2, pow1 + pow2);
                    product.addTerm(m);
                    j++;
                }
            }
        product.sortTerms();
        result= product.eliminateTermsWithSamePower();
        return result;
    }
}
