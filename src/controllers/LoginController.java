/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.LoginModel;
import includes.MySqlConnection;
import models.LoginRegisterModel;

/**
 *
 * @author kavindu
 */
public class LoginController {

    private final String tableName = "login";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` "
                + "INNER JOIN access_role ON login.access_role_id=access_role.id "
                + "INNER JOIN employee ON login.employee_id=employee.id");
    }

    public ResultSet show(String id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet showLoginDeatils(String id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `employee_id`='" + id + "'");
    }

    public ResultSet store(LoginRegisterModel loginModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`id`,`password`, `access_role_id`, `employee_id`, `otp_code`) VALUES "
                + "('" + loginModel.getId() + "', "
                + "'" + loginModel.getPassword() + "', "
                + "'" + loginModel.getAccessRoleId() + "', "
                + "'" + loginModel.getEmployeeId() + "',"
                + "'" + generateOTP() + "') ");
    }

    public ResultSet update(String loginID, int AccessRoleId) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`access_role_id`='" + AccessRoleId + "' "
                + "WHERE `id`='" + loginID + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`password` LIKE '%" + searchText + "%' OR "
                + "`access_role_id` LIKE '%" + searchText + "%' OR "
                + "`employee_id` LIKE '%" + searchText + "%' ");
    }

    public ResultSet delete(String id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

    public String generateOTP() {
        // Get the current time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();
        // Convert to a string and take the last 6 digits
        String code = Long.toString(currentTimeMillis);
        if (code.length() > 6) {
            code = code.substring(code.length() - 6); // Take the last 6 digits
        }
        return code;
    }

}
