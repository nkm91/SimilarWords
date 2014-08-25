/**
 * 
 */
package com.nk.processor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Set;

import com.nk.novel.Novel;
import com.nk.novel.storage.Trie;
import com.nk.novel.storage.WordStore;

/**
 * @author Nandkishor
 *
 */
public class NovelProcessor {
	
	private Novel novel;
	
	private TextProcessorHelper TxtHelper;
	
	private WordStore trie;
	
	private WordGrouper wordGrouper;
	
	public NovelProcessor(String novelName, String NovelUrl) throws MalformedURLException{
		novel = new Novel();
		novel.setNoveUrl(NovelUrl);
		trie = new Trie(novelName);
		wordGrouper = null;
	}
	
	/*
	 *  Builds Trie 
	 */
	public void buildTrie(){
		String[] wordsInNovel = null;
		if(TxtHelper == null){
			TxtHelper = new TextProcessorHelper();
		}
		try {
			wordsInNovel = TxtHelper.getWordsFromText(novel.getSiteText());
		} catch (IOException e) {
			e.printStackTrace();
		}
		novel = null; // novel not required now
		if(wordsInNovel == null){
			return;
		}
		for (String word : wordsInNovel) { // store words in the trie structure
			word = TxtHelper.removePunctuation(word);
			if(word != null && word != ""){
				trie.addWord(word);
			}
		}
	}
	
	/*
	 * Returns list of similar words for given input
	 */
	public ArrayList<String> getSimilarWords(String input){
		if(input == null){
			return null;
		}
		input = input.toLowerCase();
		ArrayList<String> similarWords = trie.wordsSimilarTo(input);
		return similarWords;
	}
	
	/*
	 * Returns set of the similar word lists
	 */
	public Set<ArrayList<String>> getAllSimilarGroups(){
		if(wordGrouper == null){
			wordGrouper = new WordGrouper(trie.getWordsGroupedByLen());
		}
		return wordGrouper.groupSimilarWords();
	}

}
