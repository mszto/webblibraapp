package sample;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Observable;

public class Authors extends AddToScroll {
    Stage window;
    Scene scene;
    Pane pane;
    TableView<Person> authorsTable;
    public Authors(){
        super();
    }
    public Authors(String title, int how, TableView<Person> books){
        super();
        window=new Stage();
        pane=new Pane();
        scene=new Scene(pane,550,400);
        authorsTable=new TableView();
        TextField textFieldName= new TextField();
        TextField textFieldLastName= new TextField();
        Button schearchButton= new Button ("Szukaj");
        Button okBytton= new Button("OK");
        
        pane.setStyle("-fx-background-color: #9bbcff;");
        textFieldName.setPromptText("imie");
        textFieldName.setLayoutY(100);
        textFieldName.setLayoutX(300);

        textFieldLastName.setPromptText("Nazwisko");
        textFieldLastName.setLayoutX(300);
        textFieldLastName.setLayoutY(150);

        okBytton.setLayoutX(500);
        okBytton.setLayoutY(250);

        schearchButton.setLayoutY(200);
        schearchButton.setLayoutX(300);
        pane.getChildren().addAll(authorsTable,textFieldLastName,textFieldName,schearchButton,okBytton);
        window.setScene(scene);
        window.show();

        showTableView(authorsTable,"fName","sName","imie","nazwisko",new Person());

        schearchButton.setOnAction(schearch->{
            if (textFieldName.getText().trim().isEmpty() && textFieldLastName.getText().trim().isEmpty()) {
                r = baseData.getData("Select imie, nazwisko, id_autor from autorzy;");
            } else {
                r = baseData.getData("Select imie, nazwisko, id_autor from autorzy where imie='" + textFieldName.getText() + "' or nazwisko='" + textFieldLastName.getText() + "';");
            }
            showTableView(authorsTable,"fName","sName","id","imie","nazwisko","id_autor",new Person());
        });

        okBytton.setOnAction(addAction->{
            ObservableList<Person> person= authorsTable.getSelectionModel().getSelectedItems();

            person.forEach(person1 -> {
                baseData.inserInto("insert into ksiazki (tytul,id_autor, ilosc) values(?,?,?)",title,person1.getId(),how);
                books.getItems().add(new Person(title,person1.getfName()+person1.getsName(),how));
            });
            window.close();
        });

    }
}
