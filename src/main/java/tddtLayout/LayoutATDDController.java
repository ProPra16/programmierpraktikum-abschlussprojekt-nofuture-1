package tddtLayout;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class LayoutATDDController extends LayoutTDDTController
{
   // Variablen
   private String acceptanceCode = "import static org.junit.Assert.*;\n" +
           "import org.junit.Test;\n\n" +
           "public class AkzeptanztestClass {\n" +
           "\t@Test\n\tpublic void test() {\n\t\t// TODO\n\t}\n}";
   private String lastPhase = "";
   private int countAcceptanceTest = 0;

   // FXML
   @FXML
   TextArea acceptanceTestCode;
   @FXML
   Label labelAkzeptanzTest;

   @FXML
   @Override
   public void initialize()
   {
      isAtdd = true;
      super.initialize();
      testCode.setEditable(false);
      labelTestCode.setStyle("");
      setPhaseAcceptance();
      if (LayoutMenuController.getBabysteps()) {
         babysteps.stop();
      }
   }

   @Override
   public void handleRunButton()
   {
      if (cycle.getPhase().equals("akzeptanz")) {
         cycle.compile(acceptanceTestCode.getText(), sourceCode.getText(), testCode.getText());
         chooseLastPhase();
         if (cycle.hasCompileErrors()) {
            acceptanceCode = acceptanceTestCode.getText();
            chooseLastPhase();
            if (LayoutMenuController.getBabysteps()) {
               babysteps.start();
            }
            cycle.getCompileErrorsAkzeptanz().forEach((s) -> compilationError.setText(s + "\n")
            );
         }
      }
      super.handleRunButton();
   }

   public void handleAcceptance()
   {
      setToNormal();
      cycle.setPhase("akzeptanz");
      statusCycle.setText("Korrigiere deinen Akzeptanztest");
      acceptanceTestCode.setEditable(true);
      acceptanceTestCode.setText(acceptanceCode);
      if (LayoutMenuController.getBabysteps()) {
         babysteps.stop();
      }
   }

   @Override
   public void handleRefactor(){
      atddRefactoring();
   }

   private void atddRefactoring() {
      cycle.compile(acceptanceTestCode.getText(), sourceCode.getText(), testCode.getText());
      if (!cycle.hasCompileErrors() && !cycle.hasFailingTest())
      {
         if (buttonClicked == 0 && cycle.getPhase().equals("green")) {
            buttonClicked++;
            if (LayoutMenuController.getBabysteps()) {
               babysteps.stop();
            }
            accomplishAcceptanceTest();
            labelTime.setVisible(false);
            textRemainingTime.setVisible(false);

         } else {
            cycle.compile(acceptanceTestCode.getText(), sourceCode.getText(), testCode.getText());

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
      else {
         tddRefactoring();
      }

   }

   private void setPhaseAcceptance()
   {
      labelAkzeptanzTest.setStyle("-fx-text-fill: TOMATO; -fx-font-weight: bold;");
      cycle.setPhase("akzeptanz");
      if (countAcceptanceTest > 0) {
         statusCycle.setText("Schreibe weiteren Akzeptanztest");
      } else {
         statusCycle.setText("Schreibe einen Akzeptanztest");
      }
      countAcceptanceTest++;
      acceptanceTestCode.setText(acceptanceCode);
   }

   private void accomplishAcceptanceTest()
   {
      setPhaseRefactor();
      if (!cycle.hasCompileErrors() && !cycle.hasFailingTest()) {
         labelAkzeptanzTest.setStyle("-fx-text-fill: MEDIUMSEAGREEN; -fx-font-weight: bold;");
         acceptanceTestCode.setEditable(true);
         statusCycle.setText("Dein Akzeptanztest wird erfüllt! Verbessere deinen Code mit Refactor");
         acceptanceTestCode.setText(acceptanceCode);
      }
   }

   private void chooseLastPhase()
   {
      switch (lastPhase) {
         case "red":
            setPhaseRed();
            break;
         case "green":
            setPhaseGreen();
            break;
         case "refactor":
            setPhaseRefactor();
            break;
         default:
            setPhaseRed();
            break;
      }
      acceptanceTestCode.setEditable(false);
   }

   private void setToNormal()
   {
      switch (cycle.getPhase()) {
         case "red":
            labelTestCode.setStyle("");
            break;
         case "green":
            labelSourceCode.setStyle("");
            break;
         case "refactor":
            labelRefactor.setStyle("");
            break;
      }
      lastPhase = cycle.getPhase();
   }
}