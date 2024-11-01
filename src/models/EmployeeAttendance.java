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
    private int attendanceStatusId;
    private String employeeId;
    private int attendanceDateId;
    private String checkin;
    private String checkout;
    

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
     * @return the attendanceStatusId
     */
    public int getAttendanceStatusId() {
        return attendanceStatusId;
    }

    /**
     * @param attendanceStatusId the attendanceStatusId to set
     */
    public void setAttendanceStatusId(int attendanceStatusId) {
        this.attendanceStatusId = attendanceStatusId;
    }

    /**
     * @return the checkin
     */
    public String getCheckin() {
        return checkin;
    }

    /**
     * @param checkin the checkin to set
     */
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    /**
     * @return the checkout
     */
    public String getCheckout() {
        return checkout;
    }

    /**
     * @param checkout the checkout to set
     */
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    /**
     * @return the attendanceDateId
     */
    public int getAttendanceDateId() {
        return attendanceDateId;
    }

    /**
     * @param attendanceDateId the attendanceDateId to set
     */
    public void setAttendanceDateId(int attendanceDateId) {
        this.attendanceDateId = attendanceDateId;
    }

}
