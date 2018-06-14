import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The LoginGUI which starts the program, this is the class
 * @author josephocchionero
 */
public class NameLoginGUI {

	private JDialog dialog = new JDialog();
	private JTextField textField1, textField2, textField3, textField4;
	private JLabel label1, label2, label3, label4;
    private JButton btnSelect;
    private JButton btnCancel;

    public NameLoginGUI(int players) {
    	dialog.setTitle("Player Names:");

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.yellow);
		panel.setBorder (BorderFactory.createLineBorder (Color.black, 2));
        
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.BOTH;
        dialog.add(panel);

        if (players >= 0) {
        	cs.gridx = 0; cs.gridy = 0;
        	label1 = new JLabel(" Player One's Name: ");
        	panel.add(label1,cs);

        	cs.gridx = 1; cs.gridy = 0;
        	textField1 = new JTextField("Enter Name Here");
        	panel.add(textField1,cs);

        	cs.gridx = 0; cs.gridy = 1;
        	label2 = new JLabel(" Player Two's Name: ");
        	panel.add(label2,cs);
        	
        	cs.gridx = 1; cs.gridy = 1;
        	textField2 = new JTextField("Enter Name Here");
        	panel.add(textField2,cs);

        	if (players >= 3) {
        		cs.gridx = 0; cs.gridy = 2;
        		label3 = new JLabel(" Player Three's Name: ");
        		panel.add(label3,cs);
        		
        		cs.gridx = 1; cs.gridy = 2;
        		textField3 = new JTextField("Enter Name Here");
        		panel.add(textField3,cs);

        		if (players == 4) {
        			cs.gridx = 0; cs.gridy = 3;
        			label4 = new JLabel(" Player Four's Name: ");
        			panel.add(label4,cs);
        			
        			cs.gridx = 1; cs.gridy = 3;
        			textField4 = new JTextField("Enter Name Here");
        			panel.add(textField4,cs);
        		}
        	}
        }

        btnSelect = new JButton("Select"); // listener for the seclect button
        btnSelect.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Do something with the names 
        		MainGUI GUI = new MainGUI();
        	}
            });
        
        btnCancel = new JButton("Cancel"); // listener for the exit button
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dialog.dispose();
            }});
             
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
		buttonPanel.setBorder (BorderFactory.createLineBorder (Color.black, 2));
        
        buttonPanel.add(btnSelect);
        buttonPanel.add(btnCancel);

        dialog.getContentPane().add(panel, BorderLayout.CENTER);
        dialog.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        dialog.pack();
        dialog.setResizable(true);
        dialog.setSize(300,250);
        dialog.setLocation(500, 350);
        dialog.setVisible(true);
    }
}