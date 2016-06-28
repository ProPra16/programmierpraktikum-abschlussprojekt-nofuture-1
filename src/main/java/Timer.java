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
    }

    public void ende(){
        ende = System.currentTimeMillis();
    }

    public int passedTime() {
        zeit = (int) (ende - start)/1000;
        return zeit;
    }



}
