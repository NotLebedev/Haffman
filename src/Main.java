import Lexical.*;

import java.util.ArrayList;

public class Main {

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

        LexicalTree lt = TreeBuilder.buildTree(initial);

        ArrayList<Boolean> compressed = Compressor.compress(lt, initial);

        for (Boolean aBoolean : compressed) {

            System.out.print(aBoolean ? "1" : "0");

        }

        System.out.println();

        String str = Compressor.decompress(lt, compressed);

        System.out.println(str);


    }
}
