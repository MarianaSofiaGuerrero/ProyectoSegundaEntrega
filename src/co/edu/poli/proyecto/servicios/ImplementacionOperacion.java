package co.edu.poli.proyecto.servicios;

import co.edu.poli.proyecto.modelo.Insumo;
import java.io.*;

public class ImplementacionOperacion implements OperacionCRUD, OperacionArchivo {
    
    private Insumo[] arregloInsumo = new Insumo[2];

    @Override
    public String crear(Insumo insumo) {
        for (int i = 0; i < arregloInsumo.length; i++) {
            if (arregloInsumo[i] == null) {
                arregloInsumo[i] = insumo;
                return "Insumo creado con éxito.";
            }
        }
        return "Error: Arreglo lleno.";
    }

    @Override
    public Insumo leeruno(String indice) { return null; }

    @Override
    public Insumo[] leertodos() {
        return arregloInsumo;
    }

    @Override
    public String modificar(String indice, Insumo insumo) { return null; }

    @Override
    public Insumo eliminar(String indice) { return null; }

    @Override
    public String serializar() {
        return "Simulación: Archivo guardado.";
    }

    @Override
    public Insumo[] deserializar() {
        return null;
    }
}
