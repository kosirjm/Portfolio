import java.awt.*;
import javax.swing.*;

public class MainGUI {

	private JDialog dialog = new JDialog(); 

	public MainGUI() {
		dialog.setTitle("Board Game");
		JPanel panelMain = new JPanel (new GridBagLayout()); // holds EVERYTHING, the main panel

		GridBagConstraints cs = new GridBagConstraints(); // used to size panels within the panels. Panception
		cs.fill = GridBagConstraints.BOTH; // resizes horizontally and vertically if gui is changed

		// ----------------------------------------------------- START WEST PANEL

		JPanel panelWestOne = new JPanel();
		panelWestOne.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelWestOne.setBackground(Color.cyan);
		panelWestOne.add (new JLabel ("West One"));
		cs.gridx = 0; cs.gridy = 1; cs.weightx = 1; cs.weighty = 1; cs.gridheight = 1; cs.gridwidth = 1;
		panelMain.add(panelWestOne,cs);

		JPanel panelWestTwo = new JPanel();
		panelWestTwo.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelWestTwo.setBackground(Color.cyan);
		panelWestTwo.add (new JLabel ("West Two"));
		cs.gridx = 0; cs.gridy = 2;
		panelMain.add(panelWestTwo,cs);

		JPanel panelWestThree = new JPanel();
		panelWestThree.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelWestThree.setBackground(Color.cyan);
		panelWestThree.add (new JLabel ("West Three"));
		cs.gridx = 0; cs.gridy = 3;
		panelMain.add(panelWestThree,cs);

		JPanel panelWestFour = new JPanel();
		panelWestFour.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelWestFour.setBackground(Color.cyan);
		panelWestFour.add (new JLabel ("West Four"));
		cs.gridx = 0; cs.gridy = 4;
		panelMain.add(panelWestFour,cs);

		JPanel panelWestFive = new JPanel();
		panelWestFive.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelWestFive.setBackground(Color.cyan);
		panelWestFive.add (new JLabel ("West Five"));
		cs.gridx = 0; cs.gridy = 5;
		panelMain.add(panelWestFive,cs);

		JPanel panelWestSix = new JPanel();
		panelWestSix.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelWestSix.setBackground(Color.cyan);
		panelWestSix.add (new JLabel ("West Six"));
		cs.gridx = 0; cs.gridy = 6;
		panelMain.add(panelWestSix,cs);

		JPanel panelWestSeven = new JPanel();
		panelWestSeven.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelWestSeven.setBackground(Color.cyan);
		panelWestSeven.add (new JLabel ("West Seven"));
		cs.gridx = 0; cs.gridy = 7;
		panelMain.add(panelWestSeven,cs);

		// ----------------------------------------------------- END WEST PANEL
		// -----
		// ----------------------------------------------------- START EAST PANEL
		
		JPanel panelEastOne = new JPanel();
		panelEastOne.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelEastOne.setBackground(Color.red);
		panelEastOne.add (new JLabel ("East One"));
		cs.gridx = 6; cs.gridy = 1;
		panelMain.add(panelEastOne,cs);

		JPanel panelEastTwo = new JPanel();
		panelEastTwo.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelEastTwo.setBackground(Color.red);
		panelEastTwo.add (new JLabel ("East Two"));
		cs.gridx = 6; cs.gridy = 2;
		panelMain.add(panelEastTwo,cs);

		JPanel panelEastThree = new JPanel();
		panelEastThree.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelEastThree.setBackground(Color.red);
		panelEastThree.add (new JLabel ("East Three"));
		cs.gridx = 6; cs.gridy = 3;
		panelMain.add(panelEastThree,cs);

		JPanel panelEastFour = new JPanel();
		panelEastFour.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelEastFour.setBackground(Color.red);
		panelEastFour.add (new JLabel ("East Four"));
		cs.gridx = 6; cs.gridy = 4;
		panelMain.add(panelEastFour,cs);

		JPanel panelEastFive = new JPanel();
		panelEastFive.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelEastFive.setBackground(Color.red);
		panelEastFive.add (new JLabel ("East Five"));
		cs.gridx = 6; cs.gridy = 5;
		panelMain.add(panelEastFive,cs);

		JPanel panelEastSix = new JPanel();
		panelEastSix.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelEastSix.setBackground(Color.red);
		panelEastSix.add (new JLabel ("East Six"));
		cs.gridx = 6; cs.gridy = 6;
		panelMain.add(panelEastSix,cs);

		JPanel panelEastSeven = new JPanel();
		panelEastSeven.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelEastSeven.setBackground(Color.red);
		panelEastSeven.add (new JLabel ("East Seven"));
		cs.gridx = 6; cs.gridy = 7;
		panelMain.add(panelEastSeven,cs);

		// ----------------------------------------------------- END EAST PANEL
		// -----
		// ----------------------------------------------------- START SOUTH PANEL

		JPanel panelSouthOne = new JPanel();
		panelSouthOne.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelSouthOne.setBackground(Color.green);
		panelSouthOne.add (new JLabel ("South One"));
		cs.gridx = 0; cs.gridy = 8;
		panelMain.add(panelSouthOne,cs);

		JPanel panelSouthTwo = new JPanel();
		panelSouthTwo.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelSouthTwo.setBackground(Color.green);
		panelSouthTwo.add (new JLabel ("South Two"));
		cs.gridx = 1; cs.gridy = 8;
		panelMain.add(panelSouthTwo,cs);

		JPanel panelSouthThree = new JPanel();
		panelSouthThree.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelSouthThree.setBackground(Color.green);
		panelSouthThree.add (new JLabel ("South Three"));
		cs.gridx = 2; cs.gridy = 8;
		panelMain.add(panelSouthThree,cs);

		JPanel panelSouthFour = new JPanel();
		panelSouthFour.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelSouthFour.setBackground(Color.green);
		panelSouthFour.add (new JLabel ("South Four"));
		cs.gridx = 3; cs.gridy = 8;
		panelMain.add(panelSouthFour,cs);

		JPanel panelSouthFive = new JPanel();
		panelSouthFive.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelSouthFive.setBackground(Color.green);
		panelSouthFive.add (new JLabel ("South Five"));
		cs.gridx = 4; cs.gridy = 8;
		panelMain.add(panelSouthFive,cs);

		JPanel panelSouthSix = new JPanel();
		panelSouthSix.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelSouthSix.setBackground(Color.green);
		panelSouthSix.add (new JLabel ("South Six"));
		cs.gridx = 5; cs.gridy = 8;
		panelMain.add(panelSouthSix,cs);

		JPanel panelSouthSeven = new JPanel();
		panelSouthSeven.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelSouthSeven.setBackground(Color.green);
		panelSouthSeven.add (new JLabel ("South Seven"));
		cs.gridx = 6; cs.gridy = 8;
		panelMain.add(panelSouthSeven,cs);

		JButton rollBtn = new JButton("Roll");
		JLabel labelSouthBottom = new JLabel("South Label Bottom");

		cs.gridx=0;cs.gridy=9;
		panelMain.add(rollBtn, cs);
		cs.gridx=5;cs.gridy=9;
		panelMain.add(labelSouthBottom, cs);

		// ----------------------------------------------------- END SOUTH PANEL
		// -----
		// ----------------------------------------------------- START NORTH PANEL
		
		JPanel panelNorthOne = new JPanel();
		panelNorthOne.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelNorthOne.setBackground(Color.yellow);
		panelNorthOne.add (new JLabel ("North One"));
		cs.gridx = 0; cs.gridy = 0;
		panelMain.add(panelNorthOne,cs);

		JPanel panelNorthTwo = new JPanel();
		panelNorthTwo.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelNorthTwo.setBackground(Color.yellow);
		panelNorthTwo.add (new JLabel ("North Two"));
		cs.gridx = 1; cs.gridy = 0;
		panelMain.add(panelNorthTwo,cs);

		JPanel panelNorthThree = new JPanel();
		panelNorthThree.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelNorthThree.setBackground(Color.yellow);
		panelNorthThree.add (new JLabel ("North Three"));
		cs.gridx = 2; cs.gridy = 0;
		panelMain.add(panelNorthThree,cs);

		JPanel panelNorthFour = new JPanel();
		panelNorthFour.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelNorthFour.setBackground(Color.yellow);
		panelNorthFour.add (new JLabel ("North Four"));
		cs.gridx = 3; cs.gridy = 0;
		panelMain.add(panelNorthFour,cs);

		JPanel panelNorthFive = new JPanel();
		panelNorthFive.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelNorthFive.setBackground(Color.yellow);
		panelNorthFive.add (new JLabel ("North Five"));
		cs.gridx = 4; cs.gridy = 0;
		panelMain.add(panelNorthFive,cs);

		JPanel panelNorthSix = new JPanel();
		panelNorthSix.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelNorthSix.setBackground(Color.yellow);
		panelNorthSix.add (new JLabel ("North Six"));
		cs.gridx = 5; cs.gridy = 0;
		panelMain.add(panelNorthSix,cs);

		JPanel panelNorthSeven = new JPanel();
		panelNorthSeven.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelNorthSeven.setBackground(Color.yellow);
		panelNorthSeven.add (new JLabel ("North Seven"));
		cs.gridx = 6; cs.gridy = 0;
		panelMain.add(panelNorthSeven,cs);
		
		// ----------------------------------------------------- END NORTH PANEL
		// -----
		// ----------------------------------------------------- START CENTER PANEL
		
		JButton ButtonCenterGood = new JButton("Good Luck");
		cs.gridx = 1; cs.gridy = 1; cs.gridheight = 2; cs.gridwidth = 2;
		panelMain.add(ButtonCenterGood,cs);
		JButton ButtonCenterBad = new JButton("Bad Luck");
		cs.gridx = 4; cs.gridy = 1; 
		panelMain.add(ButtonCenterBad,cs);

		JPanel panelCenterBottomData = new JPanel();
		panelCenterBottomData.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		panelCenterBottomData.setBackground(Color.orange);
		panelCenterBottomData.add (new JLabel ("Data Goes Here"));
		cs.gridx = 2; cs.gridy = 4; cs.gridheight = 2; cs.gridwidth = 3;
		panelMain.add(panelCenterBottomData, cs);

		// ----------------------------------------------------- END CENTER PANEL

		panelMain.setBackground(Color.lightGray);
		dialog.add(panelMain);
		
		dialog.pack();
		dialog.setResizable(true);
		dialog.setSize(1000,1000);
		dialog.setLocation(0, 0);
		dialog.setVisible(true);  
	}
}
