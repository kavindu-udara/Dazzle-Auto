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
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`login_id`, `nic`, `first_name`, `last_name`, `email`, `mobile`, `registered_date`, `status_id`) "
                + "VALUES ('" + employeeModel.getLoginId() + "', '" + employeeModel.getNic() + "', '" + employeeModel.getFirstName() + "', '" + employeeModel.getLastName() + "', "
                + "'" + employeeModel.getEmail() + "', '" + employeeModel.getMobile() + "', '" + employeeModel.getRegisteredDate() + "', '" + employeeModel.getStatusId() + "') ");
    }
}
