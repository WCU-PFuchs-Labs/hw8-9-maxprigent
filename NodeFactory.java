import java.util.Random;

public class NodeFactory {
    private int numIndepVars;
    private Node[] currentOps;

    public NodeFactory(Binop[] binops, int numVars) {
        numIndepVars = numVars;

        //creates a new node for each binop and adds to Node array
        Node[] nodeArray = new Node[4];

        for (int i=0;i<binops.length;i++) {
            nodeArray[i] = new Node(binops[i]);
        }
        currentOps = nodeArray;
    }

    public Node getOperator(Random rand) {
        int index = rand.nextInt(currentOps.length);
        return (Node) currentOps[index].clone();
        //cast Op clone to Node type
    }
    public int getNumOps() {
        return currentOps.length;
    }
    public Node getTerminal(Random rand) {
        int randNum = rand.nextInt(numIndepVars+1);
        Node n1;

        if (randNum == numIndepVars) {
            n1 = new Node(new Variable(randNum));
        }
        else {
            n1 = new Node(new Const(rand.nextDouble()));
        }

        return n1;
    }
    public int getNumIndepVars() {
        return numIndepVars;
    }
}
