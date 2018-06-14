package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Borrows implements Data{
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty title;
    private final SimpleIntegerProperty ilosc;


    Borrows(String fName, String sName, String title){
        this.firstName=new SimpleStringProperty(fName);
        this.secondName=new SimpleStringProperty(sName);
        this.title=new SimpleStringProperty(title);
        this.ilosc=null;
    }

    Borrows(String fName, String sName, String title, int ile){
        this.firstName=new SimpleStringProperty(fName);
        this.secondName=new SimpleStringProperty(sName);
        this.title=new SimpleStringProperty(title);
        this.ilosc=new SimpleIntegerProperty(ile);
    }

    Borrows(String fName, String sName, int ile){
        this.firstName=new SimpleStringProperty(fName);
        this.secondName=new SimpleStringProperty(sName);
        this.title = null;
        this.ilosc=new SimpleIntegerProperty(ile);

    }

    Borrows(String fName, int sName){
        this.firstName=new SimpleStringProperty(null);
        this.secondName=new SimpleStringProperty(null);
        this.title=new SimpleStringProperty(fName);
        this.ilosc=new SimpleIntegerProperty(sName);
    }

    Borrows(String fName, String sName){
        this.firstName=new SimpleStringProperty(fName);
        this.secondName=new SimpleStringProperty(sName);
        this.title=new SimpleStringProperty(null);
        this.ilosc=new SimpleIntegerProperty(-1);
    }
    Borrows(String titles){
        this.title=new SimpleStringProperty(titles);
        this.firstName=new SimpleStringProperty(null);
        this.secondName=new SimpleStringProperty(null);
        this.ilosc=new SimpleIntegerProperty(-1);
    }

    public Borrows() {

        title = null;
        secondName = null;
        firstName = null;
        ilosc=null;
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
    public Data createData(String fName, int sName) {
        return new Borrows(fName,sName);
    }

    @Override
    public Data createData(String fName) {
        return new Borrows(fName);
    }

    @Override
    public Data createData(String fName, String sName, int id) {
        return new Borrows(fName,sName,id);
    }


    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public int getIlosc() {
        return ilosc.get();
    }

    public SimpleIntegerProperty iloscProperty() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc.set(ilosc);
    }
}

