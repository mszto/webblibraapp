package sample;

import com.sun.org.apache.bcel.internal.generic.Select;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AddwypWindow extends AddToScroll {

    // private ResultSet r;


    public AddwypWindow() {
        super();

    }

    public void AddwypWindowShow() {
        Button showBooks=new Button(">");
        Button hideBooks= new Button("<");
        Stage newWindow = new Stage();
        Pane secondPane = new Pane();
        Scene secondScene = new Scene(secondPane, 950, 600);
        TableView persons=new TableView();
        TableView books=new TableView();
        TextField nameTextField = new TextField();
        TextField snameTextField = new TextField();
        Button saershPerson = new Button("szukaj");


        secondPane.setStyle("-fx-background-color: #9bbcff;");
        newWindow.setX(100);
        newWindow.setY(100);
        newWindow.setScene(secondScene);
        newWindow.show();

        persons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        showBooks.setLayoutX(270);
        showBooks.setLayoutY(200);

        hideBooks.setLayoutY(235);
        hideBooks.setLayoutX(270);

        snameTextField.setPromptText("nazwisko");
        snameTextField.setLayoutY(240);
        snameTextField.setLayoutX(330);

        nameTextField.setPromptText("imie");
        nameTextField.setLayoutX(330);
        nameTextField.setLayoutY(200);

        saershPerson.setLayoutX(330);
        saershPerson.setLayoutY(300);

       // showTableView(persons,"firstName","secondName","imie","nazwisko",new Borrows());
        secondPane.getChildren().addAll(nameTextField, snameTextField, saershPerson,persons,showBooks,hideBooks);


        saershPerson.setOnAction(szuk -> {

            if (nameTextField.getText().trim().isEmpty() && snameTextField.getText().trim().isEmpty()) {
                r = baseData.getData("Select imie, nazwisko from osoby;");
            } else {
                r = baseData.getData("Select imie, nazwisko from osoby where imie='" + nameTextField.getText() + "' or nazwisko='" + snameTextField.getText() + "';");
            }

            showTableView(persons,"fName","sName","imie","nazwisko",new Person());

                Button add = new Button("dodaj");
                Button saershBooks = new Button("szukaj");
                books.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                add.setLayoutX(630);
                add.setLayoutY(350);
                saershBooks.setLayoutY(300);
                saershBooks.setLayoutX(630);

                add.setDisable(true);

                persons.setOnMouseClicked(event -> {
                    if(! persons.getSelectionModel().isEmpty()){
                        nameTextField.clear();
                        nameTextField.setLayoutX(630);
                        nameTextField.setPromptText("Tytuł");
                        books.setLayoutX(300);
                        showTableView(books,"title","ilosc","tytul","ilosc",new Person());
                        secondPane.getChildren().add(books);
                        secondPane.getChildren().addAll(saershBooks, add);
                    }else{
                        secondPane.getChildren().removeAll(books,add,saershBooks);
                        nameTextField.clear();
                        nameTextField.setPromptText("imie");
                        nameTextField.setLayoutX(330);
                    }
                });


                saershBooks.setOnAction( event2 -> {

                    if (nameTextField.getText().trim().isEmpty()) {
                        r = baseData.getData("Select tytul, ilosc from ksiazki");
                    } else {
                        r = baseData.getData("Select  tytul, ilosc from ksiazki where tytul='" + nameTextField.getText() + "';");
                    }

                    showTableViewInt(books,"fName","id","tytul","ilosc", new Person());

                    });

                     books.setOnMouseClicked(event -> {
                         ObservableList<Person> bo=books.getSelectionModel().getSelectedItems();
                         bo.forEach(borrows -> {
                             if(borrows.getId()<=0){
                                 Stage win=new Stage();
                                 Pane pane=new Pane();
                                 Scene scene=new Scene(pane,100,100);
                                 Label label=new Label("Brak danego tytułu !!");
                                 Button ok=new Button("OK");
                                 ok.setLayoutY(25);
                                 pane.getChildren().addAll(label,ok);
                                 win.setScene(scene);
                                 win.show();

                                 ok.setOnAction(event1->{
                                     win.close();
                                 });
                                 add.setDisable(true);
                                 books.getSelectionModel().clearSelection();
                             }
                         });
                         if(!books.getSelectionModel().isEmpty()){
                             add.setDisable(false);
                         }else{
                             add.setDisable(true);
                         }
                     });

                     add.setOnAction(event -> {
                         if(!books.getSelectionModel().isEmpty() && !persons.getSelectionModel().isEmpty()){


                             ObservableList<Person> people=persons.getSelectionModel().getSelectedItems();
                             ObservableList<Person> boks=books.getSelectionModel().getSelectedItems();

                             for (Person p:people) {

                                 StringBuilder query=new StringBuilder("Select id_o from osoby where nazwisko='");
                                 query.append(p.getsName());
                                 query.append("' and imie='");
                                 query.append(p.getfName()+"'");
                                 r=baseData.getData(query.toString());
                                 int idO=-1;
                                 int idB=-1;
                                 try {
                                     r.next();
                                     idO=r.getInt("id_o");
                                 } catch (SQLException e) {
                                     e.printStackTrace();
                                 }

                                 for (Person b:boks) {


                                     query=new StringBuilder("Select id from ksiazki where tytul='");
                                     query.append(b.getfName()+"'");
                                     r=baseData.getData(query.toString());

                                     try {
                                         r.next();
                                          idB=r.getInt("id");
                                     } catch (SQLException e) {
                                         e.printStackTrace();
                                     }

                                     if(idO>-1 && idB>-1)
                                     baseData.inserInto("insert into wypozyczenia (id_ksiazki, id_osoby) values(?, ?);",idB,idO);

                                     newWindow.close();
                                 }
                             }
                         }
                     });



        });
    }
}
