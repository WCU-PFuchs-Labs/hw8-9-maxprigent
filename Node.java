import java.util.Random;
//import ./Collector;

/**
 * Author: Max Prigent
 * Code Template Author: David G. Cooper
 * Purpose: A Binary Tree class for Arithmetic evaluation
 */
public class Node implements Cloneable{
    private Node left;
    private Node right;
    private Op operation;
    protected int depth;

    public Node(Unop operation) {
        depth = 0;
        this.operation = operation;
    }
    public Node(Binop operation,Node left, Node right) {
        depth = 0;
        this.left = left;
        this.left.depth = depth + 1;
        this.right = right;
        this.right.depth = depth + 1;
        this.operation = operation;
    }
    public Node(Binop operation) {
        depth = 0;
        this.operation = operation;
    }

    public double eval(double[] values) {
        if (operation instanceof Unop) {
              return ((Unop)operation).eval(values);
        } else if (operation instanceof Binop) {
              return ((Binop)operation).eval(left.eval(values),right.eval(values));
        } else {
              System.err.println("Error operation is not a Unop or a Binop!");
              return 0.0;
        }
    }

    public String toString() {
        //if both children are null,
        //return "(" + left.toString() + operation.toString() + right.toString() + ")";

        if (operation instanceof Const) {
            double[] values = {0};
            return String.valueOf(((Const)operation).eval(values));
        }
        else if (operation instanceof Variable) {
            return operation.toString();
        }
        else if (left != null && right != null) {
            return "("+left.toString()+" "+operation.toString()+" "+right.toString()+")";
        }
        else {
            return "";
        }

    }

    public Object clone() {
        Object o = null;
        try {
            o =  super.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("Op can't clone.");
        }
        Node b = (Node) o;
        if(left != null) {
            b.left = (Node) left.clone();
        }
        if(right != null) {
        b.right = (Node) right.clone();
        }
        if(operation != null) {
        b.operation = (Op) operation.clone();
        }
        return o;
    }

    public void addRandomKids(NodeFactory factory, int maxDepth, Random rand) {
        while (true) {
            if (depth == maxDepth) {
                left = factory.getTerminal(rand);
                left.depth = depth + 1;
                right = factory.getTerminal(rand);
                right.depth = depth + 1;

                return;
            }
            else {
                int randNum = rand.nextInt(factory.getNumOps()+factory.getNumIndepVars()+1);
                
                if (randNum < factory.getNumOps()) {
                    left = factory.getOperator(rand);
                    left.depth = depth + 1;
                    left.addRandomKids(factory, maxDepth, rand);

                    right = factory.getOperator(rand);
                    right.depth = depth + 1;
                    right.addRandomKids(factory, maxDepth, rand);
                }
                else {
                    left = factory.getTerminal(rand);
                    left.depth = depth + 1;

                    right = factory.getTerminal(rand);
                    right.depth = depth + 1;
                }

                return;
            }
        }
    }

    public void traverse(Collector c) {
        c.collect(this);
        if (left != null) {
            left.traverse(c);
        }
        if (right != null) {
            right.traverse(c);
        }
        return;
    }

    public boolean isLeaf() {
        if (operation instanceof Unop) {
            return true;
        }
        else {
            return false;
        }
    }

    public void swapLeft(Node trunk) {
        Node temp = left;
        left = trunk.left;
        trunk.left = temp;
    }

    public void swapRight(Node trunk) {
        Node temp = right;
        right = trunk.right;
        trunk.right = temp;
    }
}
