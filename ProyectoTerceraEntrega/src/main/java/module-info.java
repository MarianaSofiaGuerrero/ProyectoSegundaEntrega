//module co.edu.poli.proyectoterceraentrega {
//    requires javafx.controls;
//    requires javafx.fxml;
//    
//    // 1. Abre el paquete de los controladores para que FXMLLoader pueda enlazarlos
//    opens co.edu.poli.proyectoterceraentrega to javafx.fxml;
//    
//    // 2. Exporta y abre el paquete de la vista donde está tu clase App
//    exports co.edu.poli.proyectoterceraentrega.vista;
//    opens co.edu.poli.proyectoterceraentrega.vista to javafx.fxml;
//}
module co.edu.poli.proyectoterceraentrega {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base; // Requerido por defecto, pero asegura la reflexión básica
    
    // 1. Abre el paquete de los controladores para que FXMLLoader pueda enlazarlos
    opens co.edu.poli.proyectoterceraentrega to javafx.fxml;
    
    // 2. Exporta y abre el paquete de la vista donde está tu clase App
    exports co.edu.poli.proyectoterceraentrega.vista;
    opens co.edu.poli.proyectoterceraentrega.vista to javafx.fxml;

    // 🌟 LA SOLUCIÓN AL ERROR:
    // Damos permisos explícitos de lectura y reflexión al módulo base de JavaFX 
    // sobre tu clase Insumo para que la tabla pueda rellenarse sola.
    exports co.edu.poli.proyectoterceraentrega.modelo;
    opens co.edu.poli.proyectoterceraentrega.modelo to javafx.base, javafx.fxml;
}