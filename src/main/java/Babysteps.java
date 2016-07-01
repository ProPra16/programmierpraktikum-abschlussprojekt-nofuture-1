import javafx.scene.control.TextArea;

/**
 * Created by Anne01 on 28.06.2016.
 */
public class Babysteps {
    String  code, testCode;
    String phase;


     public Babysteps(String phase, String code, String testCode) {
       this.phase = phase;
         this.code = code;
         this.testCode = testCode;
    }

    public void babysteps(){

        Timer timer = new Timer();
        int setTime = 120; //Variable als Textfeld mit getText abfragen
        timer.timeline();

        while(timer.timer>=setTime) {
            switch (phase) {

                case "RED":
                   setPhase("REFACTOR");

                    setCode(" ");
                    setTestCode(" ");
                    timer.timeline.stop();
                        //Code löschen
                        //GOTO Phase REFACTOR

                    break;
                case "GREEN":

                    phase = "RED";

                    setCode(" ");
                    setTestCode(" ");
                    timer.timeline.stop();
                        //Code löschen
                        //GOTO RED


                    break;


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
