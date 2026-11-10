package cesur.dam.inventarioappg1.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {

    private static Stage stage;

    public static void init(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void goTo(View view) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Navigator.class.getResource("/cesur/dam/inventarioappg1/views/" + view.getFile()));
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la vista " + view, e);
        }
    }
}