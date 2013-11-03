package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;
import pl.lodz.uni.math.decisionTrees.WaitEstimate;

public class Estimate extends TreeAttribute {

    @Override
    protected double entropy(ArrayList<Example> examples) {
        ArrayList<Example> examplesEstimateTo10 = new ArrayList<>();
        ArrayList<Example> examplesEstimateTo30 = new ArrayList<>();
        ArrayList<Example> examplesEstimateTo60 = new ArrayList<>();
        ArrayList<Example> examplesEstimateMoreThan60 = new ArrayList<>();

        for (Example example : examples) {
            WaitEstimate estimate = example.getWaitEstimate();
            switch (estimate) {
            case TO10:
                examplesEstimateTo10.add(example);
                break;
            case TO30:
                examplesEstimateTo30.add(example);
                break;
            case TO60:
                examplesEstimateTo60.add(example);
                break;
            case MORETHAN60:
                examplesEstimateMoreThan60.add(example);
                break;
            default:
                break;
            }
        }

        double probabilityTo10 = ((double)examplesEstimateTo10.size() / (double)examples.size());
        double probabilityTo30 = ((double)examplesEstimateTo30.size() / (double)examples.size());
        double probabilityTo60 = ((double)examplesEstimateTo60.size() / (double)examples.size());
        double probabilityMoreThan60 = ((double)examplesEstimateMoreThan60.size() / (double)examples
                .size());

        return -(probabilityTo10 * log2(probabilityTo10)
                + probabilityMoreThan60 * log2(probabilityMoreThan60)
                + probabilityTo30 * log2(probabilityTo30) + probabilityTo60
                * log2(probabilityTo60));
    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
        return (TreeAttribute.finalDecisionEntropy(examples) - remainder(examples));
    }

    @Override
    public LinkedHashMap<Enum, Object> getPossibilities(
            ArrayList<Example> examples) {
        LinkedHashMap<Enum, Object> possibilities = new LinkedHashMap<>();
        ArrayList<Example> examplesTo10 = new ArrayList<>();
        ArrayList<Example> examplesTo30 = new ArrayList<>();
        ArrayList<Example> examplesTo60 = new ArrayList<>();
        ArrayList<Example> examplesMoreThan60 = new ArrayList<>();
        for (Example example : examples) {
            if (example.getWaitEstimate() == WaitEstimate.TO10) {
                examplesTo10.add(example);
            } else if (example.getWaitEstimate() == WaitEstimate.TO30) {
                examplesTo30.add(example);
            } else if (example.getWaitEstimate() == WaitEstimate.TO60) {
                examplesTo60.add(example);
            } else {
                examplesMoreThan60.add(example);
            }
        }
        possibilities.put(WaitEstimate.TO10, examplesTo10);
        possibilities.put(WaitEstimate.TO30, examplesTo30);
        possibilities.put(WaitEstimate.TO60, examplesTo60);
        possibilities.put(WaitEstimate.MORETHAN60, examplesMoreThan60);
        return possibilities;
    }

    @Override
    protected double remainder(ArrayList<Example> examples) {
        ArrayList<Example> examplesEstimateTo10 = new ArrayList<>();
        ArrayList<Example> examplesEstimateTo30 = new ArrayList<>();
        ArrayList<Example> examplesEstimateTo60 = new ArrayList<>();
        ArrayList<Example> examplesEstimateMoreThan60 = new ArrayList<>();

        for (Example example : examples) {
            WaitEstimate estimate = example.getWaitEstimate();
            switch (estimate) {
            case TO10:
                examplesEstimateTo10.add(example);
                break;
            case TO30:
                examplesEstimateTo30.add(example);
                break;
            case TO60:
                examplesEstimateTo60.add(example);
                break;
            case MORETHAN60:
                examplesEstimateMoreThan60.add(example);
                break;
            default:
                break;
            }
        }

        double remainderTo10 = ((double)examplesEstimateTo10.size() / (double)examples.size())
                * TreeAttribute.finalDecisionEntropy(examplesEstimateTo10);
        double remainderTo30 = ((double)examplesEstimateTo30.size() / (double)examples.size())
                * TreeAttribute.finalDecisionEntropy(examplesEstimateTo30);
        double remainderTo60 = ((double)examplesEstimateTo60.size() / (double)examples.size())
                * TreeAttribute.finalDecisionEntropy(examplesEstimateTo60);
        double remainderMoreThan60 = ((double)examplesEstimateMoreThan60.size() / (double)examples
                .size())
                * TreeAttribute
                        .finalDecisionEntropy(examplesEstimateMoreThan60);
        return remainderMoreThan60 + remainderTo10 + remainderTo30
                + remainderTo60;
    }

}
