/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.AccessRoleModel;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class AccessRoleController {

    private final String tableName = "access_role";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(AccessRoleModel accessRoleModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`type_name`) VALUES "
                + "('" + accessRoleModel.getRole() + "') ");
    }
}
