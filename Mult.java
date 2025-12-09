/*
 * Author: Max Prigent
 * Purpose: A binary operator for Multiplication
*/

public class Mult extends Binop{
    public double eval(double left, double right) {
        return left*right;
    }

    public String toString() {
        return "*";
    }
}
