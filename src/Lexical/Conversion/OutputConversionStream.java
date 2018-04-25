package Lexical.Conversion;

import java.util.ArrayList;

public class OutputConversionStream<I, O> {

    private ArrayList<I> inputBuffer;
    private ArrayList<O> outputBuffer;


    private STOAConversionOperator<I, O> conversionOperator;

    public OutputConversionStream(STOAConversionOperator<I, O> conversionOperator) {

        this.conversionOperator = conversionOperator;

        inputBuffer = new ArrayList<>();
        outputBuffer = new ArrayList<>();

    }

    public void setInputBuffer(ArrayList<I> inputBuffer) {
        this.inputBuffer = inputBuffer;
    }

    public void push(I input) {
        inputBuffer.add(input);
    }

    public O getOutput() {

        if(outputBuffer.isEmpty()) {
            outputBuffer = conversionOperator.stoa(inputBuffer.remove(0));
        }

        return outputBuffer.remove(0);

    }

    public Boolean isEmpty() {

        return outputBuffer.isEmpty() && inputBuffer.isEmpty();

    }

}
