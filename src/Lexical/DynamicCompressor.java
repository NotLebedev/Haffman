package Lexical;

import Lexical.Conversion.ByteConverter;
import Lexical.Conversion.InputConversionStream;
import Lexical.Conversion.OutputConversionStream;

import java.util.ArrayList;

public class DynamicCompressor {

    public static ArrayList<Byte> compress(String str) {

        DynamicLexicalTree dlt = new DynamicLexicalTree();

        InputConversionStream<Boolean, Byte> inputConversionStream = new InputConversionStream<>(8, (ArrayList<Boolean> arr) -> {

            byte output = 0;

            for (int i = 0; i < 8; i++) {
                output |= (arr.remove(0) ? 1 : 0) << i;
            }

            return output;

        });

        for (Character c : str.toCharArray()) {

            if (!dlt.hasChar(c)) {

                for (Boolean aBoolean : dlt.getBuilderNodeCode()) {
                    inputConversionStream.push(aBoolean);
                }

                for (Boolean aBoolean : ByteConverter.charToBooleanArray(c)){
                    inputConversionStream.push(aBoolean);
                }

            } else {

                for (Boolean aBoolean : dlt.getCharacterCode(c)) {
                    inputConversionStream.push(aBoolean);
                }

            }

            dlt.processChar(c);

        }

        Integer bitsMissing = 8 - inputConversionStream.getInputBufferSize();

        for (int i = 0; i < bitsMissing; i++) {
            inputConversionStream.push(false);
        }

        return inputConversionStream.getOutputBuffer();

    }

    public static String decompress(ArrayList<Byte> input) {

        OutputConversionStream<Byte, Boolean> outputConversionStream = new OutputConversionStream<>((Byte b) -> {

            ArrayList<Boolean> arr = new ArrayList<>();

            for (int i = 0; i < 8; i++) {
                arr.add((b & 1 << i) != 0);
            }

            return arr;

        });

        outputConversionStream.setInputBuffer(input);

        DynamicLexicalTree dynamicLexicalTree = new DynamicLexicalTree();

        Node curNode = dynamicLexicalTree.getVertex();
        StringBuilder stringBuilder = new StringBuilder();

        while (!outputConversionStream.isEmpty()) {

            curNode = ((EmptyNode) curNode).getChild(outputConversionStream.getOutput());

            if (curNode instanceof BuilderNode) {

                ArrayList<Boolean> newChar = new ArrayList<>();

                for (int i = 0; i < 16; i++) {
                    newChar.add(outputConversionStream.getOutput());
                }

                Character c = Lexical.Conversion.ByteConverter.booleanArrayToChar(newChar);

                stringBuilder.append(c);

                dynamicLexicalTree.processChar(c);

                curNode = dynamicLexicalTree.getVertex();

            } else if (curNode instanceof CharacterNode) {

                dynamicLexicalTree.processChar(((CharacterNode) curNode).getSymbol());
                stringBuilder.append(((CharacterNode) curNode).getSymbol());

                curNode = dynamicLexicalTree.getVertex();

            }

        }

        return stringBuilder.toString();

    }

}
