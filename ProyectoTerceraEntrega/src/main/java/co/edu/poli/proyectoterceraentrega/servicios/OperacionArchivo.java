package co.edu.poli.proyectoterceraentrega.servicios;

import co.edu.poli.proyectoterceraentrega.modelo.Insumo;

public interface OperacionArchivo {
    String serializar();
    Insumo[] deserializar();
}