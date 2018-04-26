package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkoby on 26.04.2018.
 */
public class AddwypWindow extends AddToScroll {

   // private ResultSet r;



    public AddwypWindow(){
        super();
    }
    public void AddwypWindowShow(){
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
                            if(nameTextField.getText().trim().isEmpty()) {
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




    }
}
