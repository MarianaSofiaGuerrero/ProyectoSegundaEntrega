package co.edu.poli.proyecto.servicios;

import co.edu.poli.proyecto.modelo.Insumo;

public interface OperacionCRUD {
    public String crear(Insumo insumo);
    public Insumo leeruno(String indice);
    public Insumo[] leertodos();
    public String modificar(String indice, Insumo insumo);
    public Insumo eliminar(String indice);
}

