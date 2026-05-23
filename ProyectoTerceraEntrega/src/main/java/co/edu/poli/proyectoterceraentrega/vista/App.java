package co.edu.poli.proyectoterceraentrega.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

//    public static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }
    private static Parent loadFXML(String fxml) throws IOException {
    // Al poner "/" al inicio, Maven busca desde la raíz de src/main/resources
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/co/edu/poli/proyectoterceraentrega/" + fxml + ".fxml"));
    return fxmlLoader.load();
}

    public static void main(String[] args) {
        launch();
    }

}