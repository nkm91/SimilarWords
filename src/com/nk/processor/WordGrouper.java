/**
 * 
 */
package com.nk.processor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Nandkishor
 *Makes group of similar words
 */
public class WordGrouper {
	Map<Integer, ArrayList<String>> wordMap;
	
	public WordGrouper(Map<Integer, ArrayList<String>> wordMap){
		this.wordMap = wordMap;
	}
	
	/*
	 * Makes sets of the similar words from wordMap
	 */
	public Set<ArrayList<String>> groupSimilarWords(){
		Set<ArrayList<String>> similarGroups = new HashSet<ArrayList<String>>();
		
		for(int i=1; i<=20; i++){
			ArrayList<String> currLenWordList = wordMap.get(i);
			if(currLenWordList == null){
				break;
			}
			int listLen = currLenWordList.size();
			for(int j=0; j<listLen; i++){
				ArrayList<String> resultList = new ArrayList<String>();
				resultList.add(currLenWordList.get(j));
				similarWordsAhead(currLenWordList.get(j), i, j, resultList);
			}
		}
		return similarGroups;
	}
	
	/*
	 * Recursive helper form the grouping similar words
	 */
	private void similarWordsAhead(String thisWord, int thisLen, int thisIndex, ArrayList<String> resultList){
		for(int i=thisLen; i < thisLen+2; i++){
			ArrayList<String> currLenWordList = wordMap.get(i);
			for(int j=thisIndex+1; j<currLenWordList.size(); i++){
				if(isMutuallySimilar(thisWord, resultList)){
					resultList.add(currLenWordList.get(j));
				}
			}
		}
	}
	
	private boolean isMutuallySimilar(String word, ArrayList<String> resultList){
		for(String otherWord : resultList){
			if(WordUtil.leastBoundEditDistance(word, otherWord) > 1){
				return false;
			}
		}
		return true;
	}

}
