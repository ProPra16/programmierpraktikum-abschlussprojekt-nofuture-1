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


   @Override
   public void initialize() {
      super.initialize();  //ruft originale Methode auf
      acceptanceTestCode.setEditable(true);
      testCode.setEditable(false);

   }

//   public void handleRunButton()
//   {
////      TODO noch ergänzen
//   }

//   public void handleBackToTestsButton()
//   {
////      TODO noch ergänzen
//   }

//   public void handleBackButton() throws IOException
//   {
//      LayoutMenuController.setHasAddt(false);
//      LayoutMenuController.setHasBabysteps(false);
//      FXMLLoader loader = new FXMLLoader();
//      TDDTMain.rootPane.setCenter(loader.load(getClass().getResource("/layoutMenu.fxml")));
//   }
//
//   public void handleRefactor() {
//      //TODO
//   }
}