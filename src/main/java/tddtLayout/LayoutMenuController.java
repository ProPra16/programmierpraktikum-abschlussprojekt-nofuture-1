package tddtLayout;

import babysteps.Babysteps;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tddtMain.TDDTMain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LayoutMenuController {
    // FXML
    @FXML
    MenuBar menuBar;
    @FXML
    Text status;
    @FXML
    TextArea code;
    @FXML
    TextArea testCode;
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
    public void babysteps() {
        Babysteps babysteps = new Babysteps(status.getText(),code,testCode);
    }

    public void setTimerToTwo () {
        //TODO
    }

    public void setTimerToThree () {
        //TODO
    }

    public void setTimerToFour () {
        //TODO
    }

    public void setTimerToFive () {
        //TODO
    }

    public void handleBabystep() {
        // TODO
    }

    public void handleATDD() {
        // TODO
    }


    // Buttonhandles
    public void handleStartButton() { // muss geändert werden
        if(exercise.getText().equals("")) errorExercise.setText("You need to choose an exercise");
        else {
            menuBar.setDisable(true);
            testCode.setWrapText(true);
            testCode.setText("import static org.junit.Assert.*;\nimport org.junit.Test;\npublic class TestClass {\n  @Test\n  public void test() {\n    // TODO\n  }\n}");
            testCode.setEditable(true);
            code.setWrapText(true);
            code.setText("public class Class {\n  // TODO\n}");
        }
    }

    public void handleExitButton() {
        Platform.exit();
    }

    public void handleStartMenuButton() throws IOException {
        if("".equals(exercise.getText())){
            errorExercise.setText("You need to choose an exercise");
        }

        else {
/*            if(babysteps == true) {
                // überprüfen, ob überhaupt ein RadioButton ausgewählt wurde
                RadioButton selectedButton = (RadioButton) babystepTimeGroup.getSelectedToggle();
                babystepTime = Integer.parseInt(selectedButton.getText());
            }
*/

            String exerciseText = exercise.getText();
            Parent areaLoad = FXMLLoader.load(getClass().getResource("layoutTDDT2.fxml"));
            Scene testArea = new Scene(areaLoad);

            Stage menuStage = TDDTMain.getStage();
            menuStage.setScene(testArea);
            menuStage.setMaximized(true);

            String stylesheet = getClass().getResource("tddt.css").toExternalForm();
            testArea.getStylesheets().add(stylesheet);

//            LayoutTDDTController.setzeAufgabe(exerciseText);
//            gibt Fehlermeldung
//            exerciseTDDT.setText(exerciseText);

        }

    }

    public void handleBackToTestsButton(ActionEvent actionEvent) {
        // TODO
    }
}
