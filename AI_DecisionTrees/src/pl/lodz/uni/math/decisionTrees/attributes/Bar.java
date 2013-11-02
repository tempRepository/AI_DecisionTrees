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
        double probabilityTrue=((double) examplesBarTrue.size()
                / ((double) examplesBarFalse.size() + (double) examplesBarTrue
                        .size()));
        double probabilityFalse=((double) examplesBarFalse.size()
                / ((double) examplesBarFalse.size() + (double) examplesBarTrue
                        .size()));
        return -(probabilityTrue*log2(probabilityTrue)+probabilityFalse*log2(probabilityFalse));

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
