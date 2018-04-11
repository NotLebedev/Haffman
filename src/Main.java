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
                symbol.incrementWieght();
            }

        }

        return chars;

    }

    public static void main(String[] args) {

        /*ArrayList<Node> chars = new ArrayList<>();

        chars.add(new CharacterNode('b', 3));
        chars.add(new CharacterNode('e', 4));
        chars.add(new CharacterNode('p', 2));
        chars.add(new CharacterNode(' ', 2));
        chars.add(new CharacterNode('o', 2));
        chars.add(new CharacterNode('r', 1));
        chars.add(new CharacterNode('!', 1));

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.addAll(chars);

        for (Node node : nodes) {
            System.out.println(node.toString());
        }

        TreeBuilder treeBuilder = new TreeBuilder();

        Node tree = treeBuilder.buildTree(nodes);

        System.out.println(tree.toString());

        for (Node aChar : chars) {

            ArrayList<Boolean> code = ((CharacterNode)aChar).getSelfCode();

            System.out.print(((CharacterNode) aChar).getSymbol() + " : ");

            for (Boolean aBoolean : code) {

                System.out.print(aBoolean ? "1" : "0");

            }

            System.out.println();

        }*/

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

        for(CharacterNode node : chars) {
            System.out.println(node.toString());
        }

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.addAll(chars);

        for (Node node : nodes) {
            System.out.println(node.toString());
        }

        TreeBuilder treeBuilder = new TreeBuilder();

        Node tree = treeBuilder.buildTree(nodes);

        System.out.println(tree.toString());

        Integer totalLength = 0;

        for (Node aChar : chars) {

            ArrayList<Boolean> code = ((CharacterNode)aChar).getSelfCode();

            System.out.print(((CharacterNode) aChar).getSymbol() + " : ");

            for (Boolean aBoolean : code) {

                System.out.print(aBoolean ? "1" : "0");

            }

            System.out.println();

            totalLength += code.size() * aChar.getWeight();

        }

        System.out.println("Initial length was : " + initial.length() * 8 + " bits, after compression : " + totalLength + " bits");

        /*Node obj = new CharacterNode('f', 3);

        int index = Collections.binarySearch(nodes, obj);

        System.out.println("Found at index " + index);

        if(index >= 0) {

            nodes.add(index, obj);

        }else {

            nodes.add(-(index + 1), obj);

        }

        for (Node node : nodes) {
            System.out.println(node.toString());
        }*/

    }
}
