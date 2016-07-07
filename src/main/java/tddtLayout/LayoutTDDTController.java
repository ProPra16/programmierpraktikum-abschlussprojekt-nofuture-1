package tddtLayout;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tddtMain.TDDTMain;

import java.io.IOException;

public class LayoutTDDTController {

   // Variablen
   boolean isMaximized = true;

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
   Label labelTestCode;
   @FXML
   Label labelSourceCode;
   @FXML
   Label labelRefactor;

   public static void initialize()
   {
      // gibt Fehler da static
      testCode.setText("import static org.junit.Assert.*;\nimport org.junit.Test;\npublic class TestClass {\n  @Test\n  public void test() {\n    // TODO\n  }\n}");
      testCode.setEditable(true);
      sourceCode.setText("public class Class {\n  // TODO\n}");
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
      Parent areaLoad = FXMLLoader.load(getClass().getResource("/layoutMenu.fxml"));
      Scene testArea = new Scene(areaLoad);

      // statt die selbe stage zu nutzen:
      // Stage menuStage = new Stage();

      Stage menuStage = TDDTMain.getStage();
      menuStage.setScene(testArea);
      menuStage.setMaximized(isMaximized);

      //String stylesheet = getClass().getResource("/tddt.css").toExternalForm();
      //testArea.getStylesheets().add(stylesheet);
      menuStage.show();
   }
}
