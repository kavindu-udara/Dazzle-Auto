package controllers;

import java.sql.ResultSet;
import models.EmployeeModel;
import includes.MySqlConnection;

public class EmployeeController {

    private final String tableName = "employee";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(EmployeeModel employeeModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`nic`, `first_name`, `last_name`, `email`, `mobile`, `registered_date`, `status_id`) "
                + "VALUES ('" + employeeModel.getNic() + "', "
                + "'" + employeeModel.getFirstName() + "', "
                + "'" + employeeModel.getLastName() + "', "
                + "'" + employeeModel.getEmail() + "', "
                + "'" + employeeModel.getMobile() + "', "
                + "'" + employeeModel.getRegisteredDate() + "', "
                + "'" + employeeModel.getStatusId() + "') ");
    }

    public ResultSet update(EmployeeModel employeeModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`nic`='" + employeeModel.getNic() + "', "
                + "`first_name`='" + employeeModel.getFirstName() + "',"
                + "`last_name`='" + employeeModel.getLastName() + "', "
                + "`email`='" + employeeModel.getEmail() + "', "
                + "`mobile`='" + employeeModel.getMobile() + "', "
                + "`registered_date`='" + employeeModel.getRegisteredDate() + "', "
                + "`status_id`='" + employeeModel.getStatusId() + "', "
                + "WHERE `id`='" + employeeModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`nic` LIKE '%" + searchText + "%' OR "
                + "`first_name` LIKE '%" + searchText + "%' OR "
                + "`last_name` LIKE '%" + searchText + "%' OR "
                + "`email` LIKE '%" + searchText + "%' OR "
                + "`mobile` LIKE '%" + searchText + "%' OR "
                + "`registered_date` LIKE '%" + searchText + "%' OR "
                + "`status_id` LIKE '%" + searchText + "%' OR ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
}
