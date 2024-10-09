/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.EmployeeImageModel;

/**
 *
 * @author kavindu
 */
public class EmployeeImageController {

    private final String tableName = "employee_image";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(EmployeeImageModel employeeImageModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`path`, `employee_id`) "
                + "VALUES ('" + employeeImageModel.getPath() + "'"
                + ",'" + employeeImageModel.getEmployeeId() + "'");
    }

    public ResultSet update(EmployeeImageModel employeeImageModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`path`='" + employeeImageModel.getPath() + "', "
                + "`employee_id`='" + employeeImageModel.getEmployeeId() + "',"
                + "WHERE `id`='" + employeeImageModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`path` LIKE '%" + searchText + "%' OR "
                + "`employee_id` LIKE '%" + searchText + "%' ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

}
