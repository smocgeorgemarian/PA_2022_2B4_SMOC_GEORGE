package test;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class TestChocoSolver {
    public static void setResultsPrintable() {
        Model model = new Model("Choco Solver");

        // Variables and their domains: x, y
        IntVar x = model.intVar("x", 1, 6); //takes value in [1,6]
        IntVar y = model.intVar("y", 1, 6);
        // Constraints: x + y = 7
        model.arithm(x, "+", y, "=", 7).post();
        // Computes all solutions
        int count = 1;
        while (model.getSolver().solve()) {
            System.out.println("Solution " + count++ + ": " + x + ", " + y);
        }
    }
}
