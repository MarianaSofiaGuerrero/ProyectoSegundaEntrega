package co.edu.poli.proyectoterceraentrega.modelo;

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

    public String getId()                        { return id; }
    public String getNombre()                    { return nombre; }
    public String getTipo()                      { return tipo; }
    public String getUnidadMedida()              { return unidadMedida; }
    public double getCantidadDisponible()        { return cantidadDisponible; }
    public double getPrecioUnitario()            { return precioUnitario; }
    public String getProveedor()                 { return proveedor; }
    public String getFechaCompra()               { return fechaCompra; }

    public void setId(String id)                             { this.id = id; }
    public void setNombre(String nombre)                     { this.nombre = nombre; }
    public void setTipo(String tipo)                         { this.tipo = tipo; }
    public void setUnidadMedida(String unidadMedida)         { this.unidadMedida = unidadMedida; }
    public void setCantidadDisponible(double cantidad)       { this.cantidadDisponible = cantidad; }
    public void setPrecioUnitario(double precio)             { this.precioUnitario = precio; }
    public void setProveedor(String proveedor)               { this.proveedor = proveedor; }
    public void setFechaCompra(String fechaCompra)           { this.fechaCompra = fechaCompra; }

    @Override
    public String toString() {
        return String.format(
            "ID: %s | Nombre: %s | Tipo: %s | Unidad: %s | Cantidad: %.2f | Precio: $%.2f | Proveedor: %s | Fecha: %s",
            id, nombre, tipo, unidadMedida, cantidadDisponible, precioUnitario, proveedor, fechaCompra
        );
    }
} 