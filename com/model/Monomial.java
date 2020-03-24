package com.model;

public class Monomial {

    private double coefficient;
    private int power;

    public Monomial(double coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public Monomial() {

    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }

    public Monomial monomialDivision(Monomial x) {
        Monomial divided = new Monomial(this.coefficient / x.getCoefficient(), this.power - x.getPower());
        return divided;
    }

    public Monomial convertFromString(String s) {
        int p = (int) (s.charAt(s.length() - 1) - '0');
        if (s.matches("([-+])*(\\d+)*[x][\\^](\\d+)")) {
            if (s.charAt(0) == '+') {
                setCoefficient(s.charAt(1) - '0');
            } else if (s.charAt(0) == '-') {
                setCoefficient(-(s.charAt(1) - '0'));
            } else if (s.charAt(0) == 'x') setCoefficient(1.0);
            else if (s.charAt(0) == '-' && s.charAt(1) == 'x') setCoefficient(-1.0);
            else if(s.charAt(1)!='x' && s.charAt(0)!='+')setCoefficient(s.charAt(0) - '0');
            setPower(p);
        } else if (s.matches("([-+])*[x][\\^](\\d+)")) {
            setPower(p);
            if (s.charAt(0) == '+' || s.charAt(0) == 'x') setCoefficient(1.0);
            else if (s.charAt(0) == '-') setCoefficient(-1.0);
            else setCoefficient(1.0);
            setPower(p);
        } else if (s.matches("([-+])*(\\d+)*[x]")) {
            if (s.charAt(0) == '+') {
                setCoefficient(s.charAt(1) - '0');
            } else if (s.charAt(0) == '-' && s.charAt(1)!='x') {
                setCoefficient(-(s.charAt(1) - '0'));
            } else if (s.charAt(0) == 'x') setCoefficient(1.0);
            else setCoefficient(s.charAt(0) - '0');
            setPower(1);
        } else if (s.matches("([-+])*(\\d)")) {
            if (s.charAt(0) == '+') setCoefficient(s.charAt(1) - '0');
            else if (s.charAt(0) == '-') setCoefficient(-(s.charAt(1) - '0'));
            else setCoefficient(s.charAt(0) - '0');
            setPower(0);
        } else if (s.matches("([-+])*[x]")) {
            if (s.charAt(0) == '+' || s.charAt(0) == 'x') setCoefficient(1.0);
            else if (s.charAt(0) == '-' && s.charAt(1) == 'x') setCoefficient(-1.0);
            setPower(1);
        }
        return this;
    }

    public String convertToStringWithIntegerCoefficients() {
        String result = "";
        if (this.coefficient > 0) {
            if (this.coefficient != 1) {
                if (this.power == 1) {
                    result += "+" + (int) this.coefficient + "x";
                } else if (this.power == 0) {
                    result += "+" + (int) this.coefficient;
                } else result += "+" + (int) this.coefficient + "x^" + this.power;
            } else if (this.coefficient == 1) {
                if (this.power == 1) {
                    result += "+x";
                } else if (this.power == 0) {
                    result += "+" + (int) this.coefficient;
                } else result += "+x^" + this.power;
            }
        } else if (this.coefficient < 0) {
            if (this.coefficient != -1) {
                if (this.power == 1) {
                    result += (int) this.coefficient + "x";
                } else if (this.power == 0) {
                    result += (int) this.coefficient;
                } else result += (int) this.coefficient + "x^" + this.power;
            } else if (this.coefficient == -1) {
                if (this.power == 1) {
                    result += "-x";
                } else if (this.power == 0) {
                    result += (int) this.coefficient;
                } else result +=  "-x^" + this.power;
            }
        }
        return result;
    }

    public String convertToStringWithDoubleCoefficients() {
        String result = "";
        if (this.coefficient > 0) {
            if (this.coefficient != 1) {
                if (this.power == 1) {
                    result += "+" +  this.coefficient + "x";
                } else if (this.power == 0) {
                    result += "+" + this.coefficient;
                } else result += "+" +  this.coefficient + "x^" + this.power;
            } else if (this.coefficient == 1) {
                if (this.power == 1) {
                    result += "+x";
                } else if (this.power == 0) {
                    result += "+" +  this.coefficient;
                } else result += "+x^" + this.power;
            }
        } else if (this.coefficient < 0) {
            if (this.coefficient != -1) {
                if (this.power == 1) {
                    result +=  this.coefficient + "x";
                } else if (this.power == 0) {
                    result += this.coefficient;
                } else result +=  this.coefficient + "x^" + this.power;
            } else if (this.coefficient == -1) {
                if (this.power == 1) {
                    result += "-x";
                } else if (this.power == 0) {
                    result += this.coefficient;
                } else result +=  "-x^" + this.power;
            }
        }
        return result;
    }
}
