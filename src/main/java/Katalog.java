import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Anne01 on 21.06.2016.
 */
public class Katalog extends Parent{
    Text t;
public Katalog() {
    //Allgemeine Struktur
    Stage primaryStage = new Stage();
    BorderPane borderPane = new BorderPane();
    Scene root = new Scene(borderPane, 500, 600);
    MenuBar menuBar = new MenuBar();
    //Menü
    Menu fileMenu = new Menu("Aufgaben");
    MenuItem anagramm = new MenuItem("Anagramm");
    MenuItem array = new MenuItem("Array");
    MenuItem fuhrpark = new MenuItem("Fuhrpark");
    MenuItem nullzeile = new MenuItem("Nullzeile");
    MenuItem pixel = new MenuItem("Pixel");
    MenuItem recursive = new MenuItem("Rekursiv");
    MenuItem reptile = new MenuItem("Reptil");
    MenuItem inter = new MenuItem("Interface");
    MenuItem sort = new MenuItem("Sortieren");
    fileMenu.getItems().addAll(anagramm, array, fuhrpark, nullzeile, pixel, recursive, reptile, inter, sort);
    menuBar.getMenus().add(fileMenu);
    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
    borderPane.setTop(menuBar);
    t = new Text();
    t.setFont(Font.font ("Georgia", 13));

    //Dateien auslesen
    anagramm.setOnAction((ActionEvent e)-> t.setText(readTxt("./Aufgaben/Anagramm.txt")));
    array.setOnAction((ActionEvent e)-> t.setText(readTxt("./Aufgaben/Array.txt")));
    fuhrpark.setOnAction((ActionEvent e)-> t.setText(readTxt("./Aufgaben/Fuhrpark.txt")));
    nullzeile.setOnAction((ActionEvent e)-> t.setText(readTxt("./Aufgaben/Nullzeile.txt")));
    pixel.setOnAction((ActionEvent e)-> t.setText(readTxt("./Aufgaben/Pixel.txt")));
    recursive.setOnAction((ActionEvent e)-> t.setText(readTxt("./Aufgaben/Rekursiv.txt")));
    reptile.setOnAction((ActionEvent e)-> t.setText(readTxt("./Aufgaben/Reptil.txt")));
    inter.setOnAction((ActionEvent e)-> t.setText(readTxt("./Aufgaben/Interface.txt")));
    sort.setOnAction((ActionEvent e)-> t.setText(readTxt("./Aufgaben/Sortieren.txt")));

    //text platzieren
    VBox vBox = new VBox();
    vBox.setPadding(new Insets(20));
    vBox.getChildren().add(t);
    borderPane.setLeft(vBox);

    primaryStage.setScene(root);
    primaryStage.setTitle("Katalog");
    primaryStage.show();
}
    //text einlesen
    public String readTxt(String file) {
        String text = "";
        try
        {
            StringBuffer buffer = new StringBuffer();
            FileReader in = new FileReader(file);
            for (int n;(n = in.read()) != -1;buffer.append((char) n));
            in.close();

            text = buffer.toString();
        }
        catch(FileNotFoundException e)
        {
        }
        catch(IOException e)
        {
        }

   return text;
    }
}
