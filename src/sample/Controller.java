package sample;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

import java.sql.ResultSet;

import java.util.ArrayList;

import java.util.List;

public class Controller extends AddToScroll {

    @FXML
    private Pane mainPane;
    public Button wypoz;
    public  Button os;
    public ListView<Label> listView;

    public Controller() {

        super();

    }




    public void wypo(ActionEvent event) {
        mainPane.getChildren().clear();
        TableView<Borrows> tablePerson = new TableView<>();

        TextField name = new TextField();
        TextField sName = new TextField();
        TextField title = new TextField();
        Button searchButoon = new Button("szukaj");
        Button deleteButton = new Button("usun");
        Button addButton = new Button("Dodaj");

        tablePerson.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        mainPane.getChildren().removeAll();
        mainPane.getChildren().add(name);
        mainPane.getChildren().add(searchButoon);
        mainPane.getChildren().add(sName);
        mainPane.getChildren().add(title);
        mainPane.getChildren().add(deleteButton);
        mainPane.getChildren().add(addButton);
        mainPane.getChildren().addAll(tablePerson,os,wypoz);

        tablePerson.setMinSize(400, 900);
        tablePerson.setLayoutY(50);

        sName.setPromptText("nazwisko");
        sName.setLayoutY(240);
        sName.setLayoutX(500);

        name.setPromptText("imie");
        name.setLayoutX(500);
        name.setLayoutY(200);

        title.setPromptText("tytuł");
        title.setLayoutX(500);
        title.setLayoutY(280);

        searchButoon.setLayoutX(750);
        searchButoon.setLayoutY(200);

        addButton.setLayoutX(500);
        addButton.setLayoutY(100);

        deleteButton.setLayoutY(100);
        deleteButton.setLayoutX(450);
        showTableView(tablePerson, "firstName", "secondName", "title", "imie", "nazwisko", "tytul",new Borrows());


        searchButoon.setOnAction((ActionEvent event1) -> {


            if (sName.getText().trim().isEmpty() && name.getText().trim().isEmpty() && title.getText().trim().isEmpty()) {
                r = baseData.getData("SELECT  osoby.imie, osoby.nazwisko, ksiazki.tytul from wypozyczenia " +
                        "join osoby on wypozyczenia.id_osoby=osoby.id_o " +
                        "join ksiazki on wypozyczenia.id_ksiazki=ksiazki.id;");
            } else {
                r = baseData.getData("SELECT  o.imie, o.nazwisko, k.tytul from wypozyczenia w " +
                        "join osoby o on w.id_osoby=o.id_o " +
                        "join ksiazki k on w.id_ksiazki=k.id " +
                        "where o.imie='" + name.getText() + "' or o.nazwisko='" + sName.getText() + "' or k.tytul='" + title.getText() + "' ;");
            }

            showTableView(tablePerson, "firstName", "secondName", "title", "imie", "nazwisko", "tytul",new Borrows());

        });

        deleteButton.setOnAction(event1 -> {
            ObservableList<Borrows> list;
            list = tablePerson.getSelectionModel().getSelectedItems();

            for (Borrows p : list) {
                baseData.delteWyp("DELETE w from wypozyczenia w " +
                        "join osoby o on o.id_o=w.id_osoby " +
                        "join ksiazki k on k.id=w.id_ksiazki " +
                        "WHERE o.imie= ? and o.nazwisko= ? and k.tytul= ?", p.getFirstName(), p.getSecondName(), p.getTitle());
                showTableView(tablePerson, "firstName", "secondName", "title", "imie", "nazwisko", "tytul",new Borrows());
            }

        });


        addButton.setOnAction((ActionEvent event1) -> {
        AddwypWindow o=new AddwypWindow();
        o.AddwypWindowShow();
        });
    }


    public void oso(ActionEvent event) {
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(os,wypoz);
        TableView<Person> personsTable=new TableView<>();
        TextField name = new TextField();
        TextField sName = new TextField();
        TextField addName = new TextField();
        TextField addsName = new TextField();
        Button searchPerson=new Button("Szukaj");
        Button addPerson= new Button("Dodaj");

        sName.setPromptText("nazwisko");
        sName.setLayoutY(240);
        sName.setLayoutX(500);

        name.setPromptText("imie");
        name.setLayoutX(500);
        name.setLayoutY(200);

        addsName.setPromptText("nazwisko");
        addsName.setLayoutY(600);


        addName.setPromptText("imie");
        addName.setLayoutX(200);
        addName.setLayoutY(600);

        addPerson.setLayoutY(600);
        addPerson.setLayoutX(400);

        searchPerson.setLayoutX(500);
        searchPerson.setLayoutY(300);

        personsTable.setMinSize(300,500);
        personsTable.setLayoutY(50);
        personsTable.setEditable(true);

        showTableViewEdit(personsTable,"fName","sName","id","imię","nazwisko","id_o",new Person());
        mainPane.getChildren().addAll(personsTable,searchPerson,name,sName,addName,addsName,addPerson);
        searchPerson.setOnAction((ActionEvent event1) -> {
            if (name.getText().trim().isEmpty() && sName.getText().trim().isEmpty()) {
                r = baseData.getData("Select imie, nazwisko, id_o from osoby;");
            } else {
                r = baseData.getData("Select imie, nazwisko, id_o from osoby where imie='" + name.getText() + "' or nazwisko='" + sName.getText() + "';");
            }


            showTableViewEdit(personsTable,"fName","sName","id","imie","nazwisko","id_o",new Person());
        });


    }
}
