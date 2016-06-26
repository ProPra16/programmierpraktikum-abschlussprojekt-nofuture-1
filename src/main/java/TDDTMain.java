import javafx.application.Application;
import javafx.stage.Stage;
import vk.core.api.CompilationUnit;

public class TDDTMain extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      primaryStage.setTitle("Hello World");
      primaryStage.show();
   }
}
