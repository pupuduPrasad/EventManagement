package lk.ijse.gdse.eventManage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.gdse.eventManage.bo.BOFactory;
import lk.ijse.gdse.eventManage.bo.custom.ReservationBO;
import lk.ijse.gdse.eventManage.dto.ReservationDto;
import lk.ijse.gdse.eventManage.dto.tm.ReservationTm;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddReservationController implements Initializable {

    @FXML
    private Button btnFinish;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEventId;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private TableColumn<?, ?> colVenue;

    @FXML
    private Label lblReservationId;

    @FXML
    private TableView<ReservationTm> tblReservation;

    @Setter
    PaymentPageController paymentPageController;
    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);

    @FXML
    void btnFinishOnAction(ActionEvent event) {
        ReservationTm selectedItem = tblReservation.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.WARNING, "Please select customer..!").show();
        } else {
            paymentPageController.setReservationId(selectedItem.getRId());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void tblAction(MouseEvent event) {
        ReservationTm reservationTm = tblReservation.getSelectionModel().getSelectedItem();
        if (reservationTm != null) {
            lblReservationId.setText(reservationTm.getRId());

            btnFinish.setDisable(false);
        }
    }

//    ReservationDAOImpl reservationDAOImpl = new ReservationDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("rId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colVenue.setCellValueFactory(new PropertyValueFactory<>("eventVenue"));
        colEventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!");
        }
    }

    private void refreshPage() throws Exception {
        loadTableData();
    }

    private void loadTableData() throws Exception {
        ArrayList<ReservationDto> reservationDtos = reservationBO.getAll();

        ObservableList<ReservationTm> reservationTms = FXCollections.observableArrayList();

        for (ReservationDto reservationDto : reservationDtos) {
            ReservationTm reservationTm = new ReservationTm(reservationDto.getRId(), reservationDto.getDate(), reservationDto.getEventVenue(), reservationDto.getEventId());
            reservationTms.add(reservationTm);
        }

        tblReservation.setItems(reservationTms);
    }
}
