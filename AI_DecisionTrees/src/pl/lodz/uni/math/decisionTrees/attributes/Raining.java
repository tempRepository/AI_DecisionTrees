package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;

public class Raining extends TreeAttribute {

    @Override
    protected double entropy(ArrayList<Example> examples) {
        ArrayList<Example> examplesRainingTrue = new ArrayList<>();
        ArrayList<Example> examplesRainingFalse = new ArrayList<>();

        for (Example example : examples) {
            if (example.getRaining() == true) {
                examplesRainingTrue.add(example);
            } else {
                examplesRainingFalse.add(example);
            }
        }

        double probabilityTrue=((double) examplesRainingTrue.size()
                / ((double) examplesRainingFalse.size() + (double) examplesRainingTrue
                        .size()));
        double probabilityFalse=((double) examplesRainingFalse.size()
                / ((double) examplesRainingFalse.size() + (double) examplesRainingTrue
                        .size()));
        return -(probabilityTrue*log2(probabilityTrue)+probabilityFalse*log2(probabilityFalse));

    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
        return ( TreeAttribute.finalDecisionEntropy(examples) - remainder(examples));
    }
    
    @Override
    public LinkedHashMap<Enum, Object> getPossibilities(
            ArrayList<Example> examples) {
        LinkedHashMap<Enum, Object> possibilities = new LinkedHashMap<>();
        ArrayList<Example> examplesYes = new ArrayList<>();
        ArrayList<Example> examplesNo = new ArrayList<>();
        for (Example example : examples) {
            if (example.getRaining() == true) {
                examplesYes.add(example);
            } else {
                examplesNo.add(example);
            }
        }
        possibilities.put(Decision.YES, examplesYes);
        possibilities.put(Decision.NO, examplesNo);
        return possibilities;
    }
    
    @Override
    protected double remainder(ArrayList<Example> examples) {
        ArrayList<Example> examplesAlternateTrue = new ArrayList<>();
        ArrayList<Example> examplesAlternateFalse = new ArrayList<>();

        for (Example example : examples) {
            if (example.getRaining() == true) {
                examplesAlternateTrue.add(example);
            } else {
                examplesAlternateFalse.add(example);
            }
        }
        
        return ((((double)examplesAlternateTrue.size())/(double)examples.size())*TreeAttribute.finalDecisionEntropy(examplesAlternateTrue))+ ((((double)examplesAlternateFalse.size())/(double)examples.size())*TreeAttribute.finalDecisionEntropy(examplesAlternateFalse));
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Raining";
    }
}
