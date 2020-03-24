package com.model;

public class AdditionSubstraction {

    public Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial sum = new Polynomial();
        Monomial s;
        int nrP1 = p1.numberOfTerms();
        int nrP2 = p2.numberOfTerms();
        int i = 0;
        int j = 0;
        while (i < nrP1 && j < nrP2) {
            double c1 = p1.getTerm(i).getCoefficient();
            int pow1 = p1.getTerm(i).getPower();
            double c2 = p2.getTerm(j).getCoefficient();
            int pow2 = p2.getTerm(j).getPower();
            if (pow1 == pow2) {
                s = new Monomial(c1 + c2, pow1);
                i++;
                j++;
            } else if (pow1 > pow2) {
                s = new Monomial(c1, pow1);
                i++;
            } else {
                s = new Monomial(c2, pow2);
                j++;
            }
            sum.addTerm(s);
        }
        while (i < nrP1) {
            double c1 = p1.getTerm(i).getCoefficient();
            int pow1 = p1.getTerm(i).getPower();
            s = new Monomial(c1, pow1);
            i++;
            sum.addTerm(s);
        }
        while (j < nrP2) {
            double c2 = p2.getTerm(j).getCoefficient();
            int pow2 = p2.getTerm(j).getPower();
            s = new Monomial(c2, pow2);
            j++;
            sum.addTerm(s);
        }
        return sum;
    }

    public Polynomial sub(Polynomial p1, Polynomial p2) {
        Polynomial difference = new Polynomial();
        Monomial d;
        int nrP2 = p2.numberOfTerms();
        int i = 0;
        while (i < nrP2) {
            double c = p2.getTerm(i).getCoefficient() * (-1);
            int pow = p2.getTerm(i).getPower();
            d = new Monomial(c, pow);
            i++;
            difference.addTerm(d);
        }
        return add(p1, difference);

    }

}
