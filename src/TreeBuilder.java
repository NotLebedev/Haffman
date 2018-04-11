import java.util.ArrayList;
import java.util.Collections;

public class TreeBuilder {

    private ArrayList<Node> nodes;

    public Node buildTree(ArrayList<Node> input) {

        nodes = input;
        Collections.sort(nodes);

        while (nodes.size() > 1) {

            Node node1 = nodes.remove(nodes.size() - 1);
            Node node0 = nodes.remove(nodes.size() - 1);

            EmptyNode emptyNode = new EmptyNode(node0, node1);
            node0.setParent(emptyNode);
            node1.setParent(emptyNode);

            emptyNode.updateWeight();

            int index = Collections.binarySearch(nodes, emptyNode);

            if (index >= 0) {
                nodes.add(index, emptyNode);
            } else {
                nodes.add(-(index + 1), emptyNode);
            }

        }

        return nodes.get(0);

    }

}
