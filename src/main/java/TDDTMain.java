

import javafx.application.Application;
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
      new Katalog();
      //primaryStage.setTitle("Hello World");
      //primaryStage.show();
   }
}
