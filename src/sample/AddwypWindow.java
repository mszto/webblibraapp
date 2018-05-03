package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkoby on 26.04.2018.
 */
public class AddwypWindow extends AddToScroll {

    // private ResultSet r;


    public AddwypWindow() {
        super();

    }

    public void AddwypWindowShow() {
        Stage newWindow = new Stage();
        Pane secondPane = new Pane();
        Scene secondScene = new Scene(secondPane, 950, 600);
        TableView persons=new TableView();
        TextField nameTextField = new TextField();
        TextField snameTextField = new TextField();
        Button saershPerson = new Button("szukaj");



        newWindow.setX(100);
        newWindow.setY(100);
        newWindow.setScene(secondScene);
        newWindow.show();

        snameTextField.setPromptText("nazwisko");
        snameTextField.setLayoutY(240);
        snameTextField.setLayoutX(330);

        nameTextField.setPromptText("imie");
        nameTextField.setLayoutX(330);
        nameTextField.setLayoutY(200);

        saershPerson.setLayoutX(330);
        saershPerson.setLayoutY(300);

        showTableView(persons,"firstName","secondName","imie","nazwisko");
        secondPane.getChildren().addAll(nameTextField, snameTextField, saershPerson,persons);

        saershPerson.setOnAction(szuk -> {

            if (nameTextField.getText().trim().isEmpty() && snameTextField.getText().trim().isEmpty()) {
                r = baseData.getData("Select imie, nazwisko from osoby;");
            } else {
                r = baseData.getData("Select imie, nazwisko from osoby where imie='" + nameTextField.getText() + "' or nazwisko='" + snameTextField.getText() + "';");
            }

            showTableView(persons,"firstName","secondName","imie","nazwisko");


                TableView books=new TableView();
                Button add = new Button("dodaj");
                Button saershBooks = new Button("szukaj");
                nameTextField.clear();
                nameTextField.setLayoutX(630);
                nameTextField.setPromptText("Tytuł");

                add.setLayoutX(630);
                add.setLayoutY(350);
                saershBooks.setLayoutY(300);
                saershBooks.setLayoutX(630);
                books.setLayoutX(300);
                add.setDisable(true);
                showTableView(books,"title","tytuł");

                secondPane.getChildren().addAll(saershBooks, add,books);


                saershBooks.setOnAction((ActionEvent event2) -> {
                    if (nameTextField.getText().trim().isEmpty()) {
                        r = baseData.getData("Select tytul from ksiazki");
                    } else {
                        r = baseData.getData("Select  tytul from ksiazki where tytul='" + nameTextField.getText() + "';");
                    }
                    secondPane.getChildren().addAll(saershBooks, add,books);

                    if(books.getSelectionModel().isEmpty()){
                        add.setDisable(false);
                    }

                });






        });
    }
}
