package lk.ijse.gdse.eventManage.dao.custom;

import lk.ijse.gdse.eventManage.dao.CrudDAO;
import lk.ijse.gdse.eventManage.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<UserDto> {
     boolean isUserNameExists(String username) throws SQLException;
     ArrayList<String> getAllUserNames() throws SQLException;
}
