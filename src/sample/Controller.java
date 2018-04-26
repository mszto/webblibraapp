package sample;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

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

    public Controller() {
        super();
    }




    public void wypo(ActionEvent event) {
        List<CheckBox> checkBoxes=new ArrayList<>();
        List<CheckBox> checkedBoxes=new ArrayList<>();
         Pane d = new Pane();
         TextField name = new TextField();
         TextField sName=new TextField();
         TextField title=new TextField();
         ScrollPane scrollPane = new ScrollPane(d);
        Button searchButoon = new Button("szukaj");
        Button deleteButton=new Button("usun");
        Button addButton = new Button("Dodaj");
        List<List<String>> lista=new ArrayList<>();


        mainPane.getChildren().removeAll();
        mainPane.getChildren().add(d);
        mainPane.getChildren().add(name);
        mainPane.getChildren().add(scrollPane);
        mainPane.getChildren().add(searchButoon);
        mainPane.getChildren().add(sName);
        mainPane.getChildren().add(title);
        mainPane.getChildren().add(deleteButton);
        mainPane.getChildren().add(addButton);

        scrollPane.setLayoutY(100);
        scrollPane.setLayoutX(30);
        scrollPane.setPrefSize(400, 500);

        sName.setPromptText("nazwisko");
        sName.setLayoutY(240);
        sName.setLayoutX(scrollPane.getLayoutX()+500);

        name.setPromptText("imie");
        name.setLayoutX(scrollPane.getLayoutX() + 500);
        name.setLayoutY(200);

        title.setPromptText("tytuł");
        title.setLayoutX(scrollPane.getLayoutX()+500);
        title.setLayoutY(280);

        searchButoon.setLayoutX(750);
        searchButoon.setLayoutY(200);

        addButton.setLayoutX(500);
        addButton.setLayoutY(100);

        deleteButton.setLayoutY(100);
        deleteButton.setLayoutX(450);





        searchButoon.setOnAction((ActionEvent event1) -> {

            checkBoxes.clear();

            if(sName.getText().trim().isEmpty() && name.getText().trim().isEmpty() && title.getText().trim().isEmpty()){
                r = baseData.getData("SELECT  osoby.imie, osoby.nazwisko, ksiazki.tytul from wypozyczenia " +
                        "join osoby on wypozyczenia.id_osoby=osoby.id_o " +
                        "join ksiazki on wypozyczenia.id_ksiazki=ksiazki.id;");
            }else{
                r = baseData.getData("SELECT  o.imie, o.nazwisko, k.tytul from wypozyczenia w " +
                        "join osoby o on w.id_osoby=o.id_o " +
                        "join ksiazki k on w.id_ksiazki=k.id " +
                        "where o.imie='"+name.getText()+"' or o.nazwisko='"+sName.getText()+"' or k.tytul='"+title.getText()+"' ;");
            }

            showScroll(checkBoxes,d,"imie","nazwisko","tytul");


            System.out.println(name.getText());
            for (int i=0;i< checkBoxes.size();i++){
                checkedBoxes.clear();
                CheckBox chBox=checkBoxes.get(i);
                chBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if(chBox.isSelected()) {
                        checkedBoxes.add(chBox);
                    }else {
                        checkedBoxes.remove(chBox);
                    }
                });
            }
        });

        for (int i=0;i< checkBoxes.size();i++){
            checkedBoxes.clear();
            CheckBox chBox=checkBoxes.get(i);
            chBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if(chBox.isSelected()) {
                    checkedBoxes.add(chBox);
                }else {
                    checkedBoxes.remove(chBox);
                }

            });
        }

        deleteButton.setOnAction(event1 -> {
            for(int i=0;i<checkedBoxes.size();i++){
                CheckBox chBox=checkedBoxes.get(i);
                String array[]=chBox.getText().split(" ",3);
                d.getChildren().remove(chBox);
                checkBoxes.removeIf(checkBox -> checkBox.equals(chBox));
                ArrayList<String> lis=new ArrayList<>();
                lis.add(array[0]);
                lis.add(array[1]);
                lis.add(array[2]);
                lista.removeIf(strings -> strings.equals(lis));

                baseData.delteWyp("DELETE w from wypozyczenia w " +
                        "join osoby o on o.id_o=w.id_osoby " +
                        "join ksiazki k on k.id=w.id_ksiazki " +
                        "WHERE o.imie= ? and o.nazwisko= ? and k.tytul= ?",lis.get(0),lis.get(1),lis.get(2));
            }

            int y=0;
            for (CheckBox chBox: checkBoxes) {
                chBox.setLayoutY(y+=40);
            }
        });




        addButton.setOnAction((ActionEvent event1) -> {

            Stage newWindow=new Stage();
            Pane secondPane=new Pane();
            Scene secondScene=new Scene(secondPane,950,600);
            Pane toSrollPane=new Pane();
            ScrollPane scrollPane1=new ScrollPane(toSrollPane);
            List<CheckBox> personsBox=new ArrayList<>();
            List<List<String>> PresonList=new ArrayList<>();
            List<CheckBox> personSelected= new ArrayList<>();
            TextField nameTextField=new TextField();
            TextField snameTextField=new TextField();
            Button saershPerson=new Button("szukaj");
            r=baseData.getData("Select imie, nazwisko from osoby");


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

            scrollPane1.setPrefSize(300,400);

            secondPane.getChildren().addAll(toSrollPane,nameTextField,snameTextField,saershPerson);
            secondPane.getChildren().add(scrollPane1);


            saershPerson.setOnAction(szuk ->{

                if(nameTextField.getText().trim().isEmpty() && snameTextField.getText().trim().isEmpty()) {
                r=baseData.getData("Select imie, nazwisko from osoby;");
                }else {
                    r = baseData.getData("Select imie, nazwisko from osoby where imie='" + nameTextField.getText() + "' or nazwisko='" + snameTextField.getText() + "';");
                }
               showScroll(personsBox,toSrollPane,"imie","nazwisko");

                for(int i=0;i<personsBox.size();i++) {
                    CheckBox person = personsBox.get(i);
                    person.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        Pane booksPane = new Pane();
                        ScrollPane booksSrcollPane = new ScrollPane(booksPane);

                        if (person.isSelected()) {
                            List<CheckBox> booksBoxes = new ArrayList<>();
                            List<CheckBox> booksCheck=new ArrayList<>();
                            booksSrcollPane.setPrefSize(300, 400);
                            booksSrcollPane.setLayoutX(310);
                            Button add= new Button("dodaj");
                            Button saershBooks= new Button("szukaj");
                            nameTextField.clear();
                            nameTextField.setLayoutX(630);
                            nameTextField.setPromptText("Tytuł");
                            personSelected.add(person);
                            add.setLayoutX(630);
                            add.setLayoutY(350);
                            saershBooks.setLayoutY(300);
                            saershBooks.setLayoutX(630);
                            secondPane.getChildren().addAll(booksPane, booksSrcollPane,saershBooks,add);


                            saershBooks.setOnAction(event2 -> {
                                if(name.getText().trim().isEmpty()) {
                                    r = baseData.getData("Select tytul from ksiazki");
                                }else{
                                    r=baseData.getData("Select from tytul from ksiazki where tytul='"+nameTextField.getText()+"';");
                                }
                                showScroll(booksBoxes,booksPane,"tytul");

                                for (CheckBox book:booksBoxes
                                     ) {
                                        book.selectedProperty().addListener((observable1, oldValue1, newValue1) -> {
                                            if(book.isSelected()){
                                                booksCheck.add(book);
                                            }
                                            else{
                                                booksCheck.remove(book);
                                            }
                                        });
                                }
                                add.setOnAction(event3 -> {

                                });
                            });
                        }
                        else{
                            personSelected.remove(person);
                            if(personSelected.size()==0) {
                                secondPane.getChildren().clear();
                                nameTextField.setLayoutX(330);
                                snameTextField.setLayoutX(330);
                                nameTextField.setPromptText("imie");
                                secondPane.getChildren().addAll(scrollPane1,snameTextField,nameTextField,saershPerson);
                            }
                        }
                    });
                }

            });


        });


    }


    public void oso(ActionEvent event) {
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(os,wypoz);
        AddwypWindow o=new AddwypWindow();
        o.AddwypWindowShow();

    }
}
