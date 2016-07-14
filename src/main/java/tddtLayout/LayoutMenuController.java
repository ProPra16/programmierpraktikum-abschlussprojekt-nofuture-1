package tddtLayout;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import tddtMain.TDDTMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LayoutMenuController {

    // Variablen
    private static boolean hasAtdd = false;
    private static boolean hasBabysteps = false;
    private static boolean hasRainbow = false;
    private static String[] LabelRainbowButton = {"Hübsch", "Hässlich"};
    private static int changeLabelRainbow = 0;
    private static IntegerProperty timer;
    private static String exerciseText;
    private HashMap<StringBuilder, StringBuilder> datenListe = new HashMap<>();
    private ObservableList<String> data = FXCollections.observableArrayList();


// FXML
    @FXML
    ToggleGroup babystepTimeGroup;
    @FXML
    Label errorExercise;
    @FXML
    TextArea exercise;
    @FXML
    Slider timerSlider;
    @FXML
    Label dauerText;
    @FXML
    ListView <String> listExercises;
    @FXML
    Button rainbow;

    @FXML
    public void initialize() {
        data = FXCollections.observableArrayList();
        aufgabenErstellen();
        listExercises.setItems(data.sorted());
        viewExercise();
        rainbow.setText(LabelRainbowButton[changeLabelRainbow]);
    }

   // Buttonhandles
    public void handleBabystep() {
        if (!hasBabysteps) {
            setHasBabysteps(true);
            timerSlider.setVisible(true);
            dauerText.setVisible(true);
            timer.bind(timerSlider.valueProperty());
            timerSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    dauerText.setText("Bitte Dauer in Minuten wählen: " + newValue.intValue() + "min");
                }
            });
        }
        else {
            setHasBabysteps(false);
            timerSlider.setVisible(false);
            dauerText.setVisible(false);
        }
    }
    public void handleATDD() {
        if (!hasAtdd) {
            setHasAtdd(true);
        }
        else {
            setHasAtdd(false);
        }
    }
    public void handleExitButton() {
        Platform.exit();
    }
    public void handleStartMenuButton() throws IOException {
        if ("".equals(exercise.getText())) {
            errorExercise.setText("Wähle eine Aufgabe aus!");

        }
        else {
            if (!hasAtdd) {
                exerciseText = exercise.getText();
                FXMLLoader loader = new FXMLLoader();
                TDDTMain.rootPane.setCenter(loader.load(getClass().getResource("/layoutTDDT.fxml")));
            }

            else {
               exerciseText = exercise.getText();
               FXMLLoader loader = new FXMLLoader();
               TDDTMain.rootPane.setCenter(loader.load(getClass().getResource("/layoutATDD.fxml")));
            }

        }
    }
    public void handleNewExercise () {
        // http://java-buddy.blogspot.de/2012/05/read-text-file-with-javafx-filechooser.html
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Textdateien (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // mehrere Dateien
        List<File> list = fileChooser.showOpenMultipleDialog(null);
        if (list != null) {
            for (File file : list) {
                String fileDir = file.getAbsolutePath();
                StringBuilder filename = new StringBuilder(file.getName());
                filename.replace(filename.length() - 4, filename.length(), "");
                // Dateiname ohne txt und inhalt in HashMap
                datenListe.put(filename, readTxt(fileDir));
            }
        }
        // Dateiname in ObservableArrayList speichern
        for (HashMap.Entry<StringBuilder, StringBuilder> m : datenListe.entrySet()) {
            if (!data.contains(m.getKey().toString())) {
                data.add(m.getKey().toString());
            }
        }
        // Daten in die ListView laden
        listExercises.setItems(data.sorted());
        viewExercise();

    }
    public  void rainbow() {
        if(!hasRainbow) {
            TDDTMain.rootPane.setStyle(" -fx-background-image: url('./Bilder/rainbow.png');  -fx-background-repeat: stretch; -fx-font-weight: bold;");
            changeLabelRainbow = 1;
            rainbow.setText(LabelRainbowButton[changeLabelRainbow]);
            hasRainbow = true;
        }
        else {
            TDDTMain.rootPane.setStyle("-fx-background-color: white;");
            changeLabelRainbow = 0;
            rainbow.setText(LabelRainbowButton[changeLabelRainbow]);
            hasRainbow = false;
        }
    }

    // TXT Einlesen und eventListener
    private StringBuilder readTxt(String file) {
        errorExercise.setText("");
        StringBuilder text = new StringBuilder("");
        try
        {
            StringBuffer buffer = new StringBuffer();
            FileReader in = new FileReader(file);
            for (int n; (n = in.read()) != -1; buffer.append((char) n));
            in.close();

            text.append(buffer.toString());
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found: "+file);
        }
        catch(IOException e) {}
        return text;
    }
    private void viewExercise() {
        // http://docs.oracle.com/javafx/2/ui_controls/ListViewSample.java.html
        listExercises.getSelectionModel().selectedItemProperty().addListener(
                (ov, old_val, new_val) -> {
                    String item = listExercises.getSelectionModel().getSelectedItem();
                    for (HashMap.Entry<StringBuilder, StringBuilder> m : datenListe.entrySet()) {
                        if (m.getKey().toString().contains(item)) {
                            exercise.setText(m.getValue().toString());
                            // Aufhören zu suchen wenn ein Eintrag vorhanden ist
                            break;
                        }
                    }
                });
    }
    private void aufgabenErstellen() {

        try {
            File f = new File("./Aufgaben");
            File[] fileArray = f.listFiles();

            for (int i = 0; i < fileArray.length; i++ ) {
                String fileAndFolder = fileArray[i].toString();
                String absolutePathFile = fileArray[i].getCanonicalPath();
                StringBuilder file = new StringBuilder();
                file.append(fileAndFolder);
                // Nur Dateinamen
                file.replace(0,11,"");
                int j = file.length();
                file.replace(j-4,j,"");
                datenListe.put(file, readTxt(absolutePathFile));

            }
            // Dateiname in ObservableArrayList speichern
            for (HashMap.Entry<StringBuilder, StringBuilder> m : datenListe.entrySet()) {
                if (!data.contains(m.getKey().toString())) {
                    data.add(m.getKey().toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ordner Aufgaben nicht vorhanden?");
        }
    }

    // getter-setter Bereich
    static int getTimer() { return timer.getValue(); }
    static boolean getBabysteps() { return hasBabysteps; }
    static String getExerciseText() {
        return exerciseText;
    }
    static void setHasAtdd(boolean b) {
       hasAtdd = b;
    }
    static void setHasBabysteps(boolean b) {
       hasBabysteps = b;
    }

    public static void setHasRainbow(boolean Rainbow) {
        hasRainbow = Rainbow;
    }
}
