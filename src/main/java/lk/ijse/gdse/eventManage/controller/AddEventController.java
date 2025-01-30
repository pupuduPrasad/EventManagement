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
import lk.ijse.gdse.eventManage.dto.EventDto;
import lk.ijse.gdse.eventManage.dto.tm.EventTm;
import lk.ijse.gdse.eventManage.dao.custom.impl.EventDAOImpl;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {

    @FXML
    private Button btnFinish;

    @FXML
    private Label lblEventId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colEventId;

    @FXML
    private TableColumn<?, ?> colEventName;

    @FXML
    private TableColumn<?, ?> colFaculity;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableView<EventTm> tblEvent;

    @Setter
    ReservationPageController reservationPageController;

    @Setter
    SponsorsController sponsorsController;

    @FXML
    void btnFinishOnAction(ActionEvent event) {
        EventTm selectedItem = tblEvent.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an event..!").show();
            return;
        }

        if (reservationPageController != null) {
            reservationPageController.setEventId(selectedItem.getEventId());
        } else {
            System.err.println("Error: reservationPageController is not initialized!");
        }

        if (sponsorsController != null) {
            sponsorsController.setEventId(selectedItem.getEventId());
        } else {
            System.err.println("Error: sponsorsController is not initialized!");
        }

        // Close the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    void tblEventAction(MouseEvent event) {
        EventTm customerTm = tblEvent.getSelectionModel().getSelectedItem();
        if (customerTm != null) {
            lblEventId.setText(customerTm.getEventId());

            btnFinish.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colFaculity.setCellValueFactory(new PropertyValueFactory<>("eventFaculty"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!");
        }
    }

    EventDAOImpl eventDAOImpl = new EventDAOImpl();

    private void refreshPage() throws Exception {
        loadTableData();

        btnFinish.setDisable(true);
    }

    private void loadTableData() throws Exception {
        ArrayList<EventDto> customerDTOS = eventDAOImpl.getAll();

        ObservableList<EventTm> eventTms = FXCollections.observableArrayList();

        for (EventDto eventDto : customerDTOS) {
            EventTm eventTm = new EventTm(eventDto.getEventId(), eventDto.getEventName(), eventDto.getEventFaculty(), eventDto.getDescription(), eventDto.getDate(), eventDto.getTime());
            eventTms.add(eventTm);
        }

        tblEvent.setItems(eventTms);
    }
}

