package Lexical;

import java.util.ArrayList;
import java.util.HashMap;

public class LexicalTree {

    private Node vertex;

    private ArrayList<CharacterNode> chars;
    private HashMap<Character, CharacterNode> charsHash;

    LexicalTree(Node vertex, ArrayList<CharacterNode> chars) {
        this.vertex = vertex;
        this.chars = chars;

        charsHash = new HashMap<>();
        for (CharacterNode node : chars) {
            charsHash.put(node.getSymbol(), node);
        }

    }

    ArrayList<Boolean> getCharacterCode(Character character) {
        return charsHash.get(character).getSelfCode();
    }

    Node getVertex() {
        return vertex;
    }
}
