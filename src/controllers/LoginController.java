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

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `login_id`='" + id + "'");
    }

    public ResultSet store(LoginModel loginModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`password`, `access_role_id`) VALUES "
                + "('" + loginModel.getPassword() + "', '" + loginModel.getAccessRoleId() + "') ");
    }

}
