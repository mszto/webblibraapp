package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person implements Data{

    private final SimpleStringProperty sName;
    private final SimpleStringProperty fName;
    private final SimpleIntegerProperty id;

    public Person() {

        id = null;
        fName = null;
        sName = null;
    }

    public Person(String fName, String sName) {
        this.fName=new SimpleStringProperty(fName);
        this.sName=new SimpleStringProperty(sName);
        id=null;
    }

    public Person(String fName, int sName) {
        this.id=new SimpleIntegerProperty(sName);
        this.fName=new SimpleStringProperty(fName);
        this.sName=null;
    }

    public String getfName() {
        return fName.get();
    }

    public SimpleStringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }



    public String getsName() {
        return sName.get();
    }

    public SimpleStringProperty sNameProperty() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName.set(sName);
    }



    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }



    public Person(String fName, String sName, int id) {
        this.fName = new SimpleStringProperty(fName);
        this.sName = new SimpleStringProperty(sName);
        this.id = new SimpleIntegerProperty(id);
    }


    @Override
    public Data createData(String fName, String sName, String title) {
        return null;
    }

    @Override
    public Data createData(String fName, String sName) {
        return new Person(fName,sName);
    }

    @Override
    public Data createData(String fName, int sName) {
        return new Person(fName,sName);
    }

    @Override
    public Data createData(String fName) {
        return null;
    }

    @Override
    public Person createData(String fName, String sName, int id) {
        return new Person(fName, sName,id);
    }


}
