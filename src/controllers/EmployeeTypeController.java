/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.EmployeeTypeModel;

/**
 *
 * @author kavindu
 */
public class EmployeeTypeController {

    private final String tableName = "employee_type";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(EmployeeTypeModel employeeTypeModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`type`, `basic_salary`, `leaves_for_month`) "
                + "VALUES ('" + employeeTypeModel.getType() + "'"
                + ",'" + employeeTypeModel.getBasicSallary() + "', "
                + "'" + employeeTypeModel.getLeavesForMonth() + "'");
    }

    public ResultSet update(EmployeeTypeModel employeeTypeModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`type`='" + employeeTypeModel.getType() + "', "
                + "`basic_salary`='" + employeeTypeModel.getBasicSallary() + "',"
                + "`leaves_for_month`='" + employeeTypeModel.getLeavesForMonth() + "', "
                + "WHERE `id`='" + employeeTypeModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`type` LIKE '%" + searchText + "%' OR "
                + "`basic_salary` LIKE '%" + searchText + "%' OR "
                + "`leaves_for_month` LIKE '%" + searchText + "%'");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

}
