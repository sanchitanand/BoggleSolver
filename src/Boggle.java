/* Program: Boggle Game Solver
 * Author: Sanchit Anand 
 * Date: January 2018
 * Description: 
 * Boggle Game Solver is a simple tool that is able to solve Boggle puzzles of arbitary sizes. 
 * 
 * To properly use this program, first run createDictionary, giving a path to 
 * your list of accepted words (lowercase) under the variable dictionaryFileName. 
 * This creates a prefix tree from the contents of your dictionary,
 * and saves it in serial form.
 * In the subsequent executions, loadDictionary loads the contents of 
 * this tree, and BoggleSolver is used to solve the puzzle. 
 * 
 * The solver itself works by 
 * using a Depth First Search to traverse the grid, and the 
 * prefix tree to check the validity of the word so far.
 * The variable  maxWordLength gives the maximum travel length.
 * 
 * The output is a list of compatible words from the dictionary.
 */



import java.io.*;
import java.util.*;
public class Boggle {

	
	
	static TrieNode root = new TrieNode();
	static String dictionaryFileName = "/Users/sanchit/Desktop/words.txt"; //Dictionary file location
	static String storageFileName = "/Users/sanchit/Desktop/dictionary.ser"; //Serial prefix tree location
	static String outputFileName = "/Users/sanchit/Desktop/output.txt"; //Words list location
	
	
	//Insert a word into the prefix tree
	public static void insert(String word)
	{
		 TrieNode p = root;
	        for(int i=0; i<word.length(); i++){
	            char c = word.charAt(i);
	            int index = c-'a';
	            if(p.children[index]==null){
	                TrieNode temp = new TrieNode();
	                p.children[index]=temp;
	                p = temp;
	            }else{
	            	    
	                p=p.children[index];
	            }
	            
	        }
	        p.isEnd = true;
	}
	

	  
	//Build Prefix tree from Dictionary file, and save the tree under give path    
	public static void createDictionary()
	{

		
		
		String word = null;
		
		try
		{
			FileReader fileReader = new FileReader(dictionaryFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((word = bufferedReader.readLine()) != null)
			{
				insert(word.toLowerCase());
			}
			
			bufferedReader.close();
		}
		
		catch (IOException ex)
		{
			 System.out.println("IOException is caught");
			 System.exit(1);
		}
		
		try {
		 FileOutputStream file = new FileOutputStream(storageFileName);
         ObjectOutputStream out = new ObjectOutputStream(file);

         
		out.writeObject(root);
		
          
         out.close();
         file.close();

		}
		
		catch(IOException ex)
        {
            System.out.println("IOException is caught");
            System.exit(1);
        }
         
      
	}
	  
	//Load tree from memory. 
	public static void loadDictionary()
	{
		 try
	        {   
	            // Reading the object from a file
	            FileInputStream file = new FileInputStream(storageFileName);
	            ObjectInputStream in = new ObjectInputStream(file);
	             
	            // Method for deserialization of object
	            root  = (TrieNode)in.readObject();
	             
	            in.close();
	            file.close();
	             

	        }
	         
	        catch(IOException ex)
	        {
	            System.out.println("IOException is caught");
	            System.exit(1);
	        }
	         
	        catch(ClassNotFoundException ex)
	        {
	            System.out.println("ClassNotFoundException is caught");
	            System.exit(1);
	        }
	 
	}
	
	//Boggle Solver Main Function
	public static void main(String[] args) {
		
		
		
		/*
		createDictionary();
		System.out.println("Done");
		*/
		
		System.out.println("Loading Dictionary");
		loadDictionary();
		final int boardSize = 4; //Change the variable in BoggleSolver as well
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the board:");
		String in = sc.next();
		sc.close();
		char[][] board = new char[boardSize][boardSize];
		for (int i=0;i<boardSize;i++)
		{
			for(int j=0;j<boardSize;j++)
			{
				board[i][j] =  in.charAt(boardSize*i + j);
			}
		}
		BoggleSolver bg = new BoggleSolver(root, board);
		ArrayList<String> words = bg.getWords();
		words.sort((a,b) -> Integer.compare(b.length(),a.length()));
		try {
		FileOutputStream fileOut = new FileOutputStream(outputFileName);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOut));
		for(String word:words)
			{
				bw.write(word);
				bw.newLine();
			}	
		bw.close();
		}
		
		catch(IOException e)
		{
			System.out.println("IOException");
			System.exit(1);
		}
		
		

		
	}

	

}
