
import com.controller.Controller;
import com.model.*;
import com.view.Calculator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AdditionSubstraction addition = new AdditionSubstraction();
        AdditionSubstraction substraction = new AdditionSubstraction();
        Integration undefinedIntegration = new Integration();
        Multiplication multiplication = new Multiplication();
        Division division = new Division();
        Derivation derivation = new Derivation();
        Calculator view = new Calculator(addition, substraction, undefinedIntegration, multiplication, derivation,division);

        Controller controller = new Controller(addition, substraction, undefinedIntegration, multiplication, derivation, division, view);


    }
}
