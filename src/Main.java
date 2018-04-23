import Lexical.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        /*String initial = "Вечер Анны Павловны был пущен." +
                " Веретена с разных сторон равномерно и не " +
                "умолкая шумели. Кроме ma tante, около которой " +
                "сидела только одна пожилая дама с исплаканным, " +
                "худым лицом, несколько чужая в этом блестящем обществе, " +
                "общество разбилось на три кружка. В одном, более мужском, " +
                "центром был аббат; в другом, молодом, — красавица " +
                "княжна Элен, дочь князя Василия, и хорошенькая, " +
                "румяная, слишком полная по своей молодости, маленькая " +
                "княгиня Болконская. В третьем — Мортемар и Анна Павловна.";

        StaticLexicalTree lt = StaticTreeBuilder.buildTree(initial);

        ArrayList<Boolean> compressed = StaticCompressor.compress(lt, initial);

        for (Boolean aBoolean : compressed) {

            System.out.print(aBoolean ? "1" : "0");

        }

        System.out.println();

        String str = StaticCompressor.decompress(lt, compressed);

        System.out.println(str);*/

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

        DynamicLexicalTree dlt = new DynamicLexicalTree();

        ArrayList<Boolean> coded = new ArrayList<>();

        for (Character c : initial.toCharArray()) {

            if(!dlt.hasChar(c)) {

                coded.addAll(dlt.getBuilderNodeCode());
                coded.addAll(ByteConverter.charToBooleanArray(c));

                for (Boolean aBoolean : dlt.getBuilderNodeCode()) {
                    System.out.print(aBoolean ? "1" : "0");
                }

                System.out.print(" ");

                for (Boolean aBoolean : ByteConverter.charToBooleanArray(c)) {
                    System.out.print(aBoolean ? "1" : "0");
                }


            }else {

                coded.addAll(dlt.getCharacterCode(c));

                for (Boolean aBoolean : dlt.getCharacterCode(c)) {
                    System.out.print(aBoolean ? "1" : "0");
                }

            }

            dlt.processChar(c);

            System.out.print(" ");

        }

        DynamicLexicalTree dynamicLexicalTree = new DynamicLexicalTree();

        Node curNode = dynamicLexicalTree.getVertex();
        StringBuilder str = new StringBuilder();

        while (coded.size() > 0) {

            curNode = ((EmptyNode)curNode).getChild(coded.remove(0));

            if(curNode instanceof BuilderNode) {

                ArrayList<Boolean> newChar = new ArrayList<>();

                for (int i = 0; i < 16; i++) {
                    newChar.add(coded.remove(0));
                }

                Character c = ByteConverter.booleanArrayToChar(newChar);

                str.append(c);

                dynamicLexicalTree.processChar(c);

                curNode = dynamicLexicalTree.getVertex();

            }else if(curNode instanceof CharacterNode) {

                dynamicLexicalTree.processChar(((CharacterNode) curNode).getSymbol());
                str.append(((CharacterNode) curNode).getSymbol());

                curNode = dynamicLexicalTree.getVertex();

            }

        }

        System.out.println(str.toString().equals(initial));

        return;

    }
}
