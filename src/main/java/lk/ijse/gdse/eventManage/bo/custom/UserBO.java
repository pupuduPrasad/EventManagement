package lk.ijse.gdse.eventManage.bo.custom;

import lk.ijse.gdse.eventManage.bo.SuperBo;
import lk.ijse.gdse.eventManage.dto.TicketDto;
import lk.ijse.gdse.eventManage.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBo {
    public boolean save(UserDto userDto) throws SQLException;
    public boolean update(UserDto userDto) throws SQLException;
    public boolean delete(String userName) throws SQLException ;
    public ArrayList<String> getAllUserNames() throws SQLException ;
    public boolean isUserNameExists(String username) throws SQLException;


}
