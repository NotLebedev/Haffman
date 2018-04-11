import java.util.ArrayList;

public class CharacterNode implements Node {

    private Integer weight;

    private Node parent;
    private ArrayList<Boolean> selfCode;

    private Character symbol;

    public CharacterNode(Character symbol, Integer weight) {

        this.weight = weight;

        this.parent = parent;
        this.symbol = symbol;

    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void incrementWieght() {

        weight ++;

    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public ArrayList<Boolean> getSelfCode(Node self) {

        if(parent == null) {
            return null;
        }else {
            return parent.getSelfCode(this);
        }

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
    public void updateWeight() {}

    public Character getSymbol() {
        return symbol;
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
        return "CharacterNode{" +
                "weight=" + weight +
                ", symbol='" + symbol +
                "'}";
    }
}
