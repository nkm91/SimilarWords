/**
 * 
 */
package com.nk.processor;


/**
 * @author Nandkishor
 *
 */
public class TextProcessorHelper {

	/*
	 * Returns all word strings from text
	 */
	public String[] getWordsFromText(String text){
		String[] words = text.split("\\s+");
		return words;
	}
	
	public String removePunctuation(String word){
		return word.replaceAll("[^a-zA-Z]", "");
	}

}
