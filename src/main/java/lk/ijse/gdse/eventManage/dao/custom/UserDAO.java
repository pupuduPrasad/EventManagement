package lk.ijse.gdse.eventManage.dao.custom;

import lk.ijse.gdse.eventManage.dao.CrudDAO;
import lk.ijse.gdse.eventManage.dto.UserDto;
import lk.ijse.gdse.eventManage.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<User> {
     boolean isUserNameExists(String username) throws SQLException;
     ArrayList<String> getAllUserNames() throws SQLException;
}
