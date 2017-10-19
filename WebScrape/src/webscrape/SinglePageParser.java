package webscrape;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SinglePageParser {
	
	private String _pageUrl;
	private Document _webDoc;

	public SinglePageParser (String page_url) throws IOException {
		this._pageUrl = page_url;
		_webDoc = Jsoup.connect(page_url).timeout(10000).get();
	}
	
	public String getPageUrl() {
		return _pageUrl;
	}
	
	public ArrayList<String> getListItems() {
		Elements rawListItems = _webDoc.select("li");
		ArrayList<String> liArrList = new ArrayList<String>();
	    for (Element currentLi : rawListItems) {
	    	String listItem = currentLi.text();
	    	liArrList.add(listItem);
	    }
	    return liArrList;
	}
	
	//Specify the markup tags from which you want to extract li's
	
	public ArrayList<String> getListItems(String tag_specify) {
		Elements specifiedElements = _webDoc.select(tag_specify);
		Elements rawListItems = specifiedElements.select("li");
		ArrayList<String> liArrList = new ArrayList<String>();
	    for (Element currentLi : rawListItems) {
	    	String listItem = currentLi.text();
	    	liArrList.add(listItem);
	    }
	    return liArrList;
	}
	
}
