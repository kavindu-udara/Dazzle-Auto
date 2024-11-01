/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.EmployeeAttendance;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class EmployeeAttendanceController {

    private final String tableName = "emp_attendance";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }
    
    public ResultSet showByDateId(int dateId) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `attendance_date_id`='" + dateId + "'");
    }

    public ResultSet show(String id, String time) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `employee_id`='" + id + "' AND `checkin`='" + time + "' OR `checkout`='" + time + "' ");
    }

    public ResultSet storeWithNulls(EmployeeAttendance employeeAttendanceModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`employee_id`, `attendance_date_id`, `attendance_status_id`) VALUES "
                + "('" + employeeAttendanceModel.getEmployeeId() + "', "
                + "'" + employeeAttendanceModel.getAttendanceDateId() + "', "
                + "'" + employeeAttendanceModel.getAttendanceStatusId() + "') ");
    }

    public ResultSet updateCheckInByUserId(String checkIn, String UserId, int dateId) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`checkin`='" + checkIn + "' "
                + "WHERE `employee_id`='" + UserId + "' AND `attendance_date_id`='" + dateId + "' ");
    }
    
    public ResultSet updateCheckOutByUserId(String checkIn, String UserId, int dateId) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`checkout`='" + checkIn + "' "
                + "WHERE `employee_id`='" + UserId + "' AND `attendance_date_id`='" + dateId + "' ");
    }
    
    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`employee_id` LIKE '%" + searchText + "%' OR "
                + "`date` LIKE '%" + searchText + "%' OR "
                + "`on_time` LIKE '%" + searchText + "%' OR "
                + "`off_time` LIKE '%" + searchText + "%'");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
}
