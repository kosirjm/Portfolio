import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The SystemRun Class that allows my program to start
 * @author josephocchionero
 *
 */
public class SystemRun {
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Board Game System");
		
		final JPanel panelMain = new JPanel(new GridBagLayout());
        panelMain.setBackground(Color.yellow);
        panelMain.setBorder(BorderFactory.createLineBorder(Color.black,2));
        
		final GridBagConstraints cs = new GridBagConstraints(); 
		cs.fill = GridBagConstraints.BOTH;
		
		cs.gridx = 0; cs.gridy = 1;
		final JLabel label = new JLabel(" Please Select An Option:");
		panelMain.add(label,cs);
		
		cs.gridx = 0; cs.gridy = 2;
		final JButton btnNew = new JButton("New Game");
		panelMain.add(btnNew,cs);
		
		cs.gridx = 0; cs.gridy = 3;
		final JButton btnLoad = new JButton("Load Previous Game");
		panelMain.add(btnLoad,cs);
		
		btnNew.addActionListener(
				new ActionListener(){ // this listener makes my program go right to the login gui
					public void actionPerformed(ActionEvent e) {
						LoginGUI loginDlg = new LoginGUI();
					}});
		btnLoad.addActionListener(
				new ActionListener(){ // this listener makes my program go right to the load of maingui
					public void actionPerformed(ActionEvent e) {
						 MainGUI lastGame = new MainGUI();
						 // More Stuff to go here
					}});
		
		frame.add(panelMain);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setLocation(500, 350);
		frame.setVisible(true);
	}
}