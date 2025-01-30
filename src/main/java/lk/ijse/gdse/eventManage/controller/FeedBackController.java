package lk.ijse.gdse.eventManage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.eventManage.dto.FeedbackDto;
import lk.ijse.gdse.eventManage.dao.FeedbackModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class FeedBackController {

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<FeedbackDto, String> colCustomerId;

    @FXML
    private TableColumn<FeedbackDto, String> colFeedBack;

    @FXML
    private TableColumn<FeedbackDto, String> colFeedbackId;

    @FXML
    private AnchorPane content;

    @FXML
    private TableView<FeedbackDto> tblFeedBack;

    @FXML
    private Label lblCustomerId;

    @FXML
    private TextArea txtFeedBack;

    @FXML
    private Label lblFeedbackId;

    FeedbackModel feedbackModel = new FeedbackModel();

    @FXML
    void DeleteFeedBack(ActionEvent event) throws Exception {
        String fId = lblFeedbackId.getText();
        String comment = txtFeedBack.getText();
        String custId =lblCustomerId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = feedbackModel.deleteFeedback(fId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Feedback deleted successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete feedback!").show();
            }
        }

    }
    @FXML
    void acUpdate(ActionEvent event) throws Exception {
        String fId = lblFeedbackId.getText();
        String comment = txtFeedBack.getText();
        String custId = lblCustomerId.getText();


        FeedbackDto feedbackDto = new FeedbackDto(fId, comment,custId);

        boolean isUpdated = feedbackModel.updateFeedback(feedbackDto);

        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Feedback updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update feedback!").show();
        }
    }
    private void refreshPage() throws Exception {
        getNextFeedbackId();
        loadTableData();

        lblCustomerId.setText("");
        txtFeedBack.setText("");
    }

    private void getNextFeedbackId() throws SQLException {
        String nextFeedbackId = feedbackModel.getNextFeedbackId();
        lblFeedbackId.setText(nextFeedbackId);

    }

    @FXML
    void SaveFeedBack(ActionEvent event) throws Exception {
        String fId = lblFeedbackId.getText();
        String comment = txtFeedBack.getText();
        String custId = txtFeedBack.getText();

        FeedbackDto feedbackDto = new FeedbackDto(fId, comment, custId);

        boolean isSaved = feedbackModel.saveFeedback(feedbackDto);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Feedback saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save feedback!").show();
        }
    }



    @FXML
    void acFeedback(MouseEvent event) {
        FeedbackDto selectedFeedback = tblFeedBack.getSelectionModel().getSelectedItem();
        if (selectedFeedback != null) {
            lblFeedbackId.setText(selectedFeedback.getFId());
            lblCustomerId.setText(selectedFeedback.getCustId());
            txtFeedBack.setText(selectedFeedback.getComment());
        }
    }

    @FXML
    void mainlayout(ActionEvent event) {
        navigateTo("/view/dashboard.fxml");
    }
    @FXML
    void acCustomer(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        colFeedbackId.setCellValueFactory(new PropertyValueFactory<>("fId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colFeedBack.setCellValueFactory(new PropertyValueFactory<>("comment"));

        try {
            loadTableData();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load feedback data!").show();
        }
    }

    private void loadTableData() throws SQLException {
        ObservableList<FeedbackDto> feedbackList = FXCollections.observableArrayList(feedbackModel.getAllFeedback());
        tblFeedBack.setItems(feedbackList);
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
}
