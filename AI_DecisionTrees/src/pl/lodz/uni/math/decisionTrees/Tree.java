package pl.lodz.uni.math.decisionTrees;

import org.w3c.dom.*;

import javax.xml.xpath.*;
import javax.xml.parsers.*;

import java.io.IOException;

import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import pl.lodz.uni.math.decisionTrees.attributes.Alternate;
import pl.lodz.uni.math.decisionTrees.attributes.TreeAttribute;

public class Tree {
    TreeAttribute root = null;

    public TreeAttribute getRoot() {
        return root;
    }

    public void setRoot(TreeAttribute root) {
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

    public Tree(TreeAttribute root) {
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
            ArrayList<TreeAttribute> attributes,
            ArrayList<Example> parent_examples) {
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
            TreeAttribute theBestAttribute = null;
            double currentMaxInformationGain = Double.MIN_VALUE;
            // znajdowanie najlepszego atrybutu
             for (TreeAttribute attribute : attributes) {
                double informationGain = attribute.informationGain(examples);
                System.out.println("Attributes: "+attributes.size()+" "+attribute.toString()+" "+informationGain);
                if (currentMaxInformationGain < informationGain) {
                    currentMaxInformationGain = informationGain;
                    theBestAttribute = attribute;
                }
            }

            Tree newTree = new Tree(theBestAttribute);
            LinkedHashMap<Enum, Object> attributePossibilities = theBestAttribute
                    .getPossibilities(examples);
            for (Enum key : attributePossibilities.keySet()) {
                ArrayList<TreeAttribute> copyOfAttributes = (ArrayList<TreeAttribute>) attributes
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
        return "Tree with root:" + root.toString();
    }

    public static ArrayList<Example> getExamples(String fileName)
            throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory
                .newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse("examples.xml");
        XPath xpath = XPathFactory.newInstance().newXPath();
        // XPath Query for showing all nodes value
        XPathExpression expr = xpath.compile("//example/*/text()");

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        ArrayList<Example> examples = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Example example = new Example();
            boolean alternative;
            boolean bar;
            boolean friOrSat;
            boolean hungry;
            Guests guests;
            Price price;
            boolean raining;
            boolean reservation;
            Type type;
            WaitEstimate estimate;
            boolean finalDecision;
            if (nodes.item(i++).getNodeValue() == "true") {
                alternative = true;
            } else {
                alternative = false;
            }

            if (nodes.item(i++).getNodeValue() == "true") {
                bar = true;
            } else {
                bar = false;
            }

            if (nodes.item(i++).getNodeValue() == "true") {
                friOrSat = true;
            } else {
                friOrSat = false;
            }

            if (nodes.item(i++).getNodeValue() == "true") {
                hungry = true;
            } else {
                hungry = false;
            }
            
            if (nodes.item(i).getNodeValue()=="some") {
                guests=Guests.SOME;
            } else if(nodes.item(i).getNodeValue()=="full"){
guests=Guests.FULL;
            }
            else {
                guests=Guests.NONE;
            }
            i++;
            
            if (nodes.item(i).getNodeValue()=="cheap") {
                price=Price.CHEAP;
            } else {

            }

            System.out.println(nodes.item(i).getChildNodes().item(0)
                    .getNodeValue());
        }
        return null;

    }
}
