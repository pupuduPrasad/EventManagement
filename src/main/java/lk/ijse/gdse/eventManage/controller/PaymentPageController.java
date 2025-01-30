package lk.ijse.gdse.eventManage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse.eventManage.dto.PaymentDto;
import lk.ijse.gdse.eventManage.dto.tm.PaymentTm;
import lk.ijse.gdse.eventManage.dao.PaymentModel;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private AnchorPane content;

    @FXML
    private ImageView imageView;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private Label lblPaymentDate;

    @FXML
    private Label lblPayId;

    @FXML
    private Label lblReservationId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtPaymentAmount;

    @FXML
    void acDelete(ActionEvent event) throws Exception {
        String paymentId = lblPayId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = paymentModel.deletePayment(paymentId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Payment deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Payment...!").show();
            }
        }
    }



    @FXML
    void acHome(ActionEvent event) {
        navigateTo("/view/dashboard.fxml");

    }

    private void navigateTo(String fxmlPath) {
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

    @FXML
    void acRefresh(ActionEvent event) throws Exception {
        refreshPage();

    }
    PaymentModel paymentModel = new PaymentModel();
    @FXML
    void acSave(ActionEvent event) {
        try {
            String paymentId = lblPayId.getText();
            String reservationId = lblReservationId.getText();
            String paymentDateString = lblPaymentDate.getText();
            String paymentAmountString = txtPaymentAmount.getText();

            LocalDate localDate = LocalDate.parse(paymentDateString);
            Date paymentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            double paymentAmount = Double.parseDouble(paymentAmountString);

            PaymentDto paymentDto = new PaymentDto(paymentId, paymentDate, paymentAmount, reservationId);

            boolean isSaved = paymentModel.savePayment(paymentDto);

            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Payment Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Payment Not Saved").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid amount! Please enter a valid number.").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    void acUpdate(ActionEvent event) {
            try {
                String paymentId = lblPayId.getText();
                String reservationId = lblReservationId.getText();
                String paymentDateString = lblPaymentDate.getText();
                String paymentAmountString = txtPaymentAmount.getText();

                if (paymentId.isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "Payment ID is required for updating.").show();
                    return;
                }

                LocalDate localDate = LocalDate.parse(paymentDateString);
                Date paymentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                double paymentAmount = Double.parseDouble(paymentAmountString);
                PaymentDto paymentDto = new PaymentDto(paymentId, paymentDate, paymentAmount, reservationId);
                boolean isUpdated = paymentModel.updatePayment(paymentDto);

                if (isUpdated) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Payment Updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Payment Not Updated").show();
                }
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid amount! Please enter a valid number.").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
                e.printStackTrace();
            }

    }

    private void refreshPage() throws Exception {
        getNextPaymentId();
        loadTableData();

        lblPaymentDate.setText(LocalDate.now().toString());
        txtPaymentAmount.setText("");
        lblReservationId.setText("");

    }

    private void loadTableData() throws Exception {
        ArrayList<PaymentDto> paymentDTOS = paymentModel.getAllPayment();

        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();

        for (PaymentDto paymentDto : paymentDTOS) {
            PaymentTm paymentTm = new PaymentTm(paymentDto.getPId(), paymentDto.getDate(), paymentDto.getAmount(), paymentDto.getReservationId());
            paymentTms.add(paymentTm);
        }

        tblPayment.setItems(paymentTms);
    }

    private void getNextPaymentId() throws Exception {
        String paymentId = paymentModel.getNextPaymentId();
        lblPayId.setText(paymentId);
    }

    @FXML
    void addReservationAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddReservation.fxml"));
            Parent root = loader.load();
            AddReservationController addReservationController = loader.getController();
            addReservationController.setPaymentPageController(this);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Evens").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Image paymentImage = new Image(getClass().getResourceAsStream("/image/simple2.jpeg"));
//        imageView.setImage(paymentImage);

        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("pId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!");
        }
    }

    public void tblPaymentActions(MouseEvent event) {
        PaymentTm paymentTm = tblPayment.getSelectionModel().getSelectedItem();
        if (paymentTm != null) {
            lblPayId.setText(paymentTm.getPId());
            lblPaymentDate.setText(paymentTm.getDate().toString());
            txtPaymentAmount.setText(String.valueOf(paymentTm.getAmount()));
            lblReservationId.setText(paymentTm.getReservationId());
        }
    }

    public void setReservationId(String reservationId) {
        lblReservationId.setText(reservationId);
    }
}
