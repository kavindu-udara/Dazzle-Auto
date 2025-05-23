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

    public ResultSet show(String userId, int dateId) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `employee_id`='" + userId + "' AND `attendance_date_id`='" + dateId + "' ");
    }

    public ResultSet showByDateId(int dateId) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `attendance_date_id`='" + dateId + "'");
    }

    public ResultSet show(String id, String time) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `employee_id`='" + id + "' AND `checkin`='" + time + "' OR `checkout`='" + time + "' ");
    }

    public ResultSet showByDateIdAndEmpId(String empId, int dateId) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `employee_id`='" + empId + "' AND `attendance_date_id`='" + dateId + "'");
    }

    public ResultSet showByDateAndStatus(String employeeId, int dateId, int statusId) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `employee_id`='" + employeeId + "' AND `attendance_date_id`='" + dateId + "' AND `attendance_status_id`='" + statusId + "' ");
    }

    public ResultSet showByEmployeeId(String employeeId) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `employee_id`='" + employeeId + "' ");
    }

    public ResultSet showCountByEmployeeId(String employeeId, int monthId, int statusId) throws Exception {
        return MySqlConnection.executeSearch("SELECT COUNT(*) AS row_count FROM `" + tableName + "` INNER JOIN `attendance_date` ON `"+tableName+"`.`attendance_date_id`=`attendance_date`.`id` WHERE `employee_id`='" + employeeId + "' AND `attendance_date`.`date` LIKE '%-"+monthId+"-%' AND `attendance_status_id`='"+statusId+"'  ");
    }

    public ResultSet showByCustomQuery(String query) throws Exception {
        return MySqlConnection.executeSearch(query);
    }
    
    public ResultSet storeWithNulls(EmployeeAttendance employeeAttendanceModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`employee_id`, `attendance_date_id`, `attendance_status_id`) VALUES "
                + "('" + employeeAttendanceModel.getEmployeeId() + "', "
                + "'" + employeeAttendanceModel.getAttendanceDateId() + "', "
                + "'" + employeeAttendanceModel.getAttendanceStatusId() + "') ");
    }

    public ResultSet updateCheckInByUserId(String checkIn, String UserId, int dateId) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`checkin`='" + checkIn + "', `attendance_status_id`='1' "
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
    
    public ResultSet showCustomQuery(String query) throws Exception{
        return MySqlConnection.executeSearch(query);
    }
}
