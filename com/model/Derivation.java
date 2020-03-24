package com.model;

public class Derivation {
    public Polynomial derivation(Polynomial p) {
        Polynomial derived = new Polynomial();
        Monomial d;
        int i = 0;
        int nrP = p.numberOfTerms();
            while (i < nrP) {
                double c = p.getTerm(i).getCoefficient();
                int pow = p.getTerm(i).getPower();
                if (pow != 0) {
                    d = new Monomial(c * pow, pow - 1);
                    derived.addTerm(d);
                    i++;
                }else if(pow==0){i++;}
                if(pow==0 && nrP==1){
                    d=new Monomial(0,0);
                    derived.addTerm(d);
                }
            }
        return derived;
    }
}
