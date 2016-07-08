package timer;

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
