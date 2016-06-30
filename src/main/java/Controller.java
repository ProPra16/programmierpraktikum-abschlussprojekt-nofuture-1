import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
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

    @FXML
    Label errorExercise;

    @FXML
    MenuBar menuBar;


    private String readTxt(String file) {
        errorExercise.setText("");
        String text = "";
        try
        {
            StringBuffer buffer = new StringBuffer();
            FileReader in = new FileReader(file);
            for (int n; (n = in.read()) != -1; buffer.append((char) n));
            in.close();

            text = buffer.toString();
        }
        catch(FileNotFoundException e) {}
        catch(IOException e) {}
        return text;
    }

    @FXML
    Label exercise;

    public void handleStartButton() {
        if(exercise.getText().equals("")) errorExercise.setText("You need to choose an exercise");
        else {
            menuBar.setDisable(true);
            testCode.setWrapText(true);
            testCode.setText("import static org.junit.Assert.*;\nimport org.junit.Test;\npublic class TestClass {\n  @Test\n  public void test() {\n    // TODO\n  }\n}");
            testCode.setEditable(true);
            code.setWrapText(true);
            code.setText("public class Class {\n  // TODO\n}");
        }
    }

    public void showAnagram(ActionEvent actionEvent) { exercise.setText(readTxt("./Aufgaben/Anagramm.txt")); }

    public void showArray(ActionEvent actionEvent) { exercise.setText(readTxt("./Aufgaben/Array.txt")); }

    public void showFuhrpark(ActionEvent actionEvent) { exercise.setText(readTxt("./Aufgaben/Fuhrpark.txt")); }

    public void showNullzeile(ActionEvent actionEvent) { exercise.setText(readTxt("./Aufgaben/Nullzeile.txt")); }

    public void showPixel(ActionEvent actionEvent) { exercise.setText(readTxt("./Aufgaben/Pixel.txt")); }

    public void showRekursiv(ActionEvent actionEvent) { exercise.setText(readTxt("./Aufgaben/Rekursiv.txt")); }

    public void showReptil(ActionEvent actionEvent) { exercise.setText(readTxt("./Aufgaben/Reptil.txt")); }

    public void showInterface(ActionEvent actionEvent) { exercise.setText(readTxt("./Aufgaben/Interface.txt")); }

    public void showSortieren(ActionEvent actionEvent) { exercise.setText(readTxt("./Aufgaben/Sortieren.txt")); }



    @FXML
    Text status;
    @FXML
    TextArea code;
    @FXML
    Label errorsCode;
    @FXML
    TextArea testCode;
    @FXML
    Label errorsTestCode;



    public void handleRunButton() {
        String error = "";
        if(status.getText().equals("Write a failing test")) error = compileTestCode(testCode.getText(), code.getText());
        errorsTestCode.setText(error);
    }

    private String compileTestCode(String testCode, String code) {
        CompilationUnit testClass = new CompilationUnit("TestClass", testCode, true);
        CompilationUnit mainClass = new CompilationUnit("Class", code, false);
        JavaStringCompiler compiler = CompilerFactory.getCompiler(testClass, mainClass);
        compiler.compileAndRunTests();
        CompilerResult compilerResult = compiler.getCompilerResult();
        TestResult testResult = compiler.getTestResult();
        if(compilerResult.hasCompileErrors()) {
            Collection<CompileError> compileErrors = compilerResult.getCompilerErrorsForCompilationUnit(testClass);


            for (CompileError ce : compileErrors) {
                if(ce.getMessage().contains("cannot find symbol")) {}

                System.out.println(ce);
            }

        }else if(testResult.getNumberOfFailedTests() > 0) {


        }else {
            return("You need to write a failing test");


        }

        return("");

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





    public void handleBackButton() {
    }

    @FXML
    ToggleGroup babystepTimeGroup;

    boolean atdd = false;
    boolean babysteps = false;
    int babystepTime;

    public void handleBabystepButton() {
        babysteps = true;

    }

    public void handleATDDButton(ActionEvent actionEvent) {
        atdd = true;
    }

    public void handleStartMenuButton(ActionEvent actionEvent) {
        RadioButton selectedButton = (RadioButton) babystepTimeGroup.getSelectedToggle();
        babystepTime = Integer.parseInt(selectedButton.getText());



    }
}
