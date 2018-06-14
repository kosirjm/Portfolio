
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the boggle class which does most of the work It creates the gui holds
 * the recursive methods to read what words are in the tray, initilizes the
 * dice, and rolls them RECURSION METHOD TAKES ABOUT A MINUTE TO FIND ALL WORDS
 *
 * @author Jonathan Kosir
 * @date December 02, 2012 CSE 271
 */
public class Boggle extends javax.swing.JFrame {

    private static final int ROWS = 4;
    private static final int COLS = 4;
    private Space[][] tray = new Space[ROWS][COLS];
    private Die[] dice = new Die[26];
    private ArrayList<String> dictionary = new ArrayList<String>();
    private String[] letters = new String[6];
    private ArrayList<String> twoLetPre = new ArrayList<String>();
    private ArrayList<String> threeLetPre = new ArrayList<String>();
    private ArrayList<String> fourLetPre = new ArrayList<String>();
    private ArrayList<String> fiveLetPre = new ArrayList<String>();
    private ArrayList<String> sixLetPre = new ArrayList<String>();
    private ArrayList<String> words = new ArrayList<String>();
    private boolean bogglePressed = false;

    /**
     * This creates a new boggle form as well as anitialize all the dice in the
     * game
     */
    public Boggle() {
        initComponents();

        //Dice Assigning the 26 dice there sides--------------------
        letters[0] = "a";
        letters[1] = "a";
        letters[2] = "a";
        letters[3] = "f";
        letters[4] = "r";
        letters[5] = "s";
        Die die1 = new Die(new Die(letters, false));
        dice[0] = die1;

        letters[0] = "a";
        letters[1] = "a";
        letters[2] = "e";
        letters[3] = "e";
        letters[4] = "e";
        letters[5] = "e";
        Die die2 = new Die(new Die(letters, false));
        dice[1] = die2;

        letters[0] = "a";
        letters[1] = "a";
        letters[2] = "f";
        letters[3] = "i";
        letters[4] = "r";
        letters[5] = "s";
        Die die3 = new Die(new Die(letters, false));
        dice[2] = die3;

        letters[0] = "a";
        letters[1] = "d";
        letters[2] = "e";
        letters[3] = "n";
        letters[4] = "n";
        letters[5] = "n";
        Die die4 = new Die(new Die(letters, false));
        dice[3] = die4;

        letters[0] = "a";
        letters[1] = "e";
        letters[2] = "e";
        letters[3] = "e";
        letters[4] = "e";
        letters[5] = "m";
        Die die5 = new Die(new Die(letters, false));
        dice[4] = die5;

        letters[0] = "a";
        letters[1] = "e";
        letters[2] = "e";
        letters[3] = "g";
        letters[4] = "m";
        letters[5] = "u";
        Die die6 = new Die(new Die(letters, false));
        dice[5] = die6;

        letters[0] = "a";
        letters[1] = "e";
        letters[2] = "g";
        letters[3] = "m";
        letters[4] = "n";
        letters[5] = "n";
        Die die7 = new Die(new Die(letters, false));
        dice[6] = die7;

        letters[0] = "a";
        letters[1] = "f";
        letters[2] = "i";
        letters[3] = "r";
        letters[4] = "s";
        letters[5] = "y";
        Die die8 = new Die(new Die(letters, false));
        dice[7] = die8;

        letters[0] = "b";
        letters[1] = "j";
        letters[2] = "k";
        letters[3] = "qu";
        letters[4] = "x";
        letters[5] = "z";
        Die die9 = new Die(new Die(letters, false));
        dice[8] = die9;

        letters[0] = "c";
        letters[1] = "c";
        letters[2] = "e";
        letters[3] = "n";
        letters[4] = "s";
        letters[5] = "t";
        Die die10 = new Die(new Die(letters, false));
        dice[9] = die10;

        letters[0] = "c";
        letters[1] = "e";
        letters[2] = "i";
        letters[3] = "i";
        letters[4] = "l";
        letters[5] = "t";
        Die die11 = new Die(new Die(letters, false));
        dice[10] = die11;

        letters[0] = "c";
        letters[1] = "e";
        letters[2] = "i";
        letters[3] = "l";
        letters[4] = "p";
        letters[5] = "t";
        Die die12 = new Die(new Die(letters, false));
        dice[11] = die12;

        letters[0] = "c";
        letters[1] = "e";
        letters[2] = "i";
        letters[3] = "p";
        letters[4] = "s";
        letters[5] = "t";
        Die die13 = new Die(new Die(letters, false));
        dice[12] = die13;

        letters[0] = "d";
        letters[1] = "d";
        letters[2] = "h";
        letters[3] = "n";
        letters[4] = "o";
        letters[5] = "t";
        Die die14 = new Die(new Die(letters, false));
        dice[13] = die14;

        letters[0] = "d";
        letters[1] = "h";
        letters[2] = "h";
        letters[3] = "l";
        letters[4] = "o";
        letters[5] = "r";
        Die die15 = new Die(new Die(letters, false));
        dice[14] = die15;

        letters[0] = "d";
        letters[1] = "h";
        letters[2] = "l";
        letters[3] = "n";
        letters[4] = "o";
        letters[5] = "r";
        Die die16 = new Die(new Die(letters, false));
        dice[15] = die16;

        letters[0] = "d";
        letters[1] = "h";
        letters[2] = "l";
        letters[3] = "n";
        letters[4] = "o";
        letters[5] = "r";
        Die die17 = new Die(new Die(letters, false));
        dice[16] = die17;

        letters[0] = "e";
        letters[1] = "i";
        letters[2] = "i";
        letters[3] = "i";
        letters[4] = "t";
        letters[5] = "t";
        Die die18 = new Die(new Die(letters, false));
        dice[17] = die18;

        letters[0] = "e";
        letters[1] = "m";
        letters[2] = "o";
        letters[3] = "t";
        letters[4] = "t";
        letters[5] = "t";
        Die die19 = new Die(new Die(letters, false));
        dice[18] = die19;

        letters[0] = "e";
        letters[1] = "n";
        letters[2] = "s";
        letters[3] = "s";
        letters[4] = "s";
        letters[5] = "u";
        Die die20 = new Die(new Die(letters, false));
        dice[19] = die20;

        letters[0] = "f";
        letters[1] = "i";
        letters[2] = "p";
        letters[3] = "r";
        letters[4] = "s";
        letters[5] = "y";
        Die die21 = new Die(new Die(letters, false));
        dice[20] = die21;

        letters[0] = "g";
        letters[1] = "o";
        letters[2] = "r";
        letters[3] = "r";
        letters[4] = "v";
        letters[5] = "w";
        Die die22 = new Die(new Die(letters, false));
        dice[21] = die22;

        letters[0] = "i";
        letters[1] = "p";
        letters[2] = "r";
        letters[3] = "r";
        letters[4] = "r";
        letters[5] = "y";
        Die die23 = new Die(new Die(letters, false));
        dice[22] = die23;

        letters[0] = "n";
        letters[1] = "o";
        letters[2] = "o";
        letters[3] = "t";
        letters[4] = "u";
        letters[5] = "w";
        Die die24 = new Die(new Die(letters, false));
        dice[23] = die24;

        letters[0] = "o";
        letters[1] = "o";
        letters[2] = "o";
        letters[3] = "t";
        letters[4] = "t";
        letters[5] = "u";
        Die die25 = new Die(new Die(letters, false));
        dice[24] = die25;

        letters[0] = "i";
        letters[1] = "k";
        letters[2] = "l";
        letters[3] = "m";
        letters[4] = "qu";
        letters[5] = "u";
        Die die26 = new Die(new Die(letters, false));
        dice[25] = die26;
        //End of dice initialization----------------------------------------------
    }

