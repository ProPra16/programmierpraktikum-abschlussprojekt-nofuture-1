import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;

public class LayoutTDDTController
{

   @FXML
   Label exerciseTDDT;

   public static void setzeAufgabe(String exerciseText)
   {
      FXMLLoader fxmlLoader = new FXMLLoader(LayoutTDDTController.class.getResource("layoutTDDT.fxml"));
      try
      {
         fxmlLoader.load();
         fxmlLoader.getController();
         LayoutTDDTController tddtController = fxmlLoader.getController();
         tddtController.exerciseTDDT.setText(exerciseText);
      } catch (IOException e)
      {
         e.printStackTrace();
      }
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
