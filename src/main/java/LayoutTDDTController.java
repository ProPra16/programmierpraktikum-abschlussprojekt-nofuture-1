import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LayoutTDDTController {

   @FXML
   static Label exerciseTDDT;

   public static void setzeAufgabe(String exerciseText)
   {
      exerciseTDDT.setText(exerciseText);
   }
}
