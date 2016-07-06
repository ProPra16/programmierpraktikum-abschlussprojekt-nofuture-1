package tddtMain;

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
   static Parent root;
   @Override
   public void start(Stage primaryStage) throws Exception
   {
      stage = primaryStage;
      root = FXMLLoader.load(getClass().getResource("/layoutMenu.fxml"));
      Scene scene = new Scene(root);
      stage.setMaximized(true);

      String stylesheet = getClass().getResource("/tddt.css").toExternalForm();
      scene.getStylesheets().add(stylesheet);
      stage.setScene(scene);
      stage.setTitle("Test Driven Development Trainer");
      stage.show();

   }

   public static Stage getStage() {return stage;}
}
