import Lexical.*;
import Lexical.Conversion.OutputConversionStream;

import javax.print.attribute.standard.Compression;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static String readFile(String path, Charset encoding) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);

    }

    static ArrayList<Byte> readFile(String path) throws IOException{

        Path p = Paths.get(path);
        byte[] data = Files.readAllBytes(p);

        ArrayList<Byte> bytes = new ArrayList<>();

        for (byte b : data) {
            bytes.add(b);
        }

        return bytes;

    }

    static void compress() {

        String str;

        try {
            str = readFile("C:\\Users\\User\\Desktop\\Ubik.txt", StandardCharsets.UTF_8);
        }catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Started compressing");
        ArrayList<Byte> compressed = DynamicCompressor.compress(str);
        System.out.println("Finished compression");

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\User\\Desktop\\Ubik-compressed.xip")) {

            byte[] arr = new byte[compressed.size()];

            for (int i = 0; i < compressed.size(); i++) {
                arr[i] = compressed.get(i);
            }

            fos.write(arr);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void decompress() {

        ArrayList<Byte> bytes;

        try {

            bytes = readFile("C:\\Users\\User\\Desktop\\Ubik-compressed.xip");


        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Started decompression");
        String str1 = DynamicCompressor.decompress(bytes);
        System.out.println("Finished decompression");

        System.out.println(str1);

    }

    public static void main(String[] args) {

        compress();
        decompress();

    }

}
