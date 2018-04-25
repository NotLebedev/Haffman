package Lexical.Conversion;

import java.util.ArrayList;

public interface STOAConversionOperator<I, O> {

    ArrayList<O> stoa(I input);

}
