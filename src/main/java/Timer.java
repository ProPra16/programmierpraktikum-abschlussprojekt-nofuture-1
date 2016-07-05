import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Anne01 on 28.06.2016.
 */
public class Timer {

    int zeit;
    long start = 0;
    long ende = 0;
    public Timer(){

        test();
        timeline();
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

    Integer timer = 120;
    Label time2 = new Label();    //muss noch in fxml eingebaut werden
    Timeline timeline = new Timeline();

    public void timeline() {


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {

                    time2.setText(timer.toString());
                    timer--;
                }));
        timeline.play();

    }
    public void test(){
        Stage primaryStage = new Stage();
        StackPane root = new StackPane();
        root.getChildren().add(time2);

        Scene scene = new Scene(root, 300, 250);

        if(timer==0){
            timeline.stop();
        }
        primaryStage.setTitle("Timer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
