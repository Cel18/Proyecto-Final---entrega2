package co.edu.uniquindio.proyectofinal.clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Marketplace implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private static List<Vendedor> listaVendedores = new ArrayList<>();

    private String nombre;
    
    public Marketplace(String nombre) {
        this.nombre = nombre;
    }

    public static void agregarVendedor(Vendedor vendedor){  
        listaVendedores.add(vendedor);
    }

    public static Vendedor buscarVendedorPorNombre(String nombre) {
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getNombre().equalsIgnoreCase(nombre)) {
                return vendedor; // Devuelve el vendedor si lo encuentra
            }
        }
        return null; // Retorna null si no encuentra el vendedor
    }

    public static List<Vendedor> obtenerVendedores() {
        return listaVendedores;
    }

    public static void guardarDatos() throws IOException {
        try (ObjectOutputStream oosVendedores = new ObjectOutputStream(new FileOutputStream("vendedores.dat"));) {
            oosVendedores.writeObject(listaVendedores);
        }
    }

    // Método para cargar datos desde archivos
    @SuppressWarnings("unchecked")
    public static void cargarDatos() throws IOException, ClassNotFoundException {
        File vendedoresFile = new File("vendedores.dat");
        

        // Cargar vendedores
        if (vendedoresFile.exists()) {
            try (ObjectInputStream oisVendedores = new ObjectInputStream(new FileInputStream(vendedoresFile))) {
                listaVendedores = (List<Vendedor>) oisVendedores.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new IOException("Error al cargar los vendedores: " + e.getMessage(), e);
            }
        } else {
            System.out.println("El archivo de vendedores no existe.");
        }
    }
    
}