import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TDDTMain extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception
   {
     // new Katalog();

    //  Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
      Parent root = FXMLLoader.load(getClass().getResource("layoutStartMenue.fxml"));
      Scene scene = new Scene(root);
      //primaryStage.setFullScreen(true);
      primaryStage.setMaximized(true);

      String stylesheet = getClass().getResource("tddt.css").toExternalForm();
      scene.getStylesheets().add(stylesheet);
      primaryStage.setScene(scene);
      primaryStage.setTitle("TDDT");
      primaryStage.show();

   }
}
