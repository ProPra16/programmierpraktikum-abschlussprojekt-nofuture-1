import javafx.scene.control.TextArea;

/**
 * Created by Anne01 on 28.06.2016.
 */
public class Babysteps {
    TextArea  code, testCode;
    String phase;


     public Babysteps(String phase, TextArea code, TextArea testCode) {
       this.phase = phase;
         this.code = code;
         this.testCode = testCode;
    }

    public void babysteps(){

        Timer timer = new Timer();
        int setTime = 120; //Variable als Textfeld mit getText abfragen
        int passedTime = timer.passedTime();
        //timeline durchlaufen bis time == 0 dann switch
        switch (phase){

            case "RED":
             //   if (passedTime<setTime && Test passed )
                phase="GREEN";
                if(passedTime>=setTime) {
                    ///Code löschen
                    //GOTO Phase REFACTOR
                }
                break;
            case "GREEN":
                //   if (passedTime<setTime && Test passed )
                phase="REFACTOR";
                if (passedTime >= setTime) {
                   //Code löschen
                    //GOTO RED
                }

                break;
            case "REFACTOR":
                //   if (passedTime<setTime && Test passed )
                phase="RED";
               //Kein Zeitlimit
                break;

        }

    }
    public void setPhase(String s){

        phase = s;
    }

    public String getPhase(){

        return phase;
    }

}
