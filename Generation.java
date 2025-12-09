import java.util.ArrayList;
import tabular.*;
import java.util.Random;
import java.util.Arrays;

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
        for (GPTree tree: trees) {
            tree.evalFitness(dataset);
        }
        Arrays.sort(trees);
    }

    public ArrayList<GPTree> getTopTen() {
        ArrayList<GPTree> topTen = new ArrayList<>();
        int limit = Math.min(trees.length, 10);

        for (int i=0;i<limit;i++) {
            topTen.add(trees[i]);
        }
        return topTen;
    }

    public void printBestFitness() {

    }

    public void printBestTree() {

    }

    public void evolve() {

    }
}
