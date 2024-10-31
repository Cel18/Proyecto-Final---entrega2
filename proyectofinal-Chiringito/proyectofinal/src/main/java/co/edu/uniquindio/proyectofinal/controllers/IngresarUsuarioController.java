package co.edu.uniquindio.proyectofinal.controllers;

import java.io.IOException;

import co.edu.uniquindio.proyectofinal.clases.Marketplace;
import co.edu.uniquindio.proyectofinal.clases.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IngresarUsuarioController {

    @FXML
    private Button bAtras;

    @FXML
    private Button buttonIngresar;

    @FXML
    private Label lContraseña;

    @FXML
    private Label lIngresarUsuario;

    @FXML
    private Label lNombre;

    @FXML
    private TextField tfContraseña;

    @FXML
    private TextField tfNombre;

    @FXML
    void click(ActionEvent event) throws ClassNotFoundException, IOException {
        Button button = (Button) event.getSource();

        if (button == bAtras) {
            irAtras();
        } else if (button == buttonIngresar) {
            ingresarUsuario();
        }
    }

    private void irAtras() {
        // Regresar al Login
        try {
            Stage stage = (Stage) bAtras.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/proyectofinal/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Error al cargar el Login");
        }
    }

    private void ingresarUsuario() throws ClassNotFoundException, IOException {
        String nombre = tfNombre.getText();
        String contrasena = tfContraseña.getText();

        if (nombre == null || nombre.trim().isEmpty() || contrasena == null || contrasena.trim().isEmpty()) {
            showError("Por favor, ingrese su nombre y contraseña.");
            return;
        }

        //verificar el vendedor
        Vendedor vendedor = verificarVendedor(nombre, contrasena);

        if (vendedor != null) {
            cargarMuroMarketplace(); // Cargar la nueva ventana si las credenciales son correctas
            showConfirmation("Ingreso exitoso. Bienvenido, " + vendedor.getNombre());
        } else {
            showError("El nombre o la contraseña son incorrectos. Por favor, regístrese.");
        }
    }

    private void cargarMuroMarketplace() {
        // Cargar la interfaz de muro
        try {
            Stage stage = (Stage) buttonIngresar.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/proyectofinal/PaginaPrincipalVendedor.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showError("Error al cargar el pagina principal.");
        }
    }

    private Vendedor verificarVendedor(String nombre, String contrasena) throws ClassNotFoundException, IOException {
        Marketplace.cargarDatos();
        Vendedor vendedor = Marketplace.buscarVendedorPorNombre(nombre);

        if (vendedor == null) {
            //showError("No existe un vendedor con ese nombre. Por favor, regístrese primero.");
            return null;
        } else if (!vendedor.getContrasena().equals(contrasena)) {
            //showError("La contraseña es incorrecta. Intente nuevamente.");
            return null;
        }
        return vendedor;
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
