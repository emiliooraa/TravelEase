package components;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class DateTimePicker extends JPanel {

    private JDateChooser dateChooser;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;

    public DateTimePicker() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // DATE
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setPreferredSize(new Dimension(150, 25));
        add(dateChooser);

        // HOUR
        SpinnerNumberModel hourModel = new SpinnerNumberModel(12, 0, 23, 1);
        hourSpinner = new JSpinner(hourModel);
        hourSpinner.setEditor(new JSpinner.NumberEditor(hourSpinner, "00"));
        hourSpinner.setPreferredSize(new Dimension(50, 25));
        add(hourSpinner);

        // MINUTES
        SpinnerNumberModel minuteModel = new SpinnerNumberModel(0, 0, 59, 1);
        minuteSpinner = new JSpinner(minuteModel);
        minuteSpinner.setEditor(new JSpinner.NumberEditor(minuteSpinner, "00"));
        minuteSpinner.setPreferredSize(new Dimension(50, 25));
        add(minuteSpinner);
    }

    // ---------------------------
    //   MÉTODO QUE NECESITÁS
    // ---------------------------
    public LocalDateTime getDateTime() {
        Date fecha = dateChooser.getDate();
        if (fecha == null) return null;

        LocalDate date = new java.sql.Date(fecha.getTime()).toLocalDate();
        int hour = (int) hourSpinner.getValue();
        int minute = (int) minuteSpinner.getValue();

        return LocalDateTime.of(date, LocalTime.of(hour, minute));
    }

    // Opcional: setear fecha/hora desde BD
    public void setDateTime(LocalDateTime dt) {
        if (dt != null) {
            dateChooser.setDate(java.sql.Timestamp.valueOf(dt));
            hourSpinner.setValue(dt.getHour());
            minuteSpinner.setValue(dt.getMinute());
        }
    }
}
