/*
Ziad Malik
Section U01
KnightsTest.java
I affirm that this program is entirely my own work 
and none of it is the work of any other person.
 */
package knightstour;

import java.io.File;
import javax.swing.JOptionPane;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * The KnightsTest Class is the class that takes care of the input side of this
 * program. It will ask the User how many tours it will like the the knight to
 * take and based on this given information will conduct that amount of
 * specified tours within the KnightsTour Class.
 */
// Implements a new Knights Tour that will run for as many times the user
// Desires
public class KnightsTest {

    // instance vars
    public static int amountOfTours; // The amount of tours that the user wants

    /**
     * The main method within the KnightsTest class will ask the User first,
     * using JOptionPane how many tours they will like to have the knight run.
     * Then, it will run a loop up until that number where the program will stop
     * and return the most successful tour and a list of tours of all amount of
     * moves (1-64).
     *
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        KnightsTour Board = new KnightsTour();
        // Creates new Instance of Knights tour which will be run as many times
        // As the user asks

        amountOfTours = Integer.parseInt(JOptionPane.showInputDialog
        ("Enter The Amount Of Tours You Will Like The Knight To Take:"));

        for (int i = 0; i < amountOfTours; i++) {
            Board.conductTour();
        }

        //Sends Output to a Seperate Text
        try (PrintWriter outFile = new PrintWriter(new File("output.txt"))) {
            outFile.print(Board.HighestBoard());
            outFile.println("\n" + Board.CharttoString());
        }

    }

}
