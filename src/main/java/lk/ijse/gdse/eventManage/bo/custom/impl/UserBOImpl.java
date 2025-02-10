package lk.ijse.gdse.eventManage.bo.custom.impl;

import lk.ijse.gdse.eventManage.bo.custom.UserBO;
import lk.ijse.gdse.eventManage.dao.DAOFactory;
import lk.ijse.gdse.eventManage.dao.custom.UserDAO;
import lk.ijse.gdse.eventManage.dto.UserDto;
import lk.ijse.gdse.eventManage.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO= (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean save(UserDto entity) throws SQLException {
        return userDAO.save(new User(
                entity.getUserName(),
                entity.getPassword()));
    }

    @Override
    public boolean update(UserDto entity) throws SQLException {
        return userDAO.update(new User(
                entity.getUserName(),
                entity.getPassword()));
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
