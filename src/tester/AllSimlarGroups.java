/**
 * 
 */
package tester;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Set;

import com.nk.processor.NovelProcessor;

/**
 * @author Nandkishor
 *
 */
public class AllSimlarGroups {


	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub

		NovelProcessor np = new NovelProcessor("Alice", "http://fiction.eserver.org/novels/alice_in_wonderland.html");
		np.buildTrie();
		Set<ArrayList<String>> similarGroups = np.getAllSimilarGroups();
		for(ArrayList<String> wordList : similarGroups){
			for(String word : wordList){
				System.out.print(word+" ");
			}
			System.out.println("");
		}
		
	}

}
