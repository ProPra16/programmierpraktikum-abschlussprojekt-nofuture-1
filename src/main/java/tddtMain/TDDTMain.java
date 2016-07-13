package tddtMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TDDTMain extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }

   public static BorderPane rootPane;

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      rootPane = FXMLLoader.load(getClass().getResource("/layoutMain.fxml"));
      Scene scene = new Scene(rootPane);
      rootPane.setId("rootPane");

      FXMLLoader loader = new FXMLLoader();
      rootPane.setCenter(loader.load(getClass().getResource("/layoutMenu.fxml")));

      primaryStage.setMaximized(true);
      String stylesheet = getClass().getResource("/tddt.css").toExternalForm();
      scene.getStylesheets().add(stylesheet);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Test Driven Development Trainer");
      primaryStage.show();

   }
}
