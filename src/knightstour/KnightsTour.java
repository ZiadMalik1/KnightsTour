/*
Ziad Malik
Section U01
KnightsTour.java
I affirm that this program is entirely my own work 
and none of it is the work of any other person.
 */
package knightstour;

import java.util.Arrays;

/**
 * The KnightsTour class is where the tour is conducted as well as where Records 
 * are kept for the most successful tour. Within This class a 2-Dimensional 
 * Array is created that keeps a record of all Tours who's moves range from 1-64
 * (64 being the max possible). While Conducting the tours this
 * class takes the amount of moves done by the given tour and increments the
 * count of tours that took that amount of moves to be halted. For example, If a
 * tour is conducted and the amount of moves reached was 36, then the count of
 * tours with 36 moves will be incremented within the 2D array boardCharts Also
 * All records of the most Successful Board are kept here. The number of moves
 * of the longest tour, a record of the longest tour, and the number of tours of
 * each length as a multi-line String
 *
 */
public class KnightsTour {

    private final Knight newKnight; // Creates a knight that will tour the Board
    private ChessBoard newBoard; // The 8x8 Board that will be toured
    private final int[][] boardCharts; // Record of all tours that are done
    /*
    This integer, highestBoardTourCount keeps track of when the most successful
    tour was done. If highestCount is changed, then highestBoardTourCount is 
    also changed to the index of the tour within the Record Array.
     */
    private int highestBoardTourCount;
    private String[][] finalBoard; //String form of most successful tour
    private ChessBoard[] record; // Record of all Tours done with this Knight
    private int recordCounter = 0; // Size of Record of Tours Array, Incremented
    private static final int MAX_TOURS = 1000; //Initial starting size of record
    /*
    highestCount integer is the integer that keeps track of the greatest number
    of moves done within all the tours done. If a new tour is done with a higher
    move count than highestCount, highestCount is changed to that number. Used
    to call back the most successful tour in the end.
     */
    public int highestCount = 0;

