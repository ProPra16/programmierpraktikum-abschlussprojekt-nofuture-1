import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import vk.core.api.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public class Controller {

    @FXML
    GridPane root;

    public void handleExitButton() {
        System.exit(0);
    }




    public String readTxt(String file) {
        String text = "";
        try
        {
            StringBuffer buffer = new StringBuffer();
            FileReader in = new FileReader(file);
            for (int n;(n = in.read()) != -1;buffer.append((char) n));
            in.close();

            text = buffer.toString();
        }
        catch(FileNotFoundException e) {}
        catch(IOException e) {}
        return text;
    }

    @FXML
    Label task;

    public void showAnagram(ActionEvent actionEvent) { task.setText(readTxt("./Aufgaben/Anagramm.txt")); }

    public void showArray(ActionEvent actionEvent) { task.setText(readTxt("./Aufgaben/Array.txt")); }

    public void showFuhrpark(ActionEvent actionEvent) { task.setText(readTxt("./Aufgaben/Fuhrpark.txt")); }

    public void showNullzeile(ActionEvent actionEvent) { task.setText(readTxt("./Aufgaben/Nullzeile.txt")); }

    public void showPixel(ActionEvent actionEvent) { task.setText(readTxt("./Aufgaben/Pixel.txt")); }

    public void showRekursiv(ActionEvent actionEvent) { task.setText(readTxt("./Aufgaben/Rekursiv.txt")); }

    public void showReptil(ActionEvent actionEvent) { task.setText(readTxt("./Aufgaben/Reptil.txt")); }

    public void showInterface(ActionEvent actionEvent) { task.setText(readTxt("./Aufgaben/Interface.txt")); }

    public void showSortieren(ActionEvent actionEvent) { task.setText(readTxt("./Aufgaben/Sortieren.txt")); }



    @FXML
    Text status;
    @FXML
    TextArea code;
    @FXML
    Text errorCode;



    @FXML
    TextArea testCode;
    @FXML
    Text errorTestCode;



    public void handleSaveButton() {
        if(status.getText().equals("Write a failing test")) handleTestCode();


    }


    @FXML
    TextField testClassName;
    @FXML
    TextField className;
    @FXML
    Text errorClassName;
    @FXML
    Text errorTestClassName;

    private void handleTestCode() {
        if(testClassName.getText().equals("")) {
            errorTestClassName.setText("you need to enter a class name");
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
                    status.setText("Make the test pass");
                    status.setStyle("-fx-fill: GREEN;");
                    testCode.setEditable(false);
                    code.setEditable(true);
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


    public void handleStartButton() {

        testCode.setWrapText(true);
        testCode.setText("import static org.junit.Assert.*;\nimport org.junit.Test;\npublic class Test {\n  @Test\n  public void test() {\n    // TODO\n  }\n}");
        testCode.setEditable(true);
        code.setWrapText(true);
        code.setText("public class Class {\n  // TODO\n}");
    }


    public void handleBackButton() {
    }


    public void handleRunButton(ActionEvent actionEvent) {
    }
}
