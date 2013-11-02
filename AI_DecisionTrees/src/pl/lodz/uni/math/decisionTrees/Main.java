package pl.lodz.uni.math.decisionTrees;

import java.util.ArrayList;

import pl.lodz.uni.math.decisionTrees.attributes.Alternate;
import pl.lodz.uni.math.decisionTrees.attributes.Attribute;
import pl.lodz.uni.math.decisionTrees.attributes.Bar;
import pl.lodz.uni.math.decisionTrees.attributes.Estimate;
import pl.lodz.uni.math.decisionTrees.attributes.FriOrSat;
import pl.lodz.uni.math.decisionTrees.attributes.GuestsAttribute;
import pl.lodz.uni.math.decisionTrees.attributes.Hungry;
import pl.lodz.uni.math.decisionTrees.attributes.PriceAttribute;
import pl.lodz.uni.math.decisionTrees.attributes.Raining;
import pl.lodz.uni.math.decisionTrees.attributes.Reservation;
import pl.lodz.uni.math.decisionTrees.attributes.TypeAttribute;

public class Main {

    public static void main(String[] args) {
        ArrayList<Example> trainingSet = new ArrayList<>();
       //training set
        trainingSet.add(new Example(true, false, false, true, Guests.SOME, Price.EXPENSIVE, false, true, Type.FRENCH, WaitEstimate.TO10, true));
        trainingSet.add(new Example(true, false, false, true, Guests.FULL, Price.CHEAP, false, false, Type.THAI, WaitEstimate.TO60, false));
        trainingSet.add(new Example(false, true, false, false, Guests.SOME, Price.CHEAP, false, false, Type.BURGER, WaitEstimate.TO10, true));
        trainingSet.add(new Example(true, false, true, true, Guests.FULL, Price.CHEAP, true, false, Type.THAI, WaitEstimate.TO30, true));
        trainingSet.add(new Example(true, false, true, false, Guests.FULL, Price.EXPENSIVE, false, true, Type.FRENCH, WaitEstimate.MORETHAN60, false));
        trainingSet.add(new Example(false, true, false, true, Guests.SOME, Price.MEDIUM, true, true, Type.ITALIAN, WaitEstimate.TO10, true));
        trainingSet.add(new Example(false, true, false, false, Guests.NONE, Price.CHEAP, true, false, Type.BURGER, WaitEstimate.TO10, false));
        trainingSet.add(new Example(false, false, false, true, Guests.SOME, Price.MEDIUM, true, true, Type.THAI, WaitEstimate.TO10, true));
        trainingSet.add(new Example(false, true, true,  false,  Guests.FULL, Price.CHEAP, true, false, Type.BURGER, WaitEstimate.MORETHAN60, false));
        trainingSet.add(new Example(true, true, true, true, Guests.FULL, Price.EXPENSIVE, false, true, Type.ITALIAN, WaitEstimate.TO30, false));
        trainingSet.add(new Example(false, false, false, false, Guests.NONE, Price.CHEAP , false, false, Type.THAI, WaitEstimate.TO10, false));
        trainingSet.add(new Example(true, true, true, true,  Guests.FULL, Price.CHEAP, false, false, Type.BURGER, WaitEstimate.TO60, true));

        ArrayList<Attribute> attributes=new ArrayList<>();
/*        trainingSet.add(new Example(false, true, null, null, null, null, null, null, null, null, false));
        trainingSet.add(new Example(true, false, null, null, null, null, null, null, null, null, false));
        trainingSet.add(new Example(true, true, null, null, null, null, null, null, null, null, true));
        trainingSet.add(new Example(false, false, null, null, null, null, null, null, null, null, false));

       attributes.add(new Alternate());
       attributes.add(new Bar());*/
       
       attributes.add(new Estimate());
       attributes.add(new FriOrSat());
       attributes.add(new GuestsAttribute());
       attributes.add(new Hungry());
       attributes.add(new PriceAttribute());
       attributes.add(new Raining());
       attributes.add(new Reservation());
       attributes.add(new TypeAttribute());

       Tree.decisionTreeLearning(trainingSet, attributes, new ArrayList<Example>()).toString();


    }

}
