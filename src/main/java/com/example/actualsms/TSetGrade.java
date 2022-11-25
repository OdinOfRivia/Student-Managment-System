

package com.example.actualsms;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.*;
        import java.util.ResourceBundle;

public class TSetGrade implements Initializable {

    @FXML
    private TableColumn<Student, Integer> anthID;

    @FXML
    private Button applyBTN;

    @FXML
    private TableColumn<Student, Integer> cpscID;

    @FXML
    private TableColumn<Student, Integer> englID;

    @FXML
    private TextField fieldClass;

    @FXML
    private TextField fieldGrade;

    @FXML
    private TextField fieldID;

    @FXML
    private TableColumn<Student, String> fn;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, Integer> mathID;

    @FXML
    private TableColumn<Student, Integer> phyID;

    @FXML
    private TableColumn<Student, Integer> physcologyID;

    @FXML
    private TableColumn<Student, Integer> statsID;

    @FXML
    private TableView<Student> table;

    @FXML
    void backBTN(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("tCheckSubjects.fxml");
    }

    private Connection conn = null;
    public static ResultSet rs;
    private ObservableList<Student> studentList;
    @FXML
    void applyeddit(ActionEvent event) throws SQLException {
        try{
        DatabaseConnection conn = new DatabaseConnection();
        int idcl=Integer.valueOf(fieldID.getText());
        int grade2=Integer.valueOf(fieldGrade.getText());
        if (!fieldID.getText().isEmpty()&& conn.isandid(idcl)&&!fieldGrade.getText().isEmpty()){
            conn.edditSubjectGrade(idcl,fieldClass.getText(),grade2);
            conn.disconnect();



        }

        }catch(Exception e){
            System.out.println(e);
        }
        finally {
            conn.close();

        }


    }


    private void handleDisplayTables() throws NullPointerException {
        try {

            String query = "SELECT * FROM Students";
            PreparedStatement statement = conn.prepareStatement(query);
            rs = statement.executeQuery();
            this.studentList = FXCollections.observableArrayList();

            while (rs.next()) {
                int StudentID = rs.getInt("StudentID");
                String firstName = rs.getString("FirstName");
                int Math201=rs.getInt("Math201");
                int Phy102Grade=rs.getInt("Phy102");
                int ENGL161Grade=rs.getInt("Engl210");
                int CPSC121Grade=rs.getInt("CPSC121");
                int Stats251Grade=rs.getInt("Stats241");
                int ANTH102Grade=rs.getInt("Anth311");
                int PSYC130Grade=rs.getInt("Phyc130");

                this.studentList.add(new Student(StudentID,firstName,Math201,Phy102Grade, ENGL161Grade,CPSC121Grade, Stats251Grade,ANTH102Grade, PSYC130Grade  ));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        this.id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        this.fn.setCellValueFactory(cellData -> cellData.getValue().fnProperty());
        this.mathID.setCellValueFactory(cellData -> cellData.getValue().MathGrade.asObject());
        this.phyID.setCellValueFactory(cellData -> cellData.getValue().Phy102Grade.asObject());
        this.englID.setCellValueFactory(cellData -> cellData.getValue().ENGL161Grade.asObject());
        this.cpscID.setCellValueFactory(cellData -> cellData.getValue().CPSC121Grade.asObject());
        this.statsID.setCellValueFactory(cellData -> cellData.getValue().Stats251Grade.asObject());
        this.anthID.setCellValueFactory(cellData -> cellData.getValue().ANTH102Grade.asObject());
        this.physcologyID.setCellValueFactory(cellData -> cellData.getValue().PSYC130Grade.asObject());
        this.table.setItems(null);
        this.table.setItems(studentList);

        table.refresh();
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

    public TSetGrade() { }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect();
        handleDisplayTables();

    }




}
