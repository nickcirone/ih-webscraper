package webscrape;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * ih-webscraper
 * Class to extract data from a single webpage. Written by Nicholas Cirone,
 * 10/19/17 - For Industry Hub - diversitypolicy.com
 */

public class SinglePageSoup {

  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    Document doc = Jsoup.connect("http://diversity.unc.edu/resources/best/").timeout(6000)
        .get();
    Elements elements = doc.select("div.panel-body");
    for (Element currentDiv : elements) {

    }
  }

}
