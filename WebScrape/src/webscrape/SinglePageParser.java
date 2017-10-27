package webscrape;

import java.io.IOException;


import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/*
 * ih-webscraper
 * Parses a single url and returns ArrayList<String>'s of web page's text data using method specified tags 
 * Nicholas Cirone for IndustryHub - diversitypolicy.com
 */

public class SinglePageParser {
	
	private String _pageUrl;
	private Document _webDoc;

	public SinglePageParser (String page_url) throws IOException {
		this._pageUrl = page_url;
		_webDoc = Jsoup.connect(page_url).timeout(10000).get();
	}
	
	protected String getPageUrl() {
		return _pageUrl;
	}
	
	// Methods to return ArrayLists of List Items : with varying degrees of specificity 
	
	protected ArrayList<String> getListItems() {
		Elements rawListItems = _webDoc.select("li");
		ArrayList<String> liArrList = new ArrayList<String>();
	    for (Element currentLi : rawListItems) {
	    	String listItem = currentLi.text();
	    	liArrList.add(listItem);
	    }
	    return liArrList;
	}
	
	// Specify the markup tags from which you want to extract li's
	
	protected ArrayList<String> getListItems(String tag_specify) {
		Elements specifiedElements = _webDoc.select(tag_specify);
		Elements rawListItems = specifiedElements.select("li");
		ArrayList<String> liArrList = new ArrayList<String>();
	    for (Element currentLi : rawListItems) {
	    	String listItem = currentLi.text();
	    	liArrList.add(listItem);
	    }
	    return liArrList;
	}
	
	// double specify 
	
	protected ArrayList<String> getListItems(String tag_specify1, String tag_specify2) {
		Elements specifiedElements1 = _webDoc.select(tag_specify1);
		Elements specifiedElements2 = specifiedElements1.select(tag_specify2);
		Elements rawListItems = specifiedElements2.select("li");
		ArrayList<String> liArrList = new ArrayList<String>();
	    for (Element currentLi : rawListItems) {
	    	String listItem = currentLi.text();
	    	liArrList.add(listItem);
	    }
	    return liArrList;
	}
	
	// Methods to return ArrayLists of paragraphs : with varying degrees of specificity
	
	protected ArrayList<String> getParagraphs() {
		Elements rawParagraphs = _webDoc.select("p");
		ArrayList<String> paraArrList = new ArrayList<String>();
		for (Element currentP : rawParagraphs) {
			String paraItem = currentP.text();
			paraArrList.add(paraItem);
		}
		return paraArrList;
	}
	
	// single specify
	
	protected ArrayList<String> getParagraphs(String tag_specify) {
		Elements specifiedElements = _webDoc.select(tag_specify);
		Elements rawParagraphs = specifiedElements.select("p");
		ArrayList<String> paraArrList = new ArrayList<String>();
		for (Element currentP : rawParagraphs) {
			String paraItem = currentP.text();
			paraArrList.add(paraItem);
		}
		return paraArrList;
	}
	
	// double specify 
	
	protected ArrayList<String> getParagraphs(String tag_specify1, String tag_specify2) {
		Elements specifiedElements = _webDoc.select(tag_specify1);
		Elements specifiedElements2 = specifiedElements.select(tag_specify2);
		Elements rawParagraphs = specifiedElements2.select("p");
		ArrayList<String> paraArrList = new ArrayList<String>();
		for (Element currentP : rawParagraphs) {
			String paraItem = currentP.text();
			paraArrList.add(paraItem);
		}
		return paraArrList;
	}
	
	// Returns an ArrayList of all links on current webpage : if the link begins with http
	
	protected ArrayList<String> getLinks() {
		Elements rawLinks = _webDoc.select("a");
		ArrayList<String> linkArrList = new ArrayList<String>();
		for (Element currentLink : rawLinks) {
			String linkText = currentLink.attr("href");
			String linkTextSub = linkText.substring(0, 4);
			if (linkTextSub.equals("http")) {
				linkArrList.add(linkText);
			}			
		}
		return linkArrList;
	}
	
	// Returns an ArrayList of all headers on current webpage
	
	protected ArrayList<String> getHeaders() {
		ArrayList<String> headArrList = new ArrayList<String>();
		for (int i = 1; i <= 6; i++) {
			Elements rawHeaders = _webDoc.select("h" + i);	
			for (Element currentHeader : rawHeaders) {
				String headText = currentHeader.text();
				headArrList.add(headText);
			}
		}		
		return headArrList;
	}
	
	// Returns headers of certain weight, i.e. h2 has weight 2, h3 has weight 3 and so on
	// Returns null ArrayList if weight is < 1 or > 6
	
	protected ArrayList<String> getHeaders(int weight) {
		ArrayList<String> headArrList = new ArrayList<String>();
		if (weight >= 1 && weight <= 6) {
			Elements rawHeaders = _webDoc.select("h" + weight);		
			for (Element currentHeader : rawHeaders) {
				String headText = currentHeader.text();
				headArrList.add(headText);
			}
		}	
		return headArrList;
	}
	
	// Returns an ArrayList of the current webpage's title
	
	protected ArrayList<String> getTitle() {
		Elements rawTitles = _webDoc.select("title");
		ArrayList<String> titleArrList = new ArrayList<String>();
		for (Element currentTitle : rawTitles) {
			String titleText = currentTitle.text();
			titleArrList.add(titleText);
		}
		return titleArrList;
	}
	
}
