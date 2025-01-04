/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.LoginHistoryModel;
import models.LoginModel;

/**
 *
 * @author kavindu
 */
public class LoginHistoryController {

    private final String tableName = "login_history";

    public ResultSet store(LoginHistoryModel loginHistoryModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`created_at`, `username`) VALUES "
                + "('" + loginHistoryModel.getCreatedAt() + "', "
                + "'" + loginHistoryModel.getUsername() + "') ");
    }

}
