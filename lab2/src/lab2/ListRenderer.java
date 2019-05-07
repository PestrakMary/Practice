package lab2;
import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.Map;
import java.util.StringTokenizer;

import static com.sun.javafx.iio.ios.IosImageLoader.RGB;

public class ListRenderer extends DefaultListCellRenderer {
    Map<String, ImageIcon> mapIcon;
    Map<String, String> mapCapitals;

    ListRenderer(Map<String, ImageIcon> map, Map<java.lang.String, String> mapCapitals) {
        super();
        mapIcon = map;
        this.mapCapitals = mapCapitals;
    }

    public Component getListCellRendererComponent(JList list,
                                                  Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        StringTokenizer tokenizer = new StringTokenizer((java.lang.String) value);
        String country = tokenizer.nextToken();
        Component component = super.getListCellRendererComponent(list,
                value, index, isSelected, cellHasFocus);
        JLabel label = (JLabel) component;
        Icon icon = mapIcon.get(country);
        label.setIcon(icon);
       if(isSelected) label.setText(value + " " + mapCapitals.get(country));
        return label;
    }

}
