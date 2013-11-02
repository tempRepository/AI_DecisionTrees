package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;

public class Hungry extends Attribute {

    @Override
    protected double entropy(ArrayList<Example> examples) {
        ArrayList<Example> examplesHungryTrue = new ArrayList<>();
        ArrayList<Example> examplesHungryFalse = new ArrayList<>();

        for (Example example : examples) {
            if (example.getHungry() == true) {
                examplesHungryTrue.add(example);
            } else {
                examplesHungryFalse.add(example);
            }
        }
        
        double informationContentHungryTrue = ((double)examplesHungryTrue.size() / (double)examples
                .size()) * informationContent(examplesHungryTrue);
        double informationContentHungryFalse = ((double)examplesHungryFalse.size() / (double)examples
                .size()) * informationContent(examplesHungryFalse);

        return informationContentHungryTrue + informationContentHungryFalse;

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
            if (example.getHungry() == true) {
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
