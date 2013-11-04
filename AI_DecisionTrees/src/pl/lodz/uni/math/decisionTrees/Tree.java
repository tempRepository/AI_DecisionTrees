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
                // System.out.println("Attributes: "+attributes.size()+" "+attribute.toString()+" "+informationGain);
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
        Document doc = builder.parse(fileName);
        XPath xpath = XPathFactory.newInstance().newXPath();
        // XPath Query for showing all nodes value
        XPathExpression expr = xpath.compile("//example");

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        ArrayList<Example> examples = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {

            boolean alternate = new Boolean(nodes.item(i).getAttributes()
                    .getNamedItem("alternative").getNodeValue());
            boolean bar = new Boolean(nodes.item(i).getAttributes()
                    .getNamedItem("bar").getNodeValue());
            boolean friOrSat = new Boolean(nodes.item(i).getAttributes()
                    .getNamedItem("friOrSat").getNodeValue());
            boolean hungry = new Boolean(nodes.item(i).getAttributes()
                    .getNamedItem("hungry").getNodeValue());
            Guests guests;
            Price price;
            boolean raining = new Boolean(nodes.item(i).getAttributes()
                    .getNamedItem("rain").getNodeValue());
            boolean reservation = new Boolean(nodes.item(i).getAttributes()
                    .getNamedItem("reservation").getNodeValue());
            Type type;
            WaitEstimate estimate;
            boolean finalDecision = new Boolean(nodes.item(i).getAttributes()
                    .getNamedItem("finalDecision").getNodeValue());
            if (nodes.item(i).getAttributes().getNamedItem("guests")
                    .getNodeValue().equals("full")) {
                guests = Guests.FULL;
            } else if (nodes.item(i).getAttributes().getNamedItem("guests")
                    .getNodeValue().equals("some")) {
                guests = Guests.SOME;
            } else {
                guests = Guests.NONE;
            }

            if (nodes.item(i).getAttributes().getNamedItem("estimation")
                    .getNodeValue().equals("to10")) {
                estimate = WaitEstimate.TO10;
            } else if (nodes.item(i).getAttributes().getNamedItem("estimation")
                    .getNodeValue().equals("to30")) {
                estimate = WaitEstimate.TO30;
            } else if (nodes.item(i).getAttributes().getNamedItem("estimation")
                    .getNodeValue().equals("to60")) {
                estimate = WaitEstimate.TO60;
            } else {
                estimate = WaitEstimate.MORETHAN60;
            }

            if (nodes.item(i).getAttributes().getNamedItem("price")
                    .getNodeValue().equals("cheap")) {
                price = Price.CHEAP;
            } else if (nodes.item(i).getAttributes().getNamedItem("price")
                    .getNodeValue().equals("medium")) {
                price = Price.MEDIUM;
            } else {
                price = Price.EXPENSIVE;
            }

            if (nodes.item(i).getAttributes().getNamedItem("type")
                    .getNodeValue().equals("burger")) {
                type = Type.BURGER;
            } else if (nodes.item(i).getAttributes().getNamedItem("type")
                    .getNodeValue().equals("thai")) {
                type = Type.THAI;
            } else if (nodes.item(i).getAttributes().getNamedItem("type")
                    .getNodeValue().equals("italian")) {
                type = Type.ITALIAN;
            } else {
                type = Type.FRENCH;
            }
            examples.add(new Example(alternate, bar, friOrSat, hungry, guests,
                    price, raining, reservation, type, estimate, finalDecision));
            // System.out.println(nodes.item(i).getAttributes().getNamedItem("name").getNodeValue());
            // System.out.println(nodes.item(i).getChildNodes().item(0)
            // .getNodeValue());
        }
        return examples;

    }

    public Object getDecision(Example example) {
        ArrayList<Example> temp = new ArrayList<>();
        temp.add(example);
        LinkedHashMap<Enum, Object> answer = root.getPossibilities(temp);
        Enum properKey=null;
        for (Enum key : answer.keySet()) {
            
          
            if (((ArrayList<Example>)answer.get(key)).size()>0 && ((ArrayList<Example>)answer.get(key)).get(0)==example) {
                properKey=key;
               break;
            }
        }
       // System.out.println(properKey);
        if (possibilities.get(properKey) instanceof Boolean) {
           // System.out.println(properKey.toString());
           // System.out.println(possibilities.get(properKey));
            return possibilities.get(properKey);
        } else {
           // System.out.println(properKey.toString());
            return ((Tree)possibilities.get(properKey)).getDecision(example);
        }
        
    }
}
