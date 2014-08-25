/**
 * 
 */
package com.nk.novel.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.nk.processor.WordUtil;

/**
 * @author Nandkishor
 *
 *Holds the words for a nove or any text
 */
public class Trie extends WordStore{

	
	private TrieNode root;
	
	public Trie(String novelName){
		super(novelName);
		root = new TrieNode();
	}
	
	/*
	 * Adds a new word to Trie structure
	 */
	@Override
	public void addWord(String word){
		word = word.toLowerCase();
		int wordLen = word.length();
		TrieNode currentNode = root;
		TrieNode[] children;
		for(int i = 0; i < wordLen; i++){
			children = currentNode.getChildren();
			if( children[word.charAt(i)-97]== null){
				children[word.charAt(i)-97] = new TrieNode();
			}
			currentNode = children[word.charAt(i)-97];
		}
		currentNode.setIsWord(true);
	}
	
	/*
	 * Adds all similar words to the ArrayList result
	 */
	@Override
	public ArrayList<String> wordsSimilarTo(String input){
		ArrayList<String> result = new ArrayList<String>();
		String trieWord = "";
		similarWordRecur(root, input, result, trieWord);
		return result;
	}
	
	/*
	 * Recursively adds the similar words to the resultant list.
	 * Checks distance for words if their length difference is less than 2
	 * Ignores words which are 2 or more chars bigger in length than input word.
	 */
	private void similarWordRecur(TrieNode node, String input, ArrayList<String> result, String trieWord){
		if(WordUtil.absDiff(trieWord.length(), input.length()) < 2){
			if(node.isWord()){
				if(WordUtil.leastBoundEditDistance(input, trieWord) == 1){
					result.add(trieWord);
				}
			}
		}
		else if(trieWord.length()-input.length() >= 1){//No word deeper can be similar
			return;
		}
		
		TrieNode[] children = node.getChildren();
		for(int i=0; i<WordUtil.CHARS_LENGTH; i++){
			if(children[i] != null){
				similarWordRecur(children[i], input, result, trieWord + (char)('a'+i));
			}
		}
	}
	
	/*
	 * Returns Map containing ArrayList of words with word length as key
	 */
	@Override
	public Map<Integer, ArrayList<String>> getWordsGroupedByLen(){
		Map<Integer, ArrayList<String>> mapOfLists = new HashMap<Integer, ArrayList<String>>();
		groupWordsRecur(root, mapOfLists, 1, "");
		return mapOfLists;
	}
	
	private void groupWordsRecur(TrieNode node, Map<Integer,ArrayList<String>> mapOfLists, int wordLen, String word){
		if(node == null){
			return;
		}
		if(node.isWord()){
			if(mapOfLists.get(wordLen) == null){
				mapOfLists.put(wordLen, new ArrayList<String>());
			}
			mapOfLists.get(wordLen).add(word);
		}
		TrieNode[] children = node.getChildren();
		for(int i=0; i<WordUtil.CHARS_LENGTH; i++){
			if(children[i] != null){
				groupWordsRecur(children[i], mapOfLists, wordLen+1, word+(char)('a'+i));
			}
		}
	}
	
	

}
