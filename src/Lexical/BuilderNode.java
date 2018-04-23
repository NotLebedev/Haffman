package Lexical;

import java.util.ArrayList;

public class BuilderNode implements Node {

    private Node parent;
    private ArrayList<Boolean> selfCode;

    public void build(Node node) {

        EmptyNode emptyNode = new EmptyNode(node, this);

        Boolean childId = getSelfCode().get(getSelfCode().size() - 1);

        ((EmptyNode)parent).setChild(childId, emptyNode);
        emptyNode.setParent(parent);

        node.setParent(emptyNode);
        setParent(emptyNode);

        parent.recacheSelfCode();

    }

    @Override
    public Integer getWeight() {
        return 1;
    }

    @Override
    public void updateWeight() {

    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public ArrayList<Boolean> getSelfCode(Node self) {
        return getSelfCode();
    }

    public ArrayList<Boolean> getSelfCode() {

        if(parent == null) {
            return null;
        }else {

            if(selfCode == null) {
                selfCode = parent.getSelfCode(this);
            }

            return new ArrayList<>(selfCode);

        }

    }

    @Override
    public void recacheSelfCode() {
        selfCode = null;
    }

    @Override
    public int compareTo(Node o) {

        if(o.getWeight() > 1) {
            return 1;
        }else {
            return 0;
        }

    }
}
