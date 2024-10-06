/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package includes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author kavindu
 */
public class OnlyDoubleDocumentFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (isDouble(string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr)
            throws BadLocationException {
        if (isDouble(string)) {
            super.replace(fb, offset, length, string, attr);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    // Utility method to check if the input is a valid double
    private boolean isDouble(String text) {
        // Allow empty string, as we don't want to prevent removal of text
        if (text.isEmpty()) {
            return true;
        }

        // Regex to allow numbers with optional decimal point and digits after it
        return text.matches("\\d*\\.?\\d*");
    }
}
