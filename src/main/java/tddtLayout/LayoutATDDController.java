package tddtLayout;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tddtMain.TDDTMain;

import java.io.IOException;

public class LayoutATDDController {

    // Variablen

    // FXML
   @FXML
   TextArea exerciseTxt;
   @FXML
   Text status;
   @FXML
   Label labelTime;
   public static TextArea sourceCode;
   @FXML
   public static TextArea testCode;
   @FXML
   public static TextArea acceptanceTest;
   @FXML
   Label labelTestCode;
   @FXML
   Label labelSourceCode;
   @FXML
   Label labelRefactor;
   @FXML
   TextArea compilationError;
   @FXML
   Label statusCycle;

   public void initialize() {
      exerciseTxt.setText(LayoutMenuController.getExerciseText());
      testCode.setText("import static org.junit.Assert.*;\nimport org.junit.Test;\npublic class TestClass {\n  @Test\n  public void test() {\n    // TODO\n  }\n}");
      testCode.setEditable(true);
      sourceCode.setText("public class Class {\n  // TODO\n}");
      sourceCode.setEditable(false);
   }

   public void handleRunButton()
   {
//      TODO noch ergänzen
   }

   public void handleBackToTestsButton()
   {
//      TODO noch ergänzen
   }

   public void handleBackButton() throws IOException
   {
      LayoutMenuController.setHasAddt(false);
      LayoutMenuController.setHasBabysteps(false);
      FXMLLoader loader = new FXMLLoader();
      TDDTMain.rootPane.setCenter(loader.load(getClass().getResource("/layoutMenu.fxml")));
   }

   public void handleRefactor() {
      //TODO
   }
}