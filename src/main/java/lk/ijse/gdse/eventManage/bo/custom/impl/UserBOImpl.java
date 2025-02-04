package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.UserBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.UserDAO;
import lk.ijse.gdse.eventManage.dto.TicketDto;
import lk.ijse.gdse.eventManage.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    private final UserDAO userDAO= (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean save(UserDto userDto) throws SQLException {
        return userDAO.save(userDto);
    }

    @Override
    public boolean update(UserDto userDto) throws SQLException {
        return userDAO.update(userDto);
    }

    @Override
    public boolean delete(String userName) throws SQLException {
        return  userDAO.delete(userName);
    }

    @Override
    public ArrayList<String> getAllUserNames() throws SQLException {
        return userDAO.getAllUserNames();
    }


    @Override
    public boolean isUserNameExists(String username) throws SQLException {
        return userDAO.isUserNameExists(username);
    }
}
