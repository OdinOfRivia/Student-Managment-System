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

public class SubjectDelete implements Initializable {

    @FXML
    private TableColumn<Subjects, String> classNameID;

    @FXML
    private TableColumn<Subjects,Integer> id;

    @FXML
    private TableView<Subjects> tableID;
    @FXML
    private TextField idtext;



    @FXML
    void backBTN(ActionEvent event) throws IOException {
            HelloApplication m = new HelloApplication();
            m.changeScene("tCheckSubjects.fxml");
    }

    @FXML
    void deleteBTN(ActionEvent event) throws SQLException {
        int y= Integer.valueOf(idtext.getText());
        DatabaseConnection conn = new DatabaseConnection();
        if(!idtext.getText().isEmpty() && conn.isaClassId(y)){
            conn.deleteSubject(y);
            conn.disconnect();

        }else{

        }

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


                this.SubjectsList.add(new Subjects(SubjectID,SubjectName));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        this.classNameID.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());


        this.tableID.setItems(null);
        this.tableID.setItems(SubjectsList);

        tableID.refresh();
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
    public SubjectDelete () { }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connect();
        handleDisplayTables();


    }

}
