package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AddToScroll<K,E,D>   {
    ResultSet r;
    DatebaseConnection baseData;
    public AddToScroll(){
        baseData=DatebaseConnection.getInstance();
    }


    protected void showTableView(TableView<Data>  table, String fTable,String sTable, String tTable, String fname, String sname, String tname, Data d)  {
        table.getColumns().clear();
        ObservableList<Data> data= FXCollections.observableArrayList();
        TableColumn fisrtColumn=new TableColumn(fname);
        fisrtColumn.setMaxWidth(150);
        fisrtColumn.setCellValueFactory(
        new PropertyValueFactory<Data,K>(fTable));

        TableColumn secondColumn=new TableColumn(sname);
        secondColumn.setMaxWidth(150);
        secondColumn.setCellValueFactory(
                new PropertyValueFactory<Data,D>(sTable));

        TableColumn thirdColumn=new TableColumn(tname);
        thirdColumn.setMaxWidth(150);
        thirdColumn.setCellValueFactory(
                new PropertyValueFactory<Data,E>(tTable));


        try{
            while(r.next()){
                    data.add(d.createData(r.getString(fname), r.getString(sname), r.getInt(tname)));
            }
        }
        catch (Exception e){
            try {
                data.add(d.createData(r.getString(fname), r.getString(sname), r.getString(tname)));
                while(r.next()){
                    data.add(d.createData(r.getString(fname), r.getString(sname), r.getString(tname)));
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
            System.out.println(e+" line 55 in class AddToScroll");

        }

        table.setItems(data);
        table.getColumns().addAll(fisrtColumn,secondColumn,thirdColumn);

    }

    protected void addColumnString(TableView<Data> table, String columnName, String dataName, Data d){
        TableColumn column=new TableColumn(columnName);

        column.setCellValueFactory(new PropertyValueFactory<Data,String>(dataName));
        table.getColumns().addAll(column);
    }

    protected void showTableView(TableView<Data>  table, String fTable,String sTable, String fname, String sname,Data d){
        table.getColumns().clear();
        ObservableList<Data> data= FXCollections.observableArrayList();
        TableColumn fisrtColumn=new TableColumn(fname);

        fisrtColumn.setCellValueFactory(
                new PropertyValueFactory<Data,K>(fTable));

        TableColumn secondColumn=new TableColumn(sname);
        secondColumn.setCellValueFactory(
                new PropertyValueFactory<Data,E>(sTable));


        try{
            while(r.next()){
                data.add(d.createData(r.getString(fname),r.getString(sname)));
                //System.out.println(r.getString(fname)+" "+ r.getInt(sname));
            }
        }
        catch (Exception e){

            System.out.println(e);
        }

        table.getColumns().addAll(fisrtColumn,secondColumn);
        table.setItems(data);

    }



    protected void showTableView(TableView<Data>  table, String fTable, String fname, Data d){
        table.getColumns().clear();
        ObservableList<Data> data= FXCollections.observableArrayList();
        TableColumn fisrtColumn=new TableColumn(fname);

        fisrtColumn.setCellValueFactory(
                new PropertyValueFactory<Data,K>(fTable));


        try{
            while(r.next()){
                data.add(d.createData(r.getString(fname)));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        table.setItems(data);
        table.getColumns().addAll(fisrtColumn);

    }



    protected void showTableViewEdit(TableView<Data>  table, String fTable,String sTable, String tTable, String fname, String sname, String tname, Data d){
        table.getColumns().clear();
        ObservableList<Data> data= FXCollections.observableArrayList();
        TableColumn fisrtColumn=new TableColumn(fname);


        fisrtColumn.setCellValueFactory(
                new PropertyValueFactory<Data,K>(fTable));

        fisrtColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        fisrtColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person,String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setfName(t.getNewValue());
                int id=t.getTableView().getItems().get(t.getTablePosition().getRow()).getId();

                baseData.UpdatedString("Update osoby set imie=? where id_o=?",t.getNewValue(),id);
            }
        });

        TableColumn secondColumn=new TableColumn(sname);
        secondColumn.setCellValueFactory(
                new PropertyValueFactory<Data,E>(sTable));

        secondColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        secondColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person,String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setsName(t.getNewValue());
                int id=t.getTableView().getItems().get(t.getTablePosition().getRow()).getId();

                baseData.UpdatedString("Update osoby set nazwisko=? where id_o=?",t.getNewValue(),id);
            }
        });

        TableColumn thirdColumn=new TableColumn(tname);
        thirdColumn.setCellValueFactory(
                new PropertyValueFactory<Data,E>(tTable));


        try{

            while(r.next()){
                data.add(d.createData(r.getString(fname),r.getString(sname),r.getInt(tname)));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        table.setItems(data);
        table.getColumns().addAll(fisrtColumn,secondColumn,thirdColumn);

    }


    protected void showTableViewInt(TableView<Data>  table, String fTable,String sTable, String fname, String sname,Data d){
        table.getColumns().clear();
        ObservableList<Data> data= FXCollections.observableArrayList();
        TableColumn fisrtColumn=new TableColumn(fname);
        fisrtColumn.setMinWidth(200);

        fisrtColumn.setCellValueFactory(
                new PropertyValueFactory<Data,K>(fTable));

        TableColumn secondColumn=new TableColumn(sname);
        secondColumn.setCellValueFactory(
                new PropertyValueFactory<Data,E>(sTable));


        try{
            while(r.next()){
                data.add(d.createData(r.getString(fname),r.getInt(sname)));
                //System.out.println(r.getString(fname)+" "+ r.getInt(sname));
            }
        }
        catch (Exception e){

            System.out.println(e);
        }

        table.getColumns().addAll(fisrtColumn,secondColumn);
        table.setItems(data);

    }


}
