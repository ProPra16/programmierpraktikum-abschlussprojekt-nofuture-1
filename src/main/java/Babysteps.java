/**
 * Created by Anne01 on 28.06.2016.
 */
public class Babysteps {

    public void babysteps(){
        Timer timer = new Timer();
        int setTime =120; // soll variable sein
       int passedTime = timer.passedTime();
        String phase = timer.getPhase();
        switch (phase){

            case "RED":
                phase="GREEN";
                if(passedTime==setTime) {
                    //Code löschen und in Phase GREEN gehen
                }
                break;
            case "GREEN":
               phase="REFACTOR";
                if (passedTime == setTime) {
                    //Code löschen und in REFACTOR gehen
                }

                break;
            case "REFACTOR":
                phase="RED";
               //in RED zurückwechseln
                break;

        }

    }
}
