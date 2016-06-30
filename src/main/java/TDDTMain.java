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

   static Stage stage;

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      stage = primaryStage;
      Parent root = FXMLLoader.load(getClass().getResource("layoutStartMenue.fxml"));
      Scene scene = new Scene(root);
      stage.setMaximized(true);

      String stylesheet = getClass().getResource("tddt.css").toExternalForm();
      scene.getStylesheets().add(stylesheet);
      stage.setScene(scene);
      stage.setTitle("TDDT");
      stage.show();

   }

   public static Stage getStage() {return stage;}
}
