import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

    int timer = 0;
    Text time2 = new Text();    //muss noch in fxml eingebaut werden
    Timeline timeline = new Timeline();
    public void timeline() {


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {

                    time2.setText(String.valueOf(timer));
                    timer++;
                }));
        timeline.play();

    }


}
