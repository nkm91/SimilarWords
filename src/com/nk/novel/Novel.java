/**
 * 
 */
package com.nk.novel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author Nandkishor
 *
 */
public class Novel {
	
	private URL novelUrl;
	
	private String novelText;
	
	public void setNoveUrl(String novelUrl) throws MalformedURLException{
		this.novelUrl = new URL(novelUrl);
		novelText = null;
	}

	/*
	 * Gives HTMl content of the site
	 */
	private String fetchSiteContents() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(novelUrl.openStream()));
		StringBuilder siteHtml = new StringBuilder("");
		String line;
		while((line = in.readLine()) != null){
			siteHtml.append(line);
		}
		in.close();
		return siteHtml.toString();
	}
	
	/*
	 * Stores Novel text
	 */
	private void storeSiteText() throws IOException{
		//String htmlContent = fetchSiteContents();
		//Document doc = Jsoup.parse(htmlContent); 
		novelText = "ge get wet set met et be we no ewrh e"; //doc.body().text();
	}
	
	/*
	 * Returns novel text and stores it
	 */
	public String getSiteText() throws IOException{
		if(novelText == null){
			storeSiteText();
		}
		return novelText;
	}
}
