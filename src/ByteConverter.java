import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ByteConverter {

    public static byte booleanArrToByte(ArrayList<Boolean> arr) {

        byte d = 0;

        for (int i = 0; i < 8; i++) {

            d |= (arr.remove(0) ? 1 : 0) << i;

        }

        return d;

    }

    public static ArrayList<Boolean> byteToBooleanArray(byte b) {

        ArrayList<Boolean> toBool = new ArrayList<>();

        for (int i = 0; i < 8; i++) {

            toBool.add((b & (1 << i)) != 0);

        }

        return toBool;

    }

    public static Character booleanArrayToChar(ArrayList<Boolean> arr) {

        byte byteArr[] = new byte[4];

        byteArr[0] = -2;
        byteArr[1] = -1;

        byteArr[2] = booleanArrToByte(arr);
        byteArr[3] = booleanArrToByte(arr);

        try {
            return (new String(byteArr, "UTF-16")).charAt(0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<Boolean> charToBooleanArray(Character c) {

        byte byteArr[] = ("" + c).getBytes(StandardCharsets.UTF_16);

        ArrayList<Boolean> toBool = byteToBooleanArray(byteArr[2]);
        toBool.addAll(byteToBooleanArray(byteArr[3]));

        return toBool;

    }

}
