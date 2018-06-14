import java.awt.List;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedSet;

import javax.swing.JOptionPane;


public class BoardGame 
{

	public void startUp(String fileName) 
	{
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream(new FileInputStream(fileName));

			while(true) 
			{
				System.out.println(input.readInt());
			}
		}
		catch (EOFException e) {

		}
		catch (IOException e) {
			System.out.println("Problem with IO");
		}
		finally
		{
			try {
				if (input!=null) {
					input.close();
				}
			}
			catch(IOException e) {
			}		
		}

	}

	public closeDown() 
	{

	}
	public static void save(ArrayList<Player> playerList, ArrayList<Space> spaceList, ArrayList<Card> cardList, String fileName) {
		try {
			ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(fileName));
			ois.writeObject(playerList);
			ois.writeObject(spaceList);
			ois.writeObject(cardList);
			ois.close();
		}
		catch (Exception e) {
			System.out.println("Error with Saving");
			//e.printStackTrace(); use this if we need to
			System.exit(0);
		}
	}
	public static void load(String fileName) {
		try {	
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			//Image changeFile = (Image)ois.readObject();
			//picture = changeFile;
			//filename = fileName;
			ois.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, " Error in opening file. ");
			e.printStackTrace();
		}
	}
	public int roll(int position) 
	{
		Random generator = new Random();
		int roll = generator.nextInt(6) + 1;

		return position + roll;
	}

	public ArrayList<Object> shuffle(ArrayList<Object> object) 
	{
		ArrayList<Object> temp = new ArrayList<Object>();
		for(int i = 1; i < (object.size() - 1); i++)
		{
			temp.add(object.get(i));
			object.remove(i);
		}
		Collections.shuffle(temp);

		temp.add(object.get(object.size()-1));
		object.remove(object.get(object.size()-1));
		object.addAll(temp);

		return object;
	}

	public void returnPostion(Player player, ArrayList<ReturnSpace> returnSpace) 
	{
	player.setSpace(returnSpace.get(returnSpace.size() - 1));
	}
	public move() 
	{
		// Gui
	}

	public void purchase(Player player, Property property) 
	{
		int cost = property.getPurchase();

		player.setScore(player.getScore() - cost);

		property.setOwned(true);
	}

	public void GoodBad(Player player, ArrayList<Card> cards) 
	{
		//Gui Message
		Card temp = cards.get(0);
		temp.action(player);
		cards.remove(0);
		cards.add(temp);
	}

	public Stats() 
	{

	}



}
