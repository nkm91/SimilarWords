/**
 * 
 */
package tester;

import java.net.MalformedURLException;
import java.util.ArrayList;

import com.nk.analyser.SentimentAnalyser;
import com.nk.processor.NovelProcessor;

/**
 * @author Nandkishor
 *
 * Bonus 2 'main' 
 */
public class SimilarSentiment {
	
	
	public static void main(String[] args) {
		
		NovelProcessor np = null;
		try {
			np = new NovelProcessor("Alice", "http://fiction.eserver.org/novels/alice_in_wonderland.html");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(np != null){
			np.buildTree();
			ArrayList<String> result = np.getSimilarSentimentWords("some");
			if(result != null){
				for(String simWord : result){
					System.out.print(simWord + " ");
				}
			}
		}

//		SentimentAnalyser sa = new SentimentAnalyser();
//		System.out.println(sa.classify("impossible"));
	}

}
