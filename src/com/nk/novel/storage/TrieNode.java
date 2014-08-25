/**
 * 
 */
package com.nk.novel.storage;

import com.nk.processor.WordUtil;

/**
 * @author Nandkishor
 *
 */
public class TrieNode {
	private boolean isWord;
	private TrieNode[] children;
	
	public TrieNode(){
		isWord = false;
		children = new TrieNode[WordUtil.CHARS_LENGTH];
	}
	
	public boolean isWord(){
		return isWord;
	}
	
	public void setIsWord(boolean isWord){
		this.isWord = isWord;
	}
	
	public TrieNode[] getChildren(){
		return children;
	}

}
