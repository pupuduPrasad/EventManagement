package lk.ijse.gdse.eventManage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.eventManage.bo.BOFactory;
import lk.ijse.gdse.eventManage.bo.custom.CustomerBO;
import lk.ijse.gdse.eventManage.dto.CustomerDto;
import lk.ijse.gdse.eventManage.dto.tm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<CustomerDto, String> colCustomerId;

    @FXML
    private TableColumn<CustomerDto, String> colCustomerName;

    @FXML
    private TableColumn<CustomerDto, Integer> colNumber;

    @FXML
    private AnchorPane content;

    @FXML
    private ImageView customer;

    @FXML
    private Label lblCustomer;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    //    private final CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    @FXML
    void acCustomer(MouseEvent event) {
        CustomerTm customerTm = tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTm != null) {
            lblCustomer.setText(customerTm.getCustId());
            txtName.setText(customerTm.getName());
            txtNumber.setText(String.valueOf(customerTm.getCoNumber()));
        }

    }

    @FXML
    void acDelete(ActionEvent event) throws Exception {
        String custId = lblCustomer.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = customerBO.delete(custId);
            if (isDeleted) {
                refreshTable();
                new Alert(Alert.AlertType.INFORMATION, "Event deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Event...!").show();
            }
        }
    }
    @FXML
    void acUpdate(ActionEvent event) {
        CustomerTm selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            showAlert(Alert.AlertType.ERROR, "No customer selected to update.");
            return;
        }

        String name = txtName.getText().trim();
        String coNumber = txtNumber.getText().trim();

        if (name.isEmpty() || coNumber.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Fields cannot be empty.");
            return;
        }

        try {
            int number = Integer.parseInt(coNumber);
            CustomerDto updatedCustomer = new CustomerDto(selectedCustomer.getCustId(), name, number);

            boolean isUpdated = customerBO.update(updatedCustomer);
            if (isUpdated) {
                refreshTable();
                showAlert(Alert.AlertType.INFORMATION, "Customer updated successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to update customer.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid number format. Please enter a valid integer.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void acSave(ActionEvent event) throws Exception {
        String custId = lblCustomer.getText();
        String name = txtName.getText().trim();
        int coNumber = Integer.parseInt(txtNumber.getText().trim());

        CustomerDto customerDto = new CustomerDto(custId,name,coNumber);

        try {
            int number = Integer.parseInt(String.valueOf(coNumber));
            CustomerDto updatedCustomer = new CustomerDto(customerDto.getCustId(), name, number);

            boolean isSaved = customerBO.save(updatedCustomer);
            if (isSaved) {
                refreshTable();
                showAlert(Alert.AlertType.INFORMATION, "Customer Save successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to Save customer.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid number format. Please enter a valid integer.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void homeAction(ActionEvent event) {
        navigateTo("/view/dashboard.fxml");

    }

    @FXML
    void refreshAction(ActionEvent event) {
        try {
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Failed to refresh table: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshTable() throws Exception {
        getNextCustomerId();
        loadTableData();
        txtName.setText("");
        txtNumber.setText("");
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        new Alert(alertType, message).show();
    }

    @FXML
    public void initialize() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("coNumber"));

        try {
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Failed to load customer data.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void getNextCustomerId() throws Exception {
        String nextEventId = customerBO.getNextId();
        lblCustomer.setText(nextEventId);
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

    private void loadTableData() throws Exception {
        ArrayList<CustomerDto> customerDTOS = customerBO.getAll();

        ObservableList<CustomerTm> customerTms = FXCollections.observableArrayList();

        for (CustomerDto customerDto : customerDTOS) {
            CustomerTm customerTm = new CustomerTm(customerDto.getCustId(), customerDto.getName(), customerDto.getCoNumber());
            customerTms.add(customerTm);
        }

        tblCustomer.setItems(customerTms);
    }

}