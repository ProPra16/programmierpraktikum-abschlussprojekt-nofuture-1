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
import phases.Phases;
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
   @FXML
   Text textRemainingTime;
   @FXML
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
   int timer = 0;
   Babysteps babysteps;

   Phases phases = new Phases("red");

   public void initialize() {
      if(LayoutMenuController.getBabysteps()) {
         timer = LayoutMenuController.getTimer();
         labelTime.setText(Integer.toString(timer));
      } else {
         labelTime.setVisible(false);
         textRemainingTime.setVisible(false);
      }
      labelTestCode.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
      exerciseTxt.setText(LayoutMenuController.getExerciseText());
      testCode.setText("import static org.junit.Assert.*;\nimport org.junit.Test;\npublic class TestClass {\n  @Test\n  public void test() {\n    // TODO\n  }\n}");
      testCode.setEditable(true);
      sourceCode.setText("public class Class {\n  // TODO\n}");
      sourceCode.setEditable(false);
   }

   public void handleRunButton() {
      if(LayoutMenuController.getBabysteps()) timeline();
      if(phases.getPhase().equals("red")) {
         // writing tests
         // sollte nicht kompilieren oder ein test soll fehl schlagen
         // überprüfen, dass genau ein Test mehr vorhanden ist als vorher


      } else if(phases.getPhase().equals("green")) {
         // writing code
         // muss kompilieren und die tests müssen durchlaufen


      } else if(phases.getPhase().equals("refactor")) {

      }

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
