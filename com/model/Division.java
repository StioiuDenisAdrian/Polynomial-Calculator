package com.model;

import java.util.ArrayList;
import java.util.List;

public class Division {
    public List<Polynomial> division(Polynomial p1, Polynomial p2) {
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();
        Polynomial aux = new Polynomial();
        List<Polynomial> result = new ArrayList<>();
        Multiplication mul = new Multiplication();
        AdditionSubstraction sub = new AdditionSubstraction();
        p1.sortTerms();
        p2.sortTerms();
        while (p1.getTerm(0).getPower()>p2.getTerm(0).getPower()) {
            quotient.addTerm(p1.getTerm(0).monomialDivision(p2.getTerm(0)));
            aux = mul.multiplication(quotient, p2);
            p1 = sub.sub(p1, aux);
            p1.sortTerms();
            if (p1.greatestPower() < p2.greatestPower()) {
                for (Monomial m : p1.getTerms())
                    remainder.addTerm(m);

            }
        }
        result.add(quotient);
        result.add(remainder);
        return result;
    }
}