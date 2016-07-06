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

public class LayoutTDDTController
{
   @FXML
   Label exerciseTDDT;
   @FXML
   Text status;
   @FXML
   static TextArea sourceCode;
   @FXML
   static TextArea testCode;

   public static void initialize () {
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

   public void handleBackButton()
   {
//      TODO noch ergänzen
   }
}
