package co.edu.uniquindio.proyectofinal.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vendedor implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido; 
    private String cedula;
    private String direccion;
    private String contrasena;private Muro muro;
    private List<Vendedor> contactos;
    private List<Producto> productos;

    public Vendedor(String nombre, String apellido, String cedula, String direccion, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.contrasena = contrasena;
        //this.muro = muro;
        this.contactos = new ArrayList<>();
        this.productos = new ArrayList<>();
    }

    public void agregarContacto(Vendedor vendedor) {
        if (!contactos.contains(vendedor)) {
            contactos.add(vendedor);
            vendedor.getContactos().add(this);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Muro getMuro() {
        return muro;
    }

    public void setMuro(Muro muro) {
        this.muro = muro;
    }

    public List<Vendedor> getContactos() {
        return contactos;
    }

    public void setContactos(List<Vendedor> contactos) {
        this.contactos = contactos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /*public void agregarProductoAMuro (Producto producto){ //Throws YaAñadidoException
        muro.add(producto);
    }*/

    public void agregarAliado (Vendedor vendedor){ //Throws YaAñadidoException
        contactos.add(vendedor);
        //Eventuales cambios considerando la lógica de enviar y aceptar solicitudes de amistad/alianza
    }
    
}
