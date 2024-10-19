/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author kavindu
 */
public class LoginModel {

    // login table
    private static String id;
    private static int accessRoleId;
    private static String employeeId;
    private static String password;

    // employee table
    private static String nic;
    private static String firstName;
    private static String lastName;
    
    // image
    private static String image;

    /**
     * @return the id
     */
    public static String getId() {
        return id;
    }

    /**
     * @param aId the id to set
     */
    public static void setId(String aId) {
        id = aId;
    }

    /**
     * @return the accessRoleId
     */
    public static int getAccessRoleId() {
        return accessRoleId;
    }

    /**
     * @param aAccessRoleId the accessRoleId to set
     */
    public static void setAccessRoleId(int aAccessRoleId) {
        accessRoleId = aAccessRoleId;
    }

    /**
     * @return the employeeId
     */
    public static String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param aEmployeeId the employeeId to set
     */
    public static void setEmployeeId(String aEmployeeId) {
        employeeId = aEmployeeId;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param aPassword the password to set
     */
    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * @return the nic
     */
    public static String getNic() {
        return nic;
    }

    /**
     * @param aNic the nic to set
     */
    public static void setNic(String aNic) {
        nic = aNic;
    }

    /**
     * @return the firstName
     */
    public static String getFirstName() {
        return firstName;
    }

    /**
     * @param aFirstName the firstName to set
     */
    public static void setFirstName(String aFirstName) {
        firstName = aFirstName;
    }

    /**
     * @return the lastName
     */
    public static String getLastName() {
        return lastName;
    }

    /**
     * @param aLastName the lastName to set
     */
    public static void setLastName(String aLastName) {
        lastName = aLastName;
    }

    /**
     * @return the image
     */
    public static String getImage() {
        return image;
    }

    /**
     * @param aImage the image to set
     */
    public static void setImage(String aImage) {
        image = aImage;
    }
    
}