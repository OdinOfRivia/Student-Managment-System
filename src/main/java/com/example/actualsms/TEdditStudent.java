package com.example.actualsms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

public class TEdditStudent implements Initializable {


    @FXML
    private TableColumn<Student, Integer> ID;

    @FXML
    private TableColumn<Student, String> fName;

    @FXML
    private TableColumn<Student, String> ln;

    @FXML
    private TextField studentID;

    @FXML
    private TableView<Student> table;



    @FXML
    void backBTN(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("tStudentMenu.fxml");

    }

    @FXML
    void deleteBTN(ActionEvent event) throws SQLException {
        try{
        DatabaseConnection conn = new DatabaseConnection();

        int y=  Integer.valueOf(studentID.getText());

            if(!studentID.getText().isEmpty() && conn.isandid(y)){
                conn.deleteStudent(y);
                conn.disconnect();

            }else{

            }}
        catch (Exception e) {
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
        this.ln.setCellValueFactory(cellData -> cellData.getValue().lnProperty());
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

    public TEdditStudent() { }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connect();
        handleDisplayTables();


    }
}
