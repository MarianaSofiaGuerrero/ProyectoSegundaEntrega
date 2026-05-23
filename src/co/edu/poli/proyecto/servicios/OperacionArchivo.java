package co.edu.poli.proyecto.servicios;

import co.edu.poli.proyecto.modelo.Insumo;

public interface OperacionArchivo {
    String serializar();
    Insumo[] deserializar();
}