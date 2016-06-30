import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by phil on 30.06.16.
 */
public class TDDTStart {


    public void handleExitButton() {
        System.exit(0);
    }

    public void handleKatalogButton() throws IOException {
        new KatalogAnne();
/*
        Parent root = FXMLLoader.load(getClass().getResource("layoutKatalog.fxml"));
        Scene scene = new Scene(root, 500, 500);
        String stylesheet = getClass().getResource("tddt.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        Stage katalogStage = new Stage();
        katalogStage.setScene(scene);
        katalogStage.setTitle("Uebungskatalog");
        katalogStage.show();
*/
    }

    public void handleStartButton() throws IOException {
        // Parent root = FXMLLoader.load(getClass().getResource("layoutTddt.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("layoutATDD.fxml"));
        Scene scene = new Scene(root, 1200, 500);
        String stylesheet = getClass().getResource("tddt.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        Stage katalogStage = new Stage();
        katalogStage.setScene(scene);
        katalogStage.setTitle("Uebungskatalog");
        katalogStage.sizeToScene();
        katalogStage.show();
    }

}
