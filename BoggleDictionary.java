/**
 * BoggleDictionary.java. An adaptor class that provides the
 * dictionary/lexicon behavior necessary for a Boggle game.
 *
 * @author   Shawn Burris (spb0015@auburn.edu)  
 *	@author   Dean Hendrix (dh@auburn.edu)
 *	@version  2012-11-13
 *
 */

   import java.io.*;
   import java.util.*;

   public class BoggleDictionary implements Iterable<String> {
   
   // Filenames of default word lists
      public static final String CSW  = "CSW12.txt";
      public static final String OWL  = "OWL.txt";
      public static final String UNIX = "words.txt";
      public TreeSet<String> tree = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
      public File fileName;
      public Scanner scan;
      public TreeSet<String> dictionary = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
      Iterator<String> itr;
      
   // You must choose an appropriate type for the dictionary field.
   // You can't leave this field typed as 'Object.'
    
      private TreeSet<String> addToTree(File file) throws FileNotFoundException
      { 
         Scanner scan = new Scanner(fileName);
         
         while (scan.hasNextLine())
         {
            String word = scan.nextLine();
            int where = word.indexOf(" ");
            if (where != -1)
            {
               word = word.substring(0, where);
            }
            tree.add(word.toUpperCase());
         }
         return tree;
      }
   
   
   /**************************************************************************
    * Create a dictionary with a default word list.
    */
      public BoggleDictionary() throws FileNotFoundException
      {
         fileName = new File(UNIX);
         addToTree(fileName);
         dictionary = tree;
      }
      
      
   /**************************************************************************
    * Create a dictionary with the specified word list.
    */
      public BoggleDictionary(String wordlist)throws FileNotFoundException
      {
         fileName = new File(wordlist);
         addToTree(fileName);
         dictionary = tree;
      }
   
   /**************************************************************************
    * Is the string a word in the dictionary?
    */   
      public boolean contains(String str) 
      {
      // You must fill this in as appropriate and change the return value.
         return dictionary.contains(str);
      }
      
      
   /**************************************************************************
    * Is the string a prefix of some word in the dictionary?
    */   
      public boolean containsAsPrefix(String str) {
      // You must fill this in as appropriate and change the return value.
         String prefix = dictionary.ceiling(str);
         if (prefix == null)
         {
            return false;
         }
         if (prefix.startsWith(str))
         {
            return true;
         }
         return false;
      }
     
     
   /**************************************************************************
    * Create an iterator over all the words in the dictionary.
    * No specific word order is guaranteed.
    */   
      public Iterator<String> iterator() {
         Iterator<String> itr = dictionary.iterator();
         return itr;
      }
   
   }