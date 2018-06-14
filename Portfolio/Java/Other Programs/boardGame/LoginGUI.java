import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The LoginGUI which starts the program, this is the class
 * @author josephocchionero
 */
public class LoginGUI {

	private JDialog dialog = new JDialog();
	private JRadioButton twoButton, threeButton, fourButton, fiveButton;
	private ButtonGroup group;
	private JLabel label;
    private JButton btnLogin;
    private JButton btnCancel;
    private ArrayList<Player> playerList = new ArrayList<Player>();


    public LoginGUI() {
    	dialog.setTitle("Player Option Menu");

		GridBagConstraints cs = new GridBagConstraints(); 
		cs.fill = GridBagConstraints.BOTH;

        JPanel RadioPanel = new JPanel(new GridBagLayout());
        RadioPanel.setBackground(Color.yellow);
		RadioPanel.setBorder (BorderFactory.createLineBorder (Color.black, 2));
        dialog.add(RadioPanel);
        
        label = new JLabel(" Please Select The Amount of Players:");
        cs.gridx = 0; cs.gridy = 0;
        RadioPanel.add(label, cs);
             
        cs.gridx = 0; cs.gridy = 1;
        twoButton = new JRadioButton("Two Players", false);
        RadioPanel.add(twoButton, cs);

        cs.gridx = 0; cs.gridy = 2;
        threeButton = new JRadioButton("Three Players", false);
        RadioPanel.add(threeButton, cs);
        
        cs.gridx = 0; cs.gridy = 3;
        fourButton = new JRadioButton("Four Players", false); 
        RadioPanel.add(fourButton, cs);


             
        group = new ButtonGroup();
        group.add(twoButton);
        group.add(threeButton);
        group.add(fourButton);
        
   
        btnLogin = new JButton("Select"); // listener for the select button
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                if (twoButton.isSelected()) {
                	NameLoginGUI GUI = new NameLoginGUI(2);       	
                }
                else if (threeButton.isSelected()) {
                	NameLoginGUI GUI = new NameLoginGUI(3);
                }
                else if (fourButton.isSelected()) {
                	NameLoginGUI GUI = new NameLoginGUI(4);
                }
                else {
                	JOptionPane.showMessageDialog(dialog, "Please select an option");
                }
            }});
        
        btnCancel = new JButton("Cancel"); // listener for the exit button
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dialog.dispose();
            }});
             
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
		buttonPanel.setBorder (BorderFactory.createLineBorder (Color.black, 2));
		
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCancel);

        dialog.getContentPane().add(RadioPanel, BorderLayout.CENTER);
        dialog.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        dialog.pack();
        dialog.setResizable(true);
        dialog.setSize(300,250);
        dialog.setLocation(500, 350);
        dialog.setVisible(true);
    }
}