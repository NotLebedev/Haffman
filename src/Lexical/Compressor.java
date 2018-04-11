package Lexical;

import java.util.ArrayList;

public class Compressor {

    public static ArrayList<Boolean> compress(LexicalTree lexicalTree, String string) {

        ArrayList<Boolean> result = new ArrayList<>();

        for (char c : string.toCharArray()) {
            result.addAll(lexicalTree.getCharacterCode(c));
        }

        return result;

    }

    public static String decompress(LexicalTree lexicalTree, ArrayList<Boolean> input) {

        Node curNode = lexicalTree.getVertex();
        StringBuilder str = new StringBuilder();

        for (Boolean aBoolean : input) {

            curNode = ((EmptyNode)curNode).getChild(aBoolean);

            if(curNode instanceof CharacterNode) {
                str.append(((CharacterNode)curNode).getSymbol());
                curNode = lexicalTree.getVertex();
            }

        }

        return str.toString();

    }

}
