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

    public ResultSet store(EmployeeAttendance employeeAttendanceModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`employee_id`, `date`, `on_time`, `off_time`) VALUES "
                + "('" + employeeAttendanceModel.getEmployeeId() + "', "
                + "'" + employeeAttendanceModel.getDate() + "', "
                + "'" + employeeAttendanceModel.getOnTime() + "', "
                + "'" + employeeAttendanceModel.getOffTime() + "') ");
    }

    public ResultSet update(EmployeeAttendance employeeAttendanceModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`employee_id`='" + employeeAttendanceModel.getEmployeeId() + "', "
                + "`date`='" + employeeAttendanceModel.getDate() + "',"
                + "`on_time`='" + employeeAttendanceModel.getOnTime() + "', "
                + "`off_time`='" + employeeAttendanceModel.getOffTime() + "', "
                + "WHERE `id`='" + employeeAttendanceModel.getId() + "' ");
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
