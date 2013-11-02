package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;
import pl.lodz.uni.math.decisionTrees.Type;
import pl.lodz.uni.math.decisionTrees.WaitEstimate;

public class TypeAttribute extends Attribute {

    @Override
    protected double entropy(ArrayList<Example> examples) {
        ArrayList<Example> examplesTypeBurger = new ArrayList<>();
        ArrayList<Example> examplesTypeFrench = new ArrayList<>();
        ArrayList<Example> examplesTypeItalian = new ArrayList<>();
        ArrayList<Example> examplesTypeThai = new ArrayList<>();

        for (Example example : examples) {
            Type type = example.getType();
            switch (type) {
            case BURGER:
                examplesTypeBurger.add(example);
                break;
            case FRENCH:
                examplesTypeFrench.add(example);
                break;
            case ITALIAN:
                examplesTypeItalian.add(example);
                break;
            case THAI:
                examplesTypeThai.add(example);
                break;
            default:
                break;
            }
        }

        double informationContentTypeBurger = (examplesTypeBurger.size() / examples
                .size()) * informationContent(examplesTypeBurger);
        double informationContentTypeFrench = (examplesTypeFrench.size() / examples
                .size()) * informationContent(examplesTypeFrench);
        double informationContentItalian = (examplesTypeItalian.size() / examples
                .size()) * informationContent(examplesTypeItalian);
        double informationContentMoreThai = (examplesTypeThai.size() / examples
                .size()) * informationContent(examplesTypeThai);

        return informationContentTypeBurger + informationContentTypeFrench
                + informationContentItalian + informationContentMoreThai;
    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
        return (informationContent(examples) - entropy(examples));
    }

    @Override
    public LinkedHashMap<Enum, Object> getPossibilities(
            ArrayList<Example> examples) {
        LinkedHashMap<Enum, Object> possibilities = new LinkedHashMap<>();
        ArrayList<Example> examplesBurger = new ArrayList<>();
        ArrayList<Example> examplesFrench = new ArrayList<>();
        ArrayList<Example> examplesItalian = new ArrayList<>();
        ArrayList<Example> examplesThai = new ArrayList<>();
        for (Example example : examples) {
            if (example.getType() == Type.BURGER) {
                examplesBurger.add(example);
            } else if (example.getType() == Type.FRENCH) {
                examplesFrench.add(example);
            } else if (example.getType() == Type.ITALIAN) {
                examplesItalian.add(example);
            } else {
                examplesThai.add(example);
            }
        }
        possibilities.put(Type.BURGER, examplesBurger);
        possibilities.put(Type.FRENCH, examplesFrench);
        possibilities.put(Type.ITALIAN, examplesItalian);
        possibilities.put(Type.THAI, examplesThai);
        return possibilities;
    }

}
