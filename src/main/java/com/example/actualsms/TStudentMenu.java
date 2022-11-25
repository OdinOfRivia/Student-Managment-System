package com.example.actualsms;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TStudentMenu implements Initializable {

    @FXML
    private TableColumn<Student,Integer> age;
    @FXML
    private TableColumn<Student,Integer> id;
    @FXML
    private TableColumn<Student, String> country;
    @FXML
    private TableColumn<Student, String> phoneNumber;
    @FXML
    private TableColumn<Student, String> email;
    @FXML
    private TableColumn<Student, String> firstName;
    @FXML
    private TableColumn<Student, String> lastName;
    @FXML
    private TableColumn<Student, String> password;

    @FXML
    private TableView<Student> tableID;


    @FXML
    void addStudentBTN(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("tAddStudent.fxml");
    }

    @FXML
    void backBTN(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("TeacherMain.fxml");
    }

    @FXML
    void edditStudentBTN(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("tEdditStudent.fxml");
    }
    @FXML
    void gonnawork(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("gonnawork2.0.fxml");

    }

    private Connection conn = null;
    public static ResultSet rs;
    private ObservableList<Student> studentList;


    private void handleDisplayTables() throws NullPointerException {
        try {

            String query = "SELECT * FROM Students";
            PreparedStatement statement = conn.prepareStatement(query);
            rs = statement.executeQuery();
            this.studentList = FXCollections.observableArrayList();

            while (rs.next()) {
                int age = rs.getInt("age");
                int StudentID = rs.getInt("StudentID");
                String country = rs.getString("Country");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                String phone = rs.getString("PhoneNumber");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                this.studentList.add(new Student(StudentID,firstName,lastName,age,email,phone,country,password));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        this.age.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        this.country.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        this.email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        this.firstName.setCellValueFactory(cellData -> cellData.getValue().fnProperty());
        this.lastName.setCellValueFactory(cellData -> cellData.getValue().lnProperty());
        this.phoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        this.id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        this.tableID.setItems(null);
        this.tableID.setItems(studentList);

        tableID.refresh();
    }

    public  void connect() {
        try {
            // create a connection to the database
            String databaseConnectionString = "jdbc:sqlite:applicationDb.db";
            conn = DriverManager.getConnection(databaseConnectionString);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public TStudentMenu() { }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect();
        handleDisplayTables();

    }
}

