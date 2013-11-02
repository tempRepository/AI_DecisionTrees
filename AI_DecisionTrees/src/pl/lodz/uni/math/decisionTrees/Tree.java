package pl.lodz.uni.math.decisionTrees;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import pl.lodz.uni.math.decisionTrees.attributes.Alternate;
import pl.lodz.uni.math.decisionTrees.attributes.Attribute;

public class Tree {
    Attribute root = null;

    public Attribute getRoot() {
        return root;
    }

    public void setRoot(Attribute root) {
        this.root = root;
    }

    public LinkedHashMap<java.lang.Enum, Object> getPossibilities() {
        return possibilities;
    }

    public void setPossibilities(
            LinkedHashMap<java.lang.Enum, Object> possibilities) {
        this.possibilities = possibilities;
    }

    @SuppressWarnings("rawtypes")
    LinkedHashMap<java.lang.Enum, Object> possibilities;

    {
        possibilities = new LinkedHashMap<>();
    }

    public Tree() {

    }

    public Tree(Attribute root) {
        this.root = root;
    }

    private static Boolean pluralityValue(ArrayList<Example> examples) {
        double positiveCounter = 0;
        double negativeCounter = 0;
        for (Example example : examples) {
            if (example.getFinalDecision() == true) {
                positiveCounter++;
            } else {
                negativeCounter++;
            }

        }
        if (positiveCounter > negativeCounter) {
            return true;
        } else if (negativeCounter > positiveCounter) {
            return false;
        } else {
            // losowanie wartości
            Random rand = new java.util.Random();
            return rand.nextBoolean();
        }

    }

    @SuppressWarnings("rawtypes")
    public static Object decisionTreeLearning(ArrayList<Example> examples,
            ArrayList<Attribute> attributes, ArrayList<Example> parent_examples) {
        Boolean allFalse = true;
        Boolean allTrue = true;

        for (Example example : examples) {
            if (example.getFinalDecision() == true) {
                allFalse = false;
            } else {
                allTrue = false;
            }
        }

        if (examples.size() == 0) {
            return pluralityValue(parent_examples);
        } else if (allTrue) {
            return true;
        } else if (allFalse) {
            return false;
        } else if (attributes.size() == 0) {
            return pluralityValue(examples);
        } else {
            Attribute theBestAttribute = null;
            double currentMaxInformationGain = Double.MIN_VALUE;
            // znajdowanie najlepszego atrybutu
            for (Attribute attribute : attributes) {
                if (currentMaxInformationGain < attribute
                        .informationGain(examples)) {
                    currentMaxInformationGain = attribute
                            .informationGain(examples);
                    theBestAttribute = attribute;
                }
            }

            Tree newTree = new Tree(theBestAttribute);
            LinkedHashMap<Enum, Object> attributePossibilities = theBestAttribute
                    .getPossibilities(examples);
            for (Enum key : attributePossibilities.keySet()) {
                ArrayList<Attribute> copyOfAttributes = (ArrayList<Attribute>) attributes
                        .clone();
                copyOfAttributes.remove(theBestAttribute);
                newTree.getPossibilities().put(
                        key,
                        decisionTreeLearning(
                                (ArrayList<Example>) attributePossibilities
                                        .get(key), copyOfAttributes, examples));
            }
            return newTree;

            /*
             * for (Enum value : theBestAttribute.getPossibilities()) {
             * ArrayList<Example> examplesWithAttribute = new ArrayList<>(); for
             * (Example example : examples) { if (theBestAttribute instanceof
             * Alternate) { if (example.getAlternate()==value) {
             * 
             * } } else {
             * 
             * }
             * 
             * } }
             * 
             * }
             */

        }

    }

    @Override
    public String toString() {
        System.out.println("Root:" + root.toString());
        System.out.println("Branches:");
        for (Enum key : possibilities.keySet()) {
            System.out.println("Key:" + key.toString() + " "
                    + possibilities.get(key).toString() + " || ");
        }
        System.out.println("\n\n");
        return "Tree with root:"+root.toString();
    }

}
