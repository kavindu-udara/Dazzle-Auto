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

    private final String tableName = "customer";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `idaddress`='" + id + "'");
    }

    public ResultSet store(EmployeeAttendance employeeAttendanceModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`employee_emp_id`, `date`, `on_time`, `off_time`) VALUES "
                + "('" + employeeAttendanceModel.getEmployeeId() + "', '" + employeeAttendanceModel.getDate() + "', '" + employeeAttendanceModel.getOnTime() + "', '" + employeeAttendanceModel.getOffTime() + "') ");
    }
}
