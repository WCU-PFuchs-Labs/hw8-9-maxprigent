import java.lang.Math;
/*
 * Author: Max Prigent
 * Purpose: A binary operator for Division
*/

public class Divide extends Binop {
    public double eval(double left, double right) {
        if (Math.abs(right) < 0.0001) {
            return 1.0;
        }
        else {
            return left/right;
        }
    }

    public String toString() {
        return "/";
    }
}
