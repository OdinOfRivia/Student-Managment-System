package com.example.actualsms;
import java.sql.*;
import java.util.List;



// Create a database connection.

public class DatabaseConnection {
    private String DatabaseConnectionString = "jdbc:sqlite:applicationDb.db";

    private Connection conn = null;

    public DatabaseConnection() {
        connect();
    }

    public List<Student> GetAllStudents() {
        String sql = "SELECT * FROM Students;";
        return null;
    }


    public List<Teacher> GetAllTeachers() {
        String sql = "SELECT * FROM Teachers;";
        return null;
    }
    public boolean isandid(int m) throws SQLException {
        try{
        String sql2 = "SELECT * from Students WHERE StudentID=" + m + ";";
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery(sql2);
        if(rs.next()==true){
            return true;

        }
        conn.close();
        return false;}
        catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public void InsertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO Students(StudentID, FirstName, LastName, Email, PhoneNumber, Country,  Password,age) VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, student.id.getValue());
            pstmt.setString(2, student.fn.getValue());
            pstmt.setString(3, student.ln.getValue());
            pstmt.setString(4, student.email.getValue());
            pstmt.setString(5, student.phone.getValue());
            pstmt.setString(6, student.Country.getValue());
            pstmt.setString(7, student.password.getValue());
            pstmt.setInt(8, student.age.getValue());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            conn.close();

        }
    }

    public void InsertTeacher(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO Teachers(TeacherID, FirstName, LastName, Email, PhoneNumber, Country,  Password,age) VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, teacher.id);
            pstmt.setString(2, teacher.fn);
            pstmt.setString(3, teacher.ln);
            pstmt.setString(4, teacher.email);

            pstmt.setString(5, teacher.phone);

            pstmt.setString(6, teacher.Country);
            pstmt.setString(7, teacher.password);
            pstmt.setInt(8, teacher.age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            conn.close();
        }

    }



        public boolean Login(String m, String inputPassword, boolean isTeacher) throws SQLException {
        try {

            String sql2 = "SELECT * from Teachers WHERE email='" + m + "' and Password='" + inputPassword+ "';";
            String sql3 = "SELECT * from Students WHERE email='" + m + "' and Password='" + inputPassword+ "';";

            System.out.println(String.format("Username %s with Password %s logging in...", m, inputPassword));
            System.out.println(String.format("Is Teacher?.. " +isTeacher));

            Statement stmt = conn.createStatement();
            ResultSet rs;

            if (isTeacher) {
                rs = stmt.executeQuery(sql2);
            } else {
                rs = stmt.executeQuery(sql3);
            }
            if (rs.next() == false) {
                System.out.println(String.format("Username %s with Password %s failed to login...", m, inputPassword));
                return false;
            }

            System.out.println(String.format("%s successfully logged in", m));
            conn.close();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void deleteStudent(int id) throws SQLException {
        String sql2 = "DELETE from Students WHERE StudentID=" + id + ";";
        try {
            Statement stmt = conn.createStatement();
            int y=stmt.executeUpdate(sql2);
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
    public void deleteSubject (int Subjectid){
        String sql2 = "DELETE from Subjects WHERE SubjectId=" + Subjectid + ";";
        try {
            Statement stmt = conn.createStatement();
            int y=stmt.executeUpdate(sql2);
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void edditStudent(int id, String fn, String ln , String email, String phone, String Country, int age) throws SQLException {




        try {
            String sql2 = "UPDATE Students SET  StudentID=?, FirstName=?, LastName=?, Email=?, PhoneNumber=?, Country=?, age=? where StudentID=?;";
            PreparedStatement bullshit =conn.prepareStatement(sql2);
            bullshit.setInt(1, id);
            bullshit.setString(2, fn);
            bullshit.setString(3, ln);
            bullshit.setString(4, email);
            bullshit.setString(5, phone);
            bullshit.setString(6, Country);
            bullshit.setInt(7, age);
            bullshit.setInt(8, id);
            bullshit.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
    public void InsertSubject(Subjects subjects) throws SQLException {
        try {
        String sql ="INSERT INTO Subjects(SubjectId, SubjectName, numStudents, Teacher)VALUES(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,subjects.id.getValue());
        pstmt.setString(2, subjects.Name.getValue());
        pstmt.setInt(3,subjects.numStudents.getValue());
        pstmt.setString(4,subjects.Teacher.getValue());
        pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            conn.close();

        }
    }
    public boolean isaClassId(int m) throws SQLException {
        try{
        String sql2 = "SELECT * from Subjects WHERE SubjectId=" + m + ";";
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery(sql2);
        if(rs.next()==true){
            return true;

        }
        conn.close();
        return false;}
        catch (Exception e) {
            System.out.println(e);
        }


        return false;
    }
    public void edditSubjectGrade(int id, String Class, int grade ) throws SQLException {
        try {
        String sql2 ="update Students SET "+ Class +"=? where StudentID=?;";
        PreparedStatement bullshit =conn.prepareStatement(sql2);
        bullshit.setInt(1, grade);
        bullshit.setInt(2, id);
        bullshit.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();

        }



    }








    public  void connect() {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(DatabaseConnectionString);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        }

        public void disconnect(){

        conn=null;
        }
    }
