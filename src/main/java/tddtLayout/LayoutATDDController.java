package tddtLayout;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class LayoutATDDController extends LayoutTDDTController{

    // Variablen

    // FXML
   @FXML
   public TextArea acceptanceTestCode;
   @FXML
   Label labelAzeptanzTest;

   private String acceptanceCode = "public class Akzeptanztest {\n" +
           "\t@Test\n\tpublic void test() {\n\t\t// TODO\n\t}\n}";

   @FXML
   @Override
   public void initialize() {
      super.initialize();
      acceptanceTestCode.setEditable(true);
      testCode.setEditable(false);
      labelAzeptanzTest.setStyle("-fx-text-fill: TOMATO; -fx-font-weight: bold;");  //bei bestanden auf MediumSeaGreen
      labelTestCode.setStyle("-fx-text-fill: BLACK; -fx-font-weight: normal;");
      cycle.setPhase("akzeptanz");
      statusCycle.setText("Schreibe einen Akzeptanztest");
      acceptanceTestCode.setText(acceptanceCode);
      if (LayoutMenuController.getBabysteps())
      {
         babysteps.stop();
      }
   }

   @Override
   public void handleRunButton() {
      // Phase rot
      if(cycle.getPhase().equals("akzeptanz"))
      {

         cycle.compile(acceptanceTestCode.getText(), sourceCode.getText(), testCode.getText());
         if (cycle.hasCompileErrors())
         {
//            acceptanceTestCode.setEditable(false);
            setPhaseRed();
            if (LayoutMenuController.getBabysteps())
            {
               babysteps.start();
            }
         }
      }
      super.handleRunButton();
   }

   public void handleBackToAcceptance () {
      // TODO
   }
}