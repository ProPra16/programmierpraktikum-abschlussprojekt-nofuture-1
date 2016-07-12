package tddtLayout;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import tddcycle.TDDCycle;

public class LayoutATDDController extends LayoutTDDTController{

    // Variablen

    // FXML
  /* @FXML
   public TextArea acceptanceTestCode;
   @FXML
   Label labelAzeptanzTest;

   @FXML
   @Override
   public void initialize() {
      super.initialize();  //ruft originale Methode auf
      acceptanceTestCode.setEditable(true);
      testCode.setEditable(false);
      labelAzeptanzTest.setStyle("-fx-text-fill: HOTPINK; -fx-font-weight: bold;");  //setze Akzeptanztest rot und fett
      labelTestCode.setStyle("-fx-text-fill: BLACK; -fx-font-weight: normal;");
      phases.setPhase("akzeptanz");
      compilationError.setText("Schreibe einen Akzeptanztest.");
   }

   @Override
   public void handleRunButton() {

      if (timer==0) timeline.stop();
      // Phase rot
      if(phases.getPhase().equals("akzeptanz"))
      {
         acceptanceTestCode.setEditable(false);
         testCode.setEditable(true);
         phases.setPhase("red");
         labelAzeptanzTest.setStyle("-fx-text-fill: BLACK; -fx-font-weight: normal;");
         labelTestCode.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
         compilationError.setText("Schreibe den Testcode.");
      }
      else if (phases.getPhase().equals("red")) {

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
               timeline.stop();
               timer= time;
               timeline.play();
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
            timeline.stop();
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
            labelAzeptanzTest.setStyle("-fx-text-fill: RED; -fx-font-weight: bold;");
            acceptanceTestCode.setEditable(true);
            testCode.setEditable(false);
            sourceCode.setEditable(false);
         }

      }
   }

   public void handleBackToAcceptance () {
      // TODO
   }
*/
}