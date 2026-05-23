package co.edu.poli.proyectoterceraentrega.servicios;

import co.edu.poli.proyectoterceraentrega.modelo.Insumo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementacionOperacion implements OperacionCRUD, OperacionArchivo {

    private static final String ARCHIVO = "insumos.bin";
    private List<Insumo> lista = new ArrayList<>();

    //  CRUD
    @Override
    public String crear(Insumo insumo) {
        for (Insumo i : lista) {
            if (i.getId().equalsIgnoreCase(insumo.getId())) {
                return "Error: Ya existe un insumo con el ID " + insumo.getId();
            }
        }
        lista.add(insumo);
        return "Insumo '" + insumo.getNombre() + "' creado con éxito.";
    }

    @Override
    public Insumo leerUno(String id) {
        for (Insumo i : lista) {
            if (i.getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public Insumo[] leerTodos() {
        return lista.toArray(new Insumo[0]);
    }

    @Override
    public String modificar(String id, Insumo nuevo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equalsIgnoreCase(id)) {
                lista.set(i, nuevo);
                return "Insumo con ID " + id + " modificado con éxito.";
            }
        }
        return "Error: No se encontró un insumo con ID " + id;
    }

    @Override
    public Insumo eliminar(String id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equalsIgnoreCase(id)) {
                Insumo eliminado = lista.remove(i);
                return eliminado;
            }
        }
        return null;
    }

    //  Archivos .bin

    @Override
    public String serializar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(lista);
            return "Datos guardados correctamente en '" + ARCHIVO + "'.";
        } catch (IOException e) {
            return "Error al guardar: " + e.getMessage();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Insumo[] deserializar() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("No existe archivo de datos previo.");
            return new Insumo[0];
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            lista = (List<Insumo>) ois.readObject();
            System.out.println("Datos cargados desde '" + ARCHIVO + "'.");
            return lista.toArray(new Insumo[0]);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return new Insumo[0];
        }
    }
}