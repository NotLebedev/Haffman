package LexicalTree;

import java.util.ArrayList;

public interface Node extends Comparable<Node> {

    Integer getWeight();
    void updateWeight();

    void setParent(Node parent);

    ArrayList<Boolean> getSelfCode(Node self);
    void recacheSelfCode();

    @Override
    int compareTo(Node o);

    String toString();

}
