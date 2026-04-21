package co.edu.poli.proyecto.modelo;

import java.io.Serializable;

public class Insumo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String nombre;
    private String tipo;
    private String unidadMedida;
    private double cantidadDisponible;
    private double precioUnitario;
    private String proveedor;
    private String fechaCompra;

    public Insumo(String id, String nombre, String tipo, String unidadMedida, 
                  double cantidadDisponible, double precioUnitario, String proveedor, String fechaCompra) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.unidadMedida = unidadMedida;
        this.cantidadDisponible = cantidadDisponible;
        this.precioUnitario = precioUnitario;
        this.proveedor = proveedor;
        this.fechaCompra = fechaCompra;
    }


    public String getNombre() { return nombre; }
    public String getId() { return id; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
}
