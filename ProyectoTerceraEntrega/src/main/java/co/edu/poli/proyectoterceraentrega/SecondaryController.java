package co.edu.poli.proyectoterceraentrega;

import co.edu.poli.proyectoterceraentrega.vista.App;
import co.edu.poli.proyectoterceraentrega.modelo.Insumo;
import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class SecondaryController {

    @FXML private Label     lblTitulo;
    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTipo;
    @FXML private TextField txtUnidad;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtProveedor;
    @FXML private TextField txtFecha;
    @FXML private TextArea  txtResultado;
    @FXML private Button    btnAccion;
    
    // Vinculamos el contenedor del formulario para poder ocultar las etiquetas fijas
    @FXML private GridPane  contenedorFormulario;

    // --- COMPONENTES PARA LA TABLA DE LISTAR ---
    @FXML private TableView<Insumo> tablaInsumos;
    @FXML private TableColumn<Insumo, String> colId;
    @FXML private TableColumn<Insumo, String> colNombre;
    @FXML private TableColumn<Insumo, String> colTipo;
    @FXML private TableColumn<Insumo, String> colUnidad;
    @FXML private TableColumn<Insumo, Double> colCantidad;
    @FXML private TableColumn<Insumo, Double> colPrecio;
    @FXML private TableColumn<Insumo, String> colProveedor;
    @FXML private TableColumn<Insumo, String> colFecha;

    @FXML
    public void initialize() {
        String accion = PrimaryController.accionActual;
        System.out.println("SecondaryController initialize - accion: " + accion);

        lblTitulo.setText(accion + " INSUMO");
        
        // Inicializar estados visuales por defecto
        if (tablaInsumos != null) tablaInsumos.setVisible(false);
        if (txtResultado != null) txtResultado.setVisible(true);
        btnAccion.setVisible(true);
        rehabilitarTodosLosCampos();

        switch (accion) {
            case "CREAR" -> {
                btnAccion.setText("Crear Insumo");
            }
            case "BUSCAR" -> {
                btnAccion.setText("Buscar por ID");
                // Escondemos visualmente el bloque de etiquetas y campos sobrantes
                ocultarCamposSobrantesParaBusquedaYEliminacion();
            }
            case "LISTAR" -> {
                btnAccion.setVisible(false);
                if (txtResultado != null) txtResultado.setVisible(false);
                if (contenedorFormulario != null) contenedorFormulario.setVisible(false);
                ocultarFormulario(); 
                
                configurarColumnasTabla();
                Platform.runLater(() -> ejecutarListar());
            }
            case "MODIFICAR" -> {
                btnAccion.setText("Modificar Insumo");
            }
            case "ELIMINAR" -> {
                btnAccion.setText("Eliminar por ID");
                ocultarCamposSobrantesParaBusquedaYEliminacion();
            }
        }
    }

    private void configurarColumnasTabla() {
        if (tablaInsumos == null) return;
        tablaInsumos.setVisible(true);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colUnidad.setCellValueFactory(new PropertyValueFactory<>("unidadMedida")); 
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadDisponible"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
    }

    @FXML
    private void ejecutarAccion() {
        String accion = PrimaryController.accionActual;
        switch (accion) {
            case "CREAR"     -> ejecutarCrear();
            case "BUSCAR"    -> ejecutarBuscar();
            case "LISTAR"    -> ejecutarListar();
            case "MODIFICAR" -> ejecutarModificar();
            case "ELIMINAR"  -> ejecutarEliminar();
            default          -> txtResultado.setText("Error: accion no reconocida: '" + accion + "'");
        }
    }

    private void ejecutarCrear() {
        try {
            String id      = txtId.getText().trim();
            String nombre  = txtNombre.getText().trim();
            String tipo    = txtTipo.getText().trim();
            String unidad  = txtUnidad.getText().trim();
            String prov    = txtProveedor.getText().trim();
            String fecha   = txtFecha.getText().trim();

            if (id.isEmpty() || nombre.isEmpty()) {
                txtResultado.setText("Error: ID y Nombre son obligatorios.");
                return;
            }

            double cantidad = Double.parseDouble(txtCantidad.getText().trim());
            double precio   = Double.parseDouble(txtPrecio.getText().trim());

            Insumo nuevo = new Insumo(id, nombre, tipo, unidad, cantidad, precio, prov, fecha);
            txtResultado.setText(PrimaryController.gestion.crear(nuevo));
            PrimaryController.gestion.serializar();
            limpiarCamposFormulario();

        } catch (NumberFormatException e) {
            txtResultado.setText("Error: Cantidad y Precio deben ser números válidos.");
        }
    }

    private void ejecutarBuscar() {
        String id = txtId.getText().trim();
        if (id.isEmpty()) {
            txtResultado.setText("Error: ingrese un ID para buscar.");
            return;
        }
        Insumo encontrado = PrimaryController.gestion.leerUno(id);
        if (encontrado != null) {
            txtResultado.setText("¡Insumo encontrado con éxito!\n\n" + formatearInsumo(encontrado));
        } else {
            txtResultado.setText("No se encontró ningún insumo con ID: " + id);
        }
    }

//    private void ejecutarListar() {
//        Insumo[] todos = PrimaryController.gestion.leerTodos();
//        if (todos == null || todos.length == 0) {
//            if (txtResultado != null) {
//                txtResultado.setVisible(true);
//                txtResultado.setText("No hay insumos registrados.");
//            }
//        } else {
//            ObservableList<Insumo> listaObservable = FXCollections.observableArrayList(todos);
//            tablaInsumos.setItems(listaObservable);
//        }
//    }
    private void ejecutarListar() {
        Insumo[] todos = PrimaryController.gestion.leerTodos();
        if (todos == null || todos.length == 0) {
            if (txtResultado != null) {
                txtResultado.setVisible(true);
                txtResultado.setText("No hay insumos registrados.");
            }
        } else {
            ObservableList<Insumo> listaObservable = FXCollections.observableArrayList(todos);
            tablaInsumos.setItems(listaObservable);
            
            // Forzar refresco visual para asegurar el renderizado de las celdas
            tablaInsumos.refresh();
        }
    }

    private void ejecutarModificar() {
        try {
            String id = txtId.getText().trim();
            if (id.isEmpty()) {
                txtResultado.setText("Error: ingrese el ID del insumo a modificar.");
                return;
            }
            Insumo modificado = new Insumo(
                id,
                txtNombre.getText().trim(),
                txtTipo.getText().trim(),
                txtUnidad.getText().trim(),
                Double.parseDouble(txtCantidad.getText().trim()),
                Double.parseDouble(txtPrecio.getText().trim()),
                txtProveedor.getText().trim(),
                txtFecha.getText().trim()
            );
            txtResultado.setText(PrimaryController.gestion.modificar(id, modificado));
        } catch (NumberFormatException e) {
            txtResultado.setText("Error: Cantidad y Precio deben ser números válidos.");
        }
    }

    private void ejecutarEliminar() {
        String id = txtId.getText().trim();
        if (id.isEmpty()) {
            txtResultado.setText("Error: ingrese un ID para eliminar.");
            return;
        }
        Insumo eliminado = PrimaryController.gestion.eliminar(id);
        if (eliminado != null) {
            txtResultado.setText("Insumo eliminado exitosamente:\n\n" + formatearInsumo(eliminado));
            txtId.clear();
        } else {
            txtResultado.setText("No se encontró ningún insumo con ID: " + id);
        }
    }

    private String formatearInsumo(Insumo ins) {
        return  "  ID:                " + ins.getId() + "\n" +
                "  Nombre:            " + ins.getNombre() + "\n" +
                "  Tipo:              " + ins.getTipo() + "\n" +
                "  Unidad:            " + ins.getUnidadMedida() + "\n" +
                "  Cantidad:          " + ins.getCantidadDisponible() + "\n" +
                "  Precio:            " + ins.getPrecioUnitario() + "\n" +
                "  Proveedor:         " + ins.getProveedor() + "\n" +
                "  Fecha de compra:   " + ins.getFechaCompra();
    }

    @FXML
    private void limpiarCampos() {
        limpiarCamposFormulario();
        if (txtResultado != null) txtResultado.clear();
    }

    private void limpiarCamposFormulario() {
        txtId.clear(); txtNombre.clear(); txtTipo.clear();
        txtUnidad.clear(); txtCantidad.clear(); txtPrecio.clear();
        txtProveedor.clear(); txtFecha.clear();
    }

    @FXML
    private void volverAlMenu() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    private void ocultarFormulario() {
        txtId.setVisible(false);       txtNombre.setVisible(false);
        txtTipo.setVisible(false);     txtUnidad.setVisible(false);
        txtCantidad.setVisible(false); txtPrecio.setVisible(false);
        txtProveedor.setVisible(false); txtFecha.setVisible(false);
    }

    private void ocultarCamposSobrantesParaBusquedaYEliminacion() {
        // Hacemos que los campos y etiquetas del formulario se vuelvan invisibles individuales
        txtNombre.setVisible(false);
        txtTipo.setVisible(false);
        txtUnidad.setVisible(false);
        txtCantidad.setVisible(false);
        txtPrecio.setVisible(false);
        txtProveedor.setVisible(false);
        txtFecha.setVisible(false);

        // Si tenemos acceso al contenedor del fxml, removemos visualmente el espacio de las filas de abajo
        if (contenedorFormulario != null) {
            // Nota: El ID se mantiene visible en su posición superior original
            txtId.setVisible(true);
        }
    }

    private void rehabilitarTodosLosCampos() {
        if (contenedorFormulario != null) contenedorFormulario.setVisible(true);
        txtId.setVisible(true);        txtId.setDisable(false);
        txtNombre.setVisible(true);    txtNombre.setDisable(false);
        txtTipo.setVisible(true);      txtTipo.setDisable(false);
        txtUnidad.setVisible(true);    txtUnidad.setDisable(false);
        txtCantidad.setVisible(true);  txtCantidad.setDisable(false);
        txtPrecio.setVisible(true);    txtPrecio.setDisable(false);
        txtProveedor.setVisible(true); txtProveedor.setDisable(false);
        txtFecha.setVisible(true);     txtFecha.setDisable(false);
    }
}