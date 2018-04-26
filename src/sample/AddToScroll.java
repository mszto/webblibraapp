package sample;

import javafx.scene.control.CheckBox;
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

}
