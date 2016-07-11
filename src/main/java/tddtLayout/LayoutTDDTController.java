package tddtLayout;

import babysteps.Babysteps;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.util.Duration;
import phases.Phases;
import tddcycle.TDDCycle;
import tddtMain.TDDTMain;
import vk.core.api.CompileError;
import vk.core.api.CompilerResult;
import vk.core.api.TestResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class LayoutTDDTController
{

   // Variablen
   int numberTests = 0;
   int timer = 0;
   int time = 0;
   Phases phases = new Phases("red");
   String oldSourceCode;
   Timeline timeline = new Timeline();
   Babysteps babysteps;
   int buttonClicked = 0;


   @FXML
   TextArea exerciseTxt;
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
   @FXML
   TextArea compilationError;
   @FXML
   Label statusCycle;

   @FXML
   public void initialize()
   {
      if (LayoutMenuController.getBabysteps()) {
         timer = LayoutMenuController.getTimer();
         time = timer;
         labelTime.setText(Integer.toString(timer));
         timeline();
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
      compilationError.setText("Schreibe den Testcode.");
   }


   public void handleRunButton()
   {

      if (timer == 0) timeline.stop();
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
         if (newNumberTests - numberTests != 1)
            compilationError.setText("Es muss genau ein neuer Test geschrieben werden");   // TODO in label schreiben (unter Aufgabentext?)
         else {
            // testen, ob kompiliert / Tests durchlaufen
            if (!TDDCycle.isCompiling(sourceCode.getText(), testCode.getText()) || TDDCycle.isTestfailing(sourceCode.getText(), testCode.getText())) {

               // prüfen, weshalb es nicht kompiliert: einziger grund darf sein, dass methode nicht gefunden wurde
               ArrayList<CompileError> compileErrors = new ArrayList(TDDCycle.getCompileErrors(sourceCode.getText(), testCode.getText()));
               if(compileErrors != null) {
                  if(compileErrors.size() == 1) {
                     CompileError compileError = compileErrors.get(0);
                     if(compileError.toString().contains("error:cannot find symbol")) {
                        timeline.stop();
                        timer = time;
                        timeline.play();
                        numberTests++;
                        phases.setPhase("green");
                        labelTestCode.setStyle("");
                        testCode.setEditable(false);
                        labelSourceCode.setStyle("-fx-text-fill: GREEN; -fx-font-weight: bold;");
                        sourceCode.setEditable(true);
                        compilationError.setText("Schreibe nun den passenden Code zum Test.\n\n" + compileError.toString());
                     } else {
                        compilationError.setText(compileError.toString());
                     }

                  } else { // mehr als ein Kompilierfehler
                     compilationError.setText("");
                     for(CompileError compileError : compileErrors) {
                        compilationError.setText(compilationError.getText() + "\n\n" + compileError.toString());
                     }
                  }

               } else {    // falls kompiliert: ein test muss fehl schlagen
                  TestResult testResult = TDDCycle.getTestResult(sourceCode.getText(), testCode.getText());
                  if(testResult.getNumberOfFailedTests() == 1) {
                     timeline.stop();
                     timer = time;
                     timeline.play();
                     numberTests++;
                     phases.setPhase("green");
                     labelTestCode.setStyle("");
                     testCode.setEditable(false);
                     labelSourceCode.setStyle("-fx-text-fill: GREEN; -fx-font-weight: bold;");
                     sourceCode.setEditable(true);
                     compilationError.setText("Schreibe nun den passenden Code zum Test.");

                  } else {
                     compilationError.setText("Es muss genau ein Test fehl schlagen");   // Fehler der Tests ausgeben
                  }
               }
            }
         }

         // Phase grün
      } else if (phases.getPhase().equals("green")) {


         // muss kompilieren und die tests müssen durchlaufen
         if (TDDCycle.isCompiling(sourceCode.getText(), testCode.getText()) && !TDDCycle.isTestfailing(sourceCode.getText(), testCode.getText())) {
            compilationError.setText("Test bestanden. Click den Button 'Refactor'.");
            buttonClicked = 0;

         }


         // Phase refactor
      } /*else if (phases.getPhase().equals("refactor")) {

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

      }*/
   }

   public void handleBackToTestsButton()
   {
      if (phases.getPhase().equals("green")) {
         timeline.stop();
         timer = time;
         timeline.play();
         numberTests--;
         sourceCode.setText(oldSourceCode);
         phases.setPhase("red");
         labelSourceCode.setStyle("");
         labelTestCode.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
         testCode.setEditable(true);
         sourceCode.setEditable(false);
         compilationError.setText(" ");
      }
   }

   public void handleBackButton() throws IOException
   {
      LayoutMenuController.setHasAddt(false);
      LayoutMenuController.setHasBabysteps(false);
      FXMLLoader loader = new FXMLLoader();
      TDDTMain.rootPane.setCenter(loader.load(getClass().getResource("/layoutMenu.fxml")));
   }

   public void timeline()
   {
      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.getKeyFrames().addAll(
              new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
                 labelTime.setText(String.valueOf(timer));
                 timer--;

                 babysteps = new Babysteps(phases.getPhase(), sourceCode.getText(), testCode.getText(), timer);

                 if (timer == 0) {
                    timeline.stop();
                    if (phases.getPhase().equals("red")) {
                       sourceCode.setText(babysteps.getCode());
                       compilationError.setText("SourceCode zurückgesetzt.");
                       phases.setPhase(babysteps.getPhase());

                    }
                    if (phases.getPhase().equals("green")) {
                       testCode.setText(babysteps.getTestCode());
                       compilationError.setText("TestCode zurückgesetzt.");
                       phases.setPhase(babysteps.getPhase());

                    }

                    System.out.println("code: \n" + sourceCode.getText() + "testcode:\n " + testCode.getText() + "phase:\n " + phases.getPhase());

                 }
              }));
      timeline.play();
   }


   public void handleRefactor()
   {
      if (buttonClicked == 0) {
         buttonClicked++;
         timeline.stop();
         phases.setPhase("refactor");
         labelSourceCode.setStyle("");
         labelRefactor.setStyle("-fx-font-weight: bold;");
         oldSourceCode = sourceCode.getText();
         testCode.setEditable(true);
         sourceCode.setEditable(true);
         compilationError.setText("Du kannst deinen Code verbessern und wenn du fertig bist nochmal auf den Button 'Refactor' clicken.");

      } else {
         if (TDDCycle.isCompiling(sourceCode.getText(), testCode.getText()) && !TDDCycle.isTestfailing(sourceCode.getText(), testCode.getText())) {
            phases.setPhase("red");
            labelRefactor.setStyle("");
            labelTestCode.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
            testCode.setEditable(true);
            sourceCode.setEditable(false);
            timer = time;
            timeline.play();
            compilationError.setText("Schreibe einen neuen Test. ");
         }
      }
      //TODO
      testCode.setEditable(true);
      sourceCode.setEditable(true);
   }

}
