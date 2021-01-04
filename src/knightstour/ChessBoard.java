/*
Ziad Malik
Section U01
ChessBoard.java
I affirm that this program is entirely my own work 
and none of it is the work of any other person.
 */
package knightstour;

/**
 * The ChessBoard Class is where the 8x8 Board is created and kept record of.
 * Within it, you are able to find if a specific element within the 2D array has
 * been visited by seeing if it does not equal 0. If it does not, then that spot
 * is open to be visited! The Class also marks where and when a move was made.
 * If Row 3 Column 5 was visited after the 5th move had been made, it would be
 * assigned the integer 6 as its Content. This allows for the user to keep track
 * of the Knights Tour and where he ventured throughout the board.
 *
 */
public class ChessBoard {

    public int[][] Board; // The Board on which the Knight will Venture
    /* 
    The Amount of moves that have happened thus far (Referring to moveNumber)
     */
    private int moveNumber = 0;

    /**
     * The Constructor of the ChessBoard Class that creates a new 8x8 Board that
     * sets all the values of the elements of the 2D array to 0.
     */
    public ChessBoard() {

        Board = new int[8][8];
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                Board[i][j] = 0;
            }
        }
    }

    /**
     * The method addNumber is used to mark the Board with the next move number
     * at the spot the knight landed on, which is indicated by the parameters.
     *
     * @param Row //Tells the method which new row the Knight resides at
     * @param Col // Tells the method which new column the Knight resides at.
     */
    public void addNumber(int Row, int Col) {

        moveNumber++;
        Board[Row][Col] = moveNumber;
    }

    /**
     * The makeMove Method is the method that allows the program to know whether
     * a knight can make a move to a certain spot on the board. Since we
     * initialized all spots to 0, it means that if a spot is containing the
     * integer 0, it has not been visited yet for it would've been given the
     * move number at which it was visited.
     *
     * @param Row //Tells the method the row of the element spot in question
     * @param Col //Tells the method the column of the element spot in question.
     * @return //Either True or False is returned which goes onto indicate
     * whether the knight can make the move or not. If false, the knight checks
     * other Options for it cannot visit a spot more than once.
     */
    public boolean makeMove(int Row, int Col) {
        /*
        Local Boolean Move is used to hold the boolean of if the spot has been
        visited previously or not. Initialized to true and if the spot has been
        visited, sets it to false and returns the boolean move.
         */
        boolean move = true;
        if (Board[Row][Col] != 0) {
            move = false;
        }
        return move;
    }

    /**
     *
     * @return Returns the Count of the last Move, AKA tells the caller of the
     * method how many moves were done in the tour before the knight ran out of
     * options.
     */
    public int getMoveCount() {
        return moveNumber;
    }

    /**
     * @return Simple toString method that traverses the Board and prints each
     * element within an 8x8 Table. Makes sure to add a new line after each row
     * has been printed. And also uses the /t operator to give the more "table"
     * look.
     */
    public String toString() {
        /*
        Local String boardS is simply an initialzer to the string that will be 
        returned. Within the method we add onto it the board in the form of a 
        string and then return it to the caller of the method. boardS is simply
        a holder. 
         */
        String boardS = "";
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                boardS += Board[i][j] + " \t ";
            }
            boardS += "\n";
        }
        return boardS;
    }

}
