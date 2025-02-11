package lk.ijse.gdse.eventManage.dao.custom.impl;

import lk.ijse.gdse.eventManage.util.CrudUtil;
import lk.ijse.gdse.eventManage.dao.custom.UserDAO;
import lk.ijse.gdse.eventManage.db.DBConnection;
import lk.ijse.gdse.eventManage.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    public ArrayList<String> getAllUserNames() throws SQLException {
        ResultSet rst = CrudUtil.execute("select username from user");

        ArrayList<String> usernames = new ArrayList<>();

        while (rst.next()) {
            usernames.add(rst.getString(1));
        }

        return usernames;
    }
    public boolean delete(String selectedUsername) throws SQLException {
        return CrudUtil.execute("delete from user where userName=?", selectedUsername);
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        return null;
    }
    @Override
    public boolean update(User user) throws SQLException {
        return false;
    }

    public boolean save(User user) throws SQLException {
        return CrudUtil.execute("insert into user values(?,?)",
                user.getUserName(),
                user.getPassword());
    }


    public boolean isUserNameExists(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM user WHERE userName = ?";
        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }


}
