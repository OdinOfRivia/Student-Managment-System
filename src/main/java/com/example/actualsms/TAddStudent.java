package com.example.actualsms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;

public class TAddStudent {

    @FXML
    private TextField LastName = new TextField("kok");

    @FXML
    private TextField age;

    @FXML
    private TextField country;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private PasswordField passWord;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField studentID;

    @FXML
    void backBTN(ActionEvent event) throws IOException {
        HelloApplication  m = new HelloApplication();
        m.changeScene("tStudentMenu.fxml");
    }

    @FXML
    void enterStudentBTN(ActionEvent event) throws SQLException {
        DatabaseConnection conn = new DatabaseConnection();
        Student s1 = new Student(
                Integer.valueOf(studentID.getText()),
                firstName.getText(),
                LastName.getText(),
                Integer.valueOf(age.getText()),
                email.getText(),
                phoneNumber.getText(),
                country.getText(),
                passWord.getText());

        conn.InsertStudent(s1);

    }




}
