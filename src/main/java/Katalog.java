import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Anne01 on 21.06.2016.
 */
public class Katalog extends Parent{
    Text t = new Text();
public Katalog() {
    Stage primaryStage = new Stage();
    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("Aufgaben");
    MenuItem anagramm = new MenuItem("Anagramm");
    MenuItem array = new MenuItem("Array");
    MenuItem fuhrpark = new MenuItem("Fuhrpark");
    MenuItem nullzeile = new MenuItem("Nullzeile");
    MenuItem pixel = new MenuItem("Pixel");
    MenuItem recursive = new MenuItem("Rekursiv");
    MenuItem reptile = new MenuItem("Reptil");
    fileMenu.getItems().addAll(anagramm, array, fuhrpark, nullzeile, pixel, recursive, reptile);
    menuBar.getMenus().add(fileMenu);
    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
    BorderPane borderPane = new BorderPane();
    borderPane.setTop(menuBar);
    anagramm.setOnAction((ActionEvent e)->{
        t.setText(readTxt("./Aufgaben/Anagramm.txt"));
        });

    Scene root = new Scene(borderPane, 500, 600);
    primaryStage.setScene(root);
    primaryStage.setTitle("Aufgaben");
    primaryStage.show();
}

    public String readTxt(String file) {
        String zusammen = "";
        try
        {
            StringBuffer buffer = new StringBuffer();
            FileReader in = new FileReader(file);
            for (int n;(n = in.read()) != -1;buffer.append((char) n));
            in.close();

            zusammen = buffer.toString();
        }
        catch(FileNotFoundException e)
        {
        }
        catch(IOException e)
        {
        }

   return zusammen;
    }
}