    /**
     * The main recursive method where all the magic happens. It finds all the
     * possible words in the current boggle tray THE RECURSION METHOD TAKES
     * APPROXIMATELY A MINUTE FOR ALL WORDS TO BE FOUND
     *
     * @param currentWord the word as currently is, even if not complete word
     * @param currentRow the row that the search is currently on
     * @param currentCol the col which the search is currently on
     * @throws FileNotFoundException
     */
    private void wordSearch(String currentWord, int currentRow, int currentCol) throws FileNotFoundException {

        //Checks if the row or col is out of bounds if is it returns and ends method
        //if not it checks if the current space that the search is on has been used
        //if it is it returns and ends method
        if (currentRow < 0 || currentRow >= ROWS
                || currentCol < 0 || currentCol >= COLS
                || tray[currentRow][currentCol].isUsed() == true) {
            return;
        }

        /*
		 * Next series of if statement is very pivotal for effincency.
		 * It Uses the created prefix dictionaries to check if the current word 
		 * of certain length matches a prefix of that amount of letters in that dictionary.
		 * If it doesn't it ends the method instead of still continuing on even though
		 * there is no hope for a word
         */
        //Checks if the current 2 letter word matches any "prefix" is two letter dictionary
        if (currentWord.length() == 2) {
            if (!twoLetPre.contains(currentWord)) {
                return;
            }
        }

        //Checks if the current 3 letter word matches any "prefix" is three letter dictionary
        if (currentWord.length() == 3) {
            if (!threeLetPre.contains(currentWord)) {
                return;
            }
        }

        //Checks if the current 4 letter word matches any "prefix" is three letter dictionary
        if (currentWord.length() == 4) {
            if (!fourLetPre.contains(currentWord)) {
                return;
            }
        }

        //Checks if the current 5 letter word matches any "prefix" is four letter dictionary
        if (currentWord.length() == 5) {
            if (!fiveLetPre.contains(currentWord)) {
                return;
            }
        }

        //Checks if the current 6 letter word matches any "prefix" is five letter dictionary
        if (currentWord.length() == 6) {
            if (!sixLetPre.contains(currentWord)) {
                return;
            }
        }

        //Sets the current space to used so the space is not used again in the word
        tray[currentRow][currentCol].setUsed(true);

        //Adds the current letter of the current space to the current word
        currentWord += tray[currentRow][currentCol].getLetter();

        //If the word is in the dictionary text provided and is above three letters it adds it to 
        //the words array
        if (dictionary.contains(currentWord)
                && currentWord.length() >= 3) {
            //System.out.println(currentWord);
            words.add(currentWord);
        }

        //Does this hole method recursively for all spaces horizontally,
        //Diagonally, and vertically touching
        wordSearch(currentWord, currentRow, currentCol - 1);
        wordSearch(currentWord, currentRow, currentCol + 1);
        wordSearch(currentWord, currentRow + 1, currentCol - 1);
        wordSearch(currentWord, currentRow + 1, currentCol);
        wordSearch(currentWord, currentRow + 1, currentCol + 1);
        wordSearch(currentWord, currentRow - 1, currentCol - 1);
        wordSearch(currentWord, currentRow - 1, currentCol);
        wordSearch(currentWord, currentRow - 1, currentCol + 1);

        //After all completes sets the space to not used so it can be used in next word
        tray[currentRow][currentCol].setUsed(false);
    }

