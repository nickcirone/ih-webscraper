package webscrape;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * ih-webscraper
 * Class to extract data from a single webpage. Written by Nicholas Cirone,
 * 10/19/17 - For Industry Hub - diversitypolicy.com
 */

public class SoupMain {

  public static void main(String[] args) throws IOException {
	  
    SinglePageParser currentPage = new SinglePageParser("http://diversity.unc.edu/resources/best/");
    ArrayList<String> listItems = currentPage.getListItems("div.panel-body");
    for (String curr: listItems) {
    	System.out.println(curr);
    }
    
  }

}
