package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;
import pl.lodz.uni.math.decisionTrees.WaitEstimate;

public class Estimate extends Attribute {

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

        double informationContentTo10 = (examplesEstimateTo10.size() / examples
                .size()) * informationContent(examplesEstimateTo10);
        double informationContentTo30 = (examplesEstimateTo30.size() / examples
                .size()) * informationContent(examplesEstimateTo30);
        double informationContentTo60 = (examplesEstimateTo60.size() / examples
                .size()) * informationContent(examplesEstimateTo60);
        double informationContentMoreThan60 = (examplesEstimateMoreThan60
                .size() / examples.size())
                * informationContent(examplesEstimateMoreThan60);

        return informationContentTo10 + informationContentTo30
                + informationContentTo60 + informationContentMoreThan60;
    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
        return (informationContent(examples) - entropy(examples));
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

}
