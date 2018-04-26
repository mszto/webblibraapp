package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by dkoby on 26.04.2018.
 */
public class AddToScroll {
    ResultSet r;
    DatebaseConnection baseData;
    public AddToScroll(){
        baseData=DatebaseConnection.getInstance();
    }
    protected void showScroll(List<CheckBox> list, Pane toSrollPane, String columnFirst, String columnSecond){
        toSrollPane.getChildren().clear();
        list.clear();
        try {
            int y=0;
            while (r.next()) {
                CheckBox chB=new CheckBox(r.getString(columnFirst)+" "+r.getString(columnSecond));
                chB.setLayoutY(y+=30);
                list.add(chB);
                toSrollPane.getChildren().add(chB);

            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

     protected void showScroll(List<CheckBox> list, Pane toSrollPane, String columnFirst) {
         toSrollPane.getChildren().clear();
         list.clear();
         try {
             int y=0;
             while (r.next()) {
                 CheckBox chB=new CheckBox(r.getString(columnFirst));
                 chB.setLayoutY(y+=30);
                 list.add(chB);
                 toSrollPane.getChildren().add(chB);

             }
         }
         catch (Exception e){
             System.out.println(e);
         }
    }

    protected void showScroll(List<CheckBox> list, Pane toSrollPane,String columnFirst, String columnSecond, String columnThird){
        toSrollPane.getChildren().clear();
        list.clear();
        try {
            int y=0;
            while (r.next()) {
                CheckBox chB=new CheckBox(r.getString(columnFirst)+" "+r.getString(columnSecond)+" "+r.getString(columnThird));
                chB.setLayoutY(y+=30);
                list.add(chB);
                toSrollPane.getChildren().add(chB);

            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    protected void showTableView(TableView<Person>  table, String fTable,String sTable, String tTable, String fname, String sname, String tname){
        table.getColumns().clear();
        ObservableList<Person> data= FXCollections.observableArrayList();
        TableColumn fisrtColumn=new TableColumn(fname);

        fisrtColumn.setCellValueFactory(
        new PropertyValueFactory<Person,String>(fTable));

        TableColumn secondColumn=new TableColumn(sname);
        secondColumn.setCellValueFactory(
                new PropertyValueFactory<Person,String>(sTable));

        TableColumn thirdColumn=new TableColumn(tname);
        thirdColumn.setCellValueFactory(
                new PropertyValueFactory<Person,String>(tTable));

        try{
            while(r.next()){
                data.add(new Person(r.getString(fname),r.getString(sname),r.getString(tname)));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        table.setItems(data);
        table.getColumns().addAll(fisrtColumn,secondColumn,thirdColumn);

    }

}
