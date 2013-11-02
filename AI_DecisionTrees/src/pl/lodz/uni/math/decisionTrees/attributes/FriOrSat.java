package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;

public class FriOrSat extends Attribute {

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
        
        double informationContentFriOrSatTrue = ((double)examplesFriOrSatTrue.size() / (double)examples
                .size()) * informationContent(examplesFriOrSatTrue);
        double informationContentFriOrSatFalse = ((double)examplesFriOrSatFalse.size() / (double)examples
                .size()) * informationContent(examplesFriOrSatFalse);

        return informationContentFriOrSatTrue + informationContentFriOrSatFalse;

    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
        return (informationContent(examples) - entropy(examples));
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


}
