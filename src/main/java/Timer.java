/**
 * Created by Anne01 on 28.06.2016.
 */
public class Timer {

    int zeit;
    long start = 0;
    long ende = 0;
    public Timer(){
    }

    public void start() {
        start = System.currentTimeMillis();
        //Startet wenn Button 'Babysteps' geklicked wurde
    }

    public void ende(){
        ende = System.currentTimeMillis();
        //Stoppen bei Zeitüberschreitung, Falscher Code?, Button clicked
    }

    public int passedTime() {
        zeit = (int) (ende - start)/1000;
        return zeit;
    }



}
