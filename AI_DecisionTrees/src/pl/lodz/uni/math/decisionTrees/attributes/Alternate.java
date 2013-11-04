package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;

public class Alternate extends TreeAttribute {
  //zmienić na protected!
    public double entropy(ArrayList<Example> examples) {
        // rozkład klas decyzyjnych dla każdej wartości atrybutu Alternate-
        // True, False
        ArrayList<Example> examplesAlternateTrue = new ArrayList<>();
        ArrayList<Example> examplesAlternateFalse = new ArrayList<>();

        for (Example example : examples) {
            if (example.getAlternate() == true) {
                examplesAlternateTrue.add(example);
            } else {
                examplesAlternateFalse.add(example);
            }
        }



        double probabilityTrue=((double) examplesAlternateTrue.size()
                / ((double) examplesAlternateFalse.size() + (double) examplesAlternateTrue
                        .size()));
        double probabilityFalse=((double) examplesAlternateFalse.size()
                / ((double) examplesAlternateFalse.size() + (double) examplesAlternateTrue
                        .size()));
        return -(probabilityTrue*log2(probabilityTrue)+probabilityFalse*log2(probabilityFalse));

    }

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
            if (example.getAlternate() == true) {
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
            if (example.getAlternate() == true) {
                examplesAlternateTrue.add(example);
            } else {
                examplesAlternateFalse.add(example);
            }
        }
        double temp=((((double)examplesAlternateTrue.size())/(double)examples.size())*TreeAttribute.finalDecisionEntropy(examplesAlternateTrue))+ ((((double)examplesAlternateFalse.size())/(double)examples.size())*TreeAttribute.finalDecisionEntropy(examplesAlternateFalse));
        return temp;
    }

}
