package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;
import pl.lodz.uni.math.decisionTrees.Price;
import pl.lodz.uni.math.decisionTrees.Type;
import pl.lodz.uni.math.decisionTrees.WaitEstimate;

public class TypeAttribute extends TreeAttribute {

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

        double probabilityTypeBurger = ((double)examplesTypeBurger.size() / (double)examples
                .size()) * informationContent(examplesTypeBurger);
        double probabilityTypeFrench = ((double)examplesTypeFrench.size() / (double)examples
                .size()) * informationContent(examplesTypeFrench);
        double probabilityTypeItalian = ((double)examplesTypeItalian.size() / (double)examples
                .size()) * informationContent(examplesTypeItalian);
        double probabilityTypeThai = ((double)examplesTypeThai.size() / (double)examples
                .size()) * informationContent(examplesTypeThai);

        return -(probabilityTypeBurger*log2(probabilityTypeBurger)+ probabilityTypeFrench*log2(probabilityTypeFrench) + probabilityTypeItalian*log2(probabilityTypeItalian) + probabilityTypeThai*log2(probabilityTypeThai));
    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
        return ( TreeAttribute.finalDecisionEntropy(examples) - remainder(examples));
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
    
    @Override
    protected double remainder(ArrayList<Example> examples) {
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

        double remainderTypeBurger = ((double)examplesTypeBurger.size() / (double)examples
                .size())
                * TreeAttribute.finalDecisionEntropy(examplesTypeBurger);
        double remainderTypeFrench = ((double)examplesTypeFrench.size() / (double)examples
                .size())
                * TreeAttribute.finalDecisionEntropy(examplesTypeFrench);
        double remainderTypeItalian = ((double)examplesTypeItalian.size() / (double)examples
                .size())
                * TreeAttribute.finalDecisionEntropy(examplesTypeItalian);
        double remainderTypeThai = ((double)examplesTypeThai.size() / (double)examples
                .size())
                * TreeAttribute.finalDecisionEntropy(examplesTypeThai);

        return remainderTypeBurger + remainderTypeFrench
                + remainderTypeItalian+ remainderTypeThai;
    }

}
