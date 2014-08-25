package com.nk.analyser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;

/**
 * @author Nandkishor
 *
 *Classifies text into positive, negative and neutral based upon the meaning.
 */
public class SentimentAnalyser {
	
	static final String filePath = "Data" + File.separator + "classifier.txt";
	
	String[] categories; 
	LMClassifier classs; 
	
	public SentimentAnalyser() { 
		try { 
			classs= (LMClassifier) AbstractExternalizable.readObject(new File(filePath)); 
			categories = classs.categories();
		} catch (ClassNotFoundException e) { 
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace(); 
		} 
	}
	
	/*
	 * From an arraylist removes the words which doesn't match the sentiment of input word
	 */
	public void filterDiffSentiWords(String word, ArrayList<String> result){
		String sentiClass = this.classify(word);
		for(int i=0; i<result.size(); i++){
			if(classify(result.get(i)) != sentiClass){ // sentiment not matched.
				result.remove(i);
			}
		}
	}
 	
	/*
	 * Returns the category of the input text
	 */
	public String classify(String text) { 
		ConditionalClassification classification = classs.classify(text); 
		return classification.bestCategory(); 
	}
}
