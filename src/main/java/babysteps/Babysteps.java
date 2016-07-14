package babysteps;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.util.function.Function;

public class Babysteps extends SimpleStringProperty{

    private Function callback;
    private int max_time, time;
    private Timeline timeline;

    public Babysteps(int max_time, Function callback) {
        this.max_time = max_time;
        reset();
        this.callback = callback;
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), (ActionEvent e) -> {
                time--;
                setValue(time + "sec");
                if(time <= 0) {
                    callback.apply(null);
                    reset();
                }
        }));
    }

    public void reset(){
        this.time = max_time;
        setValue(time + "sec");
    }

    public void start(){
        timeline.play();
    }

    public void stop(){
        timeline.stop();
    }
}
