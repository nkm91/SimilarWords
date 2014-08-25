/**
 * 
 */
package com.nk.processor;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Set;

import com.nk.analyser.SentimentAnalyser;
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
	
	private WordStore tree;
	
	private WordGrouper wordGrouper;
	
	private SentimentAnalyser sentiAnalyser;
	
	public NovelProcessor(String novelName, String NovelUrl) throws MalformedURLException{
		novel = new Novel();
		novel.setNoveUrl(NovelUrl);
		tree = new Trie(novelName);
		wordGrouper = null;
		sentiAnalyser = null;
	}
	
	/*
	 *  Builds Word Stroage
	 */
	public void buildTree(){
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
		for (String word : wordsInNovel) { // store words in the Storage structure
			word = TxtHelper.removePunctuation(word);
			if(word != null && word != ""){
				tree.addWord(word);
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
		return tree.wordsSimilarTo(input);
	}
	
	/*
	 * Returns set of the similar word lists
	 */
	public Set<ArrayList<String>> getAllSimilarGroups(){
		if(wordGrouper == null){
			wordGrouper = new WordGrouper(tree.getWordsGroupedByLen());
		}
		return wordGrouper.groupSimilarWords();
	}
	
	/*
	 * Returns list of similar words for given input which fall in same category as input
	 */
	public ArrayList<String> getSimilarSentimentWords(String input){
		ArrayList<String> result = this.getSimilarWords(input);
		if(sentiAnalyser == null){
			sentiAnalyser = new SentimentAnalyser();
		}
		sentiAnalyser.filterDiffSentiWords(input, result);
		return result;
	}

}
