import Lexical.*;
import Lexical.Conversion.OutputConversionStream;

import javax.print.attribute.standard.Compression;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static void compress() {

        System.out.println("Enter your string to compress : \n" +
                ">");

        Scanner in = new Scanner(System.in);

        String input = in.next();

        ArrayList<Byte> compressed = DynamicCompressor.compress(input);

        byte arr[] = new byte[compressed.size()];

        for (int i = 0; i < compressed.size(); i++) {
            arr[i] = compressed.get(i);
        }

        StringSelection stringSelection = new StringSelection(new String(arr, StandardCharsets.UTF_8));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        System.out.println("Compressed string added to your clipboard");

        System.out.println(new String(arr, StandardCharsets.UTF_8).length());

    }

    private static void deompress() {

    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Choose action : \n" +
                "\n" +
                "0)Compress\n" +
                "1)Decomress\n" +
                "\n" +
                ">");

        Integer choice = in.nextInt();

        switch (choice) {

            case 0:
                compress();
                break;
            case 1:
                deompress();
                break;
            default:
                System.out.println("Problems typing single digit numbers?");

        }

    }
}
