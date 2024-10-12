package models;

public class EmployeeModel {

    private int statusId;
    private int employeeTypeId;
    private String id;
    private String nic;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String registeredDate;
    
    //
    private String employeeTypeName;

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
     * @return the statusId
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the registeredDate
     */
    public String getRegisteredDate() {
        return registeredDate;
    }

    /**
     * @param registeredDate the registeredDate to set
     */
    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    /**
     * @return the employeeTypeId
     */
    public int getEmployeeTypeId() {
        return employeeTypeId;
    }

    /**
     * @param employeeTypeId the employeeTypeId to set
     */
    public void setEmployeeTypeId(int employeeTypeId) {
        this.employeeTypeId = employeeTypeId;
    }

    /**
     * @return the employeeTypeName
     */
    public String getEmployeeTypeName() {
        return employeeTypeName;
    }

    /**
     * @param employeeTypeName the employeeTypeName to set
     */
    public void setEmployeeTypeName(String employeeTypeName) {
        this.employeeTypeName = employeeTypeName;
    }

}
