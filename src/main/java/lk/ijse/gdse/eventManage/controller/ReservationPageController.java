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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse.eventManage.bo.BOFactory;
import lk.ijse.gdse.eventManage.bo.custom.ReservationBO;
import lk.ijse.gdse.eventManage.dto.ReservationDto;
import lk.ijse.gdse.eventManage.dto.tm.ReservationTm;
import lombok.Setter;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReservationPageController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;


    @FXML
    private TableColumn<?, ?> colEventId;

    @FXML
    private TableColumn<?, ?> colEventVenue;

    @FXML
    private TableColumn<?, ?> colReservationDate;


    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private AnchorPane content;


    @FXML
    private ImageView imageView;

    @FXML
    private Label lblEventId;

    @FXML
    private Label lblReservationId;

    @FXML
    private Label lblDate;

    @FXML
    private DatePicker picReservationDate;

    @FXML
    private TableView<ReservationTm> tblReservation;

    @FXML
    private TextField txtVenue;

    @Setter
    ReservationPageController reservationPageController;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);

    @FXML
    void acDelete(ActionEvent event) throws Exception {
        String rId = lblReservationId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDeleted = reservationBO.delete(rId);
                if (isDeleted) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Reservation deleted...!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete Reservation...!").show();
                }
            }

    }

    @FXML
    void acHome(ActionEvent event) {
        navigateTo("/view/dashboard.fxml");

    }

    @FXML
    void acRefresh(ActionEvent event) throws Exception {
        refreshPage();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image reservationImage = new Image(getClass().getResourceAsStream("/image/simple7.jpeg"));
        imageView.setImage(reservationImage);

        colReservationId.setCellValueFactory(new PropertyValueFactory<>("rId"));
        colReservationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEventVenue.setCellValueFactory(new PropertyValueFactory<>("eventVenue"));
        colEventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!");
        }

    }

    private void refreshPage() throws Exception {
        getNextReservationId();
        loadTableData();

        picReservationDate.setValue(null);
        txtVenue.setText("");
        lblEventId.setText("");
        lblDate.setText("");

    }

    @FXML
    void acSave(ActionEvent event) throws Exception {
        // Validation for required fields
        if (picReservationDate.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a reservation date.").show();
            return;
        }

        if (txtVenue.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter an event venue.").show();
            return;
        }

        if (lblEventId.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Event ID is missing. Please select an event.").show();
            return;
        }

        String rId = lblReservationId.getText();
        LocalDate localDate = picReservationDate.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String eventVenue = txtVenue.getText();
        String eventId = lblEventId.getText();

        // Create ReservationDto and save
        ReservationDto reservationDto = new ReservationDto(rId, date, eventVenue, eventId);
        boolean isSaved = reservationBO.save(reservationDto);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Reservation Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Reservation Not Saved").show();
        }
    }


    @FXML
    void acUpdate(ActionEvent event) throws Exception {
        if (txtVenue.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter an event venue.").show();
            return;
        }

        if (lblEventId.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Event ID is missing. Please select an event.").show();
            return;
        }

        LocalDate reservationDate = picReservationDate.getValue();
        if (reservationDate == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a reservation date.").show();
            return;
        }

        Date dateD = Date.from(reservationDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String rId = lblReservationId.getText();
        String eventVenue = txtVenue.getText();
        String eventId = lblEventId.getText();

        ReservationDto reservationDto = new ReservationDto(rId, dateD, eventVenue, eventId);

        boolean isUpdated = reservationBO.update(reservationDto);

        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Reservation Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Reservation Not Updated").show();
        }
    }




    @FXML
    void addAc(ActionEvent event)  {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddEvent.fxml"));
                Parent root = loader.load();
                AddEventController addEventController = loader.getController();
                addEventController.setReservationPageController(this);

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

    @FXML
    void tblReservationAction(MouseEvent event) {
        ReservationTm reservationTm = tblReservation.getSelectionModel().getSelectedItem();
        if (reservationTm != null) {
            lblReservationId.setText(reservationTm.getRId());
            lblDate.setText(reservationTm.getDate().toString());
            txtVenue.setText(reservationTm.getEventVenue());
            lblEventId.setText(reservationTm.getEventId());
        }

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

    private void getNextReservationId() throws Exception {
        String nextReservationId = reservationBO.getNextId();
        lblReservationId.setText(nextReservationId);
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

    public void setEventId(String eventId) {
        lblEventId.setText(eventId);
    }
}
