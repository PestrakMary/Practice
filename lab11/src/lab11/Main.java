package lab11;

import javax.swing.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main extends JFrame {
    private JTable langTable;
    private LangTableModel tableModel;
    private JCheckBoxMenuItem validateXML;


    public Main() {
        setSize(650, 560);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem loadFromXML = new JMenuItem("[DOM] Load XML...");
        loadFromXML.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    tableModel = new LangTableModel(chooser.getSelectedFile(), validateXML.getState());
                    langTable.setModel(tableModel);
                   if (validateXML.getState())
                        JOptionPane.showMessageDialog(this,
                                "XML is valid and matches XSD schema",
                                "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, "Invalid XML file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        file.add(loadFromXML);

        validateXML = new JCheckBoxMenuItem("Use XML Schema");
        validateXML.setState(true);
        file.add(validateXML);
        file.addSeparator();

        JMenuItem saveToXML = new JMenuItem("Save to XML");
        saveToXML.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    tableModel.saveToXML(chooser.getSelectedFile());
                } catch (FileNotFoundException err) {
                    JOptionPane.showMessageDialog(this, "Invalid file path", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(saveToXML);

        JMenuItem calcXml = new JMenuItem("[SAX] Do calculations");
        calcXml.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser parser = factory.newSAXParser();
                    MySAXHandler handler = new MySAXHandler();
                    parser.parse(chooser.getSelectedFile(), handler);
                    JOptionPane.showMessageDialog(this,
                            "Languages count: " + handler.getLangCount() +
                                    "\nTotal words: " + handler.getTotalWords() +
                                    "\nAverage words count: " + handler.getAvgWords()+
                                    "\nThe oldest language is: " + handler.getOldestLang()                                    ,
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, "Invalid XML file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        file.add(calcXml);

        file.addSeparator();

        JMenuItem saveToBinary = new JMenuItem("Save to binary");
        saveToBinary.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    tableModel.saveToBinary(chooser.getSelectedFile());
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(this, "Invalid file path", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(saveToBinary);

        JMenuItem loadFromBinary = new JMenuItem("Load from binary");
        loadFromBinary.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    tableModel = LangTableModel.loadFromBinary(chooser.getSelectedFile());
                    langTable.setModel(tableModel);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, "Invalid binary file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(loadFromBinary);

        file.addSeparator();

        JMenuItem convertToHTML = new JMenuItem("Convert to HTML");
        convertToHTML.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    TransformerFactory factory = TransformerFactory.newInstance();
                    Transformer  transformer = factory.newTransformer(new StreamSource(LangTableModel.class.getClassLoader().getResourceAsStream("html.xsl")));
                    transformer.transform(new StreamSource(chooser.getSelectedFile()), new StreamResult(new File("output.html")));
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, "Invalid XML file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(convertToHTML);

        JMenuItem convertToTXT = new JMenuItem("Convert to TXT");
        convertToTXT.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    TransformerFactory factory = TransformerFactory.newInstance();
                    Transformer transformer = factory.newTransformer(new StreamSource(LangTableModel.class.getClassLoader().getResourceAsStream("txt.xsl")));
                    transformer.transform(new StreamSource(chooser.getSelectedFile()), new StreamResult(new File("output.txt")));
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, "Invalid XML file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(convertToTXT);



        JMenu langMenu = new JMenu("Languages");
        JMenuItem addLanguage = new JMenuItem("Add language");
        addLanguage.addActionListener(e -> {
            tableModel.getItems().add(new Lang());
            langTable.updateUI();
        });
        langMenu.add(addLanguage);

        JMenuItem deleteLanguage = new JMenuItem("Delete selected language");
        deleteLanguage.addActionListener(e -> {
            tableModel.deleteRows(langTable.getSelectedRows());
            langTable.updateUI();
        });
        langMenu.add(deleteLanguage);

        menuBar.add(file);
        menuBar.add(langMenu);
        add(menuBar, BorderLayout.NORTH);


        tableModel = new LangTableModel();
        langTable = new JTable(tableModel);
        add(new JScrollPane(langTable), BorderLayout.CENTER);
    }



    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }
}