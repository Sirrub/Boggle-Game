/**
 * BoggleBoard.java. Models a Boggle board.
 *
 * @author   Shawn Burris (spb0015@auburn.edu)  
 *	@author   Dean Hendrix (dh@auburn.edu)
 *	@version  2012-11-13
 *
 */
 
   import java.util.*;
 
   public class BoggleBoard 
   {
   // default board dimension
      public int N = 4;
   // sample board   
      public static String[][] SAMPLE_BOARD = 
      {               
         		{"D","G","O","X"},
         		{"A","W","E","R"},
         		{"I","S","E","K"},
         		{"E","T","S","P"}
         		};
   
      private static String[][] board;   // grid of letters
      private String randomBoard[][] = new String[N][N];
      private LetterDie[] dice;   // letter dice used on board
      Random generator = new Random();
   
   /**************************************************************************
    * Create a new 4x4 BoggleBoard with a random
    * letter die arrangement. This should simulate
    * the shuffling of the board that would occur in
    * a physical game.
    */
      public BoggleBoard() 
      {
         dice = initStandardDice();
         for (int i = 0; i < N; i++)
         {
            for (int j = 0; j < N; j++)
            {
               int d = generator.nextInt(16);
               dice[d].toss();
               randomBoard[i][j] = (dice[d].getLetter());
            }
         }
         board = randomBoard;
      }
      
   /**************************************************************************
    * Create a new BoggleBoard with a specified
    * letter die arrangement. If the grid is null
    * or if the grid is not square, this method
    * throws an IllegalArgumentException.
    */
      public BoggleBoard(String[][] grid) 
      {
         if (grid == null) throw new NullPointerException();
         if (grid.length != grid[0].length) throw new IllegalArgumentException();
      
         dice = initStandardDice();
         board = grid;
         N = grid.length;
      }
      	   
   /**************************************************************************
    * Return the (i,j) letter on the board. The top-left
    * of the board is (0,0). If row or col has an illegal
    * value, this method throws an IllegalArgumentException.
    */   
      public String getLetter(int row, int col) 
      {
      // You must fill this in.
         if (row < 0 || row > N - 1 || col < 0 || col > N - 1)
         {
            throw new IllegalArgumentException();
         }
      
         return board[row][col];
      }
     
   /**************************************************************************
    * Return the board dimension
    */   
      public int size() 
      {
      // DO NOT MODIFY THIS METHOD
         return N;
      }
   
   /**************************************************************************
    * Return a String representation of the board.
    */	
      public String toString() 
      {
      // DO NOT MODIFY THIS METHOD
         StringBuilder s = new StringBuilder();
         s.append("* * * *\n");   
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) 
               s.append(board[i][j] + " ");
            s.append("\n");
         }
         s.append("* * * *");
         return s.toString();
      }
         
   /**************************************************************************
    * Initialize the 16 letter die for a standard Boggle game.
    */   
      private LetterDie[] initStandardDice() 
      {
      // Official Boggle dice, except for QU on die #2
      //
      // DO NOT MODIFY THIS METHOD
      //
         LetterDie[] dice = new LetterDie[16];
         
         dice[0]  = new LetterDie("F", "O", "R", "I", "X", "B");
         dice[1]  = new LetterDie("M", "O", "Q", "A", "B", "J"); // should have QU
         dice[2]  = new LetterDie("G", "U", "R", "I", "L", "W");
         dice[3]  = new LetterDie("S", "E", "T", "U", "P", "L");
         dice[4]  = new LetterDie("C", "M", "P", "D", "A", "E");
         dice[5]  = new LetterDie("A", "C", "I", "T", "A", "O");
         dice[6]  = new LetterDie("S", "L", "C", "R", "A", "E");
         dice[7]  = new LetterDie("R", "O", "M", "A", "S", "H");
         dice[8]  = new LetterDie("N", "O", "D", "E", "S", "W");
         dice[9]  = new LetterDie("H", "E", "F", "I", "Y", "E");
         dice[10] = new LetterDie("O", "N", "U", "D", "T", "K");
         dice[11] = new LetterDie("T", "E", "V", "I", "G", "N");
         dice[12] = new LetterDie("A", "N", "E", "D", "V", "Z");
         dice[13] = new LetterDie("P", "I", "N", "E", "S", "H");
         dice[14] = new LetterDie("A", "B", "I", "L", "Y", "T");
         dice[15] = new LetterDie("G", "K", "Y", "L", "E", "U");
      
         return dice;
      }
   }