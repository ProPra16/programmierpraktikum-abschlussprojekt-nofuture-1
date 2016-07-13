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

   private String acceptanceCode = "import static org.junit.Assert.*;\n" +
           "import org.junit.Test;\n\n" +
           "public class AkzeptanztestClass {\n" +
           "\t@Test\n\tpublic void test() {\n\t\t// TODO\n\t}\n}";
   private String lastPhase = "";

   @FXML
   @Override
   public void initialize() {
      super.initialize();
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
            acceptanceCode = acceptanceTestCode.getText();
            chooseLastPhase();
            if (LayoutMenuController.getBabysteps())
            {
               babysteps.start();
            }
            cycle.getCompileErrorsAkzeptanz().forEach((s) ->
               compilationError.setText(s + "\n")
            );
         }
      }
      super.handleRunButton();
   }

   public void handleAcceptance () {

      switch (cycle.getPhase())
      {
         case "red":
            labelTestCode.setStyle("-fx-text-fill: BLACK; -fx-font-weight: normal;");
            break;
         case "green":
            labelSourceCode.setStyle("-fx-text-fill: BLACK; -fx-font-weight: normal;");
            break;
         case "refactor":
            labelRefactor.setStyle("-fx-font-weight: normal;");
            break;
      }
      lastPhase = cycle.getPhase();
      cycle.setPhase("akzeptanz");
      statusCycle.setText("Korrigiere deinen Akzeptanztest");
      acceptanceTestCode.setText(acceptanceCode);
      if (LayoutMenuController.getBabysteps())
      {
         babysteps.stop();
      }
   }

   private void chooseLastPhase()
   {
      switch (lastPhase)
      {
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
   }
}