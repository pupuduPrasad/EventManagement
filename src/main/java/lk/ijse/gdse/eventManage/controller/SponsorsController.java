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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse.eventManage.bo.BOFactory;
import lk.ijse.gdse.eventManage.bo.custom.SponsorBO;
import lk.ijse.gdse.eventManage.dto.EventSponsorsDto;
import lk.ijse.gdse.eventManage.dto.JoinSponserEventDetailDto;
import lk.ijse.gdse.eventManage.dto.SponsorDto;
import lk.ijse.gdse.eventManage.dto.tm.SponsorTm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SponsorsController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEventId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSponsorId;



    @FXML
    private Label lblEventId;

    @FXML
    private Label lblSponsorId;

    @FXML
    private TableView<SponsorTm> tbtSponsor;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private AnchorPane content;

    SponsorBO sponsorBO = (SponsorBO) BOFactory.getInstance().getBO(BOFactory.BOType.SPONSOR);
    @FXML
    void acAddEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddEvent.fxml"));
            Parent root = loader.load();
            AddEventController addEventController = loader.getController();
            addEventController.setSponsorsController(this);

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
    void acDelete(ActionEvent event) throws Exception {
        String sponcerId = lblSponsorId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = sponsorBO.delete(sponcerId);
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

    @FXML
    void acRefresh(ActionEvent event) throws Exception {
        refreshPage();
    }


    @FXML
    void acSave(ActionEvent event) throws Exception {
        if (!validateInputs()) {
            return;
        }

        String sponsorId = lblSponsorId.getText();
        String eventId = lblEventId.getText();
        String sponsorName = txtName.getText();
        String phoneNumber = txtContact.getText();
        String address = txtAddress.getText();
        double amount = Double.parseDouble(txtAmount.getText());

        SponsorDto sponsorDto = new SponsorDto(sponsorId, sponsorName, phoneNumber, address);
        EventSponsorsDto eventSponsorsDto = new EventSponsorsDto(eventId, sponsorId, amount);

        ArrayList<SponsorDto> sponsorDtos = new ArrayList<>();
        sponsorDtos.add(sponsorDto);
        ArrayList<EventSponsorsDto> eventSponsorsDtos = new ArrayList<>();
        eventSponsorsDtos.add(eventSponsorsDto);

        boolean isSavedS = sponsorBO.save(sponsorDtos, eventSponsorsDtos);

        if (isSavedS) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Successfully Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Save").show();
        }
    }

    @FXML
    void acUpdate(ActionEvent event) throws Exception {
        if (!validateInputs()) {
            return;
        }

        String sponsorId = lblSponsorId.getText();
        String eventId = lblEventId.getText();
        String sponsorName = txtName.getText();
        String phoneNumber = txtContact.getText();
        String address = txtAddress.getText();
        double amount = Double.parseDouble(txtAmount.getText());

        SponsorDto sponsorDto = new SponsorDto(sponsorId, sponsorName, phoneNumber, address);
        EventSponsorsDto eventSponsorsDto = new EventSponsorsDto(eventId, sponsorId, amount);

        ArrayList<SponsorDto> sponsorDtos = new ArrayList<>();
        sponsorDtos.add(sponsorDto);
        ArrayList<EventSponsorsDto> eventSponsorsDtos = new ArrayList<>();
        eventSponsorsDtos.add(eventSponsorsDto);

        boolean isUpdatedS = sponsorBO.update(sponsorDtos, eventSponsorsDtos);

        if (isUpdatedS) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Successfully Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Update").show();
        }
    }

    private boolean validateInputs() {
        String name = txtName.getText().trim();
        String contact = txtContact.getText().trim();
        String amountText = txtAmount.getText().trim();

        if (!name.matches("^[A-Za-z ]{3,}$")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Name! Name must contain at least 3 letters.");
            return false;
        }

        if (!contact.matches("^(07[0-9]{8})$")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Contact Number! It should be a 10-digit number starting with 07.");
            return false;
        }

        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showAlert(Alert.AlertType.ERROR, "Invalid Amount! Amount should be a positive number.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Amount! Please enter a valid number.");
            return false;
        }

        return true;
    }
    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.show();
    }


    @FXML
    void tblAction(MouseEvent event) {
        SponsorTm sponsorTm = tbtSponsor.getSelectionModel().getSelectedItem();
        if (sponsorTm != null) {
            lblSponsorId.setText(sponsorTm.getSId());
            lblEventId.setText(sponsorTm.getEventId());
            txtName.setText(sponsorTm.getName());
            txtContact.setText(sponsorTm.getContactNumber());
            txtAddress.setText(sponsorTm.getAddress());
            txtAmount.setText(String.valueOf(sponsorTm.getAmount()));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSponsorId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        colEventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!").show();
        }
    }



    private void refreshPage() throws Exception {
        loadTableData();
        loadNextSponsorId();
        lblEventId.setText("");
        txtName.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        txtAmount.setText("");
    }


    private void loadNextSponsorId() throws Exception {
        String nextSponsorId = sponsorBO.getNextId();
        lblSponsorId.setText(nextSponsorId);
    }

    private void loadTableData() throws Exception {
        ArrayList<JoinSponserEventDetailDto> list = sponsorBO.getAll();

        if (list == null || list.isEmpty()) {
            System.out.println("No data found!");
            tbtSponsor.setItems(FXCollections.observableArrayList());
            return;
        }

        ObservableList<SponsorTm> sponsorTms = FXCollections.observableArrayList();

        for (JoinSponserEventDetailDto dto : list) {
            sponsorTms.add(new SponsorTm(
                    dto.getSId(),
                    dto.getEventId(),
                    dto.getName(),
                    dto.getContactNumber(),
                    dto.getAddress(),
                    dto.getAmount()
            ));
        }

        tbtSponsor.setItems(sponsorTms);
        tbtSponsor.refresh();
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
