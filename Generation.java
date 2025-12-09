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

        //get length of x values of first row of dataset
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
        System.out.println("Best Fitness: " + trees[0].getFitness());
    }

    public void printBestTree() {
        System.out.println("Best Tree: " + trees[0]);
    }

    public void evolve() {
        GPTree[] newTrees = new GPTree[trees.length];
        evalAll();

        for (int i=0;i<trees.length;i++) {
            GPTree parent1 = trees[rand.nextInt(trees.length/2)];
            GPTree parent2 = trees[rand.nextInt(trees.length/2)];
            GPTree child1 = (GPTree) parent1.clone();
            GPTree child2 = (GPTree) parent2.clone();

            child1.crossover(child2, rand);
        }
    }
}
