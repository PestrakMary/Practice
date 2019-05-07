package lab3_3;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.stream.JsonParser;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MyFrame extends ApplicationFrame {
    File file;
    ArrayList<Item> arrayList;
    Color[] colors = {new Color(200, 200, 255), new Color(255, 200, 200),
            new Color(200, 255, 200), new Color(200, 255, 200)};
    MyFrame(){
        super("Diagram");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        arrayList = new ArrayList<Item>();
        file = new File("data.json");
        try {
            FileInputStream fileReader = new FileInputStream(file);

            JsonParser parser = Json.createParser(fileReader);
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();
                if (e == JsonParser.Event.KEY_NAME) {
                    String key = parser.getString();
                    parser.next();
                    double value = Double.parseDouble(parser.getString());
                    if(value<0){
                        throw new NumberFormatException();
                    }
                   arrayList.add(new Item(key, value));
                }

            }

            DefaultPieDataset dataset = new DefaultPieDataset();

            for(Item obj: arrayList){
                dataset.setValue(obj.key, obj.value);
            }
            JFreeChart chart = ChartFactory.createPieChart(
                    "Diagram",  // chart title
                    dataset,             // data
                    true,               //  legend
                    true,                // tooltips
                    false                // no URL generation
            );
           chart.setBackgroundPaint(new GradientPaint(new Point(0, 0),
                    new Color(100, 100, 225),
                    new Point(600, 400),
                   Color.PINK));
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setBackgroundPaint(null);
            plot.setInteriorGap(0.04);

            ChartPanel panel = new ChartPanel(chart);
            this.setContentPane(panel);

        } catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(this, "File is not found!");
        }
        catch (JsonException ex){
            JOptionPane.showMessageDialog(this, "Parsing exception!");
        }
        catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Wrong args!");
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

}
