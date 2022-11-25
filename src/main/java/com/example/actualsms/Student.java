package com.example.actualsms;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Stack;
import java.util.StringJoiner;

public class Student {
    public SimpleIntegerProperty id;
    public StringProperty fn;
    public StringProperty ln;
    public SimpleIntegerProperty age;
    public StringProperty email;
    public StringProperty phone;
    public StringProperty Country;
    public StringProperty password;
    public static  int numStudent;
    public Stack<Object> Classes;
    public SimpleIntegerProperty MathGrade;
    public SimpleIntegerProperty Phy102Grade;
    public SimpleIntegerProperty ENGL161Grade;
    public SimpleIntegerProperty CPSC121Grade;
    public SimpleIntegerProperty Stats251Grade;
    public SimpleIntegerProperty ANTH102Grade;
    public SimpleIntegerProperty PSYC130Grade;




    public Student(int id, String fn, String ln, int age, String email, String phone, String country, String password) {
        this.id= new SimpleIntegerProperty(id);
        this.fn = new SimpleStringProperty(fn);
        this.ln= new SimpleStringProperty(ln);
        this.age = new SimpleIntegerProperty(age);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.password= new SimpleStringProperty(password);
        Country = new SimpleStringProperty(country);
        numStudent=numStudent+1;
        Stack<Object> stack23 = new Stack<Object>();
        Classes=stack23;
    }
    public Student (int id, String fn, String ln){
        this.id=new SimpleIntegerProperty(id);
        this.fn = new SimpleStringProperty(fn);
        this.ln= new SimpleStringProperty(ln);

    }

    public Student (int id, String fn, int Math201, int Phy102Grade, int ENGL161Grade, int CPSC121Grade, int Stats251Grade, int ANTH102Grade,int PSYC130Grade)
 {
        this.id=new SimpleIntegerProperty(id);
        this.fn = new SimpleStringProperty(fn);
        this.MathGrade=new SimpleIntegerProperty(Math201);
        this.Phy102Grade=new SimpleIntegerProperty(Phy102Grade);
        this.ENGL161Grade=new SimpleIntegerProperty(ENGL161Grade);
        this.CPSC121Grade=new SimpleIntegerProperty(CPSC121Grade);
        this.Stats251Grade=new SimpleIntegerProperty(Stats251Grade);
        this.ANTH102Grade=new SimpleIntegerProperty(ANTH102Grade);
        this.PSYC130Grade=new SimpleIntegerProperty(PSYC130Grade);

    }
    public void addClasses(Object e){
        Classes.add(e);

    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public StringProperty fnProperty() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn.set(fn);
    }

    public StringProperty lnProperty() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln.set(ln);
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty countryProperty() {
        return Country;
    }

    public void setCountry(String country) {
        this.Country.set(country);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public static int getNumStudent() {
        return numStudent;
    }

    public static void setNumStudent(int numStudent) {
        Student.numStudent = numStudent;
    }

    public Stack<Object> getClasses() {
        return Classes;
    }

    public void setClasses(Stack<Object> classes) {
        Classes = classes;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("fn=" + fn)
                .add("ln=" + ln)
                .add("age=" + age)
                .add("email=" + email)
                .add("phone=" + phone)
                .add("Country=" + Country)
                .add("Classes=" + Classes)
                .add("password=" + password)
                .toString();
    }
}