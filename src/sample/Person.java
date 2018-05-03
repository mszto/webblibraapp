package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by dkoby on 26.04.2018.
 */
public class Person {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty title;

    Person(String fName, String sName, String title){
        this.firstName=new SimpleStringProperty(fName);
        this.secondName=new SimpleStringProperty(sName);
        this.title=new SimpleStringProperty(title);

    }
    Person(String fName, String sName){
        this.firstName=new SimpleStringProperty(fName);
        this.secondName=new SimpleStringProperty(sName);
        this.title=new SimpleStringProperty(null);
    }

    Person(String titles){
        this.title=new SimpleStringProperty(titles);
        this.firstName=new SimpleStringProperty(null);
        this.secondName=new SimpleStringProperty(null);
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
}
