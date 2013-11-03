package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Example;

public abstract class TreeAttribute {
    protected double informationContent(ArrayList<Example> examples) {
        double positiveCounter = 0;
        double negativeCounter = 0;
        for (Example example : examples) {
            if (example.getFinalDecision() == true) {
                positiveCounter++;
            } else {
                negativeCounter++;
            }

        }
        // returns information gain
        // returns NaN because of log0!
        if (positiveCounter != 0 && negativeCounter != 0) {
            double informationContent = -((positiveCounter / (positiveCounter + negativeCounter))
                    * Math.log((positiveCounter / (positiveCounter + negativeCounter))) + (negativeCounter / (positiveCounter + negativeCounter))
                    * Math.log((negativeCounter / (positiveCounter + negativeCounter))));
            return informationContent;
        } else {
            return 0;
        }

    }
    
    protected abstract double remainder(ArrayList<Example> examples);
    
    protected static double log2(double argument)
    {
        return Math.log(argument)/Math.log(2);
    }
    
    protected abstract double entropy(ArrayList<Example> examples);
    
    public abstract LinkedHashMap<java.lang.Enum, Object> getPossibilities(ArrayList<Example> examples);
    
    public abstract double informationGain(ArrayList<Example> examples);
    
    private static double B(double q) {
        return -(q * log2(q) + (1 - q) * log2(1 - q));
    }

    protected static double finalDecisionEntropy(ArrayList<Example> examples) {
        double positiveCounter = 0;
        double negativeCounter = 0;
        for (Example example : examples) {
            if (example.getFinalDecision() == true) {
                positiveCounter++;
            } else {
                negativeCounter++;
            }

        }
       return B(positiveCounter/(positiveCounter+negativeCounter));
    }
}
