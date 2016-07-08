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

        System.out.println("timer"+timer);
        System.out.println(code + " "+ testCode+" "+phase);
        if (timer==0) {
            switch (phase) {

                case "RED":
                    setPhase("REFACTOR");

                    setCode(" ");
                    setTestCode(" ");


                    //Code löschen
                    //GOTO Phase REFACTOR

                    break;
                case "GREEN":

                    setPhase("RED");

                    setCode(" ");
                    setTestCode(" ");

                    //Code löschen
                    //GOTO RED


                    break;

                default: break;
            }
            System.out.println("phase "+phase);
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
