package lab11;

import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LangTableModel extends AbstractTableModel implements Serializable {
    private List<Lang> langList;
    private final String COL_NAMES[] = {"Name", "Author", "Extension", "Year", "Words"};

    public LangTableModel(File file, boolean validate) throws IOException, SAXException, org.xml.sax.SAXException {
        langList = new ArrayList<>();
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);

          if (validate) {
              SchemaFactory factory = (SchemaFactory) SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(LangTableModel.class.getClassLoader().getResource("schema1.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));
         }

        try {
            DocumentBuilder builder = f.newDocumentBuilder();
            Document doc = builder.parse(file);
            Node root = doc.getFirstChild();
            if (!root.getNodeName().equals("languages"))
                throw new SAXException();
            NodeList items = root.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node node = items.item(i);
                if (!node.getNodeName().equals("language"))
                    continue;
                Lang curLang= new Lang();
                curLang.setYear(Integer.parseInt(node.getAttributes().getNamedItem("year").getTextContent()));
                curLang.setExtension(node.getAttributes().getNamedItem("extension").getTextContent());
                NodeList fields = node.getChildNodes();
                for(int j = 0; j < fields.getLength(); j++) {
                    switch (fields.item(j).getNodeName()) {
                        case "name":
                            curLang.setName(fields.item(j).getTextContent());
                            break;
                        case "author":
                            curLang.setAuthor(fields.item(j).getTextContent());
                            break;
                        case "words":
                            curLang.setWords(Integer.parseInt(fields.item(j).getTextContent()));
                            break;
                    }
                }
                langList.add(curLang);
            }
        } catch (ParserConfigurationException ignored) {}
    }

    public LangTableModel() {
        langList = new ArrayList<>();
    }

    public List<Lang> getItems() {
        return langList;
    }

    public void setItems(List<Lang> list) {
        this.langList = list;
    }

    @Override
    public int getRowCount() {
        return langList.size();
    }

    @Override
    public int getColumnCount() {
        return COL_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return langList.get(rowIndex).getName();
            case 1:
                return langList.get(rowIndex).getAuthor();
            case 2:
                return langList.get(rowIndex).getExtension();
            case 3:
                return langList.get(rowIndex).getYear();
            case 4:
                return langList.get(rowIndex).getWords();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return COL_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column < 3)
            return String.class;
        else
            return Integer.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                langList.get(rowIndex).setName((String) aValue);
                break;
            case 1:
                langList.get(rowIndex).setAuthor((String) aValue);
                break;
            case 2:
                langList.get(rowIndex).setExtension((String) aValue);
                break;
            case 3:
                langList.get(rowIndex).setYear((Integer) aValue);
                break;
            case 4:
                langList.get(rowIndex).setWords((Integer) aValue);
                break;
        }
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<languages>\n");
        for (Lang lang : langList){
            sb.append(lang.toXML());
            sb.append("\n");
        }
        sb.append("</languages>\n");
        return sb.toString();
    }

    public void saveToXML(File file) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        pw.println(toXML());
        pw.close();
    }

    public void saveToBinary(File file) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
        stream.writeObject(langList);
        stream.close();
    }

    public static LangTableModel loadFromBinary(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
        LangTableModel model = new LangTableModel();
        model.setItems((List<Lang>) stream.readObject());
        stream.close();
        return model;
    }

    public void deleteRows(int[] rows) {
        for (int i = rows.length - 1; i >= 0; --i)
            if (rows[i] < langList.size())
                langList.remove(rows[i]);
    }

}
