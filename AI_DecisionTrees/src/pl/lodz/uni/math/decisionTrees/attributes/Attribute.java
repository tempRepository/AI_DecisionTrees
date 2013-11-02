package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Example;

public abstract class Attribute {
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

    
    protected double log2(double argument)
    {
        return Math.log(argument)/Math.log(2);
    }
    
    protected abstract double entropy(ArrayList<Example> examples);
    
    public abstract LinkedHashMap<java.lang.Enum, Object> getPossibilities(ArrayList<Example> examples);
    
    public abstract double informationGain(ArrayList<Example> examples);
}
