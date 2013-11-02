package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;

public class Bar extends Attribute {

    @Override
    protected double entropy(ArrayList<Example> examples) {
        ArrayList<Example> examplesBarTrue = new ArrayList<>();
        ArrayList<Example> examplesBarFalse = new ArrayList<>();

        for (Example example : examples) {
            if (example.getBar() == true) {
                examplesBarTrue.add(example);
            } else {
                examplesBarFalse.add(example);
            }
        }
        
      /*  double a=informationContent(examplesBarTrue);*/
        double informationContentBarTrue = ((double)examplesBarTrue.size() / (double)examples
                .size()) * informationContent(examplesBarTrue);
        double informationContentBarFalse = ((double)examplesBarFalse.size() / (double)examples
                .size()) * informationContent(examplesBarFalse);

        return informationContentBarTrue + informationContentBarFalse;

    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
/*        double a = informationContent(examples);
        double b = entropy(examples);*/
        return (informationContent(examples) - entropy(examples));
    }
    
    @Override
    public LinkedHashMap<Enum, Object> getPossibilities(
            ArrayList<Example> examples) {
        LinkedHashMap<Enum, Object> possibilities = new LinkedHashMap<>();
        ArrayList<Example> examplesYes = new ArrayList<>();
        ArrayList<Example> examplesNo = new ArrayList<>();
        for (Example example : examples) {
            if (example.getBar() == true) {
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
