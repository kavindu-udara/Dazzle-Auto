package includes;

import controllers.EmployeeController;
import controllers.ProductController;
import controllers.SupplierController;
import java.sql.ResultSet;
import java.util.logging.Logger;
/**
 *
 * @author Dinuka
 */
public class IDGenarator {
    
    private static final Logger logger = LoggerConfig.getLogger();

    public static String invoiceID() {
        // Get the current time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        // Convert to a string and take the last 8 digits
        String code = Long.toString(currentTimeMillis);
        if (code.length() > 8) {
            code = code.substring(code.length() - 8); // Take the last 8 digits
        }

        return "INV-" + code;
    }

    public static String shopInvoiceID() {
        // Get the current time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        // Convert to a string and take the last 8 digits
        String code = Long.toString(currentTimeMillis);
        if (code.length() > 8) {
            code = code.substring(code.length() - 8); // Take the last 8 digits
        }

        return "SINV-" + code;
    }

    public static String appointmentID() {
        // Get the current time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        // Convert to a string and take the last 6 digits
        String code = Long.toString(currentTimeMillis);
        if (code.length() > 6) {
            code = code.substring(code.length() - 6); // Take the last 6 digits           
        }

        return "AP" + code;
    }
    
    public static String employeeID() {
        int newEmployeeNumber = 0;
        int row = 0;
        try {
            ResultSet rs = new EmployeeController().show();           
            
            while (rs.next()) {                
                row++;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while generate Employee ID : "+e.getMessage());
        }
        
        newEmployeeNumber += row+1;

        return "EMP0"+newEmployeeNumber;
    }
    
    public static String GrnID() {
        // Get the current time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        // Convert to a string and take the last 6 digits
        String code = Long.toString(currentTimeMillis);
        if (code.length() > 6) {
            code = code.substring(code.length() - 6); // Take the last 6 digits           
        }

        return "GRN" + code;
    }
    
    public static String productID() {
        int newProductNumber = 0;
        int row = 0;
        try {
            ResultSet rs = new ProductController().show();           
            
            while (rs.next()) {                
                row++;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while generate product ID : "+e.getMessage());
        }
        
        newProductNumber += row+1;

        return "ITEM0"+newProductNumber;
    }
    
    public static String supplierID() {
        int newSupplierNumber = 0;
        int row = 0;
        try {
            ResultSet rs = new SupplierController().show();           
            
            while (rs.next()) {                
                row++;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while generate supplier ID : "+e.getMessage());
        }
        
        newSupplierNumber += row+1;

        return "SUP0"+newSupplierNumber;
    }
}

