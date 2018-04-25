package Lexical.Conversion;

import java.util.ArrayList;

public interface ATOSConversionOperator <I, O> {

    O atos(ArrayList<I> input);

}
