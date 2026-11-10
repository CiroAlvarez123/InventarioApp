package cesur.dam.inventarioappg1;

import cesur.dam.inventarioappg1.data.DataBaseInitializer;
import cesur.dam.inventarioappg1.data.DataSourceFactory;
import cesur.dam.inventarioappg1.navigation.Navigator;
import cesur.dam.inventarioappg1.navigation.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sql.DataSource;
import java.io.IOException;

public class AppG1 extends Application {
    @Override
    public void start(Stage stage) {
        DataSource ds = DataSourceFactory.getDataSource();
        DataBaseInitializer.initialize(ds);
        DataBaseInitializer.loadData(ds);

        Navigator.init(stage);
        stage.setTitle("Inventario App G1");
        Navigator.goTo(View.LOGIN);
        stage.show();
    }
}
