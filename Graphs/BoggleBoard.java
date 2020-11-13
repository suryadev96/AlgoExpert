/*
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically or diagonally neighboring. The cell itself does not count as an adjacent cell.
The same letter cell cannot be used more than once
[
  ["thisisa"],
  ["simplex"],
  ["bxxxxeb"],
  ["xogglxo"],
  ["xxxDTra"],
  ["REPEAdx"],
  ["xxxxxxx"],
  ["NOTRE-P"],
  ["xxDETAE"]
]
word = "this", -> returns 1,
word = "is", -> returns 1,
word = "a", -> returns 1,
word = "simple" -> returns 1
word = "boggle" -> returns 1
word = "board" -> returns 1
*/
import java.util.*;
class Question{

	public static boolean isExists(List<String> board, String word){
		if (board == null || word == null)return false;

		int rows = board.size();
		int columns = board.get(0).length();

		if (rows == 0 || columns == 0){
			return false;
		}

		boolean[][] visited = new boolean[rows][columns];

		for (int i=0;i<rows;i++){
			for (int j=0;j<columns;j++){
				//if u are able to search from this index 
				if (dfs(board,word,i,j,0,visited)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isSafe(int rowIndex, int rows, int colIndex, int columns){
		if (rowIndex < 0 || rowIndex >= rows || colIndex < 0 || colIndex >= columns){
			return false;
		}
		return true;
	}

	//does dfs search from (rowIndex,colIndex)
	//there is always two approaches; listen carefully
	//1. you know that the current i,j is safe and we have to perform dfs on it and in this case
	//	 when u call neighbours u make sure they are safe to call dfs upon
	//2. u don't know if the i,j is safe u check it at first hand and then call all the neighbours
	//3. Here u can move the first check character comparison to isSafe function
	public static boolean dfs(List<String> board, String word, int rowIndex, int colIndex, int wordIndex,boolean[][] visited){

		int rows = board.size();
		int columns = board.get(0).length();

		if (board.get(rowIndex).charAt(colIndex) != word.charAt(wordIndex)){
			return false;
		}else if (wordIndex == word.length()-1){
			return true;
		}else{
			visited[rowIndex][colIndex] = true;

			int[] dx = {-1,-1,-1,0,0,1,1,1};
			int[] dy = {-1,0,1,-1,1,-1,0,1};

			boolean isPresent = false;

			for (int k=0;k<8;k++){
				int x = rowIndex + dx[k];
				int y = colIndex + dy[k];
				if (isSafe(x,rows,y,columns) && !visited[x][y]){
					isPresent = dfs(board,word,x,y,wordIndex+1,visited);
				}
				if (isPresent)return true; //if found in particular direction, no need to check other directions
			}
			visited[rowIndex][colIndex] = false; //backtracking is necessary because this node can be used for some other path
			return false;
		}	
	}	

	public static void main(String[] args){

		List<String> board = Arrays.asList(
			"thisisa",
			"simplex",
			"bxxxxeb",
			"xogglxo",
			"xxxDTra",
			"REPEAdx",
			"xxxxxxx",
			"NOTRE-P",
			"xxDETAE"
			);

		List<String> words = Arrays.asList("this","is","not","a","simple","boggle","board","test","REPEATED","NOTRE-PEATED", "siss");

		for (String word : words){
			boolean isPresent = isExists(board,word);
			System.out.println(word + "-->" + isPresent);
		}
	}
}
/*
this-->true
is-->true
not-->false
a-->true
simple-->true
boggle-->true
board-->true
test-->false
REPEATED-->false
NOTRE-PEATED-->true
sis->false
*/

//my solution:
import java.util.*;
class TrieNode{
	Map<Character,TrieNode> children = new HashMap<>();
	String word;
}
class Trie{
	
	TrieNode root = new TrieNode();
	//-1,0,1
	int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	
	public Trie(String[] words){
		buildTrie(words);
	}
	
	public void buildTrie(String[] words){
		for (String word : words){
			TrieNode node = root;
			for (char ch : word.toCharArray()){
				node = node.children.computeIfAbsent(ch, k -> new TrieNode());
			}
			node.word = word;
		}
	}
	
	public List<String> findWords(char[][] board){
		List<String> result = new ArrayList<>();
		for (int i=0;i<board.length;i++){
			for (int j=0;j<board[0].length;j++){
				dfs(board,i,j,root,result);
			}
		}
		return result;
	}
	
	public void dfs(char[][] board, int i, int j, TrieNode node, List<String> result){

		char ch = board[i][j];
		
		if (!node.children.containsKey(ch)) return;
		
		node = node.children.get(ch);
		
		if (node.word != null){
			result.add(node.word);
			node.word = null;
		}
		
		board[i][j] = '#'; //marking it as visited
		
		for (int[] d : dirs){
			int x = i + d[0];
			int y = j + d[1];
			if (isSafe(x,y,board.length,board[0].length) && board[x][y] != '#'){
				dfs(board,x,y,node,result);
			}
		}
		board[i][j] = ch; //backtracking
	}
	
	private boolean isSafe(int x, int y, int m, int n){
		return x>=0 && x<m && y>=0 && y<n;
	}
	
}
class Program {
  public static List<String> boggleBoard(char[][] board, String[] words) {
    Trie trie = new Trie(words);
		return trie.findWords(board);
  }
}
