/*
* File: MainController.java
* Author: Balog Levente
* Copyright: 2025, Balog Levente
* Group: Szoft I-2-E
* Date: 2025-05-24
* Github: https://github.com/BalogKTCH/
* Licenc: MIT
*/

package com.example;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class MainController {



    @FXML
    private Button addButton;

    @FXML
    private TextField CustomerField;

    @FXML
    private ListView<String> CustomerListView;

    @FXML
    private Button deleteButton;

    @FXML
    private Button modifyButton;

    @FXML
    private Button saveButton;

    BooleanProperty editMode = new SimpleBooleanProperty(false);

    @FXML
    void initialize() {

        ArrayList<String> customerList = Storage.readuserCustomers();
        CustomerListView.getItems().addAll(customerList);

        addButton.disableProperty().bind(editMode);
        deleteButton.disableProperty().bind(editMode);
        saveButton.disableProperty().bind(editMode);
        modifyButton.disableProperty().bind(editMode.not());
    }

    @FXML
    void onClickAboutButton(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Névjegy");
        alert.setHeaderText("Vevő névjegye");
        alert.setContentText("Balog Levente\nI-2-E\n2025-05-24");
        alert.initOwner(App._stage);
        alert.show();
    }

    @FXML
    void onClickAddButton(ActionEvent event) {
        String customer = CustomerField.getText();
        if (customer.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Hiba!");
            alert.setHeaderText("Bemeneti hiba!");
            alert.setContentText("A névjegy nem lehet üres!");
            alert.initOwner(App._stage);
            alert.show();
            return;
        }
        CustomerListView.getItems().add(customer);
        CustomerField.setText("");
    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {
        int index = CustomerListView.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Hiba!");
            alert.setHeaderText("Kiválasztási hiba!");
            alert.setContentText("Nincs kiválsztva vevő!");
            alert.initOwner(App._stage);
            alert.show();
            return;
        }
        CustomerListView.getItems().remove(index);
    }

    @FXML
    void onClickModifyButton(ActionEvent event) {
        String customer = CustomerField.getText();
        if (customer.isEmpty()) {
            return;
        }
        int index = CustomerListView.getSelectionModel().getSelectedIndex();
        CustomerListView.getItems().set(index, customer);
        CustomerField.setText("");
        CustomerListView.setDisable(false);
        editMode.set(false);

    }

    @FXML
    void onClickSaveButton(ActionEvent event) {
        ArrayList<String> customerList = new ArrayList<>(CustomerListView.getItems());

        CustomerListView.getItems();
        Storage.writeCities(customerList);
    }

    @FXML
    void onMouseClickedCustomerListView(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String selected = CustomerListView.getSelectionModel().getSelectedItem();
            CustomerField.setText(selected);
            CustomerListView.setDisable(true);
            editMode.set(true);

        }

    }

}
