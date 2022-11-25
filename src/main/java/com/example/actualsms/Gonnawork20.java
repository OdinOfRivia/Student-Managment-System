package com.example.actualsms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Gonnawork20 implements Initializable {
    @FXML
    private TextField Country;

    @FXML
    private TableColumn<Student, Integer> ID;

    @FXML
    private TextField age;

    @FXML
    private TextField email;

    @FXML
    private TableColumn<Student, String> fName;

    @FXML
    private TextField fin;

    @FXML
    private TextField id1;

    @FXML
    private TextField ln;

    @FXML
    private TableColumn<Student, String> ln1;

    @FXML
    private TextField phone;

    @FXML
    private TableView<Student> table;




    @FXML
    void back(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("tStudentMenu.fxml");

    }

    @FXML
    void edditthe(ActionEvent event) throws SQLException {
        try{
        DatabaseConnection conn = new DatabaseConnection();
        int ID2=  Integer.valueOf(id1.getText());
        int age3= Integer.valueOf(age.getText());

        if(!ID.getText().isEmpty() && conn.isandid(ID2)){
            conn.edditStudent(ID2, fin.getText(), ln.getText(), email.getText(),phone.getText(), Country.getText(), age3);
            conn.disconnect();

        } else {


        }}catch (Exception e) {
            System.out.println(e);
        }




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

                int StudentID = rs.getInt("StudentID");

                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                this.studentList.add(new Student(StudentID,firstName,lastName));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("before");

        this.fName.setCellValueFactory(cellData -> cellData.getValue().fnProperty());
        this.ln1.setCellValueFactory(cellData -> cellData.getValue().lnProperty());
        this.ID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        this.table.setItems(null);
        this.table.setItems(studentList);

        table.refresh();
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

    public Gonnawork20() { }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connect();
        handleDisplayTables();
}

    }

