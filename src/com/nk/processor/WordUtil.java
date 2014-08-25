/**
 * 
 */
package com.nk.processor;

/**
 * @author Nandkishor
 *
 */
public class WordUtil {
	
	public static int CHARS_LENGTH = 26; //english alphabets

	/*
	 * Returns least bound on edit distance, like 0 for equal words
	 * 1 if one char change is required
	 * 2 in all other cases,    Edit distance DP method optimized
	 */
	public static int leastBoundEditDistance(String word1, String word2){
		if(word1==null || word2==null){
			return 0;
		}
		int M = word1.length(); int N = word2.length();
		if(word1 == ""){
			return N;
		}
		if(word2 == ""){
			return M;
		}
		int[][] Table = new int[M+1][N+1];
		for(int i=0; i<=M; i++){
			Table[i][0] = i;
		}
		for(int i=0; i<=N; i++){
			Table[0][i] = i;
		}
		for(int i=1; i<=M; i++){
			int minDistSoFar = 0; //tracks distance while processing
			for(int j=1; j<=N; j++){
				Table[i][j] = min(Table[i-1][j]+1,
						Table[i][j-1] + 1,
						Table[i-1][j-1] + charDiff(word1.charAt(i-1), word2.charAt(j-1)));
				if(Table[i][j] < minDistSoFar){
					minDistSoFar = Table[i][j];
				}
			}
			if(minDistSoFar > 1){ // no way to be similar, early decision
				return minDistSoFar;
			}
		}
		return Table[M][N];
	}
	
	public static int min(int a, int b, int c){
		int tempMin = a < b ? a : b;
		return tempMin < c ? tempMin : c;
	}
	
	public static int charDiff(char ch1, char ch2){
		return ch1==ch2 ? 0 : 1; 
	}
	
	public static int absDiff(int a, int b){
		return Math.abs(a-b);
	}

}
