package lk.ijse.gdse.eventManage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.eventManage.bo.BOFactory;
import lk.ijse.gdse.eventManage.bo.custom.TicketBO;
import lk.ijse.gdse.eventManage.bo.custom.impl.TicketBOImpl;
import lk.ijse.gdse.eventManage.dto.TicketDto;
import lk.ijse.gdse.eventManage.dto.tm.TicketTm;
import lk.ijse.gdse.eventManage.dao.custom.impl.TicketDAOImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TicketPageController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEventId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colTicketId;

    @FXML
    private AnchorPane content;


    @FXML
    private ImageView imageView;

    @FXML
    private TextField lblCustomerId;

    @FXML
    private TextField lblEventId;

    @FXML
    private Label lblId;

    @FXML
    private TableView<TicketTm> tblTicket;

    @FXML
    private TextField txtPrice;

    private final TicketBO ticketBO = (TicketBO) BOFactory.getInstance().getBO(BOFactory.BOType.TICKET);

    @FXML
    void acDelete(ActionEvent event) throws Exception {
        String ticketId = lblId.getText();
        Double price = Double.parseDouble(txtPrice.getText());
        String custId = lblCustomerId.getText();
        String eventId = lblEventId.getText();

        TicketDto ticketDto = new TicketDto(ticketId, price, custId, eventId);
        boolean isDeleted = ticketBO.delete(ticketId);

        if (isDeleted) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Ticket Delete").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Ticket Not Delete").show();
        }

    }

    @FXML
    void acHome(ActionEvent event) {
        navigateTo("/view/dashboard.fxml");

    }

    @FXML
    void ticketAction(MouseEvent event) {
        TicketTm ticketTm = tblTicket.getSelectionModel().getSelectedItem();
        if (ticketTm != null) {
            lblId.setText(ticketTm.getTicketId());
            txtPrice.setText(ticketTm.getPrice().toString());
            lblCustomerId.setText(ticketTm.getCustId());
            lblEventId.setText(ticketTm.getEventId());
        }
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

//    TicketDAOImpl ticketDAOImpl =new TicketDAOImpl();

    @FXML
    void acSave(ActionEvent event) throws Exception {
        String ticketId = lblId.getText();
        Double price = Double.parseDouble(txtPrice.getText());
        String custId = lblCustomerId.getText();
        String eventId = lblEventId.getText();



        TicketDto ticketDto = new TicketDto(ticketId, price, custId, eventId);

        boolean isSaved = ticketBO.save(ticketDto);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Ticket Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Ticket Not Saved").show();
        }

    }

    private void refreshPage() throws Exception {
        getNextTicketId();
        loadTableData();

        txtPrice.setText("");
        lblCustomerId.setText("");
        lblEventId.setText("");
    }

    private void loadTableData() throws SQLException {
        ArrayList<TicketDto> ticketDtos = ticketBO.getAll();

        ObservableList<TicketTm> ticketTms = FXCollections.observableArrayList();

        for (TicketDto ticketDto : ticketDtos) {
            TicketTm ticketTm = new TicketTm(ticketDto.getTicketId(), ticketDto.getPrice(), ticketDto.getCustId(), ticketDto.getEventId());
            ticketTms.add(ticketTm);
        }

        tblTicket.setItems(ticketTms);
    }

    private void getNextTicketId() throws Exception {
            String nextTicketId = ticketBO.getNextId();
            lblId.setText(nextTicketId);

    }



    @FXML
    void acUpdate(ActionEvent event) throws Exception {
        String ticketId = lblId.getText();
        Double price = Double.parseDouble(txtPrice.getText());
        String custId = lblCustomerId.getText();
        String eventId = lblEventId.getText();


            TicketDto ticketDto = new TicketDto(ticketId,price,custId,eventId);
            boolean isUpdated = ticketBO.update(ticketDto);

            if (isUpdated) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Ticket Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Ticket Not Saved").show();
            }

    }

    @FXML
    void getEventAction(ActionEvent event) {

    }
    @FXML
    void getCustomerAction(ActionEvent event) {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colTicketId.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colEventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to Initialize").show();
        }



    }

}
