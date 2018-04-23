package Lexical;

import java.util.ArrayList;
import java.util.HashMap;

public class StaticLexicalTree {

    Node vertex;

    ArrayList<CharacterNode> chars;
    HashMap<Character, CharacterNode> charsHash;

    public StaticLexicalTree() {
    }

    StaticLexicalTree(Node vertex, ArrayList<CharacterNode> chars) {
        this.vertex = vertex;
        this.chars = chars;

        charsHash = new HashMap<>();
        for (CharacterNode node : chars) {
            charsHash.put(node.getSymbol(), node);
        }

    }

    public ArrayList<Boolean> getCharacterCode(Character character) {
        return charsHash.get(character).getSelfCode();
    }

    public Boolean hasChar(Character c) {

        return charsHash.get(c) != null;

    }

    public Node getVertex() {
        return vertex;
    }

}
