package tddtLayout;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import tddtMain.TDDTMain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LayoutMenuController {

    // Variablen
    static boolean hasAddt = false;
    static boolean hasBabysteps = false;
    static int timer = 0;
    boolean isMaximized = true;

    // FXML
    @FXML
    MenuBar menuBar;
    @FXML
    ToggleGroup babystepTimeGroup;
    @FXML
    Label errorExercise;
    @FXML
    Label exercise;

    // TXT Einlesen
    private String readTxt(String file) {
        errorExercise.setText("");
        String text = "";
        try
        {
            StringBuffer buffer = new StringBuffer();
            FileReader in = new FileReader(file);
            for (int n; (n = in.read()) != -1; buffer.append((char) n));
            in.close();

            text = buffer.toString();
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found: "+file);
        }
        catch(IOException e) {}
        return text;
    }

    // Aufgaben in Label anzeigen
    public void showAnagram() { exercise.setText(readTxt("./Aufgaben/Anagramm.txt")); }

    public void showArray() { exercise.setText(readTxt("./Aufgaben/Array.txt")); }

    public void showFuhrpark() { exercise.setText(readTxt("./Aufgaben/Fuhrpark.txt")); }

    public void showNullzeile() { exercise.setText(readTxt("./Aufgaben/Nullzeile.txt")); }

    public void showPixel() { exercise.setText(readTxt("./Aufgaben/Pixel.txt")); }

    public void showSortieren() { exercise.setText(readTxt("./Aufgaben/Sortieren.txt")); }


    // Checkboxen Handles


    public void setTimerToTwo () { timer = 120; }
    public void setTimerToThree () { timer = 180; }
    public void setTimerToFour () { timer = 240; }
    public void setTimerToFive () { timer = 300; }

    public static int getTimer() { return timer; }

    public void handleBabystep() {
        if (!hasBabysteps) {
            hasBabysteps = true;
        }
        else {
            hasBabysteps = false;
        }
    }

    public static boolean getBabysteps() { return hasBabysteps; }

    public void handleATDD() {
        if (!hasAddt) {
            hasAddt = true;
        }
        else {
            hasAddt = false;
        }
    }


    // Buttonhandles

    public void handleExitButton() {
        Platform.exit();
    }

    static String exerciseText;

    public void handleStartMenuButton() throws IOException {
        if ("".equals(exercise.getText())) {
            errorExercise.setText("You need to choose an exercise");

        }
        else {
/*            if(babysteps == true) {
                // überprüfen, ob überhaupt ein RadioButton ausgewählt wurde
                RadioButton selectedButton = (RadioButton) babystepTimeGroup.getSelectedToggle();
                babystepTime = Integer.parseInt(selectedButton.getText());
            }
*/
            if (!hasAddt) {
                exerciseText = exercise.getText();

                // Muss in dieser Methode passieren, da FXMLLoader nicht in einer anderen
                // static Methode aufgerufen werden kann
                // layoutTDDT2.fxml ist die schönste variante
                Parent areaLoad = FXMLLoader.load(getClass().getResource("/layoutTDDT.fxml"));
                Scene testArea = new Scene(areaLoad);

                // statt die selbe stage zu nutzen:
                //Stage menuStage = new Stage();

                Stage menuStage = TDDTMain.getStage();
                menuStage.setScene(testArea);
                menuStage.setMaximized(isMaximized);

                //String stylesheet = getClass().getResource("/tddt.css").toExternalForm();
                //testArea.getStylesheets().add(stylesheet);


                // geben Fehlermeldung
                // exerciseTxt.setText(exerciseText);
                // LayoutTDDTController.initialize();

                menuStage.show();
            }

            else {
                String exerciseText = exercise.getText();

                Parent areaLoad = FXMLLoader.load(getClass().getResource("/layoutATDD.fxml"));
                Scene testArea = new Scene(areaLoad);

                // statt die selbe stage zu nutzen:
                // Stage menuStage = new Stage();

                Stage menuStage = TDDTMain.getStage();
                menuStage.setScene(testArea);
                menuStage.setMaximized(isMaximized);

                //String stylesheet = getClass().getResource("/tddt.css").toExternalForm();
                //testArea.getStylesheets().add(stylesheet);


                // geben Fehlermeldung
                // exerciseTxt.setText(exerciseText);
                // LayoutTDDTController.initialize();

                menuStage.show();
            }

        }
    }

    public void handleBackToTestsButton(ActionEvent actionEvent) throws IOException {

    }


    public static String getExerciseText() {
        return exerciseText;

    }

    // getter-setter Bereich


}
