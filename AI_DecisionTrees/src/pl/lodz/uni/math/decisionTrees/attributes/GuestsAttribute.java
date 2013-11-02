package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;
import pl.lodz.uni.math.decisionTrees.Guests;
import pl.lodz.uni.math.decisionTrees.WaitEstimate;

public class GuestsAttribute extends Attribute {

    @Override
    protected double entropy(ArrayList<Example> examples) {
        ArrayList<Example> examplesGuestsFull = new ArrayList<>();
        ArrayList<Example> examplesGuestsSome = new ArrayList<>();
        ArrayList<Example> examplesGuestsNone = new ArrayList<>();

        for (Example example : examples) {
            Guests guests = example.getGuests();
            switch (guests) {
            case FULL:
                examplesGuestsFull.add(example);
                break;
            case SOME:
                examplesGuestsSome.add(example);
                break;
            case NONE:
                examplesGuestsNone.add(example);
                break;
            default:
                break;
            }
        }

        double informationContentGuestsFull = (examplesGuestsFull.size() / examples
                .size()) * informationContent(examplesGuestsFull);
        double informationContentGuestsSome = (examplesGuestsSome.size() / examples
                .size()) * informationContent(examplesGuestsSome);
        double informationContentNone = (examplesGuestsNone.size() / examples
                .size()) * informationContent(examplesGuestsNone);

        return informationContentGuestsFull + informationContentGuestsSome
                + informationContentNone;
    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
        return (informationContent(examples) - entropy(examples));
    }

    @Override
    public LinkedHashMap<Enum, Object> getPossibilities(
            ArrayList<Example> examples) {
        LinkedHashMap<Enum, Object> possibilities = new LinkedHashMap<>();
        ArrayList<Example> examplesFull = new ArrayList<>();
        ArrayList<Example> examplesNone = new ArrayList<>();
        ArrayList<Example> examplesSome = new ArrayList<>();
        for (Example example : examples) {
            if (example.getGuests() == Guests.FULL) {
                examplesFull.add(example);
            } else if (example.getGuests() == Guests.NONE) {
                examplesNone.add(example);
            } else if (example.getGuests() == Guests.SOME) {
                examplesSome.add(example);
            }
        }
        possibilities.put(Guests.FULL, examplesFull);
        possibilities.put(Guests.NONE, examplesNone);
        possibilities.put(Guests.SOME, examplesSome);
        return possibilities;
    }

}
