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
import tddcycle.TDDCycle;
import tddtMain.TDDTMain;

import java.io.IOException;

public class LayoutTDDTController {

   // Variablen
   boolean isMaximized = false;
   int numberTests = 0;
   int timer = 0;
   int time = 0;
   Phases phases = new Phases("red");
   boolean initialized = false;
   String oldSourceCode;

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
   Babysteps babysteps;


   public void initialize() {
      if(!initialized) {
         initialized = true;
         if(LayoutMenuController.getBabysteps()) {
            timer = LayoutMenuController.getTimer();
            time = timer;
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
         oldSourceCode = "public class Class {\n  // TODO\n}";
      }
   }

   public void handleRunButton() {
      if (LayoutMenuController.getBabysteps()) timeline();

      // Phase rot
      if (phases.getPhase().equals("red")) {
         // sollte nicht kompilieren oder ein test soll fehl schlagen

         // überprüfen, dass genau ein Test mehr vorhanden ist als vorher
         int newNumberTests = 0;
         String testCodeText = testCode.getText();
         String[] parts = testCodeText.split(" ");
         for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("@Test")) newNumberTests++;
         }
         // System.out.println("number Tests = " + numberTests + "\nNumber New Tests = " + newNumberTests);
         if (newNumberTests - numberTests != 1) System.out.println("Es muss genau ein neuer Test geschrieben werden");   // TODO in label schreiben (unter Aufgabentext?)
         else {
            // testen, ob kompiliert / Tests durchlaufen
            if (!TDDCycle.isCompiling(sourceCode.getText(), testCode.getText()) || TDDCycle.isTestfailing(sourceCode.getText(), testCode.getText())) {
               // TODO prüfen, dass nur ein Test fehl schlägt
               // TODO prüfen, weshalb es nicht kompiliert
               numberTests++;
               phases.setPhase("green");
               labelTestCode.setStyle("");
               testCode.setEditable(false);
               labelSourceCode.setStyle("-fx-text-fill: GREEN; -fx-font-weight: bold;");
               sourceCode.setEditable(true);
            }
         }

      // Phase grün
      } else if (phases.getPhase().equals("green")) {
         // muss kompilieren und die tests müssen durchlaufen
         if (TDDCycle.isCompiling(sourceCode.getText(), testCode.getText()) && !TDDCycle.isTestfailing(sourceCode.getText(), testCode.getText())) {
            // timeline(); TODO muss neu starten
            phases.setPhase("refactor");
            labelSourceCode.setStyle("");
            labelRefactor.setStyle("-fx-font-weight: bold;");
            oldSourceCode = sourceCode.getText();
         }

      // Phase refactor
      } else if (phases.getPhase().equals("refactor")) {
         // muss kompilieren und die tests müssen durchlaufen
         if (TDDCycle.isCompiling(sourceCode.getText(), testCode.getText()) && !TDDCycle.isTestfailing(sourceCode.getText(), testCode.getText())) {
            phases.setPhase("red");
            labelRefactor.setStyle("");
            labelTestCode.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
            testCode.setEditable(true);
            sourceCode.setEditable(false);
         }

      }
   }

   public void handleBackToTestsButton()
   {
      sourceCode.setText(oldSourceCode);
      phases.setPhase("red");
      labelSourceCode.setStyle("");
      labelTestCode.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
      testCode.setEditable(true);
      sourceCode.setEditable(false);
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
