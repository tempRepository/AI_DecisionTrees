package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;

public class Reservation extends Attribute {

    @Override
    protected double entropy(ArrayList<Example> examples) {
        ArrayList<Example> examplesReservationTrue = new ArrayList<>();
        ArrayList<Example> examplesReservationFalse = new ArrayList<>();

        for (Example example : examples) {
            if (example.getReservation() == true) {
                examplesReservationTrue.add(example);
            } else {
                examplesReservationFalse.add(example);
            }
        }

        double informationContentReservationTrue = ((double) examplesReservationTrue
                .size() / (double) examples.size())
                * informationContent(examplesReservationTrue);
        double informationContentReservationFalse = ((double) examplesReservationFalse
                .size() / (double) examples.size())
                * informationContent(examplesReservationFalse);

        return informationContentReservationTrue
                + informationContentReservationFalse;

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
            if (example.getReservation() == true) {
                examplesYes.add(example);
            } else {
                examplesNo.add(example);
            }
        }
        possibilities.put(Decision.YES, examplesYes);
        possibilities.put(Decision.NO, examplesNo);
        return possibilities;
    }

    public Boolean testExample(Example example, Enum value) {
        if (Decision.YES == value) {
            return true;
        } else {
            return false;
        }
    }

}
