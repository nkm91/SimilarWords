/**
 * 
 */
package tester;

import java.net.MalformedURLException;
import java.util.ArrayList;

import com.nk.processor.NovelProcessor;

/**
 * @author Nandkishor
 *
 */
public class Similar {
	
	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		
		NovelProcessor np = new NovelProcessor("Alice", "http://fiction.eserver.org/novels/alice_in_wonderland.html");
		np.buildTrie();
		ArrayList<String> result = np.getSimilarWords("ge");
		if(result != null){
			for(String simWord : result){
				System.out.print(simWord + " ");
			}
		}
	}

}
