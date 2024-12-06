package views.shop.stock;

/**
 *
 * @author Dinuka
 */
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class QuantityRenderer extends DefaultTableCellRenderer {

    private int targetColumn; // Column to apply the condition

    public QuantityRenderer(int targetColumn) {
        this.targetColumn = targetColumn;
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == targetColumn) {
            try {
                double quantity = Double.parseDouble(value.toString());
                if (quantity <= 5) {
                    c.setForeground(Color.RED);
                    c.setBackground(new Color(255, 240, 240));
                } else {
                    c.setForeground(isSelected ? table.getSelectionForeground() : Color.BLACK);
                    c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                }
            } catch (NumberFormatException e) {
                c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            }
        } else {
            c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        }

        return c;
    }
}
