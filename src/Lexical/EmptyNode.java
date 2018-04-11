package Lexical;

import java.util.ArrayList;

public class EmptyNode implements Node {

    private Integer weight;

    private Node parent;
    private ArrayList<Boolean> selfCode;

    private Node child0;
    private Node child1;

    public EmptyNode(Node child0, Node child1) {

        weight = 0;

        this.child0 = child0;
        this.child1 = child1;

    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public void updateWeight() {

        child0.updateWeight();
        child1.updateWeight();

        weight = child0.getWeight() + child1.getWeight();

    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public ArrayList<Boolean> getSelfCode(Node self) {

        if(selfCode == null) {

            if (parent == null) {
                selfCode = new ArrayList<>();
            } else {
                selfCode = parent.getSelfCode(this);
            }

        }

        ArrayList<Boolean> code = new ArrayList<>(selfCode);

        if (self == child0) {
            code.add(false);
        } else {
            code.add(true);
        }

        return code;

    }

    @Override
    public void recacheSelfCode() {

        selfCode = null;

        child0.recacheSelfCode();
        child1.recacheSelfCode();

    }

    @Override
    public int compareTo(Node o) {

        if(((Node)o).getWeight() > this.weight) {
            return 1;
        }else if(((Node)o).getWeight() < this.weight) {
            return -1;
        }else {
            return 0;
        }

    }

    @Override
    public String toString() {
        return "Lexical.EmptyNode{" +
                "weight=" + weight +
                '}';
    }
}
