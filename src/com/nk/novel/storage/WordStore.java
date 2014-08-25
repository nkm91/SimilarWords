/**
 * 
 */
package com.nk.novel.storage;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Nandkishor
 *
 */
public abstract class WordStore{
	
	protected String novelName;
	
	public WordStore(String novelName){
		this.novelName = novelName;
	}
	
	public String getNovelName(){
		return novelName;
	}
	
	public abstract void addWord(String word);

	public abstract ArrayList<String> wordsSimilarTo(String input);

	public abstract Map<Integer, ArrayList<String>> getWordsGroupedByLen();
}