    /**
     * Constructor of the Knights Tour class that creates a new Knight from the
     * Knights Class and also creates a new List to keep a record of all the
     * move Counts of each tour, classifying them. Also creates a new record
     * array where it keeps track of all the tours it completes.
     */
    public KnightsTour() {

        record = new ChessBoard[MAX_TOURS];
        newKnight = new Knight();
        boardCharts = new int[64][2];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < boardCharts.length; j++) {
                boardCharts[j][i] = j + 1;
            }
        }
    }

    /**
     * This Method Conducts the tour after a record Keeping list has been made
     * for the new KnightsTour. This method is run within the KnightsTest class
     * as many times as the User Indicated. Within the method it calls the Play
     * Method within the Knight class which returns an integer. This integer
     * indicates how many moves were done within the given tour and then
     * increments the count of tours that took that amount of moves to be
     * halted.
     */
    public void conductTour() {

        newBoard = new ChessBoard();
        /*
        Local Variable "Count" is simply a holder for the integer returned by
        the play method in the Knight Class. Allows for less space taking.
         */
        int Count = newKnight.play(newBoard);
        /*
        Count is assigned to Count - 1 in order to avoid counter 0. No tour 
        will have 0 moves done. For example, if 10 is returned. Row 9 (which 
        holds the counter for tours with 10 Moves, due to Row 0 being the 
        counter for tours with 1 move.) Column 1 will be incremented in order
        to say that there was another tour that had that amount of moves.
         */
        boardCharts[Count - 1][1]++;
        setHighestBoard(Count);
        addToRecord(newBoard);

    }

    /*
    Checks to see if Parameter Integer Count Points to a value that is greater
    than the current highestCount. If so, then highestCount points to the same
    value of Count and the Method Terminates. If not, then nothing is changed.
    (Helps find the most Successful Board Later on).
     */
    private void setHighestBoard(int Count) {
        if (Count > highestCount) {
            highestCount = Count;
        }
    }

    /*
    This Method simply adds the most recent Board into the Record Array for all
    Boards that had a tour done on them. First, The method checks to see if the
    Board is full. If so, it creates a new Record Array that copies the first 
    MAX_TOURS elements from the Original Record into the new Array and also
    adds MAX_TOURS new Empty Elements. (For if I would like to do more than 1000
    Tours ;) ). Then, it will proceed to add the Board to the next available 
    element within Record Array (Given by our own Counter recordCounter which
    is incremented every time a Board is added).
     */
    private void addToRecord(ChessBoard Z) {

        if (recordCounter == record.length) {
            record = Arrays.copyOf(record, recordCounter + MAX_TOURS);
        }
        record[recordCounter++] = Z;
    }

    /**
     *
     * @return Returns a String of the Record List given from the 2D Array
     * boardCharts. This Method first iterates through the original boardCharts
     * List and increments a local integer whenever there is a non-zero element
     * Within the second element of the [i]'th row which will be used to specify
     * the amount of rows within the simplified List that is made within the
     * following loop in order to avoid Tour counts of 0. For example, If the
     * count for tours with moves amount of 36 is 0, row 36 will be skipped
     * within the simplified list in order to avoid 0's like instructions
     * indicated. In the end the method returns the simplified List as a string
     *
     */
    public String CharttoString() {

        /*
        Integer used to Specify amount of rows in Simplified List later on in 
        Method. Incremented whenever nonzero element is encountered when 
        traversing the second column of the boardCharts Array
         */
        int counter = 0;
        for (int i = 0; i < boardCharts.length; i++) {
            if (boardCharts[i][1] != 0) {
                counter++;
            }
        }
        /*
        int[][]boardCopy will become the simplified version of boardCharts and 
        be given in turn as a string by ridding itself of all rows that contain
        0's within the second column element of that specific row.
        It will use the earlier local integer "counter" as the value to specify
        the amount of rows it will contain.
         */
        int[][] boardCopy = new int[counter][boardCharts[0].length];
        /*
        String boardS is an initializer for the string that is returned in the 
        end of this method. It will concatenated with the string version of
        boardCopy later on. 
         */
        String boardS = "";
        /*
        counterForCopy integer is a counter for the new Simplified Array 
        boardCopy. Whenever an element is found to be copied into the simplified
        version. Which is to say it contains a non-zero element within the rows
        second column element, it will increment the counter. Allows to create
        a new simplified version of boardCharts which will not list all 1-64 
        move tours rather, will list only the move amounts that have more than
        0 tours. 
         */
        int counterForCopy = 0;
        for (int i = 0; i < boardCharts.length; i++) {
            if (boardCharts[i][1] != 0) {
                boardCopy[counterForCopy++] = boardCharts[i];
            }
        }
        for (int i = 0; i < boardCopy.length; i++) {
            for (int j = 0; j < boardCopy[i].length; j++) {
                boardS += boardCopy[i][j] + "                     "
                        + "\t";
            }
            boardS += "\n";
        }
        boardS = "Tour Length:            "
                + "Number of Tours:\n-----"
                + "-------            ----"
                + "------------\n---------"
                + "---            --------"
                + "--------\n\n"
                + boardS;
        return boardS;
    }

    /**
     * The String Method that returns the most successful Board throughout all
     * the tours done by the Knight. First what this method does is that it
     * initializes finalBoard to an 8 by 8 String, (To allow *'s). Then, it will
     * traverse through the record Array that holds all the Tours the knight
     * completed and find which one has the same moveCount as highestCount. Once
     * the tour is found, the highestBoardTourCount points to the value of i + 1
     * Since Index 0 of the Record is actually the first tour not the 0 Tour.
     * This allows us to know when the most successful tour happened throughout
     * the number of tours the knight completed. Once that is done, the method
     * will done do a for loop that traverses the most successful tour and
     * copies all nonzero integers into the finalBoard as Strings. For all zero
     * AKA non-visited spots, an * is put in its place to show clearly where the
     * knight was not able to go. Then the method traverses the finalBoard and
     * prints all the elements in the form of a string for it to be returned to
     * the caller of the method and to ultimately be printed for the user to see
     * which Board was the most Successful. Also some Strings were concatenated
     * in order to make all the elements of the String more clear.
     *
     * @return Returns String Containing: Number of Tour that was most
     * successful, Amount of Moves done within the most Successful tour, and the
     * Board itself showing the complete tour of the knight and where it went
     * starting from (0,0).
     */
    /*
    This Method simply checks to see if the Count Parameter is greater than the
    current highestCount. If so, highestCount points to the what count points to
    (Only used to keep track of which board has the highest move count in order
    to easily access it later.)
     */
    public String HighestBoard() {
        finalBoard = new String[8][8];
        /*
        Local String which Holds The String as a whole. Is the String that is
        returned at the end of the method.
         */
        String HighestBoard = "";
        for (int i = 0; i < record.length; i++) {
            if (record[i].getMoveCount() == highestCount) {
                highestBoardTourCount = i + 1;
                for (int j = 0; j < record[i].Board.length; j++) {
                    for (int z = 0; z < record[i].Board[j].length; z++) {
                        if (record[i].Board[j][z] != 0) {
                            finalBoard[j][z]
                                    = Integer.toString(record[i].Board[j][z]);
                        } else {
                            finalBoard[j][z] = "*";
                        }
                    }
                }
                /*
                Local String Variable that holds the finalBoard in Stringform.
                Is Concatenated onto the HighestBoard String along with 
                formalities.
                 */
                String boardS = "";
                for (int b = 0; b < finalBoard.length; b++) {
                    for (int c = 0; c < finalBoard[b].length; c++) {
                        boardS += finalBoard[b][c] + " \t ";
                    }
                    boardS += "\n";
                }
                HighestBoard = "\nMost Successful Tour: (Of "
                        + KnightsTest.amountOfTours
                        //Amount of tours User asked for
                        + " Tours)\n\nTour #: "
                        + highestBoardTourCount + "                            "
                        + "     "
                        + "Tour Length: "
                        + highestCount
                        + "\n-----------------------------"
                        + "------------------------------"
                        + "\n-----------------------------"
                        + "------------------------------"
                        + "\n" + boardS;
                break;
            }
        }
        return HighestBoard;
    }
}
