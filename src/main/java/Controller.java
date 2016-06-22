import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Controller {

    @FXML
    GridPane root;

    public void handleExitButton() {
        System.exit(0);
    }

    public void handleBackButton() {
        root.getScene().getWindow().hide();
    }

    public void handleKatalogButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("layoutKatalog.fxml"));
        Scene scene = new Scene(root, 500, 500);
        String stylesheet = getClass().getResource("tddt.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        Stage katalogStage = new Stage();
        katalogStage.setScene(scene);
        katalogStage.setTitle("Uebungskatalog");
        katalogStage.show();
    }

    public void handleStartButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("layoutTddt.fxml"));
        Scene scene = new Scene(root, 500, 500);
        String stylesheet = getClass().getResource("tddt.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        Stage katalogStage = new Stage();
        katalogStage.setScene(scene);
        katalogStage.setTitle("Uebungskatalog");
        katalogStage.show();


    }
}
