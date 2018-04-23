package Lexical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class DynamicLexicalTree extends StaticLexicalTree {

    private BuilderNode builderNode;
    private CharacterNode eofNode;

    public DynamicLexicalTree() {

        builderNode = new BuilderNode();
        eofNode = new CharacterNode(null, 1);

        vertex = new EmptyNode(eofNode, builderNode);

        builderNode.setParent(vertex);
        eofNode.setParent(vertex);

        vertex.updateWeight();
        vertex.recacheSelfCode();

        chars = new ArrayList<>();
        charsHash = new HashMap<>();

    }

    public void processChar(Character c) {

        CharacterNode characterNode = charsHash.get(c);

        if(characterNode == null) {

            characterNode = new CharacterNode(c, 1);
            charsHash.put(c, characterNode);
            chars.add(characterNode);
            builderNode.build(characterNode);

            vertex.updateWeight();

        }else {
            incrementWeight(c);
        }

    }

    private void incrementWeight(Character c) {

        CharacterNode incrementedNode = charsHash.get(c);

        incrementedNode.setWeight(incrementedNode.getWeight() + 1);

        vertex.updateWeight();

        Boolean ordered = true;

        for (int i = 1; i < chars.size(); i++) {

            Integer currentWeight = chars.get(i).getWeight();
            Integer prevWeight = chars.get(i - 1).getWeight();

            Integer currentCodeLenght = chars.get(i).getSelfCode().size();
            Integer prevCodeLenght = chars.get(i - 1).getSelfCode().size();

            if(!((currentWeight >= prevWeight && currentCodeLenght <= prevCodeLenght) || (currentWeight < prevWeight && currentCodeLenght >= prevCodeLenght))) {
                ordered = false;
                break;
            }

        }

        if(ordered) {
            return;
        }

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.addAll(chars);

        nodes.add(eofNode);
        nodes.add(builderNode);

        vertex = StaticTreeBuilder.buildTree(nodes);
        vertex.recacheSelfCode();

        /*Collections.sort(chars);
        Integer incrementedNodeId = chars.indexOf(incrementedNode);

        incrementedNode.setWeight(incrementedNode.getWeight() + 1);

        CharacterNode swapNode = null;
        Integer swapNodeId = null;

        for (int i = incrementedNodeId - 1; i >= 0; i--) {

            if(Objects.equals(chars.get(i).getWeight(), incrementedNode.getWeight())) {

                swapNode = chars.get(i);
                swapNodeId = i;

                break;

            }

        }

        if(swapNode == null) {
            return;
        }

        Collections.swap(chars, incrementedNodeId, swapNodeId);

        Node swapNodeParent = swapNode.getParent();
        Node incrementedNodeParent = incrementedNode.getParent();

        Boolean swapNodeChildId = ((EmptyNode)swapNodeParent).getChild(true).equals(swapNode);
        Boolean incrementedNodeChildId = ((EmptyNode)incrementedNodeParent).getChild(true).equals(incrementedNode);

        ((EmptyNode) swapNodeParent).setChild(swapNodeChildId, incrementedNode);
        ((EmptyNode) incrementedNodeParent).setChild(incrementedNodeChildId, swapNode);

        swapNode.setParent(incrementedNodeParent);
        incrementedNode.setParent(swapNodeParent);

        vertex.updateWeight();
        vertex.recacheSelfCode();*/

    }

    public ArrayList<Boolean> getBuilderNodeCode() {

        return builderNode.getSelfCode();

    }

}
