package com.example.actualsms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class TAddSubjects {
    @FXML
    private TextField ClassId;

    @FXML
    private TextField Teacher;

    @FXML
    private TextField name23;

    @FXML
    private TextField numStudents;
    @FXML
    void backBTN(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("tCheckSubjects.fxml");

    }
    @FXML
    void apply(ActionEvent event) throws SQLException {
        try{
        DatabaseConnection conn=new DatabaseConnection();
        Subjects s1 =new Subjects(Integer.valueOf(ClassId.getText()),
                name23.getText(),
                Integer.valueOf(numStudents.getText()),
                Teacher.getText()
                );
        conn.InsertSubject(s1);
        conn.disconnect();}
        catch (Exception e) {
            System.out.println(e);
        }


    }

}
