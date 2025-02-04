package lk.ijse.gdse.eventManage.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.eventManage.bo.BOFactory;
import lk.ijse.gdse.eventManage.bo.custom.UserBO;
import lk.ijse.gdse.eventManage.bo.custom.impl.UserBOImpl;
import lk.ijse.gdse.eventManage.dto.UserDto;
import lk.ijse.gdse.eventManage.dao.custom.impl.UserDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private Button btnRemove;
    @FXML
    private AnchorPane content;

    @FXML
    private ComboBox<String> cmbRemoveUser;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @Setter
    private DashboardController dashboardController;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void addUserOnAction(ActionEvent event) throws SQLException {
        String userName = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String confirmPassword = txtConfirmPassword.getText().trim();

        if (userName.isEmpty() || userName.contains(" ")) {
            new Alert(Alert.AlertType.ERROR, "Invalid username. Ensure there are no spaces and it is not empty.").show();
            return;
        }

        if (userBO.isUserNameExists(userName)) {
            new Alert(Alert.AlertType.ERROR, "Username already exists. Please choose a different username.").show();
            return;
        }

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Password fields cannot be empty.").show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
            return;
        }

        UserDto userDto = new UserDto(userName, password);
        boolean isSaved = userBO.save(userDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "User saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save user...!").show();
        }
    }

    @FXML
    void removeUerOnAction(ActionEvent event) throws SQLException {
        String userName = cmbRemoveUser.getValue();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = userBO.delete(userName);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "User deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete User...!").show();
            }
        }
    }
    @FXML
    void homeAc(ActionEvent event) {
        navigateTo("/view/dashboard.fxml");


    }

    @FXML
    void selectOnAction(ActionEvent event) {
        if(cmbRemoveUser != null) {
            btnRemove.setDisable(false);
        }
    }

//    UserDAOImpl userDAOImpl = new UserDAOImpl();

    private void loadUsernames() throws SQLException {
        ArrayList<String> usernames = userBO.getAllUserNames();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(usernames);
        cmbRemoveUser.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to Load!").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadUsernames();

        cmbRemoveUser.setPromptText("Select user you want to remove");

        txtUsername.setText(null);
        txtPassword.setText("");
        txtConfirmPassword.setText("");

        btnRemove.setDisable(true);
    }
    public void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));

            load.prefWidthProperty().bind(content.widthProperty());
            load.prefHeightProperty().bind(content.heightProperty());

            content.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }


}