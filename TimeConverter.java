import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConverter {
    public static void main(String[] args) {
        
        LocalDateTime now = LocalDateTime.now();

        
        JFrame frame = new JFrame("Indian Time Zone Converter");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        
        JLabel yearLabel = new JLabel("Select Year:");
        Integer[] years = new Integer[31];
        for (int i = 0; i < 31; i++) {
            years[i] = 2000 + i;
        }
        JComboBox<Integer> yearDropdown = new JComboBox<>(years);
        yearDropdown.setSelectedItem(now.getYear());  

        JLabel monthLabel = new JLabel("Select Month:");
        Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        JComboBox<Integer> monthDropdown = new JComboBox<>(months);
        monthDropdown.setSelectedItem(now.getMonthValue());  

        JLabel dayLabel = new JLabel("Select Day:");
        Integer[] days = new Integer[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = i;
        }
        JComboBox<Integer> dayDropdown = new JComboBox<>(days);
        dayDropdown.setSelectedItem(now.getDayOfMonth());  

        JLabel hourLabel = new JLabel("Select Hour:");
        Integer[] hours = new Integer[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = i;
        }
        JComboBox<Integer> hourDropdown = new JComboBox<>(hours);
        hourDropdown.setSelectedItem(now.getHour());  

        JLabel minuteLabel = new JLabel("Select Minute:");
        Integer[] minutes = new Integer[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = i;
        }
        JComboBox<Integer> minuteDropdown = new JComboBox<>(minutes);
        minuteDropdown.setSelectedItem(now.getMinute());  

        JLabel zoneLabel = new JLabel("Select Target Time Zone:");
        String[] topTimeZones = {
            "UTC",
            "Asia/Kolkata",
            "America/New_York",
            "Asia/Tokyo",
            "Europe/London",
            "Europe/Berlin",
            "Asia/Shanghai",
            "America/Los_Angeles",
            "Australia/Sydney",
            "Asia/Dubai"
        };
        JComboBox<String> timeZoneDropdown = new JComboBox<>(topTimeZones);

        JButton convertButton = new JButton("Convert Time");

        JLabel resultLabel = new JLabel("Converted Time will appear here.", SwingConstants.CENTER);

        frame.add(yearLabel);
        frame.add(yearDropdown);
        frame.add(monthLabel);
        frame.add(monthDropdown);
        frame.add(dayLabel);
        frame.add(dayDropdown);
        frame.add(hourLabel);
        frame.add(hourDropdown);
        frame.add(minuteLabel);
        frame.add(minuteDropdown);
        frame.add(zoneLabel);
        frame.add(timeZoneDropdown);
        frame.add(convertButton);
        frame.add(resultLabel);

        convertButton.addActionListener(e -> {
            try {
                int year = (Integer) yearDropdown.getSelectedItem();
                int month = (Integer) monthDropdown.getSelectedItem();
                int day = (Integer) dayDropdown.getSelectedItem();
                int hour = (Integer) hourDropdown.getSelectedItem();
                int minute = (Integer) minuteDropdown.getSelectedItem();

                LocalDateTime indianLocalDateTime = LocalDateTime.of(year, month, day, hour, minute);

                String targetTimeZoneId = (String) timeZoneDropdown.getSelectedItem();
                ZoneId indianZoneId = ZoneId.of("Asia/Kolkata");
                ZonedDateTime indianZonedDateTime = indianLocalDateTime.atZone(indianZoneId);
                ZoneId targetZoneId = ZoneId.of(targetTimeZoneId);

                ZonedDateTime targetZonedDateTime = indianZonedDateTime.withZoneSameInstant(targetZoneId);
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

                resultLabel.setText("Converted Time: " + targetZonedDateTime.format(outputFormatter));
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        frame.setVisible(true);
    }
}