    /**
     * Method to read in the dictionary text and assign it to all the words to
     * the arrayList dictionary THE RECURSION METHOD TAKES APPROXIMATELY A
     * MINUTE FOR ALL WORDS TO BE FOUND
     *
     * @return the dictionary arrayList filed with words
     * @throws FileNotFoundException
     */
    public ArrayList<String> readInDict() throws FileNotFoundException {
        File file = new File("brit-a-z.txt");
        ArrayList<String> dictionary = new ArrayList<String>();

        //Reads in the dictionary text
        try {

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                dictionary.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return dictionary;
    }

    /**
     * This sets up the recursive method, does the method on all spaces to find
     * each wiord beginning with that space also creates the "prefix"
     * dictionaries which are used in the above method
     *
     * @throws FileNotFoundException
	 *
     */
    public void recursionSetup() throws FileNotFoundException {
        dictionary = readInDict();

        //Creates all the "prefix" dictionaries
        for (int j = 0; j < dictionary.size(); j++) {
            if (dictionary.get(j).length() > 2) {
                twoLetPre.add(dictionary.get(j).substring(0, 2));
            }

            if (dictionary.get(j).length() > 3) {
                threeLetPre.add(dictionary.get(j).substring(0, 3));
            }

            if (dictionary.get(j).length() > 4) {
                fourLetPre.add(dictionary.get(j).substring(0, 4));
            }

            if (dictionary.get(j).length() > 5) {
                fiveLetPre.add(dictionary.get(j).substring(0, 5));
            }

            if (dictionary.get(j).length() > 6) {
                sixLetPre.add(dictionary.get(j).substring(0, 6));
            }
        }

        //Runs the recursive algorithm for each space
        for (int j = 0; j < ROWS; j++) {
            for (int k = 0; k < COLS; k++) {
                wordSearch("", j, k);
            }

        }
    }

    //Created By NetBeans for my GUI DONT MODIFY---------------------------------------------------------------------
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        jDialog1.setTitle("Words In Tray");
        jDialog1.setType(java.awt.Window.Type.POPUP);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
                jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
                jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
        );

