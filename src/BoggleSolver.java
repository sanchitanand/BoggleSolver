import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;
public class BoggleSolver {
	
	final int maxWordLength = 8; //Max length of word formed
	final int boardSize = 4; //Change the variable in main function as well
	boolean[][] visited; 
	ArrayList<String> words = new ArrayList<String>();
	TrieNode root;
	char[][] boggleArray;
	
	public BoggleSolver(TrieNode root, char[][] boggle)
	{
		this.root = root;
		this.boggleArray = boggle;
		this.visited = new boolean[boardSize][boardSize];
		
	}
	
	
	//Perform Depth First Search from every position on the graph. Return words list.
	public ArrayList<String> getWords()
	{
		for (int i=0;i<boardSize;i++)
		{
			for (int j = 0;j<boardSize;j++)
			{
					boggleSolver(i,j,new StringBuilder(),root);
			}
		}
		return this.words;
	}
	
	//Recursive function to search every node for validity
	private void boggleSolver(int i,int j,StringBuilder word,TrieNode curr)
	{
		
		//Check for max word length
		if(word.length() >= maxWordLength)
			return;
		
		//Check for valid child in prefix tree
		int letter = boggleArray[i][j]- 'a';
		if (curr.children[letter] == null) 
			return;
		
		//Append current letter to word
		curr = curr.children[letter];
		word.append(boggleArray[i][j]);
		if (curr.isEnd)
		{
			String nextWord = word.toString();
			if (!words.contains(nextWord))
				words.add(nextWord);
		}
		
		//Find neighboring nodes on graph
		HashMap<Integer,Pair<Integer,Integer>> next = new HashMap<Integer,Pair<Integer,Integer>>();
		next.put(0,new Pair<Integer,Integer>(i+1,j));
		next.put(1,new Pair<Integer,Integer>(i,j+1));
		next.put(2,new Pair<Integer,Integer>(i-1,j));
		next.put(3,new Pair<Integer,Integer>(i,j-1));
		next.put(4,new Pair<Integer,Integer>(i+1,j+1));
		next.put(5,new Pair<Integer,Integer>(i+1,j-1));
		next.put(6,new Pair<Integer,Integer>(i-1,j+1));
		next.put(7,new Pair<Integer,Integer>(i-1,j-1));
		
		switch(i)
		{
		case 0:
			next.remove(2);
			next.remove(6);
			next.remove(7);
			break;
		case boardSize-1:
			next.remove(0);
			next.remove(4);
			next.remove(5);
			break;
		}
		
		switch(j)
		{
		case 0:
			next.remove(3);
			next.remove(5);
			next.remove(7);
			break;
		case boardSize-1:
			next.remove(1);
			next.remove(4);
			next.remove(6);
			break;
		}
		//set visited
		visited[i][j]= true;
		for(Pair<Integer, Integer> p : next.values())
		{
			//recursively visit all unvisited children 
			if(  !visited[(int) p.getKey()][(int) p.getValue()]  )
			{
				
				boggleSolver((int) p.getKey(),(int) p.getValue(),word,curr);
			}
		}
		//remove last letter, set unvisited and return
		word.deleteCharAt(word.length()-1);
		visited[i][j] = false;
	}
	
	
	
}
