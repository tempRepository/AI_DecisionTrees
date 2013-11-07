package pl.lodz.uni.math.decisionTrees;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import pl.lodz.uni.math.decisionTrees.attributes.Alternate;
import pl.lodz.uni.math.decisionTrees.attributes.Bar;
import pl.lodz.uni.math.decisionTrees.attributes.Estimate;
import pl.lodz.uni.math.decisionTrees.attributes.FriOrSat;
import pl.lodz.uni.math.decisionTrees.attributes.GuestsAttribute;
import pl.lodz.uni.math.decisionTrees.attributes.Hungry;
import pl.lodz.uni.math.decisionTrees.attributes.PriceAttribute;
import pl.lodz.uni.math.decisionTrees.attributes.Raining;
import pl.lodz.uni.math.decisionTrees.attributes.Reservation;
import pl.lodz.uni.math.decisionTrees.attributes.TreeAttribute;
import pl.lodz.uni.math.decisionTrees.attributes.TypeAttribute;

public class ApplicationWindow {

    private JFrame frmDecisionTreesfilipjerzyna;
    private Tree tree;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ApplicationWindow window = new ApplicationWindow();
                    window.frmDecisionTreesfilipjerzyna.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * @throws IOException 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     * @throws XPathExpressionException 
     */
    public ApplicationWindow() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     * @throws IOException 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     * @throws XPathExpressionException 
     */
    private void initialize() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
        frmDecisionTreesfilipjerzyna = new JFrame();
        frmDecisionTreesfilipjerzyna.setTitle("DTrees -Filip Jerzyna");
       
