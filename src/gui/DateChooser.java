package gui;
/*
 * @author HongAnh
 * @created 03 / 02 / 2024 - 9:14 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class DateChooser extends JFrame {
    JDatePickerImpl datePicker;

    DateChooser(){
        SqlDateModel model = new SqlDateModel();
        Properties properties = new Properties();
        properties.put("text.day","Day");
        properties.put("text.month","Month");
        properties.put("text.year","Year");

        JDatePanelImpl panel = new JDatePanelImpl(model,properties);
        datePicker = new JDatePickerImpl(panel,new CustomFormat());
        this.add(datePicker);
        this.pack();
        this.setVisible(true);

    }

    public class CustomFormat extends JFormattedTextField.AbstractFormatter{

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
    }
}
