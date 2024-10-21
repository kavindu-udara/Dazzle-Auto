package controllers;

import java.sql.ResultSet;
import models.EmployeeModel;
import includes.MySqlConnection;

public class EmployeeController {

    private final String tableName = "employee";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(String id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(EmployeeModel employeeModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`id`, `nic`, `first_name`, `last_name`, `email`, `mobile`, `registered_date`, `employee_type_id`, `status_id`) "
                + "VALUES ('" + employeeModel.getId() + "'"
                + ",'" + employeeModel.getNic() + "', "
                + "'" + employeeModel.getFirstName() + "', "
                + "'" + employeeModel.getLastName() + "', "
                + "'" + employeeModel.getEmail() + "', "
                + "'" + employeeModel.getMobile() + "', "
                + "'" + employeeModel.getRegisteredDate() + "', "
                + "'" + employeeModel.getEmployeeTypeId() + "', "
                + "'" + employeeModel.getStatusId() + "') ");
    }

    public ResultSet update(EmployeeModel employeeModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`nic`='" + employeeModel.getNic() + "', "
                + "`first_name`='" + employeeModel.getFirstName() + "',"
                + "`last_name`='" + employeeModel.getLastName() + "', "
                + "`email`='" + employeeModel.getEmail() + "', "
                + "`mobile`='" + employeeModel.getMobile() + "', "
                + "`employee_type_id`='" + employeeModel.getEmployeeTypeId() + "', "
                + "`status_id`='" + employeeModel.getStatusId() + "' "
                + "WHERE `id`='" + employeeModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`id` LIKE '%" + searchText + "%' OR "
                + "`nic` LIKE '%" + searchText + "%' OR "
                + "`first_name` LIKE '%" + searchText + "%' OR "
                + "`last_name` LIKE '%" + searchText + "%' OR "
                + "`email` LIKE '%" + searchText + "%' OR "
                + "`mobile` LIKE '%" + searchText + "%' OR "
                + "`registered_date` LIKE '%" + searchText + "%' OR "
                + "`status_id` LIKE '%" + searchText + "%' ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
}
