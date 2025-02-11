package lk.ijse.gdse.eventManage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.eventManage.bo.BOFactory;
import lk.ijse.gdse.eventManage.bo.custom.EventBo;
import lk.ijse.gdse.eventManage.dto.EventDto;
import lk.ijse.gdse.eventManage.dto.tm.EventTm;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventController implements Initializable {

    @FXML
    private Button btDelete, btnSave, btnUpdate;

    @FXML
    private TableColumn<EventDto, ?> colDate, colDescription, colEventId, colEventName, colFaculity, colTime;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lblDate, lblEventId;

    @FXML
    private DatePicker picDate;

    @FXML
    private TableView<EventTm> tblEvent;

    @FXML
    private TextField txtDescription, txtFaculty, txtTime, txtTitle;

    @FXML
    private AnchorPane content;

    EventBo eventBo = (EventBo) BOFactory.getInstance().getBO(BOFactory.BOType.EVENT);

    @FXML
    void deleteAction(ActionEvent event) throws Exception {
        String eventId = lblEventId.getText();

        if (eventId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "No Event Selected for Deletion!").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this event?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = eventBo.delete(eventId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Event Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete Event!").show();
            }
        }
    }

    @FXML
    void homeAction(ActionEvent event) {
        navigateTo("/view/dashboard.fxml");
    }

    @FXML
    void refreshAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void saveOnAction(ActionEvent event) throws Exception {
        if (!validateInputs()) return;

        String eventId = lblEventId.getText();
        String eventName = txtTitle.getText();
        String eventFaculty = txtFaculty.getText();
        String description = txtDescription.getText();
        LocalDate localDate = picDate.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String time = txtTime.getText();

        EventDto eventDto = new EventDto(eventId, eventName, eventFaculty, description, date, time);
        boolean isSaved = eventBo.save(eventDto);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Event Saved Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Save Event!").show();
        }
    }

    @FXML
    void tblEventAction(MouseEvent event) {
        EventTm eventTm = tblEvent.getSelectionModel().getSelectedItem();
        if (eventTm == null) {
            new Alert(Alert.AlertType.WARNING, "Please Select an Event from the Table!").show();
            return;
        }

        lblEventId.setText(eventTm.getEventId());
        txtTitle.setText(eventTm.getEventName());
        txtFaculty.setText(eventTm.getEventFaculty());
        txtDescription.setText(eventTm.getDescription());
        lblDate.setText(eventTm.getDate().toString());
        txtTime.setText(eventTm.getTime());
    }

    @FXML
    void updateAction(ActionEvent event) throws Exception {
        if (!validateInputs()) return;

        String eventId = lblEventId.getText();
        String eventName = txtTitle.getText();
        String eventFaculty = txtFaculty.getText();
        String description = txtDescription.getText();
        String time = txtTime.getText();

        LocalDate selectedDate = picDate.getValue();

        if (selectedDate == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an event date.").show();
            return;
        }

        Date dateD = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        EventDto eventDto = new EventDto(eventId, eventName, eventFaculty, description, dateD, time);

        boolean isUpdated = eventBo.update(eventDto);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Event Updated Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Update Event!").show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.setImage(new Image(getClass().getResourceAsStream("/image/events.jpg")));

        colEventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colFaculity.setCellValueFactory(new PropertyValueFactory<>("eventFaculty"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            refreshPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to Load Data!").show();
        }
    }

    private void refreshPage() throws Exception {
        getNextEventId();
        loadTableData();

        txtTitle.clear();
        txtFaculty.clear();
        txtDescription.clear();
        picDate.setValue(null);
        txtTime.clear();
        lblDate.setText("");
    }

    private void loadTableData() throws Exception {
        ArrayList<EventDto> eventDtos = eventBo.getAll();
        ObservableList<EventTm> eventTms = FXCollections.observableArrayList();

        for (EventDto event : eventDtos) {
            EventTm eventTm = new EventTm(event.getEventId(), event.getEventName(), event.getEventFaculty(), event.getDescription(), event.getDate(), event.getTime());
            eventTms.add(eventTm);
        }

        tblEvent.setItems(eventTms);
    }

    private void getNextEventId() throws Exception {
        String nextEventId = eventBo.getNextId();
        lblEventId.setText(nextEventId);
    }

    public void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.prefWidthProperty().bind(content.widthProperty());
            load.prefHeightProperty().bind(content.heightProperty());
            content.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to Load Page!").show();
        }
    }

    private boolean validateInputs() {
        if (txtTitle.getText().isEmpty() || txtFaculty.getText().isEmpty() || txtDescription.getText().isEmpty() || txtTime.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "All Fields Are Required!").show();
            return false;
        }

        if (picDate.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please Select a Valid Date!").show();
            return false;
        }

        if (!txtTime.getText().matches("\\d{2}:\\d{2}")) {
            new Alert(Alert.AlertType.WARNING, "Invalid Time Format! Use HH:mm").show();
            return false;
        }

        return true;
    }
}
