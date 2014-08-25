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
		
		for(int wordLen=1; wordLen<=20; wordLen++){
			ArrayList<String> currLenWordList = wordMap.get(wordLen);
			if(currLenWordList == null){
				continue;
			}
			int listLen = currLenWordList.size();
			for(int wordIdx=0; wordIdx < listLen; wordIdx++){
				ArrayList<String> resultList = new ArrayList<String>();
				resultList.add(currLenWordList.get(wordIdx));
				similarWordsAhead(wordLen, wordIdx, resultList);
				if(resultList.size() > 1){
					similarGroups.add(resultList);
				}
			}
		}
		return similarGroups;
	}
	
	/*
	 * Recursive helper form the grouping similar words
	 * 
	 * Group hold words which are mutually similar to each other.
	 */
	private void similarWordsAhead(int wordLen, int wordIdx, ArrayList<String> resultList){
		for(int i=wordLen; i < wordLen+2; i++){
			ArrayList<String> currLenWordList = wordMap.get(i);
			if(currLenWordList == null){
				continue;
			}
			for(int j=wordIdx+1; j<currLenWordList.size(); j++){
				String thisWord = currLenWordList.get(j);
				if(isMutuallySimilar(thisWord, resultList)){
					resultList.add(thisWord);
				}
			}
		}
	}
	
	/*
	 * Returns true if the input word is similar to all other words
	 */
	private boolean isMutuallySimilar(String word, ArrayList<String> resultList){
		for(String otherWord : resultList){
			if(WordUtil.leastBoundEditDistance(word, otherWord) > 1){
				return false;
			}
		}
		return true;
	}

}
