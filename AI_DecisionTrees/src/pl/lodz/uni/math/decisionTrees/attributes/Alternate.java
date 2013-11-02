package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;

public class Alternate extends Attribute {

    protected double entropy(ArrayList<Example> examples) {
        // rozkład klas decyzyjnych dla każdej wartości atrybutu Alternate-
        // True, False
        /*
         * double counterAlternateTrueFinalTrue = 0; double
         * counterAlternateFalseFinalTrue = 0; double
         * counterAlternateTrueFinalFalse = 0; double
         * counterAlternateFalseFinalFalse = 0;
         */

        /*
         * for (Example example : examples) { if (example.getAlternate() ==
         * true) { if (example.getFinalDecision() == true) {
         * counterAlternateTrueFinalTrue++; } else {
         * counterAlternateTrueFinalFalse++; } } else { if
         * (example.getFinalDecision() == true) {
         * counterAlternateFalseFinalTrue++; } else {
         * counterAlternateFalseFinalFalse++; } } }
         */
        ArrayList<Example> examplesAlternateTrue = new ArrayList<>();
        ArrayList<Example> examplesAlternateFalse = new ArrayList<>();

        for (Example example : examples) {
            if (example.getAlternate() == true) {
                examplesAlternateTrue.add(example);
            } else {
                examplesAlternateFalse.add(example);
            }
        }

        // liczenie entropii dla zbioru przykładów dla atrybutu Alternate
        /*
         * System.out.println(((double)examplesAlternateTrue.size() /
         * ((double)examplesAlternateFalse.size() +
         * (double)examplesAlternateTrue.size()) *
         * informationGain(examplesAlternateTrue)));
         */
        /*
         * System.out.println((examplesAlternateFalse.size() /
         * (examplesAlternateFalse.size() + examplesAlternateTrue .size()) *
         * informationGain(examplesAlternateFalse)));
         */
        return ((double) examplesAlternateTrue.size()
                / ((double) examplesAlternateFalse.size() + (double) examplesAlternateTrue
                        .size()) * informationContent(examplesAlternateTrue))
                + ((double) examplesAlternateFalse.size()
                        / ((double) examplesAlternateFalse.size() + (double) examplesAlternateTrue
                                .size()) * informationContent(examplesAlternateFalse));

    }

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
            if (example.getAlternate() == true) {
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
