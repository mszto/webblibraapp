package sample;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.sql.SQLException;


public class Controller extends AddToScroll {

    @FXML
    private Pane mainPane;
    public Button wypoz;
    public  Button os,showBooksButton;
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

        Label nameLabel =new Label("imię:");
        Label sNameLabel =new Label("Nazwisko:");
        Label titleLabel =new Label("Tytuł:");
        mainPane.getChildren().addAll(tablePerson,os,wypoz,showBooksButton,nameLabel,sNameLabel,titleLabel);


        tablePerson.setMinSize(400, 600);
        tablePerson.setLayoutY(50);

        sName.setPromptText("nazwisko");
        sName.setLayoutY(250);
        sName.setLayoutX(500);
        sNameLabel.setLayoutX(500);
        sNameLabel.setLayoutY(230);

        name.setPromptText("imie");
        name.setLayoutX(500);
        name.setLayoutY(200);

        nameLabel.setLayoutX(500);
        nameLabel.setLayoutY(180);

        titleLabel.setLayoutX(500);
        titleLabel.setLayoutY(290);

        title.setPromptText("tytuł");
        title.setLayoutX(500);
        title.setLayoutY(310);

        searchButoon.setLayoutX(500);
        searchButoon.setLayoutY(400);

        addButton.setLayoutX(500);
        addButton.setLayoutY(100);

        deleteButton.setLayoutY(100);
        deleteButton.setLayoutX(450);

        deleteButton.setDisable(true);
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

        tablePerson.setOnMouseClicked(clikedTable->{
            if(tablePerson.getSelectionModel().isEmpty()) {
                deleteButton.setDisable(true);
            }else{
                deleteButton.setDisable(false);
            }
        });

        deleteButton.setOnAction(event1 -> {
            ObservableList<Borrows> list;
            list = tablePerson.getSelectionModel().getSelectedItems();

            for (Borrows p : list) {
                baseData.delteWyp("DELETE w from wypozyczenia w " +
                        "join osoby o on o.id_o=w.id_osoby " +
                        "join ksiazki k on k.id=w.id_ksiazki " +
                        "WHERE o.imie= ? and o.nazwisko= ? and k.tytul= ?", p.getFirstName(), p.getSecondName(), p.getTitle());
                tablePerson.getItems().removeIf(borrows -> {return borrows.equals(p);});
            }

        });



        addButton.setOnAction((ActionEvent event1) -> {
        AddwypWindow o=new AddwypWindow();
        o.AddwypWindowShow();
        });
    }


    public void oso(ActionEvent event) {
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(os,wypoz,showBooksButton);
        TableView<Person> personsTable=new TableView<>();
        TextField name = new TextField();
        TextField sName = new TextField();
        TextField addName = new TextField();
        TextField addsName = new TextField();
        Button deletePerson=new Button("Usuń");
        Button searchPerson=new Button("Szukaj");
        Button addPerson= new Button("Dodaj");


        deletePerson.setLayoutX(400);
        deletePerson.setLayoutY(50);

        sName.setPromptText("nazwisko");
        sName.setLayoutY(240);
        sName.setLayoutX(500);

        name.setPromptText("imie");
        name.setLayoutX(500);
        name.setLayoutY(200);

        addsName.setPromptText("nazwisko");
        addsName.setLayoutY(600);
        addName.prefHeightProperty().bind(mainPane.heightProperty());


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

        deletePerson.setDisable(true);

        personsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        showTableViewEdit(personsTable,"fName","sName","id","imię","nazwisko","id_o",new Person());


        mainPane.getChildren().addAll(personsTable,searchPerson,name,sName,addName,addsName,addPerson,deletePerson);
        searchPerson.setOnAction((ActionEvent event1) -> {
            if (name.getText().trim().isEmpty() && sName.getText().trim().isEmpty()) {
                r = baseData.getData("Select imie, nazwisko, id_o from osoby;");
            } else {
                r = baseData.getData("Select imie, nazwisko, id_o from osoby where imie='" + name.getText() + "' or nazwisko='" + sName.getText() + "';");
            }

            showTableViewEdit(personsTable,"fName","sName","id","imie","nazwisko","id_o",new Person());
        });

        personsTable.setOnMouseClicked(clikedTable->{
            if(personsTable.getSelectionModel().isEmpty()) {
                deletePerson.setDisable(true);
            }else{
                deletePerson.setDisable(false);
            }
        });

        addPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id=-1;
                baseData.inserInto("insert into osoby (imie, nazwisko) values(?,?);",addName.getText(), addsName.getText());
                r=baseData.getData("Select id_o from osoby where imie='"+addName.getText()+"' and nazwisko='"+addsName.getText()+"';");
                try {
                    id=r.getInt("id_o");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                personsTable.getItems().add(new Person(addName.getText(),addsName.getText(),id));
                addName.clear();
                addsName.clear();

            }
        });

        deletePerson.setOnAction(deletePersonEvent ->{
            ObservableList<Person> list;
            list = personsTable.getSelectionModel().getSelectedItems();

            for (Person p : list) {
                baseData.delteWyp("DELETE o from osoby o " +
                        "WHERE o.id_o= ?",p.getId());
                personsTable.getItems().removeIf(person -> {return person.equals(p);});
            }
        });

    }

    public void books(ActionEvent event){
        TableView<Person> booksTable=new TableView();
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(os,wypoz,showBooksButton);

        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(os,wypoz,showBooksButton);

        TextField title = new TextField();
        TextField addTitle= new TextField();
        TextField addHowMuch= new TextField();

        Button searchBook=new Button("Szukaj");
        Button addBook= new Button("Dodaj");


        title.setPromptText("Tytuł");
        title.setLayoutX(500);
        title.setLayoutY(200);

        addTitle.setPromptText("Tytuł");
        addTitle.setLayoutX(200);
        addTitle.setLayoutY(600);

        addHowMuch.setLayoutY(600);
        addHowMuch.setPromptText("ilosc");

        addBook.setLayoutY(600);
        addBook.setLayoutX(400);

        searchBook.setLayoutX(500);
        searchBook.setLayoutY(300);

        booksTable.setMinSize(500,500);
        booksTable.setLayoutY(50);
        booksTable.setEditable(true);



        showTableView(booksTable,"fName","sName","ilość","tytul","autor","ilosc",new Person());


        mainPane.getChildren().addAll(booksTable,title,searchBook,addBook,addTitle,addHowMuch);
        searchBook.setOnAction((ActionEvent event1) -> {
            if (title.getText().trim().isEmpty()) {
                r = baseData.getData("Select k.tytul, k.ilosc as ilosc, Concat(a.imie,' ',a.nazwisko) as autor from ksiazki k join autorzy a  on k.id_autor=a.id_autor ");
            } else {
                r = baseData.getData("Select k.tytul, k.ilosc as ilosc, Concat(a.imie,' ',a.nazwisko) as autor from ksiazki k join autorzy a on k.id_autor=a.id_autor where k.tytul='" + title.getText() + "'");
            }

            showTableView(booksTable,"fName","sName","id","tytul","autor","ilosc",new Person());

    });
        addBook.setOnAction((ActionEvent a)->{
            String text=addHowMuch.getText();
            int i=Integer.parseInt(text);
            Authors authors=new Authors(addTitle.getText(),i,booksTable);
        });
    }
}
