package tabular;
/**
 * Author: Max Prigent
 * Date: 10/2/25
 * Purpose: Object to hold all DataRows (rows of data from csv)
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DataSet {
    private int numIndepVariables;
    private ArrayList<DataRow> data;

    /**
     * @param filename the name of the file to read the data set from
     */
    public DataSet(String filename)
    {
        //create File object for "filename"
        File file = new File(filename);
         
        //try to run if file is valid
        try (Scanner in = new Scanner(file)) {
            String line = in.nextLine();//read first line of file
            String[] lineArray = line.split(",");//store first line in array
            int size = lineArray.length;//store first line size for error checking
            numIndepVariables = size-1;//initialize numIndepVariables
            data = new ArrayList<>();

            String str;//stores line from file
            String[] strArray;//stores line split into array

            while (in.hasNext()) {
                str = in.nextLine();
                strArray = str.split(",");

                if (strArray.length == size) {
                    //set y to first element in row
                    double y = Double.parseDouble(strArray[0]);

                    //create array for other elements
                    double[] x = new double[size-1];//size-1 since first element is already stored in y

                    //iterate through String array and add elements to double array
                    for (int i=1;i<strArray.length;i++) {//start at [1] since [0] is y
                        x[i-1] = Double.parseDouble(strArray[i]);
                    }

                    data.add(new DataRow(y, x));//add DataRow to arrayList
                }
            }
        } catch (FileNotFoundException e) { //if file is invalid
            System.out.println("ERROR: File not found");
        }
    }

    /**
     * @return the list of rows
     */
    public ArrayList<DataRow> getRows() {
        return data;
    }

    /**
     * @return the number of independent variables in each row of the data set
     */
    public int getNumIndependentVariables() {
        return numIndepVariables;
    }
}
