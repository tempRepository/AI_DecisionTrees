package pl.lodz.uni.math.decisionTrees.attributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.lodz.uni.math.decisionTrees.Decision;
import pl.lodz.uni.math.decisionTrees.Example;
import pl.lodz.uni.math.decisionTrees.Guests;
import pl.lodz.uni.math.decisionTrees.Price;
import pl.lodz.uni.math.decisionTrees.WaitEstimate;

public class PriceAttribute extends Attribute {

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

        double informationContentPriceCheap = (examplesPriceCheap.size() / examples
                .size()) * informationContent(examplesPriceCheap);
        double informationContentPriceMedium = (examplesPriceMedium.size() / examples
                .size()) * informationContent(examplesPriceMedium);
        double informationPriceExpensive = (examplesPriceExpensive.size() / examples
                .size()) * informationContent(examplesPriceExpensive);

        return informationContentPriceCheap + informationContentPriceMedium
                + informationPriceExpensive;
    }

    @Override
    public double informationGain(ArrayList<Example> examples) {
        return (informationContent(examples) - entropy(examples));
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


}
