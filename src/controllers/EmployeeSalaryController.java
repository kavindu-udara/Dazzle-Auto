/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.SalaryModel;

/**
 *
 * @author Dumindu
 */
public class EmployeeSalaryController {

    private final String tableName = "employee_salary";
    private final String tableName2 = "months";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet showByEmployeeId(String empId) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `employee_id`='" + empId + "'");
    }

    public ResultSet show(String employeeId, String monthId) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `employee_id`='" + employeeId + "' AND `months_id`='" + monthId + "'  ");
    }

    public ResultSet showByMonthRange(String startMonth, String endMonth) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `date` >= '" + startMonth + "' AND `date` < '" + endMonth + "' ");
    }

    public ResultSet showByMonthId(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `months_id` = '" + id + "' ");
    }

    public ResultSet showBydateYearAndMonthId(String employeeId, String year, int monthId) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `months_id`='" + monthId + "' AND `employee_id`='" + employeeId + "' AND `date` LIKE '" + year + "%' ");
    }

    public ResultSet store(SalaryModel salaryModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`id`,`date`, `salary`, `months_id`, `employee_id`) VALUES ("
                + "'" + salaryModel.getId() + "',"
                + "'" + salaryModel.getDate() + "', "
                + "'" + salaryModel.getSalary() + "', "
                + "'" + salaryModel.getMonthId() + "', "
                + "'" + salaryModel.getEmployeeId() + "') ");
    }

    public ResultSet update(SalaryModel salaryModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`id`='" + salaryModel.getId() + "', "
                + "`date`='" + salaryModel.getDate() + "', "
                + "`salary`='" + salaryModel.getSalary() + "', "
                + "`employee_id`='" + salaryModel.getEmployeeId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` "
                + "INNER JOIN payment_method ON service_invoice.payment_method_id=payment_method.id "
                + "INNER JOIN employee ON service_invoice.employee_id=employee.id WHERE "
                + "`vehicle_number` LIKE '%" + searchText + "%' OR `service_invoice`.`id` LIKE '%" + searchText + "%' ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

    public ResultSet getMonthlyTotal(int month, int year) throws SQLException, Exception {
        return MySqlConnection.executeSearch("SELECT SUM(salary) AS total_salary FROM `" + tableName + "` WHERE MONTH(date) = '" + month + "' AND YEAR(date) ='" + year + "' ");
    }

    public ResultSet getYearlyTotal(int year) throws SQLException, Exception {
        return MySqlConnection.executeSearch("SELECT SUM(salary) AS total_salary FROM `" + tableName + "` WHERE YEAR(date) = '" + year + "' ");
    }
}
