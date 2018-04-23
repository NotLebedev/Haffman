package Lexical;

import java.util.ArrayList;

public class StaticCompressor {

    public static ArrayList<Boolean> compress(StaticLexicalTree staticLexicalTree, String string) {

        ArrayList<Boolean> result = new ArrayList<>();

        for (char c : string.toCharArray()) {
            result.addAll(staticLexicalTree.getCharacterCode(c));
        }

        return result;

    }

    public static String decompress(StaticLexicalTree staticLexicalTree, ArrayList<Boolean> input) {

        Node curNode = staticLexicalTree.getVertex();
        StringBuilder str = new StringBuilder();

        for (Boolean aBoolean : input) {

            curNode = ((EmptyNode)curNode).getChild(aBoolean);

            if(curNode instanceof CharacterNode) {
                str.append(((CharacterNode)curNode).getSymbol());
                curNode = staticLexicalTree.getVertex();
            }

        }

        return str.toString();

    }

}
