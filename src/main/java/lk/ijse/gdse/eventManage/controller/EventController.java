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
import lk.ijse.gdse.eventManage.dto.EventDto;
import lk.ijse.gdse.eventManage.dto.tm.EventTm;
import lk.ijse.gdse.eventManage.model.EventModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
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
    private Button btDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

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
    private ImageView imageView;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEventId;


    @FXML
    private DatePicker picDate;

    @FXML
    private TableView<EventTm> tblEvent;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtFaculty;

    @FXML
    private TextField txtTime;

    @FXML
    private TextField txtTitle;

    @FXML
    private AnchorPane content;

    @FXML
    void deleteAction(ActionEvent event) throws Exception {
        String eventId = lblEventId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = eventModel.deleteEvent(eventId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Event deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Event...!").show();
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
        String eventId = lblEventId.getText();
        String eventName = txtTitle.getText();
        String eventFaculty = txtFaculty.getText();
        String description = txtDescription.getText();
        LocalDate localDate = picDate.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String time = txtTime.getText();

        EventDto eventDto = new EventDto(eventId, eventName, eventFaculty, description, date, time);

        boolean isSaved = eventModel.saveEvent(eventDto);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Event Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Event Not Saved").show();
        }
    }

    @FXML
    void tblEventAction(MouseEvent event) {
        EventTm eventTm = tblEvent.getSelectionModel().getSelectedItem();
        if (eventTm != null) {
            lblEventId.setText(eventTm.getEventId());
            txtTitle.setText(eventTm.getEventName());
            txtFaculty.setText(eventTm.getEventFaculty());
            txtDescription.setText(eventTm.getDescription());
            lblDate.setText(eventTm.getDate().toString());
            txtTime.setText(eventTm.getTime());
        }
    }

    @FXML
    void updateAction(ActionEvent event) throws Exception {
        String eventId = lblEventId.getText();
        String eventName = txtTitle.getText();
        String eventFaculty = txtFaculty.getText();
        String description = txtDescription.getText();
        String date = lblDate.getText();
        String time = txtTime.getText();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateD = formatter.parse(date);
            EventDto eventDto = new EventDto(eventId, eventName, eventFaculty, description, dateD, time);
            boolean isUpdated = eventModel.updateEvent(eventDto);

            if (isUpdated) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Event Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Event Not Saved").show();
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format!");
        }





    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/image/events.jpg"));
        imageView.setImage(loginImage);

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

    private void refreshPage() throws Exception {
        getNextEventId();
        loadTableData();

        txtTitle.setText("");
        txtFaculty.setText("");
        txtDescription.setText("");
        picDate.setValue(null);
        txtTime.setText("");
        lblDate.setText("");
    }

    EventModel eventModel = new EventModel();

    private void loadTableData() throws Exception {
        ArrayList<EventDto> eventDtos = eventModel.getAllEvents();

        ObservableList<EventTm> eventTms = FXCollections.observableArrayList();

        for (EventDto eventDto : eventDtos) {
            EventTm eventTm = new EventTm(eventDto.getEventId(), eventDto.getEventName(), eventDto.getEventFaculty(), eventDto.getDescription(), eventDto.getDate(), eventDto.getTime());
            eventTms.add(eventTm);
        }

        tblEvent.setItems(eventTms);
    }

    private void getNextEventId() throws Exception {
        String nextEventId = eventModel.getNextEventId();
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
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
}
