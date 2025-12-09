import java.util.ArrayList;
import tabular.*;
import java.util.Random;

public class Generation {
    private GPTree[] trees;
    private DataSet dataset;
    private Random rand;
    private NodeFactory factory;

    public Generation(int size, int maxDepth, String filename) {
        dataset = new DataSet(filename);//create dataset
        rand = new Random();//create rand

        //create factory
        Binop[] binops = {new Plus(), new Minus(), new Mult(), new Divide()};
        int numVars = dataset.getRows().get(0).getIndependentVariables().length;
        factory = new NodeFactory(binops, numVars);

        //create trees
        trees = new GPTree[size];
        for (int i=0; i<size; i++) {
            trees[i] = new GPTree(factory, maxDepth, rand);
        }
    }

    public void evalAll() {

    }

    public ArrayList<GPTree> getTopTen() {

    }

    public void printBestFitness() {

    }

    public void printBestTree() {

    }

    public void evolve() {

    }
}
