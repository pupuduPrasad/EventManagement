package lk.ijse.gdse.eventManage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import lk.ijse.gdse.eventManage.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {

    @FXML
    private Button login;

    @FXML
    private AnchorPane content;


    @FXML
    private TextField password;

    @FXML
    private TextField user;

        @FXML
        public void loginAc(ActionEvent actionEvent) throws IOException {
            String UserName = user.getText();
            String Password = password.getText();

            if (isValid()) {
                try {
                    cheakCredintial(UserName, Password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private boolean isValid() {
            if (user.getText().isEmpty() || password.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Username or Password cannot be empty").show();
                return false;
            }
            return true;
        }


        public void cheakCredintial(String user_name, String password) throws SQLException, ClassNotFoundException, IOException {
            String sql = "SELECT userName,password FROM user WHERE userName = ?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, user_name);

            ResultSet rst = pstm.executeQuery();
            if (rst.next()) {
                String pass = rst.getString("password");
                if (password.equals(pass)) {
                    navigateToCustomer();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong Password").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "User not found").show();
            }
        }

    private void navigateToCustomer() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage primaryStage = (Stage) login.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }


}

