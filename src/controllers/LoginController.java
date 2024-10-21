/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.LoginModel;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class LoginController {

    private final String tableName = "login";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(String id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(LoginModel loginModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`id`,`password`, `access_role_id`, `employee_id`) VALUES "
                + "('" + loginModel.getId() + "', "
                + "'" + loginModel.getPassword() + "', "
                + "'" + loginModel.getAccessRoleId() + "', "
                + "'" + loginModel.getEmployeeId() + "') ");
    }

    public ResultSet update(LoginModel loginModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`password`='" + loginModel.getPassword() + "', "
                + "`access_role_id`='" + loginModel.getAccessRoleId() + "',"
                + "`employee_id`='" + loginModel.getEmployeeId() + "', "
                + "WHERE `id`='" + loginModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`password` LIKE '%" + searchText + "%' OR "
                + "`access_role_id` LIKE '%" + searchText + "%' OR "
                + "`employee_id` LIKE '%" + searchText + "%' ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

}
