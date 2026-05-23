package co.edu.poli.proyectoterceraentrega.servicios;

import co.edu.poli.proyectoterceraentrega.modelo.Insumo;

public interface OperacionCRUD {
    String crear(Insumo insumo);
    Insumo leerUno(String id);
    Insumo[] leerTodos();
    String modificar(String id, Insumo insumo);
    Insumo eliminar(String id);
}