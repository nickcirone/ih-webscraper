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
      //SinglePageParser acadTestPage = new SinglePageParser("https://college.harvard.edu/academics");
      //ArrayList<String> listItems = currentPage.getListItems("div.panel-body");
      //ArrayList<String> headItems = currentPage.getTitle();
      //ArrayList<String> paraItems = secondPage.getParagraphs();



      //System.out.println(diversityTextPage.textToSingleString());
      Classifier<String, String> firstBayes = new BayesClassifier<String, String>();

      ArrayList<String> diversitySample = diversityPage.getFullText();
      ArrayList<String> campusRecSample = campusRecPage.getFullText();
      ArrayList<String> academicsSample = academicsPage.getFullText();

      firstBayes.setMemoryCapacity(5000);

      ArrayList<ArrayList<String>> diversityExamples = new ArrayList<ArrayList<String>>();
      ArrayList<ArrayList<String>> academicsExamples = new ArrayList<ArrayList<String>>();
      ArrayList<ArrayList<String>> athleticsExamples = new ArrayList<ArrayList<String>>();
      ArrayList<ArrayList<String>> employeeExamples = new ArrayList<ArrayList<String>>();

      DiversityPolicyExamples divPolicy = new DiversityPolicyExamples();
      EmployeePolicyExamples emplPolicy = new EmployeePolicyExamples();
      AthleticPolicyExamples athlPolicy = new AthleticPolicyExamples();

      for (ArrayList<String> curr : divPolicy.exampleList) {
          firstBayes.learn("diversity", curr);
      }

      for (ArrayList<String> curr : emplPolicy.exampleList) {
          firstBayes.learn("employee", curr);
      }

      for (ArrayList<String> curr : athlPolicy.exampleList) {
          firstBayes.learn("athletics", curr);
      }


      System.out.println(diversityPage.getDateAccessed().toString());
      System.out.println(campusRecPage.getDateAccessed().toString());


      System.out.println(firstBayes.classify(diversityPage.getTitle()).getCategory());
      System.out.println(firstBayes.classify(campusRecPage.getTitle()).getCategory());



  }

}
