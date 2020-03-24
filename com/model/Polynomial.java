package com.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class Polynomial {

    private List<Monomial> terms = new ArrayList<Monomial>();


    public void addTerm(Monomial term) {
        terms.add(term);
    }

    public List<Monomial> getTerms() {
        return terms;
    }

    public int numberOfTerms() {
        int nr = 0;
        for (Monomial m : terms)
            nr++;
        return nr;
    }

    public Monomial getTerm(int t) {
        return terms.get(t);
    }

    // We sort the terms of the polynomial after the the highest power
    public void sortTerms() {
        int n = numberOfTerms();
        Monomial aux = new Monomial();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++) {
                if (getTerm(j).getPower() < getTerm(j + 1).getPower()) {
                    aux.setPower(getTerm(j).getPower());
                    aux.setCoefficient(getTerm(j).getCoefficient());

                    getTerm(j).setCoefficient(getTerm(j + 1).getCoefficient());
                    getTerm(j).setPower(getTerm(j + 1).getPower());

                    getTerm(j + 1).setCoefficient(aux.getCoefficient());
                    getTerm(j + 1).setPower(aux.getPower());

                }
            }
    }

    public double calculatePolynomial(int x) {
        double value = 0;
        for (Monomial m : terms)
            value += pow(x, m.getPower()) * m.getCoefficient();
        return value;
    }

    /**
     * We check to see if the polynomial has monomials with the same power.This happens in case of multiplications or if the user introduced
     * two monomials with the same power in that case we will add the coefficients and keep only one term with that power.
     **/

    public Polynomial eliminateTermsWithSamePower() {
        AdditionSubstraction a = new AdditionSubstraction();
        Polynomial aux1 = new Polynomial();
        Polynomial aux2 = new Polynomial();
        Polynomial result;
        Monomial m;
        int i = 0;
        int j = numberOfTerms() / 2;
        int nP = numberOfTerms();
        while (i < j) {
            double c = getTerm(i).getCoefficient();
            int pow = getTerm(i).getPower();
            m = new Monomial(c, pow);
            aux1.addTerm(m);
            aux1.sortTerms();
            i++;
        }
        while (j < nP) {
            double c = getTerm(j).getCoefficient();
            int pow = getTerm(j).getPower();
            m = new Monomial(c, pow);
            aux2.addTerm(m);
            aux2.sortTerms();
            j++;
        }
        result = a.add(aux1, aux2);
        return result;
    }

    public int greatestPower() {
        sortTerms();
        return getTerm(0).getPower();
    }

    public String toStringInteger() {
        String result = "";
        if (numberOfTerms() == 1 && getTerm(0).getCoefficient() == 0) {
            result += "0";
        } else {
            for (Monomial i : terms)
                result += i.convertToStringWithIntegerCoefficients();
        }
        return result;
    }

    public String toStringDouble() {
        String result = "";
        for (Monomial i : terms)
            result += i.convertToStringWithDoubleCoefficients();

        return result;
    }

}
