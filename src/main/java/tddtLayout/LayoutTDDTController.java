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
   TDDCycle cycle = new TDDCycle("red");
   Babysteps babysteps;
   int buttonClicked = 0;

   String oldSourceCode = "public class Class {\n  // TODO\n}";
   String oldTestCode = "import static org.junit.Assert.*;\nimport org.junit.Test;\npublic class TestClass {\n  @Test\n  public void test() {\n    // TODO\n  }\n}";

   @FXML
   TextArea exerciseTxt;
   @FXML
   Label labelTime;
   @FXML
   Text textRemainingTime;
   @FXML
   TextArea sourceCode;
   @FXML
   TextArea testCode;
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
   public void initialize(){
        if (LayoutMenuController.getBabysteps()) {
         babysteps = new Babysteps(LayoutMenuController.getTimer(), this::resetCode);
         labelTime.textProperty().bind(babysteps);
        } else {
         labelTime.setVisible(false);
         textRemainingTime.setVisible(false);
        }
        exerciseTxt.setText(LayoutMenuController.getExerciseText());
        resetCode(null);
        setPhaseRed();
   }

   private Object resetCode(Object o) {
       testCode.setText(oldTestCode);
       sourceCode.setText(oldSourceCode);
       if(cycle.getPhase().equals("green")){
            setPhaseRed();
       }
       return null;
   }

    private void setPhaseRed() {
        cycle.setPhase("red");
        labelTestCode.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
        testCode.setEditable(true);
        sourceCode.setEditable(false);
        statusCycle.setText("Schreibe den Testcode.");
    }

    private void setPhaseGreen() {
        cycle.setPhase("green");
        labelTestCode.setStyle("");
        labelSourceCode.setStyle("-fx-text-fill: GREEN; -fx-font-weight: bold;");
        testCode.setEditable(false);
        sourceCode.setEditable(true);
        statusCycle.setText("Schreibe nun den passenden Code zum Test.");
    }


    public void handleRunButton(){
      // Phase rot
      if (cycle.getPhase().equals("red")) {

         // sollte nicht kompilieren oder ein test soll fehl schlagen

         if (!hasNewTest()) {
             statusCycle.setText("Es muss genau ein neuer Test geschrieben werden");   // TODO in label schreiben (unter Aufgabentext?)
         } else {

             cycle.compile(sourceCode.getText(), testCode.getText());

             if(cycle.hasCompileErrors()){
                 if(cycle.getCompileErrorsTest().size() == 1) {
                     if(cycle.getCompileErrorsTest().toArray()[0].toString().contains("error:cannot find symbol")) {
                         setPhaseGreen();
                     }
                 }
                 cycle.getCompileErrorsTest().forEach((s) -> {
                     compilationError.setText(s + "\n");
                 });
             }else if(cycle.hasFailingTest()){
                 setPhaseGreen();
             }else{
                 statusCycle.setText("Es muss genau ein Test fehl schlagen");
             }

         // Phase grün
      } else if (phases.getPhase().equals("green")) {

            statusCycle.setText("");
         // muss kompilieren und die tests müssen durchlaufen
         if (TDDCycle.isCompiling(sourceCode.getText(), testCode.getText()) && !TDDCycle.isTestfailing(sourceCode.getText(), testCode.getText())) {
            timeline.stop();
            statusCycle.setText("Test bestanden. Click den Button 'Refactor'.");
            compilationError.setText("");
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

    public boolean hasNewTest(){
        int newNumberTests = 0;
        String testCodeText = testCode.getText();
        String[] parts = testCodeText.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("@Test")) newNumberTests++;
        }
        return newNumberTests - numberTests != 1;
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
         statusCycle.setText(" ");
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
                    if (phases.getPhase().equals("green")) {
                       sourceCode.setText(oldSourceCode);
                       statusCycle.setText("SourceCode zurückgesetzt.");
                       timer = time;
                       timeline.play();
                       phases.setPhase("red");
                       labelTestCode.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
                       testCode.setEditable(true);
                       labelSourceCode.setStyle("");
                       sourceCode.setEditable(false);

                    }
                    else if (phases.getPhase().equals("red")) {
                       testCode.setText(oldTestCode);
                       statusCycle.setText("TestCode zurückgesetzt.");
                       timer = time;
                       timeline.stop();
                       phases.setPhase("refactor");
                       buttonClicked++;
                       labelRefactor.setStyle("-fx-text-fill: BLACK; -fx-font-weight: bold;");
                       testCode.setEditable(true);
                       labelTestCode.setStyle("");
                       sourceCode.setEditable(true);

                    }
                    //System.out.println("code: \n" + sourceCode.getText() + "testcode:\n " + testCode.getText() + "phase:\n " + phases.getPhase());
                 }
              }));
      timeline.play();
   }


   public void handleRefactor()
   {
      if (buttonClicked == 0) {
         buttonClicked++;
         phases.setPhase("refactor");
         labelSourceCode.setStyle("");
         labelRefactor.setStyle("-fx-font-weight: bold;");
         oldSourceCode = sourceCode.getText();
         testCode.setEditable(true);                             // darf nicht nur der Code verbesert werden?
         sourceCode.setEditable(true);
         statusCycle.setText("Verbesser deinen Code oder nicht und click auf Button 'Refactor' .");
         labelTime.setVisible(false);
         textRemainingTime.setVisible(false);

      } else {
         if (TDDCycle.isCompiling(sourceCode.getText(), testCode.getText()) && !TDDCycle.isTestfailing(sourceCode.getText(), testCode.getText())) {
            phases.setPhase("red");
            labelRefactor.setStyle("");
            labelTestCode.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
            testCode.setEditable(true);
            sourceCode.setEditable(false);
            timer = time;
            timeline.play();
            statusCycle.setText("Schreibe einen neuen Test. ");
            if (LayoutMenuController.getBabysteps()) {
               labelTime.setVisible(true);
               textRemainingTime.setVisible(true);
            }
         }
         else {
            ArrayList<CompileError> compileErrors = new ArrayList(TDDCycle.getCompileErrors(sourceCode.getText(), testCode.getText()));
            CompileError compileError = compileErrors.get(0);
            compilationError.setText(compileError.toString());

         }
      }

   }

}
