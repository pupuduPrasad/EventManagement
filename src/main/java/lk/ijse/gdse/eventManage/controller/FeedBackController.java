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
import lk.ijse.gdse.eventManage.bo.BOFactory;
import lk.ijse.gdse.eventManage.bo.custom.FeedbackBO;
import lk.ijse.gdse.eventManage.dto.FeedbackDto;

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

    FeedbackBO feedbackBo = (FeedbackBO) BOFactory.getInstance().getBO(BOFactory.BOType.FEEDBACK);
    @FXML
    void DeleteFeedBack(ActionEvent event) throws Exception {
        String fId = lblFeedbackId.getText();
        String comment = txtFeedBack.getText();
        String custId =lblCustomerId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = feedbackBo.delete(fId);
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
        boolean isUpdated = feedbackBo.update(feedbackDto);

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
        String nextFeedbackId = feedbackBo.getNextId();
        lblFeedbackId.setText(nextFeedbackId);

    }

    @FXML
    void SaveFeedBack(ActionEvent event) throws Exception {
        // Get next feedback ID
        String fId = lblFeedbackId.getText();
        String comment = txtFeedBack.getText();
        String custId = lblCustomerId.getText();

        // Ensure feedbackId is set (call the method to generate the next ID if it's empty)
        if (fId == null || fId.isEmpty()) {
            fId = feedbackBo.getNextId();  // Fetch the next ID
            lblFeedbackId.setText(fId);    // Set it on the label
        }

        // Create FeedbackDto object
        FeedbackDto feedbackDto = new FeedbackDto(fId, comment, custId);

        // Save feedback
        boolean isSaved = feedbackBo.save(feedbackDto);

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
            // Load the feedback table data
            loadTableData();

            // Set the next Feedback ID
            getNextFeedbackId();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load feedback data!").show();
        }
    }

    private void loadTableData() throws SQLException {
        ObservableList<FeedbackDto> feedbackList = FXCollections.observableArrayList(feedbackBo.getAll());
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