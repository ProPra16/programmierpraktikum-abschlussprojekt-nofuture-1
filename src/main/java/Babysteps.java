/**
 * Created by Anne01 on 28.06.2016.
 */
public class Babysteps {
    String phase;

    /* public Babysteps(String phase) {
       this.phase = phase;
    }   */

    public void babysteps(){

        Timer timer = new Timer();
        Controller controller = new Controller();
        int setTime =120; //Variable als Textfeld mit getText abfragen
       int passedTime = timer.passedTime();

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
