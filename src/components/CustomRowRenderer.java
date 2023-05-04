package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomRowRenderer extends DefaultTableCellRenderer {

    private static final Color BEFORE_COLOR = Color.red;
    private static final Color CURRENT_COLOR = Color.green;
    private static final Color AFTER_COLOR = Color.orange;

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Calendar calendar = Calendar.getInstance();

    // Override the getTableCellRendererComponent method
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateCuurent = dateObj.format(formatter);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        cell.setFont(new Font("monospace", Font.BOLD, 12));

        try {
            date = sdf.parse(value.toString());
            // Get the current date
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();

            System.out.println(dateCuurent.equals(value.toString()));

            if (dateCuurent.equals(value.toString())) {
                cell.setForeground(CURRENT_COLOR);

            } else {
                if (date.before(currentDate)) {
                    cell.setForeground(BEFORE_COLOR);
                } else if (date.after(currentDate)) {
                    cell.setForeground(AFTER_COLOR);
                }
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cell;
    }
}
