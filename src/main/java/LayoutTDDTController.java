import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class LayoutTDDTController
{

   @FXML
   Label exerciseTDDT;
   @FXML
   Label time;

   Babysteps babysteps;
   int timer = 120;
   Timeline timeline = new Timeline();

   public static void setzeAufgabe(String exerciseText)
   {
      FXMLLoader fxmlLoader = new FXMLLoader(LayoutTDDTController.class.getResource("layoutTDDT.fxml"));
      try
      {

         fxmlLoader.load();
         LayoutTDDTController tddtController = fxmlLoader.getController();
         tddtController.exerciseTDDT.setText(exerciseText);

      } catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   public void handleRunButton() {

      timeline();

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


String phase= "GREEN";
   String code = "123";
   String testCode = "456";
   public void timeline() {


      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.getKeyFrames().addAll(
              new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {

                 time.setText(String.valueOf(timer));
                 timer--;
                 babysteps = new Babysteps(phase,code,testCode, timer);
                 code= babysteps.getCode();
                 testCode=babysteps.getTestCode();
                 phase=babysteps.getPhase();
                 if (timer==0){
                    timeline.stop();
                    System.out.println("code"+ code+"testcode"+testCode+"phase"+phase);
                 }
              }));
      timeline.play();


   }

}
