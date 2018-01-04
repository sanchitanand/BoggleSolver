import java.io.Serializable;


//Base Class for prefix tree nodes

public class TrieNode implements Serializable{
	
    TrieNode[] children;
    boolean isEnd = false;
    public TrieNode() {
        this.children = new TrieNode[26]; // 26 elements for letters a-z
    }
 
}
