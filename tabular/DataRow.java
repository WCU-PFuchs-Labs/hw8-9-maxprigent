package tabular;

/**
 * Author: Max Prigent
 * Date: 10/2/25
 * Purpose: Object to store 1 row of data from a csv file
 */


public class DataRow {
    private double y;
    private double[] x;

    /**
     * @param y the dependent variable
     * @param x the array of independent variables
     */
    public DataRow(double y, double[] x)
    {
        this.y = y;
        this.x = x;
    }

    /**
     * @return the dependent variable
     */
    public double getDependentVariable() {
        return y;
    }

    /**
     * @return the array of independent variables
     */
    public double[] getIndependentVariables() {
        return x;
    }
}
