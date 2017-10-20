package webscrape;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * ih-webscraper
 * Main class for webscraper. Written by Nicholas Cirone,
 * 10/19/17 - For IndustryHub - diversitypolicy.com
 */

public class SoupMain {

  public static void main(String[] args) throws IOException {
	  
    SinglePageParser currentPage = new SinglePageParser("http://diversity.unc.edu/resources/best/");
    SinglePageParser secondPage = new SinglePageParser("http://diversity.arizona.edu/diversity-policies-statements");
    ArrayList<String> listItems = currentPage.getListItems("div.panel-body");
    ArrayList<String> headItems = currentPage.getTitle();
    ArrayList<String> paraItems = secondPage.getParagraphs();
    for (String curr: listItems) {
    	System.out.println(curr);
    }
    System.out.println();
    for (String curr: headItems) {
    	System.out.println(curr);
    }
    System.out.println();
    for (String curr: paraItems) {
    	System.out.println(curr);
    }
  }

}
