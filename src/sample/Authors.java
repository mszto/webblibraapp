package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Authors extends AddToScroll {
    Stage window;
    Scene scene;
    Pane pane;
    TableView<Person> authorsTable;
    public Authors(){
        super();
    }
    public Authors(String title, TableView<Person> books){
        super();
        window=new Stage();
        pane=new Pane();
        scene=new Scene(pane,400,300);
        authorsTable=new TableView();
        TextField textFieldName= new TextField();
        TextField textFieldLastName= new TextField();
        Button schearchButton= new Button ("Szukaj");
        Button okBytton= new Button("OK");

        textFieldName.setPromptText("imie");
        textFieldName.setLayoutY(100);
        textFieldName.setLayoutX(300);

        textFieldLastName.setPromptText("Nazwisko");
        textFieldLastName.setLayoutX(300);
        textFieldLastName.setLayoutY(150);

        schearchButton.setLayoutY(200);
        schearchButton.setLayoutX(300);
        pane.getChildren().addAll(authorsTable,textFieldLastName,textFieldName,schearchButton);
        window.setScene(scene);
        window.show();

        showTableView(authorsTable,"fName","sName","imie","nazwisko",new Person());

        schearchButton.setOnAction(schearch->{
            if (textFieldName.getText().trim().isEmpty() && textFieldLastName.getText().trim().isEmpty()) {
                r = baseData.getData("Select imie, nazwisko from autorzy;");
            } else {
                r = baseData.getData("Select imie, nazwisko from autorzy where imie='" + textFieldName.getText() + "' or nazwisko='" + textFieldLastName.getText() + "';");
            }
            showTableView(authorsTable,"fName","sName","imie","nazwisko",new Person());
        });



    }
}
