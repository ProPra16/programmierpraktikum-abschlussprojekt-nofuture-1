package tddtLayout;

import babysteps.Babysteps;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
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
   public TextArea sourceCode;
   @FXML
   public TextArea testCode;
   @FXML
   Label labelTestCode;
   @FXML
   Label labelSourceCode;
   @FXML
   Label labelRefactor;

   Timeline timeline = new Timeline();
   int timer = 120;
   Babysteps babysteps;

   public void initialize() {
      exerciseTxt.setText(LayoutMenuController.getExerciseText());
      testCode.setText("import static org.junit.Assert.*;\nimport org.junit.Test;\npublic class TestClass {\n  @Test\n  public void test() {\n    // TODO\n  }\n}");
      testCode.setEditable(true);
      sourceCode.setText("public class Class {\n  // TODO\n}");
      sourceCode.setEditable(false);
   }

   public void handleRunButton()
   { timeline();
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

   String phase= "GREEN";
   String code = "123";
   String testCode2 = "456";
   public void timeline() {


      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.getKeyFrames().addAll(
              new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {

                 labelTime.setText(String.valueOf(timer));
                 timer--;
                 babysteps = new Babysteps(phase,code,testCode2, timer);
                 code= babysteps.getCode();
                 testCode2=babysteps.getTestCode();
                 phase=babysteps.getPhase();
                 if (timer==0){
                    timeline.stop();
                    System.out.println("code"+ code+"testcode"+testCode2+"phase"+phase);
                 }
              }));
      timeline.play();


   }



}
