package Lexical;

import java.util.ArrayList;
import java.util.Collections;

public class TreeBuilder {

    private static ArrayList<CharacterNode> count(String str) {

        ArrayList<CharacterNode> chars = new ArrayList<>();

        for (char c : str.toCharArray()) {

            CharacterNode symbol = null;

            for (CharacterNode node : chars) {

                if(node.getSymbol().compareTo(c) == 0){

                    symbol = node;
                    break;

                }

            }

            if(symbol == null) {
                chars.add(new CharacterNode(c, 1));
            }else {
                symbol.incrementWeight();
            }

        }

        return chars;

    }

    public static LexicalTree buildTree(String str) {

        ArrayList<CharacterNode> input = count(str);

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.addAll(input);
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

        LexicalTree lexicalTree = new LexicalTree(nodes.get(0), input);

        return lexicalTree;

    }

}
