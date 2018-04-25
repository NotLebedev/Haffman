package Lexical.Conversion;

import java.util.ArrayList;

public class InputConversionStream<I, O> {

    private ArrayList<I> inputBuffer;
    private ArrayList<O> outputBuffer;

    private Integer inputLimit;

    private ATOSConversionOperator<I, O> conversionOperator;

    public InputConversionStream(Integer inputLimit, ATOSConversionOperator<I, O> conversionOperator) {

        this.inputLimit = inputLimit;

        this.conversionOperator = conversionOperator;

        inputBuffer = new ArrayList<>();
        outputBuffer = new ArrayList<>();

    }

    public void push(I input) {

        inputBuffer.add(input);

        if(inputBuffer.size() == inputLimit) {

            outputBuffer.add(conversionOperator.atos(inputBuffer));
            inputBuffer.clear();

        }

    }

    public Integer getInputBufferSize() {
        return inputBuffer.size();
    }

    public O pop() {
        return outputBuffer.remove(0);
    }

    public ArrayList<O> getOutputBuffer() {

        ArrayList<O> tmp = outputBuffer;
        outputBuffer = new ArrayList<>();

        return tmp;
    }
}
