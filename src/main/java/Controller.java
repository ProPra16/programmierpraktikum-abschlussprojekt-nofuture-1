import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import vk.core.api.*;

import java.io.IOException;
import java.util.Collection;

public class Controller {

    @FXML
    GridPane root;

    public void handleExitButton() {
        System.exit(0);
    }

    public void handleBackButton() {
        root.getScene().getWindow().hide();
    }

    public void handleKatalogButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("layoutKatalog.fxml"));
        Scene scene = new Scene(root, 500, 500);
        String stylesheet = getClass().getResource("tddt.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        Stage katalogStage = new Stage();
        katalogStage.setScene(scene);
        katalogStage.setTitle("Uebungskatalog");
        katalogStage.show();
    }

    public void handleStartButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("layoutTddt.fxml"));
        Scene scene = new Scene(root, 1200, 500);
        String stylesheet = getClass().getResource("tddt.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        Stage katalogStage = new Stage();
        katalogStage.setScene(scene);
        katalogStage.setTitle("Uebungskatalog");
        katalogStage.show();
    }

    @FXML
    Text status;
    @FXML
    TextField className;
    @FXML
    TextArea code;
    @FXML
    Text errorClassName;
    @FXML
    Text errorCode;


    @FXML
    TextField testClassName;
    @FXML
    TextArea testCode;
    @FXML
    Text errorTestClassName;
    @FXML
    Text errorTestCode;



    public void handleSaveButton() {
        if(status.getText().equals("Write a failing test")) handleTestCode();







    }

    private void handleTestCode() {
        if(testClassName.getText().equals("")) {
            errorTestClassName.setText("you need to enter a class phase");
        } else if(testCode.getText().equals("")) {
            errorTestClassName.setText("");
            errorTestCode.setText("you need to enter code");
        }else {
            errorTestClassName.setText("");
            errorTestCode.setText("");
            if (status.getText().equals("Write a failing test")) {
                CompilationUnit compilationUnit = new CompilationUnit(testClassName.getText(), testCode.getText(), true);
                JavaStringCompiler compiler = CompilerFactory.getCompiler(compilationUnit);
                compiler.compileAndRunTests();
                CompilerResult compilerResult = compiler.getCompilerResult();
                TestResult testResult = compiler.getTestResult();
                // was wenn, es nicht kompiliert wegen Fehler im TestCode?
                // Fehler ausgeben
                // Exception?
                if(compilerResult.hasCompileErrors()) {
                    Collection<CompileError> compileErrors = compilerResult.getCompilerErrorsForCompilationUnit(compilationUnit);
                    for (CompileError ce : compileErrors) {
                        System.out.println(ce);
                        if(ce.getMessage().contains("should be declared in a file named")) {}


                        if(ce.getMessage().contains("cannot find symbol")) {}
                        //"cannot find symbol\n  symbol:   class Test"    import org.junit.Test;   fehlt

                        /*
                        kein error, wenn
                        import static org.junit.Assert.*;
                        fehlt
                        */

                    }
                   // status.setText("Make the test pass");
                   // status.setStyle("-fx-fill: GREEN;");
                   // testCode.setEditable(false);
                   // code.setEditable(true);
                } else {
                    // testen ob mehrere Tests fehl schlagen

                    // testResult.getNumberOfFailedTests() == 1

                }


                /*
                CompilerResult compilerResult = compiler.getCompilerResult();
                if(compilerResult.hasCompileErrors()) {
                    Collection<CompileError> compileErrors = compilerResult.getCompilerErrorsForCompilationUnit(compilationUnit);
                    for (CompileError ce : compileErrors) System.out.println(ce);
                } else {

                    //assertFalse(TestHelpers.getErrorMessages(compiler, compilerResult),
                    //        compilerResult.hasCompileErrors());
                    TestResult testResult = compiler.getTestResult();
                    //   int x = testResult.getNumberOfSuccessfulTests();

                    System.out.println(testResult.getNumberOfFailedTests());
                    //      if(testResult.getNumberOfFailedTests() > 0) {
                    //         Collection<TestFailure> testFailures = testResult.getTestFailures();
                    //       for (TestFailure tf : testFailures) System.out.println(tf);
                    //  }
*/

            }

        }



    }


}
