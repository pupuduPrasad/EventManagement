package lk.ijse.gdse.eventManage.model;

import lk.ijse.gdse.eventManage.dto.UserDto;
import lk.ijse.gdse.eventManage.util.CrudUtil;
import lk.ijse.gdse.eventManage.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel {
    public static UserDto authenticateUser(String username, String password) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM user WHERE userName = ? AND password = ?", username, password);
            if (resultSet.next()) {
                String dbUserName = resultSet.getString("userName");
                String dbPassword = resultSet.getString("password");
                return new UserDto(dbUserName, dbPassword);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<String> getAllUserNames() throws SQLException {
        ResultSet rst = CrudUtil.execute("select userName from user");

        ArrayList<String> userNames = new ArrayList<>();

        while (rst.next()) {
            userNames.add(rst.getString(1));
        }

        return userNames;
    }

    public UserDto findByUsername(String selectedUserName) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from user where userName=?", selectedUserName);

        if (rst.next()) {
            return new UserDto(rst.getString(1), rst.getString(2));
        }
        return null;
    }

    public boolean deleteUser(String selectedUsername) throws SQLException {
        return CrudUtil.execute("delete from user where userName=?", selectedUsername);
    }

    public boolean saveUser(UserDto userDto) throws SQLException {
        return CrudUtil.execute("insert into user values(?,?)", userDto.getUserName(), userDto.getPassword());
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
