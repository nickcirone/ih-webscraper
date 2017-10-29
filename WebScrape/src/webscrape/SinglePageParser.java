package webscrape;

import java.io.IOException;


import java.util.*;

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
    private ArrayList<String> _titles = new ArrayList<String>();
    private ArrayList<String> _paragraphs = new ArrayList<String>();
    private ArrayList<String> _listItems = new ArrayList<String>();
    private ArrayList<String> _headers = new ArrayList<String>();
    private ArrayList<String> _links = new ArrayList<String>();
    private Date _dateAccessed;

    // Constructor - Parameters: String of web page URL

	protected SinglePageParser (String page_url) throws IOException {
		this._pageUrl = page_url;
		_webDoc = Jsoup.connect(page_url).timeout(10000).get();
		scanTitle();
		scanHeaders();
		scanListItems();
		scanParagraphs();
		scanLinks();
		_dateAccessed = new Date();
	}

	// START SCANNER METHODS

    // Private methods called withing constructor, sets all ArrayList<String> fields according to HTML Tags

    // Scans all List Items (li) into _listItems ArrayList<String>
	
	private void scanListItems() {
		Elements rawListItems = _webDoc.select("li");
	    for (Element currentLi : rawListItems) {
	    	String listItem = currentLi.text();
	    	_listItems.add(listItem);
	    }
	}

	// Scans all Paragraphs (p) into _paragraphs ArrayList<String>
	
	private void scanParagraphs() {
		Elements rawParagraphs = _webDoc.select("p");
		for (Element currentP : rawParagraphs) {
			String paraItem = currentP.text();
			_paragraphs.add(paraItem);
		}
	}

	// Scans all Links (a) into _links, if the links begin in "http"
	
	private void scanLinks() {
		Elements rawLinks = _webDoc.select("a");
		for (Element currentLink : rawLinks) {
			String linkText = currentLink.attr("href");
			if (linkText.length() > 4) {
                String linkTextSub = linkText.substring(0, 4);
                if (linkTextSub.equals("http")) {
                    _links.add(linkText);
                }
            }
		}
	}

	// Scans all Headers (h1 - h6) into _headers
	
	private void scanHeaders() {
		for (int i = 1; i <= 6; i++) {
			Elements rawHeaders = _webDoc.select("h" + i);	
			for (Element currentHeader : rawHeaders) {
				String headText = currentHeader.text();
				_headers.add(headText);
			}
		}
	}

	// Scans all Titles - usually only one -  into _titles
	
	private void scanTitle() {
		Elements rawTitles = _webDoc.select("title");
		ArrayList<String> titleArrList = new ArrayList<String>();
		for (Element currentTitle : rawTitles) {
			String titleText = currentTitle.text();
			_titles.add(titleText);
		}
	}

	// END SCANNER METHODS

    // START GETTER METHODS

    // Returns current url as String

    protected String getPageUrl() {
        return _pageUrl;
    }

    // Returns ArrayList<String> of titles on page

	protected ArrayList<String> getTitle() {
	    return _titles;
    }

    // Returns ArrayList<String> of all Headers (h1 - h6)

    protected ArrayList<String> getHeaders() {
	    return _headers;
    }

    /*
    Returns ArrayList<String> of Headers of a particular weight
    For example, weight 1 will return all "h1" headers, weight 2 will return all "h2" headers
    Does not store the headers in _headers or return _headers, but a separate ArrayList initialized in the method
    */

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

    // Returns ArrayList<String> of all List Items (li) on page

    protected ArrayList<String> getListItems() {
	    return _listItems;
    }

    // Returns ArrayList<String> of all List Items present within a separate HTML tag
    // For example if you only want the List Items that exist within <div>'s, specify div as a parameter

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

    // double specify, I.E. div, td would only give you the List Items that exist within divs and tables

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

    protected ArrayList<String> getParagraphs() {
	    return _paragraphs;
    }

    // single specify, similar to List Items specifiers

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

    // Returns all links on page that begin in "http"

    protected ArrayList<String> getLinks() {
	    return _links;
    }

    // Returns ArrayList of all text on page - not including links
    // The ArrayList holds text in this order: Title, Headers, Paragraphs, List Items

    protected ArrayList<String> getFullText() {
	    ArrayList<String> _allText = new ArrayList<String>();
        for (String str: _titles) {
            _allText.add(str);
        }
        for (String str: _headers) {
            _allText.add(str);
        }
        for (String str: _paragraphs) {
            _allText.add(str);
        }
        for (String str: _listItems) {
            _allText.add(str);
        }
        return _allText;
    }

    // Returns all text on page - not including links - as a single, very long String
    // String lists text in this order: Title, Headers, Paragraphs, List Items
    // Adds new line between each individual String object that is appended to the returned String

    protected String getFullTextString() {
        StringBuilder bigString = new StringBuilder();
        for (String str : _titles) {
            bigString.append(str);
            bigString.append(System.getProperty("line.separator"));
        }
        bigString.append(System.getProperty("line.separator"));
        for (String str : _headers) {
            bigString.append(str);
            bigString.append(System.getProperty("line.separator"));
        }
        bigString.append(System.getProperty("line.separator"));
        for (String str : _paragraphs) {
            bigString.append(str);
            bigString.append(System.getProperty("line.separator"));
        }
        bigString.append(System.getProperty("line.separator"));
        for (String str : _listItems) {
            bigString.append(str);
            bigString.append(System.getProperty("line.separator"));
        }
        bigString.append(System.getProperty("line.separator"));
        return bigString.toString();
    }
	
}
