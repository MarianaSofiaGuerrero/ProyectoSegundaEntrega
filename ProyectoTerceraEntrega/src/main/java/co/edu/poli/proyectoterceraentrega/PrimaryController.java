package co.edu.poli.proyectoterceraentrega;

import co.edu.poli.proyectoterceraentrega.vista.App;
import co.edu.poli.proyectoterceraentrega.servicios.ImplementacionOperacion;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrimaryController {

    @FXML private Label lblEstado;

    // Instancia global para la gestión de los insumos y persistencia
    public static ImplementacionOperacion gestion = new ImplementacionOperacion();
    public static String accionActual = "";

    @FXML
    public void initialize() {
        // Carga los datos guardados en el archivo binario al arrancar la app
        gestion.deserializar();
    }

    @FXML
    private void irACrear() throws IOException {
        accionActual = "CREAR";
        System.out.println("Accion seleccionada: " + accionActual);
        App.setRoot("secondary");
    }

    @FXML
    private void irABuscar() throws IOException {
        accionActual = "BUSCAR";
        System.out.println("Accion seleccionada: " + accionActual);
        App.setRoot("secondary");
    }

    @FXML
    private void irAListar() throws IOException {
        accionActual = "LISTAR";
        System.out.println("Accion seleccionada: " + accionActual);
        App.setRoot("secondary"); 
    }

    @FXML
    private void irAModificar() throws IOException {
        accionActual = "MODIFICAR";
        System.out.println("Accion seleccionada: " + accionActual);
        App.setRoot("secondary");
    }

    @FXML
    private void irAEliminar() throws IOException {
        accionActual = "ELIMINAR";
        System.out.println("Accion seleccionada: " + accionActual);
        App.setRoot("secondary");
    }

    @FXML
    private void guardarArchivo() {
        String resultado = gestion.serializar();
        if (lblEstado != null) {
            lblEstado.setText(resultado);
        }
        System.out.println("Guardar: " + resultado);
    }

    @FXML
    private void salir() {
        System.exit(0);
    }

    public static String getAccionActual() {
        return accionActual;
    }
}