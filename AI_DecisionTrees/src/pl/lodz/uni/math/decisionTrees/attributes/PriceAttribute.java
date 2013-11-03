package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;
import pl.lodz.uni.math.decisionTrees.Guests;
import pl.lodz.uni.math.decisionTrees.Price;
import pl.lodz.uni.math.decisionTrees.WaitEstimate;

public class PriceAttribute extends TreeAttribute {

    @Override
    protected double entropy(ArrayList<Example> examples) {
        ArrayList<Example> examplesPriceCheap = new ArrayList<>();
        ArrayList<Example> examplesPriceMedium = new ArrayList<>();
        ArrayList<Example> examplesPriceExpensive = new ArrayList<>();

        for (Example example : examples) {
            Price price = example.getPrice();
            switch (price) {
            case CHEAP:
                examplesPriceCheap.add(example);
                break;
            case MEDIUM:
                examplesPriceMedium.add(example);
                break;
            case EXPENSIVE:
                examplesPriceExpensive.add(example);
                break;

            default:
                break;
            }
        }

        double probabilityPriceCheap = (examplesPriceCheap.size() / examples
                .size()) * informationContent(examplesPriceCheap);
        double probabilityPriceMedium = (examplesPriceMedium.size() / examples
                .size()) * informationContent(examplesPriceMedium);
        double probabilityPriceExpensive = (examplesPriceExpensive.size() / examples
                .size()) * informationContent(examplesPriceExpensive);

        return -(probabilityPriceCheap * log2(probabilityPriceCheap)
                + probabilityPriceMedium * log2(probabilityPriceMedium) + probabilityPriceExpensive
                * log2(probabilityPriceExpensive));
    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
        return (TreeAttribute.finalDecisionEntropy(examples) - remainder(examples));
    }

    @Override
    public LinkedHashMap<Enum, Object> getPossibilities(
            ArrayList<Example> examples) {
        LinkedHashMap<Enum, Object> possibilities = new LinkedHashMap<>();
        ArrayList<Example> examplesCheap = new ArrayList<>();
        ArrayList<Example> examplesExpensive = new ArrayList<>();
        ArrayList<Example> examplesMedium = new ArrayList<>();
        for (Example example : examples) {
            if (example.getPrice() == Price.CHEAP) {
                examplesCheap.add(example);
            } else if (example.getPrice() == Price.EXPENSIVE) {
                examplesExpensive.add(example);
            } else if (example.getPrice() == Price.MEDIUM) {
                examplesMedium.add(example);
            }
        }
        possibilities.put(Price.CHEAP, examplesCheap);
        possibilities.put(Price.EXPENSIVE, examplesExpensive);
        possibilities.put(Price.MEDIUM, examplesMedium);
        return possibilities;
    }

    @Override
    protected double remainder(ArrayList<Example> examples) {
        ArrayList<Example> examplesPriceCheap = new ArrayList<>();
        ArrayList<Example> examplesPriceMedium = new ArrayList<>();
        ArrayList<Example> examplesPriceExpensive = new ArrayList<>();

        for (Example example : examples) {
            Price price = example.getPrice();
            switch (price) {
            case CHEAP:
                examplesPriceCheap.add(example);
                break;
            case MEDIUM:
                examplesPriceMedium.add(example);
                break;
            case EXPENSIVE:
                examplesPriceExpensive.add(example);
                break;

            default:
                break;
            }
        }

        double remainderPriceExpensive = (examplesPriceExpensive.size() / examples
                .size())
                * TreeAttribute.finalDecisionEntropy(examplesPriceExpensive);
        double remainderPriceMedium = (examplesPriceMedium.size() / examples
                .size())
                * TreeAttribute.finalDecisionEntropy(examplesPriceMedium);
        double remainderGuestsCheap = (examplesPriceCheap.size() / examples
                .size())
                * TreeAttribute.finalDecisionEntropy(examplesPriceCheap);

        return remainderPriceExpensive + remainderPriceMedium
                + remainderGuestsCheap;
    }

}
