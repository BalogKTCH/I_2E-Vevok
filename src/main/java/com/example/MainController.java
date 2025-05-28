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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class MainController {



    @FXML
    private Button addButton;

    @FXML
    private TextField customerField;

    @FXML
    private ListView<String> customerListView;

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
        customerListView.getItems().addAll(customerList);

        addButton.disableProperty().bind(editMode);
        deleteButton.disableProperty().bind(editMode);
        saveButton.disableProperty().bind(editMode);
        modifyButton.disableProperty().bind(editMode.not());
    }


    @FXML
    void onClickAddButton(ActionEvent event) {
        String customer = customerField.getText();
    
        customerListView.getItems().add(customer);
        customerField.setText("");
    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {
        int index = customerListView.getSelectionModel().getSelectedIndex();
        
        customerListView.getItems().remove(index);
    }

    @FXML
    void onClickModifyButton(ActionEvent event) {
        String customer = customerField.getText();
        if (customer.isEmpty()) {
            return;
        }
        int index = customerListView.getSelectionModel().getSelectedIndex();
        customerListView.getItems().set(index, customer);
        customerField.setText("");
        customerListView.setDisable(false);
        editMode.set(false);

    }

    @FXML
    void onClickSaveButton(ActionEvent event) {
        ArrayList<String> customerList = new ArrayList<>(customerListView.getItems());

        customerListView.getItems();
        Storage.writeCities(customerList);
    }

    @FXML
    void onMouseClickedCustomerListView(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String selected = customerListView.getSelectionModel().getSelectedItem();
            customerField.setText(selected);
            customerListView.setDisable(true);
            editMode.set(true);

        }

    }

}
