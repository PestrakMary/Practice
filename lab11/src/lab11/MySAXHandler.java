package lab11;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySAXHandler extends DefaultHandler {
    private int rowCount;
    private int totalWords;
    private int oldestLang = 2019;
    private String nameOldestLang = "";
    private boolean change;
    private boolean isParsingName;
    private boolean isParsingWords;

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        super.startElement(namespaceURI, localName, qName, atts);
        if (qName.equals("languages")) {
            rowCount = 0;
            totalWords = 0;
        }
        if (qName.equals("language")) {
            rowCount++;
            if(oldestLang>Integer.parseInt(atts.getValue("year"))){
                oldestLang = Integer.parseInt(atts.getValue("year"));
                change = true;
            }

        }
        if (qName.equals("words")) {
            isParsingWords = true;
        }
        if (qName.equals("name")) {
            if(change) {
                isParsingName = true;
                change = false;
            }

        }
    }

    @Override
    public void endElement(String s, String s1, String qName) throws SAXException {
        super.endElement(s, s1, qName);
        if (qName.equals("words")) {
            isParsingWords = false;
        }
        if (qName.equals("name")) {
            isParsingName = false;
        }
    }

    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {
        super.characters(chars, i, i1);
        if(isParsingName)
            nameOldestLang = new String(chars, i, i1);
            if (isParsingWords)
                totalWords += Integer.parseInt(new String(chars, i, i1));
        }

    public int getLangCount() {
        return rowCount;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public double getAvgWords() {
        return ((double)totalWords)/rowCount;
    }

    public String getOldestLang(){
        return nameOldestLang;
    }
}
