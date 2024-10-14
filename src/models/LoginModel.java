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
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the accessRoleId
     */
    public int getAccessRoleId() {
        return accessRoleId;
    }

    /**
     * @param accessRoleId the accessRoleId to set
     */
    public void setAccessRoleId(int accessRoleId) {
        this.accessRoleId = accessRoleId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
