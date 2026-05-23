package co.edu.poli.proyecto.servicios;

import co.edu.poli.proyecto.modelo.Insumo;

public interface OperacionCRUD {
    String crear(Insumo insumo);
    Insumo leerUno(String id);
    Insumo[] leerTodos();
    String modificar(String id, Insumo insumo);
    Insumo eliminar(String id);
}