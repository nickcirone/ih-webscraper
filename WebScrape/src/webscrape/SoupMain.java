package webscrape;

import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;
import de.daslaboratorium.machinelearning.classifier.Classifier;

import java.io.IOException;
import java.util.*;

/*
 * ih-webscraper
 * Main class for webscraper. Written by Nicholas Cirone,
 * 10/19/17 - For IndustryHub - diversitypolicy.com
 */

public class SoupMain {

  public static void main(String[] args) throws IOException {
	  
      SinglePageParser diversityPage = new SinglePageParser("http://diversity.unc.edu/resources/best/");
      SinglePageParser campusRecPage = new SinglePageParser("http://campusrec.unc.edu/about-us/");
      SinglePageParser academicsPage = new SinglePageParser("http://www.unc.edu/academics/");
      SinglePageParser divTestPage = new SinglePageParser("http://diversity.arizona.edu/diversity-policies-statements");
      SinglePageParser acadTestPage = new SinglePageParser("https://college.harvard.edu/academics");
    //ArrayList<String> listItems = currentPage.getListItems("div.panel-body");
    //ArrayList<String> headItems = currentPage.getTitle();
    //ArrayList<String> paraItems = secondPage.getParagraphs();
      TextWebPage diversityTextPage = new TextWebPage(diversityPage);
      TextWebPage campusRecTextPage = new TextWebPage(campusRecPage);
      TextWebPage academicsTextPage = new TextWebPage(academicsPage);
      TextWebPage divTestTextPage = new TextWebPage(divTestPage);
      TextWebPage acadTestTextPage = new TextWebPage(acadTestPage);
      //System.out.println(diversityTextPage.textToSingleString());
      Classifier<String, String> bayes = new BayesClassifier<String, String>();

      ArrayList<String> diversitySample = diversityTextPage.getAllText();
      ArrayList<String> campusRecSample = campusRecTextPage.getAllText();
      ArrayList<String> academicsSample = academicsTextPage.getAllText();

      bayes.setMemoryCapacity(2500);

      bayes.learn("diversity", diversitySample);
      bayes.learn("athletics", campusRecSample);
      bayes.learn("academics", academicsSample);


      System.out.println(bayes.classify(divTestTextPage.getAllText()).getCategory());
      System.out.println(bayes.classify(acadTestTextPage.getAllText()).getCategory());



  }

}
