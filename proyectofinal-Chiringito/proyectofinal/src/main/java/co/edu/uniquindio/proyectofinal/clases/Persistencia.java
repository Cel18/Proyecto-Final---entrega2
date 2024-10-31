package co.edu.uniquindio.proyectofinal.clases;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public static void guardarVendedores(List<Vendedor> vendedores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vendedores.dat"))) {
            oos.writeObject(vendedores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Vendedor> cargarVendedores() {
        File file = new File("vendedores.dat");

        if (!file.exists()) {
            return new ArrayList<>(); // Si el archivo no existe, retornamos una lista vacía
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Vendedor>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Se imprime el error en la consola para depuración
            return new ArrayList<>();
        }
    }
}