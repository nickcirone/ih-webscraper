package webscrape;
;
import java.util.*;

public class TextWebPage {

    //private String _page_url;
    private ArrayList<String> _titles = new ArrayList<String>();
    private ArrayList<String> _paragraphs = new ArrayList<String>();
    private ArrayList<String> _listItems = new ArrayList<String>();
    private ArrayList<String> _headers = new ArrayList<String>();
    private ArrayList<String> _allText = new ArrayList<String>();
    private Date _dateAccessed;

    public TextWebPage(SinglePageParser spp) {
        this._titles = spp.getTitle();
        this._paragraphs = spp.getParagraphs();
        this._listItems = spp.getListItems();
        this._headers = spp.getHeaders();
        //this._page_url = spp.getPageUrl();
        toAllText();
        _dateAccessed = new Date();
        System.out.println("Parsed: " + spp.getPageUrl() + " on " +_dateAccessed.toString());
    }

    public String textToSingleString() {
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

    public ArrayList<String> getAllText() {
        return _allText;
    }

    private void toAllText() {
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
    }

}
