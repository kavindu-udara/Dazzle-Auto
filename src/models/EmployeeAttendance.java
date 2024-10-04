/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author kavindu
 */
public class EmployeeAttendance {

    private int id;
    private int employeeId;
    private String date;
    private String onTime;
    private String offTime;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the onTime
     */
    public String getOnTime() {
        return onTime;
    }

    /**
     * @param onTime the onTime to set
     */
    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

    /**
     * @return the offTime
     */
    public String getOffTime() {
        return offTime;
    }

    /**
     * @param offTime the offTime to set
     */
    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

}
