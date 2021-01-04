/*
Ziad Malik
Section U01
Knight.java
I affirm that this program is entirely my own work 
and none of it is the work of any other person.
 */
package knightstour;

import java.util.Random;

/**
 * The Knight Class is the class where the action of deciding to move and moving
 * both come into play. The Knight Class is the only class that is aware of 
 * which moves it can make and also its Knights current position on the Board.
 */
public class Knight {

    private int currentRow; // The Row of the Knight Currently.
    private int currentCol; // The Column of the Knight Currently.
    /*
    Both Arrays Following this comment are the legal Row and Column moves the 
    Knight can make respectively. They must be done in respect to one index.
    If the knight decides to pick i index of rowMoves to change its row, it must
    also pick the very same i index of colMoves to change its column. NO 
    EXCEPTIONS.
    */
    private final int[] rowMoves = {-1, -2, -2, -1, 1, 2, 2, 1}; 
    private final int[] colMoves = {2, 1, -1, -2, -2, -1, 1, 2};
    /*
    Random Generator is used to create a variety of boards. This is done by
    allowing the knight to pick moves randomly and not repeat the same tour
    over and over.
    */
    private Random gen = new Random();
   
    /**
     * The Knight Constructor simply initializes a new random generator for the 
     * knight to use later on. 
     */
    public Knight() {
        
        gen = new Random();
       
    }
    /*
    rowFit is used within the Knight Class to see whether a move to a certain
    row would even be possible. How So? Well Suppose the knight wants to make a
    row move of 2 Up and 1 to the Right. The Row Fit would be called first to 
    see if the currentRow plus 2 would be within the Board Array. If it returns 
    something that is greater than 7 (the 8th element within the board 2D Array)
    then it will return false as that row would be out of bounds within the 
    board.
    */
    private boolean rowFit(int row) {

        return !(currentRow + row < 0 || currentRow + row > 7);
    }
    /*
    colFit acts the same as rowFit except it checks to see that the colMove 
    would keep the knight within the bounds of the Board in terms of the column
    since the ChessBoard is an 8x8 it would also have to be something less than 
    7 and greater than -1. Basically makes sure Knight doesnt move somewhere 
    that isnt on the board. Out of Bounds Otherwise.
    */
    private boolean colFit(int col) {

        return !(currentCol + col < 0 || currentCol + col > 7);
    }
    /*
    makeMove method simply takes the board the Knight is currently touring on, 
    along with the new Row and Column it has moved to and makes sure that the 
    board adds the next moveCount onto that spot to make sure it is not visited
    again. 
    */
    private void makeMove(ChessBoard newBoard, int Row, int Col) {

        newBoard.addNumber(Row, Col);
    }
    /*
    The Fit method is the method where a very Crucial step within the program
    is taken. The fit method creates a loop that will create a random integer
    between 0 and 7 first. Then based on that random integer will use that as 
    the index to access the rowMoves and colMoves Array. It will then proceed
    to check if those moves are legal and if they are not it will repeat the 
    process until a legal move is found. For example, lets say the random int
    comes out to be 5. It will check to see if rowMove and colMove Index 5 would
    be legal moves for the knight based on where he is. If not then i is 
    incremented and it repeats. If no Moves are found by the time i is 50, this 
    means more than likely there are no legal moves left. Therefore the local
    boolean remains false and the Knight exits the tour. 
    */
    private boolean Fit(ChessBoard newBoard) {

        /*
        Local Boolean that holds the Boolean for whether a legal move was found. 
        */
        boolean found = false; 
        for (int i = 0; !found && i < 50; i++) {
            int checkRow = gen.nextInt(8) ; //Local Int to hold the random int.
            /*
            Checks first if the move dictates a new spot WITHIN THE BOARD
            If so, checks if the potential new spot has already been visited.
            If not, the knight is moved and currentRow and currentCol are 
            changed accordingly.
            */
            if (rowFit(rowMoves[checkRow]) && colFit(colMoves[checkRow])
                    && (newBoard.makeMove((currentRow + rowMoves[checkRow]),
                            (currentCol + colMoves[checkRow])))) {
                found = true;
                currentRow = currentRow + rowMoves[checkRow];
                currentCol = currentCol + colMoves[checkRow];

            }
        }
        return found;
    }

    /**
     * The Play method takes into account the Board the Knight will play on and
     * initializes its currentRow and currentCol to 0 to start at 0,0. Then, 
     * While the Fit Method returns true, it will continue to let the knight
     * tour the board. When the Knights Tour comes to a halt, it takes the count
     * of the amount of moves that were done within the tour and returns it to 
     * the caller of the method to be taken record of within the Record-keeping
     * chart within the KnightsTour class. (See conductTour()). Also, it adds
     * the Board that was played on to the record Array of all boards that have
     * been toured on by the knight. It then checks to see if the boards move
     * Count was higher than the highest moveCount thus far. If so, the 
     * highest moveCount holder, highestCount points to what local Integer 
     * Count points to.
     * @param newBoard Takes the Board the Knight is playing on.
     * @return Returns the Move Count for the tour for recording
     */
    public int play(ChessBoard newBoard) {
        
        currentRow = 0;
        currentCol = 0;
        newBoard.addNumber(0, 0);
        while (Fit(newBoard)) {
            makeMove(newBoard, currentRow, currentCol);
        }
        /*
        Local Variable Count holds the return integer of getMoveCount() from
        the ChessBoard class that gives the move count of our Specific Board
        After the tour is done.
        */
        int Count = newBoard.getMoveCount(); 
        return Count;
    }
    

    
    
    
}
