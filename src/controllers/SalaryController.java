/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.SalaryModel;

/**
 *
 * @author Dumindu
 */
public class SalaryController {
    private final String tableName = "employee_salary";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(SalaryModel salaryModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`id`,`date`, `salary`, `employee_id`) VALUES ("
                + "'" + salaryModel.getId() + "',"
                + "'" + salaryModel.getDate()+ "', "
                + "'" + salaryModel.getSalary()+ "', "
                + "'" + salaryModel.getEmployeeId()+ "') ");
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
        String query = "SELECT SUM(salary) AS total_salary FROM `" + tableName + "` WHERE MONTH(date) = ? AND YEAR(date) = ?";

        try {
            MySqlConnection.createConnection();
            PreparedStatement stmt = MySqlConnection.connection.prepareStatement(query);
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            return stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ResultSet getYearlyTotal(int year) throws SQLException, Exception {
        String query = "SELECT SUM(salary) AS total_salary FROM `" + tableName + "` WHERE YEAR(date) = ?";

        try {
            MySqlConnection.createConnection();
            PreparedStatement stmt = MySqlConnection.connection.prepareStatement(query);
            stmt.setInt(1, year);
            return stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
