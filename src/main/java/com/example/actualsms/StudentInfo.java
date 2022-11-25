package com.example.actualsms;


import com.example.actualsms.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class StudentInfo {

    @FXML
    private Label Country;

    @FXML
    private Label FirstName;

    @FXML
    private Label FirstName1;

    @FXML
    private Label FirstName11;

    @FXML
    private Label LastName;

    @FXML
    private Label email;

    @FXML
    private TextField id2;



    @FXML
    void back(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("StudentMain.fxml");

    }

    @FXML
    void enter(ActionEvent event) {
        String mw=id2.getText();



    }






}
