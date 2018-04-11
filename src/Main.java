import Lexical.*;

import java.util.ArrayList;

public class Main {

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

    public static void main(String[] args) {

        String initial = "Вечер Анны Павловны был пущен." +
                " Веретена с разных сторон равномерно и не " +
                "умолкая шумели. Кроме ma tante, около которой " +
                "сидела только одна пожилая дама с исплаканным, " +
                "худым лицом, несколько чужая в этом блестящем обществе, " +
                "общество разбилось на три кружка. В одном, более мужском, " +
                "центром был аббат; в другом, молодом, — красавица " +
                "княжна Элен, дочь князя Василия, и хорошенькая, " +
                "румяная, слишком полная по своей молодости, маленькая " +
                "княгиня Болконская. В третьем — Мортемар и Анна Павловна.";

        ArrayList<CharacterNode> chars = count(initial);

        TreeBuilder treeBuilder = new TreeBuilder();

        LexicalTree lt = treeBuilder.buildTree(chars);

        ArrayList<Boolean> compressed = Compressor.compress(lt, initial);

        for (Boolean aBoolean : compressed) {

            System.out.print(aBoolean ? "1" : "0");

        }

        System.out.println();

        String str = Compressor.decompress(lt, compressed);

        System.out.println(str);


    }
}
