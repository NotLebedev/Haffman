package Lexical;

import java.util.ArrayList;

public interface Node extends Comparable<Node> {

    Integer getWeight();
    void updateWeight();

    void setParent(Node parent);
    Node getParent();

    ArrayList<Boolean> getSelfCode(Node self);
    void recacheSelfCode();

    @Override
    int compareTo(Node o);

    String toString();

}
