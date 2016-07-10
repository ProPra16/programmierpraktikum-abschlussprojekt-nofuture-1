package tddtLayout;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class LayoutATDDController extends LayoutTDDTController{

    // Variablen

    // FXML
   @FXML
   Text status;   //Wofür ist der Text???
   @FXML
   public TextArea acceptanceTestCode;
   @FXML
   Label labelAzeptanzTest;

   @Override
   public void initialize() {
      super.initialize();  //ruft originale Methode auf
      acceptanceTestCode.setEditable(true);
      testCode.setEditable(false);
      labelAzeptanzTest.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");  //setze Akzeptanztest rot und fett
      labelTestCode.setStyle("-fx-text-fill: BLACK; -fx-font-weight: normal;");
   }
}