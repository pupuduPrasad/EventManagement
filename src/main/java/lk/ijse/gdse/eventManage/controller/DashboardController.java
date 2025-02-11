package lk.ijse.gdse.eventManage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/image/dashboard.jpg"));
        Image.setImage(loginImage);
    }

    @FXML
    private ImageView Image;

    @FXML
    private AnchorPane content;

    @FXML
    void eventAc(ActionEvent event) {
        navigateTo("/view/event.fxml");
    }
    @FXML
    void feedbackAc(ActionEvent event) {navigateTo("/view/feedbacklayout.fxml");}
    @FXML
    void paymentAc(ActionEvent event) {
        navigateTo("/view/payment.fxml");
    }
    @FXML
    void reservationAc(ActionEvent event) {
        navigateTo("/view/reservation.fxml");
    }
    @FXML
    void sponsorsAc(ActionEvent event) {
        navigateTo("/view/Sponsors.fxml");
    }
    @FXML
    void studentAc(ActionEvent event) {navigateTo("/view/Customer.fxml");}
    @FXML
    void ticketAc(ActionEvent event) {
        navigateTo("/view/ticket.fxml");
    }
    @FXML
    void userAc(ActionEvent event) {navigateTo("/view/AddUser.fxml");}
    @FXML
    void logoutAc(ActionEvent event) {navigateTo("/view/loginView.fxml");}

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
