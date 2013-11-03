package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;
import pl.lodz.uni.math.decisionTrees.Guests;
import pl.lodz.uni.math.decisionTrees.WaitEstimate;

public class GuestsAttribute extends TreeAttribute {

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

      
        double probabilityGuestsFull = (examplesGuestsFull.size() / examples
                .size());
        double probabilityGuestsSome = (examplesGuestsSome.size() / examples
                .size());
        double probabilityGuestsNone = (examplesGuestsNone.size() / examples
                .size());

        return -(probabilityGuestsFull*log2(probabilityGuestsFull) + probabilityGuestsSome*log2(probabilityGuestsSome) + probabilityGuestsNone*log2(probabilityGuestsNone));
    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
        return ( TreeAttribute.finalDecisionEntropy(examples) - remainder(examples));
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

    
    @Override
    protected double remainder(ArrayList<Example> examples) {
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

        double remainderGuestsFull = (examplesGuestsFull.size() / examples.size())
                * TreeAttribute.finalDecisionEntropy(examplesGuestsFull);
        double remainderGuestsSome = (examplesGuestsSome.size() / examples.size())
                * TreeAttribute.finalDecisionEntropy(examplesGuestsSome);
        double remainderGuestsNone = (examplesGuestsNone.size() / examples.size())
                * TreeAttribute.finalDecisionEntropy(examplesGuestsNone);

        return   remainderGuestsFull + remainderGuestsSome
                + remainderGuestsNone;
    }
    
}
