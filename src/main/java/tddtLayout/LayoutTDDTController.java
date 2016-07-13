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
           babysteps.start();
        } else {
         labelTime.setVisible(false);
         textRemainingTime.setVisible(false);
        }
        exerciseTxt.setText(LayoutMenuController.getExerciseText());
        resetCode(null);
        setPhaseRed();
      System.out.println("hallo");
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
        labelSourceCode.setStyle("");
        labelRefactor.setStyle("");
        labelTestCode.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
        testCode.setEditable(true);
        sourceCode.setEditable(false);
        statusCycle.setText("Schreibe den Testcode.");
    }

    private void setPhaseGreen() {
        cycle.setPhase("green");
        labelTestCode.setStyle("");
        labelRefactor.setStyle("");
        labelSourceCode.setStyle("-fx-text-fill: GREEN; -fx-font-weight: bold;");
        testCode.setEditable(false);
        sourceCode.setEditable(true);
        statusCycle.setText("Schreibe nun den passenden Code zum Test.");
    }

    private void setPhaseRefactor() {
        cycle.setPhase("refactor");
        labelTestCode.setStyle("");
        labelSourceCode.setStyle("");
        labelRefactor.setStyle("-fx-font-weight: bold;");
        testCode.setEditable(false);
        sourceCode.setEditable(true);
        statusCycle.setText("Verbessere deinen Code oder click auf den Button 'Refactor'.");
    }


    public void handleRunButton(){
      // Phase rot
      if (cycle.getPhase().equals("red")) {

         // sollte nicht kompilieren oder ein test soll fehl schlagen

         if (!hasNewTest()) {
             statusCycle.setText("Es muss genau ein neuer Test geschrieben werden");   // TODO in label schreiben (unter Aufgabentext?)
         } else {

             cycle.compile(sourceCode.getText(), testCode.getText());

             if (cycle.hasCompileErrors()) {
                 if (cycle.getCompileErrorsTest().size() == 1) {
                     if (cycle.getCompileErrorsTest().toArray()[0].toString().contains("error:cannot find symbol")) {
                         oldTestCode = testCode.getText();
                         numberTests++;
                        babysteps.reset();
                         setPhaseGreen();
                     }
                 }
                 cycle.getCompileErrorsTest().forEach((s) -> {
                     compilationError.setText(s + "\n");
                 });
             } else if (cycle.hasFailingTest()) {
                oldTestCode = testCode.getText();
                numberTests++;
                babysteps.reset();
                 setPhaseGreen();
             } else {
                 statusCycle.setText("Es muss genau ein Test fehlschlagen");
             }
         }

         // Phase grün
      } else if (cycle.getPhase().equals("green")) {

          cycle.compile(sourceCode.getText(), testCode.getText());

         // muss kompilieren und die tests müssen durchlaufen
         if (!cycle.hasCompileErrors() && !cycle.hasFailingTest()) {
             if (LayoutMenuController.getBabysteps()) {
                 babysteps.stop();
             }
            oldSourceCode = sourceCode.getText();
            statusCycle.setText("Test bestanden. Click den Button 'Refactor'.");
            compilationError.setText("");
            buttonClicked = 0;
         }else{
             cycle.getCompileErrorsCode().forEach((s) -> {
                 compilationError.setText(s + "\n");
             });
         }
      }
   }

    public boolean hasNewTest(){
        int newNumberTests = 0;
        String testCodeText = testCode.getText();
        String[] parts = testCodeText.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("@Test")) newNumberTests++;
        }
        return newNumberTests - numberTests == 1;
    }

   public void handleBackToTestsButton(){
      if (cycle.getPhase().equals("green")) {
          if (LayoutMenuController.getBabysteps()) {
              babysteps.reset();
          }
          numberTests--;
          resetCode(null);
      }
   }

   public void handleBackButton() throws IOException{
      LayoutMenuController.setHasAddt(false);
      LayoutMenuController.setHasBabysteps(false);
      FXMLLoader loader = new FXMLLoader();
      TDDTMain.rootPane.setCenter(loader.load(getClass().getResource("/layoutMenu.fxml")));
   }

   public void handleRefactor(){
      if (buttonClicked == 0 && cycle.getPhase().equals("green")) {
         buttonClicked++;
          if (LayoutMenuController.getBabysteps()) {
              babysteps.stop();
          }
         setPhaseRefactor();
         labelTime.setVisible(false);
         textRemainingTime.setVisible(false);

      } else {
          cycle.compile(sourceCode.getText(), testCode.getText());

         if (!cycle.hasCompileErrors() && !cycle.hasFailingTest()) {
            setPhaseRed();
             if (LayoutMenuController.getBabysteps()) {
                 babysteps.reset();
                 babysteps.start();
             }
            statusCycle.setText("Schreibe einen neuen Test.");
            if (LayoutMenuController.getBabysteps()) {
               labelTime.setVisible(true);
               textRemainingTime.setVisible(true);
            }
         }
         else {
             cycle.getCompileErrorsCode().forEach((s) -> {
                 compilationError.setText(s + "\n");
             });

         }
      }

   }

}
