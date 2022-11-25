package com.example.actualsms;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Subjects {
    public SimpleIntegerProperty id;
    public StringProperty Name;
   public SimpleIntegerProperty numStudents;
    public StringProperty Teacher;


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public int getNumStudents() {
        return numStudents.get();
    }

    public SimpleIntegerProperty numStudentsProperty() {
        return numStudents;
    }

    public String getTeacher() {
        return Teacher.get();
    }

    public StringProperty teacherProperty() {
        return Teacher;
    }



    public Subjects(int id, String name, int numStudents, String teacher) {
        this.id =new SimpleIntegerProperty(id);
        this.Name= new SimpleStringProperty(name);
        this.numStudents=new SimpleIntegerProperty(numStudents);
        this.Teacher=new SimpleStringProperty(teacher);
    }
    public Subjects(int id, String name) {
        this.id =new SimpleIntegerProperty(id);
        this.Name= new SimpleStringProperty(name);

    }
}
