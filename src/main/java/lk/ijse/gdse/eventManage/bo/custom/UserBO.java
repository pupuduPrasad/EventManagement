package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.TicketDto;
import lk.ijse.gdse.eventManage.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBo {
     boolean save(UserDto userDto) throws SQLException;
     boolean update(UserDto userDto) throws SQLException;
     boolean delete(String userName) throws SQLException ;
     ArrayList<String> getAllUserNames() throws SQLException ;
     boolean isUserNameExists(String username) throws SQLException;


}
