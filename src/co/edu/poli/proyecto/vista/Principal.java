package co.edu.poli.proyecto.vista;

import co.edu.poli.proyecto.modelo.Insumo;
import co.edu.poli.proyecto.servicios.ImplementacionOperacion;

public class Principal {

    public static void main(String[] args) {
        // Instancia de la implementación
        ImplementacionOperacion gestion = new ImplementacionOperacion();
        
        // 1. Crear un insumo (Usando el constructor de Insumo)
        Insumo i1 = new Insumo("001", "Martillo", "Herramienta", "Unidad", 10.0, 25000.0, "Ferrer", "2023-10-25");
        
        // 2. Probar el método crear
        System.out.println(gestion.crear(i1));
        
        // 3. Mostrar los nombres de los insumos (aquí era el error del paréntesis)
        for (Insumo item : gestion.leertodos()) {
            if (item != null) {
                System.out.println("Insumo en lista: " + item.getNombre());
            }
        }
        
        // 4. Probar serialización
        System.out.println(gestion.serializar());
    }
}

