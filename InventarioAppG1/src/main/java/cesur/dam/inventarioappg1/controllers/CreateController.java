package cesur.dam.inventarioappg1.controllers;

import cesur.dam.inventarioappg1.data.DAO;
import cesur.dam.inventarioappg1.data.DataSourceFactory;
import cesur.dam.inventarioappg1.data.EquipoJdbcDAO;
import cesur.dam.inventarioappg1.models.Equipo;
import cesur.dam.inventarioappg1.navigation.Navigator;
import cesur.dam.inventarioappg1.navigation.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import cesur.dam.inventarioappg1.navigation.Navigator;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateController implements Initializable {

    @FXML private TextField txtNombre;
    @FXML private ChoiceBox<String> choiceCategoria;
    @FXML private CheckBox chkDisponible;

    private final DAO<Equipo> dao = new EquipoJdbcDAO(DataSourceFactory.getDataSource());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceCategoria.getItems().addAll(
                "Portátiles", "Tablets", "Proyectores", "Fotografía",
                "Audio", "Monitores", "Impresoras", "Redes");
        choiceCategoria.getSelectionModel().selectFirst();
        chkDisponible.setSelected(true);
    }

    @FXML
    public void onGuardar(ActionEvent event) {
        String nombre = txtNombre.getText() == null ? "" : txtNombre.getText().trim();
        String categoria = choiceCategoria.getValue();

        if (nombre.isEmpty()) {
            mostrar(Alert.AlertType.WARNING, "Falta el nombre", "Escribe un nombre para el equipo.");
            return;
        }
        if (nombre.length() > 80) {
            mostrar(Alert.AlertType.WARNING, "Nombre demasiado largo", "El nombre admite 80 caracteres como máximo.");
            return;
        }
        if (categoria == null) {
            mostrar(Alert.AlertType.WARNING, "Falta la categoría", "Selecciona una categoría.");
            return;
        }

        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setCategoria(categoria);
        equipo.setDisponible(chkDisponible.isSelected());

        try {
            dao.save(equipo);
        } catch (RuntimeException e) {
            mostrar(Alert.AlertType.ERROR, "No se pudo guardar", e.getMessage());
            return;
        }

        Navigator.goTo(View.MAIN);
    }

    @FXML
    public void onCancelar(ActionEvent event) {
        Navigator.goTo(View.MAIN);
    }

    private void mostrar(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}