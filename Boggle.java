/**
 * Boggle.java. Implements a Boggle game engine.
 *
 * @author   Shawn Burris (spb0015@auburn.edu)  
 *	@author   Dean Hendrix (dh@auburn.edu)
 *	@version  2012-11-13
 *
 */
   import java.util.*;
   import java.io.FileNotFoundException;

   public class Boggle {
   
   // the Boggle game board
      private BoggleBoard board;
   // game dictionary
      private BoggleDictionary dictionary;
      int numCount;
      ArrayList<String> userwords = new ArrayList<String>();
      ArrayList<String> machinewords = new ArrayList<String>();
      public int N = 4;
      static long endTime;
      String userWords;
      String machineWords;
      public static final int MINIMUM_WORD_LENGTH = 3;
      int userCount = 0;
      boolean visited[][] = new boolean[N][N];
      int machineCount = 0;
      String wordSoFar = "";
      String possibleWord;
      Scanner userInput = new Scanner(System.in);
   
   /**************************************************************************
    * Initializes the game to use a random board
    * and default dictionary.
    */
      public Boggle()throws FileNotFoundException
      {
      // setup board
         board = new BoggleBoard();
      // setup dictionary
         dictionary = new BoggleDictionary();
      // other stuff ...
      // You must fill this in.
      }
      
   /**************************************************************************
    * Initializes the game to use the specified board
    * and the specified dictionary.
    */
      public Boggle(BoggleBoard b, BoggleDictionary d) throws FileNotFoundException 
      {  
         board = b;
      
         dictionary = d;
      }  
   	
   
   /**************************************************************************
    * Play the game.
    */
      public void play() {
      
      // DO NOT MODIFY THIS METHOD
      
         System.out.println("/ / / / / / / / / /");
         System.out.println("Let's play Boggle!\n");
         
         System.out.println("The board: ");
         displayBoard();
         System.out.println();
         
         System.out.println("Your turn: ");
         System.out.println("-------------------");
         userTurn();
         System.out.println();
         
         System.out.println("My turn: ");
         System.out.println("-------------------");
         machineTurn();
         System.out.println();
         
         System.out.println("The results: ");
         System.out.println("-------------------");
         reportResults();
         System.out.println();
         System.out.println("/ / / / / / / / / /");
      }
   
   /**************************************************************************
    * Play the user's turn.
    */   
      public void userTurn() 
      {
         machineTurn();
         System.out.println("Please enter your guesses followed by a space. " 
            + "If you would like to end the game"
            + " please press *.");
         do
         {
            userWords = userInput.next();
            getUserWords();
         }
         while (!userWords.equals("*"));
         
         ArrayList<String> userwords = getUserWords();
      }
   
   /**************************************************************************
    * Play the machine's turn.
    */
      public void machineTurn() 
      {
         for (int i = 0; i < board.size(); i++)
            for (int j = 0; j < board.size(); j++)
               depthFirstSearch("", i, j);
      }
      
   /**************************************************************************
    * Depth first search
    */
      private void depthFirstSearch(String possibleWord, int i, int j) 
      {
         if (i < 0 || j < 0 || i >= board.size() || j >= board.size()) 
            return;
      
         if (visited[i][j]) 
            return;
            
         visited[i][j] = true;
        
         possibleWord = possibleWord + board.getLetter(i, j);
         
      	//main issue here was resetting the visited part upon each return.
         if (!dictionary.containsAsPrefix(possibleWord))
         {
            visited[i][j] = false;
            return;  
         }
            
         if (possibleWord.length() >= MINIMUM_WORD_LENGTH 
            && dictionary.contains(possibleWord))
         {
            machineWords = possibleWord;
            getMachineWords();
         }
         
         for (int ii = -1; ii <= 1; ii++)
            for (int jj = -1; jj <= 1; jj++)
               depthFirstSearch(possibleWord, i + ii, j + jj);
      	
         visited[i][j] = false;
      }
       
   /**************************************************************************
    * Display the game results.
    */	
      public void reportResults() 
      {
         System.out.println("Number of user words: " + userCount);
         Collections.sort(userwords);

         System.out.println("User score: " + getScore(userwords));
         System.out.println("Number of machine words: " + machineCount);
         Collections.sort(machinewords);
         numCount = 0;
         for (int i = 0; i < machinewords.size(); i++)
         {
            numCount++;
            System.out.println(machinewords.get(i)
               + " - " + numCount);
         }
         System.out.println("Machine score: " + getScore(machinewords));
       
      }  
    
   /**************************************************************************
    * Print the board to standard out.
    */   
      public void displayBoard() 
      {
      // DO NOT MODIFY THIS METHOD
         System.out.println(board);
      }
    
   /**************************************************************************
    * Return a list of valid words identified by the user.
    */   
      public ArrayList<String> getUserWords() 
      {
         if (machinewords.contains(userWords.toUpperCase()))
         {
         //checking for duplicates
            if (!userwords.contains(userWords.toUpperCase()))
            {
               userCount++;
               userwords.add(userWords.toUpperCase());
            }
         }
         return userwords;
      }
      
   /**************************************************************************
    * Return a list of valid words identified by the machine.
    */   
      public ArrayList<String> getMachineWords() 
      {
         if (!machinewords.contains(machineWords))
         {
            machineCount++;
            machinewords.add(machineWords);
         }
         return machinewords;
      }
      
   /**************************************************************************
    * Return the score of a list of valid words.
    */   
      public int getScore(ArrayList<String> words) 
      {
         int score = 0;
         for (int i = 0; i < words.size(); i++)
         {
            if (words.get(i).length() == 3 
            || words.get(i).length() == 4)
            {
               score = score + 1;
            }
            else if (words.get(i).length() == 5) 
            {
               score = score + 2;
            }
            else if (words.get(i).length() == 6)
            {
               score = score + 3;
            }
            else if (words.get(i).length() == 7)
            {
               score = score + 5;
            }
            else if (words.get(i).length() >= 8) 
            {
               score = score + 11;
            }
         }
         return score;
      }	   	
   
   /**************************************************************************
    * Play an example game.
    */	
    
      public static void main(String[] args) throws FileNotFoundException 
      {
         BoggleBoard board = new BoggleBoard(BoggleBoard.SAMPLE_BOARD);
         BoggleDictionary dictionary = new BoggleDictionary(BoggleDictionary.UNIX);
        
         Boggle boggle = new Boggle(board, dictionary);
         boggle.play();
      }	
   }