        jDialog1.getAccessibleContext().setAccessibleParent(jButton2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Boggle!");

        jLabel1.setText("*");

        jLabel2.setText("*");

        jLabel3.setText("*");

        jLabel4.setText("*");

        jLabel5.setText("*");

        jLabel6.setText("*");

        jLabel7.setText("*");

        jLabel8.setText("*");

        jLabel9.setText("jLabel9");

        jLabel10.setText("*");

        jLabel11.setText("*");

        jLabel12.setText("*");

        jLabel13.setText("*");

        jLabel14.setText("*");

        jLabel15.setText("*");

        jLabel16.setText("*");

        jLabel17.setText("*");

        jButton1.setText("BOGGLE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Find Words");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel18.setText("             Press Boggle To Get Your Tray!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel13)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel17)
                                                        .addComponent(jLabel1))
                                                .addGap(42, 42, 42)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel12)
                                                        .addComponent(jLabel16)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel2))
                                                .addGap(10, 10, 10)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jButton2))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(jLabel15)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel14))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(jLabel11)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel10))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel7)
                                                                        .addComponent(jLabel3))
                                                                .addGap(46, 46, 46)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel8)
                                                                        .addComponent(jLabel4))))))
                                .addContainerGap(21, Short.MAX_VALUE))
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[]{jButton1, jButton2});

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel8))
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel12)
                                                        .addComponent(jLabel13)
                                                        .addComponent(jLabel10))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel16)
                                                        .addComponent(jLabel14)
                                                        .addComponent(jLabel17)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jLabel4))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel11)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel15)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[]{jButton1, jButton2});

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Nets Beans Method Finished----------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Action Listener for button one the Boggle Button It puts all the dice
     * values in their place in the gui
     *
     * @param evt when the Boggle Button is pressed
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //Picks random dice rolls them and assigns them to a space
        //and then assigns that space to a position in the 2d tray array
        for (int j = 0; j < ROWS; j++) {
            for (int k = 0; k < COLS; k++) {
                Random rand = new Random();
                Die thisDie = null;

                //If the dice has already been used choose random die again
                do {
                    int randDie = rand.nextInt(26);
                    thisDie = dice[randDie];
                    tray[j][k] = new Space(new Space(thisDie.rollDie(), false));
                } while (thisDie.isUsed());

                //Sets that the die is being used this round
                thisDie.setUsed(true);
            }
        }

        //Sets the Gui jLabel to the corresponding 
        //position in the 2d tray array
        jLabel1.setText(tray[0][0].getLetter());
        jLabel2.setText(tray[0][1].getLetter());
        jLabel3.setText(tray[1][2].getLetter());
        jLabel4.setText(tray[0][3].getLetter());
        jLabel5.setText(tray[1][0].getLetter());
        jLabel6.setText(tray[1][1].getLetter());
        jLabel7.setText(tray[0][2].getLetter());
        jLabel8.setText(tray[1][3].getLetter());
        jLabel10.setText(tray[2][3].getLetter());
        jLabel11.setText(tray[2][2].getLetter());
        jLabel12.setText(tray[2][1].getLetter());
        jLabel13.setText(tray[2][0].getLetter());
        jLabel14.setText(tray[3][3].getLetter());
        jLabel15.setText(tray[3][2].getLetter());
        jLabel16.setText(tray[3][1].getLetter());
        jLabel17.setText(tray[3][0].getLetter());

        //Resets all dice to not used so they can be used next round
        for (int j = 0; j < dice.length; j++) {
            dice[j].setUsed(false);
        }

        //Enables the find words button, this prevents the user
        //from pressing the button before a tray is created
        jButton2.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * This is the action listener for the find words button this initializes a
     * new window and displays the words that AFTER BUTTON IS PUSHED IT TAKE
     * APROXIMATELY A MINUTE FOR RECURSION METHOD TO FIND ALL WORDS IN TRAY AND
     * DISPLAY IN TEXT AREA were found in that window
     *
     * @param evt
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        //Brings up new window
        jDialog1.setSize(300, 300);
        jDialog1.setVisible(true);

        //Calls the recursionSetup method to find words in tray
        try {
            recursionSetup();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Boggle.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Builds String of all words in tray
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < words.size(); j++) {
            sb.append("\n" + words.get(j));
        }

        //Prints those words to a text area in the jDialog frame
        jTextArea1.setText("Here Are The Words In This Tray" + sb.toString());

        //Clears the words arrayList and the stringBuilder so new list can be
        //made for next tray
        words.clear();
        sb.delete(0, sb.length());
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Boggle().setVisible(true);
            }
        });
    }

    //Created By netBeans DONT MODIFY-------------------------------------------------
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    //Creation Ended----------------------------------------------------------------------

}
