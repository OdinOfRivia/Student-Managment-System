package com.example.actualsms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TCheckSubjects implements Initializable {
    @FXML
    private TableColumn<Subjects,Integer > SubjectId;

    @FXML
    private TableColumn<Subjects, String> SubjectName;

    @FXML
    private TableColumn<Subjects, String> Teacher;

    @FXML
    private TableColumn<Subjects, Integer> num;

    @FXML
    private TableView<Subjects> table2;



    @FXML
    void addSubjectBTN(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("tAddSubjects.fxml");
    }

    @FXML
    void backBTN(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("TeacherMain.fxml");
    }

    @FXML
    void setGradeBTN(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("tSetGrade.fxml");

    }
    @FXML
    void deleteSubject(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("subject Delete.fxml");
    }
    private Connection conn = null;
    public static ResultSet rs;
    private ObservableList<Subjects> SubjectsList;
    private void handleDisplayTables() throws NullPointerException {
        try {

            String query = "SELECT * FROM Subjects";
            PreparedStatement statement = conn.prepareStatement(query);
            rs = statement.executeQuery();
            this.SubjectsList = FXCollections.observableArrayList();

            while (rs.next()) {

                int SubjectID = rs.getInt("SubjectID");
                String SubjectName = rs.getString("SubjectName");
                String Teacher = rs.getString("Teacher");
                int num = rs.getInt("numStudents");


                this.SubjectsList.add(new Subjects(SubjectID,SubjectName,num, Teacher));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        this.SubjectName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.Teacher.setCellValueFactory(cellData -> cellData.getValue().teacherProperty());
        this.SubjectId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        this.num.setCellValueFactory(cellData -> cellData.getValue().numStudentsProperty().asObject());

        this.table2.setItems(null);
        this.table2.setItems(SubjectsList);

        table2.refresh();
    }
    public  void connect() {
        try {
            //create a connection to the database
            String databaseConnectionString = "jdbc:sqlite:applicationDb.db";
            conn = DriverManager.getConnection(databaseConnectionString);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public TCheckSubjects() { }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connect();
        handleDisplayTables();


    }

}
