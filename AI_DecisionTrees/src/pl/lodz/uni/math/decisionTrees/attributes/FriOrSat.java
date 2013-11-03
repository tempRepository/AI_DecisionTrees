package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;

public class FriOrSat extends TreeAttribute {

    @Override
    protected double entropy(ArrayList<Example> examples) {
        ArrayList<Example> examplesFriOrSatTrue = new ArrayList<>();
        ArrayList<Example> examplesFriOrSatFalse = new ArrayList<>();

        for (Example example : examples) {
            if (example.getFriOrSat() == true) {
                examplesFriOrSatTrue.add(example);
            } else {
                examplesFriOrSatFalse.add(example);
            }
        }
        
        double probabilityTrue=((double) examplesFriOrSatTrue.size()
                / ((double) examplesFriOrSatFalse.size() + (double) examplesFriOrSatTrue
                        .size()));
        double probabilityFalse=((double) examplesFriOrSatFalse.size()
                / ((double) examplesFriOrSatFalse.size() + (double) examplesFriOrSatTrue
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
            if (example.getFriOrSat() == true) {
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
            if (example.getFriOrSat() == true) {
                examplesAlternateTrue.add(example);
            } else {
                examplesAlternateFalse.add(example);
            }
        }
        
        return (((examplesAlternateTrue.size())/examples.size())*TreeAttribute.finalDecisionEntropy(examplesAlternateTrue))+ (((examplesAlternateFalse.size())/examples.size())*TreeAttribute.finalDecisionEntropy(examplesAlternateFalse));
    }

}