        frmDecisionTreesfilipjerzyna.setBounds(100, 100, 257, 384);
        frmDecisionTreesfilipjerzyna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton btnShowGraph = new JButton("Show graph");
        btnShowGraph.setBounds(5, 320, 237, 25);
        btnShowGraph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame tempFrame=new JFrame();
                tempFrame.getContentPane().add(new LoadImage());
                tempFrame.pack();
               // tempFrame.setDefaultCloseOperation(JFrame.ABORT);
                tempFrame.setVisible(true);
            }
        });
        frmDecisionTreesfilipjerzyna.getContentPane().setLayout(null);
        frmDecisionTreesfilipjerzyna.getContentPane().add(btnShowGraph);
        
        JLabel lblAlternate = new JLabel("Alternate");
        lblAlternate.setBounds(12, 5, 448, 15);
        frmDecisionTreesfilipjerzyna.getContentPane().add(lblAlternate);

    
        
        JLabel lblBar = new JLabel("Bar");
        lblBar.setBounds(12, 30, 70, 15);
        frmDecisionTreesfilipjerzyna.getContentPane().add(lblBar);
        
        final JComboBox cbBar = new JComboBox();
        cbBar.setModel(new DefaultComboBoxModel(BooleanEnum.values()));
        cbBar.setBounds(108, 30, 102, 24);
        frmDecisionTreesfilipjerzyna.getContentPane().add(cbBar);
        
        JLabel lblFriOrSat = new JLabel("FriOrSat");
        lblFriOrSat.setBounds(12, 55, 70, 15);
        frmDecisionTreesfilipjerzyna.getContentPane().add(lblFriOrSat);
        
        final JComboBox cbFriOrSat = new JComboBox();
        cbFriOrSat.setModel(new DefaultComboBoxModel(BooleanEnum.values()));
        cbFriOrSat.setBounds(108, 55, 102, 24);
        frmDecisionTreesfilipjerzyna.getContentPane().add(cbFriOrSat);
        
        JLabel lblHungry = new JLabel("Hungry");
        lblHungry.setBounds(12, 80, 70, 15);
        frmDecisionTreesfilipjerzyna.getContentPane().add(lblHungry);
        
        final JComboBox cbHungry = new JComboBox();
        cbHungry.setModel(new DefaultComboBoxModel(BooleanEnum.values()));
        cbHungry.setBounds(108, 80, 102, 24);
        frmDecisionTreesfilipjerzyna.getContentPane().add(cbHungry);
        
        JLabel lblGuests = new JLabel("Guests");
        lblGuests.setBounds(12, 105, 70, 15);
        frmDecisionTreesfilipjerzyna.getContentPane().add(lblGuests);
        
        final JComboBox cbGuests = new JComboBox();
        cbGuests.setModel(new DefaultComboBoxModel(Guests.values()));
        cbGuests.setBounds(108, 105, 102, 24);
        frmDecisionTreesfilipjerzyna.getContentPane().add(cbGuests);
        
        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(12, 130, 70, 15);
        frmDecisionTreesfilipjerzyna.getContentPane().add(lblPrice);
        
        final JComboBox cbPrice = new JComboBox();
        cbPrice.setModel(new DefaultComboBoxModel(Price.values()));
        cbPrice.setBounds(108, 130, 102, 24);
        frmDecisionTreesfilipjerzyna.getContentPane().add(cbPrice);
        
        JLabel lblRaining = new JLabel("Rain");
        lblRaining.setBounds(12, 155, 70, 15);
        frmDecisionTreesfilipjerzyna.getContentPane().add(lblRaining);
        
        final JComboBox cbRain = new JComboBox();
        cbRain.setModel(new DefaultComboBoxModel(BooleanEnum.values()));
        cbRain.setBounds(108, 155, 102, 24);
        frmDecisionTreesfilipjerzyna.getContentPane().add(cbRain);
        
        JLabel lblReservation = new JLabel("Reservation");
        lblReservation.setBounds(12, 180, 90, 15);
        frmDecisionTreesfilipjerzyna.getContentPane().add(lblReservation);
        
        final JComboBox cbReservation = new JComboBox();
        cbReservation.setModel(new DefaultComboBoxModel(BooleanEnum.values()));
        cbReservation.setBounds(108, 180, 102, 24);
        frmDecisionTreesfilipjerzyna.getContentPane().add(cbReservation);
        
        JLabel lblType = new JLabel("Type");
        lblType.setBounds(12, 205, 70, 15);
        frmDecisionTreesfilipjerzyna.getContentPane().add(lblType);
        
        final JComboBox cbType = new JComboBox();
        cbType.setModel(new DefaultComboBoxModel(Type.values()));
        cbType.setBounds(108, 205, 102, 24);
        frmDecisionTreesfilipjerzyna.getContentPane().add(cbType);
        
        JLabel lblEstimation = new JLabel("Estimate");
        lblEstimation.setBounds(12, 230, 70, 15);
        frmDecisionTreesfilipjerzyna.getContentPane().add(lblEstimation);
        
        final JComboBox cbEstimation = new JComboBox();
        cbEstimation.setModel(new DefaultComboBoxModel(WaitEstimate.values()));
        cbEstimation.setBounds(108, 230, 102, 24);
        frmDecisionTreesfilipjerzyna.getContentPane().add(cbEstimation);
        
        final JComboBox cbAlternate = new JComboBox();
        cbAlternate.setModel(new DefaultComboBoxModel(BooleanEnum.values()));
        cbAlternate.setBounds(108, 5, 102, 24);
        frmDecisionTreesfilipjerzyna.getContentPane().add(cbAlternate);
        
        JButton btnAnswer = new JButton("Check");
        btnAnswer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {                
                JOptionPane.showMessageDialog(null, tree.getDecision(new Example(new Boolean(cbAlternate.getSelectedItem().toString()), new Boolean(cbBar.getSelectedItem().toString()), new Boolean(cbFriOrSat.getSelectedItem().toString()), new Boolean(cbHungry.getSelectedItem().toString()), (Guests)cbGuests.getSelectedItem(), (Price)cbPrice.getSelectedItem(), new Boolean(cbRain.getSelectedItem().toString()), new Boolean(cbReservation.getSelectedItem().toString()), (Type)cbType.getSelectedItem(), (WaitEstimate)cbEstimation.getSelectedItem(), null)));
            }
        });
        btnAnswer.setBounds(93, 270, 117, 25);
        frmDecisionTreesfilipjerzyna.getContentPane().add(btnAnswer);
        
        
        
    ArrayList<Example> trainingSet = Tree.getExamples("examples.xml");
      /*  ArrayList<Example> trainingSet = new ArrayList<>();
        
        trainingSet.add(new Example(true, false, false, true, Guests.SOME, Price.EXPENSIVE, false, true, Type.FRENCH, WaitEstimate.TO10, true));
        trainingSet.add(new Example(true, false, false, true, Guests.FULL, Price.CHEAP, false, false, Type.THAI, WaitEstimate.TO60, false));
        trainingSet.add(new Example(false, true, false, false, Guests.SOME, Price.CHEAP, false, false, Type.BURGER, WaitEstimate.TO10, true));
        trainingSet.add(new Example(true, false, true, true, Guests.FULL, Price.CHEAP, true, false, Type.THAI, WaitEstimate.TO30, true));
        trainingSet.add(new Example(true, false, true, false, Guests.FULL, Price.EXPENSIVE, false, true, Type.FRENCH, WaitEstimate.MORETHAN60, false));
        trainingSet.add(new Example(false, true, false, true, Guests.SOME, Price.MEDIUM, true, true, Type.ITALIAN, WaitEstimate.TO10, true));
        trainingSet.add(new Example(false, true, false, false, Guests.NONE, Price.CHEAP, true, false, Type.BURGER, WaitEstimate.TO10, false));
        trainingSet.add(new Example(false, false, false, true, Guests.SOME, Price.MEDIUM, true, true, Type.THAI, WaitEstimate.TO10, true));
        trainingSet.add(new Example(false, true, true, false, Guests.FULL, Price.CHEAP, true, false, Type.BURGER, WaitEstimate.MORETHAN60, false));
        trainingSet.add(new Example(true, true, true, true, Guests.FULL, Price.EXPENSIVE, false, true, Type.ITALIAN, WaitEstimate.TO30, false));
        trainingSet.add(new Example(false, false, false, false, Guests.NONE, Price.CHEAP , false, false, Type.THAI, WaitEstimate.TO10, false));
        trainingSet.add(new Example(true, true, true, true, Guests.FULL, Price.CHEAP, false, false, Type.BURGER, WaitEstimate.TO60, true));
*/

        ArrayList<TreeAttribute> attributes=new ArrayList<>();
       attributes.add(new Alternate());
       attributes.add(new Bar());   
       attributes.add(new FriOrSat());
       attributes.add(new Hungry());
       attributes.add(new GuestsAttribute());
       attributes.add(new PriceAttribute());
       attributes.add(new Raining());
       attributes.add(new Reservation());
       attributes.add(new TypeAttribute());
       attributes.add(new Estimate());

 tree=(Tree) Tree.decisionTreeLearning(trainingSet, attributes, new ArrayList<Example>());  
 Graph.generateGraphImage(tree);
    }
}
