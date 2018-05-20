package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Borrows implements Data{
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty title;


    Borrows(String fName, String sName, String title){
        this.firstName=new SimpleStringProperty(fName);
        this.secondName=new SimpleStringProperty(sName);
        this.title=new SimpleStringProperty(title);


    }

    Borrows(String fName, String sName, int id){
        this.firstName=new SimpleStringProperty(fName);
        this.secondName=new SimpleStringProperty(sName);
        this.title = null;

    }
    Borrows(String fName, String sName){
        this.firstName=new SimpleStringProperty(fName);
        this.secondName=new SimpleStringProperty(sName);
        this.title=new SimpleStringProperty(null);

    }

    Borrows(String titles){
        this.title=new SimpleStringProperty(titles);
        this.firstName=new SimpleStringProperty(null);
        this.secondName=new SimpleStringProperty(null);
    }

    public Borrows() {

        title = null;
        secondName = null;
        firstName = null;
    }

    public String getFirstName(){
        return firstName.get();
    }

    public void setFirstName(String fname){
        this.firstName.set(fname);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public SimpleStringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public Data createData(String fName, String sName, String title) {
        return new Borrows(fName,sName,title);
    }

    @Override
    public Data createData(String fName, String sName) {
        return new Borrows(fName,sName);
    }

    @Override
    public Data createData(String fName) {
        return new Borrows(fName);
    }

    @Override
    public Data createData(String fName, String sName, int id) {
        return null;
    }
}

