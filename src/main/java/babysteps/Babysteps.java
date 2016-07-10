package babysteps;

public class Babysteps {
    int timer;
    String  code, testCode;
    String phase;

    public Babysteps(String phase, String code, String testCode, int timer) {
        this.phase = phase;
        this.code = code;
        this.testCode = testCode;
        this.timer = timer;
        babysteps();
    }

    public void babysteps(){


        if (timer==0) {
            switch (phase) {

                case "red":
                    setPhase("refactor");

                    setCode("public class Class {\n  // TODO\n}");
                    setTestCode("import static org.junit.Assert.*;\nimport org.junit.Test;\npublic class TestClass {\n  @Test\n  public void test() {\n    // TODO\n  }\n}");


                    //Code löschen
                    //GOTO Phase REFACTOR

                    break;
                case "green":

                    setPhase("red");

                    setCode("public class Class {\n  // TODO\n}");
                    setTestCode("import static org.junit.Assert.*;\nimport org.junit.Test;\npublic class TestClass {\n  @Test\n  public void test() {\n    // TODO\n  }\n}");

                    //Code löschen
                    //GOTO RED


                    break;

                default: break;
            }

        }
    }
    public void setPhase(String s){

        phase = s;
    }

    public String getPhase(){

        return phase;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getCode() {
        return code;
    }

    public String getTestCode() {
        return testCode;
    }
}
