package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Mensajero {
    private int identificacion;
    private String nombre;
    private String email;
    private String direccion;
    private int telefono;

    // Representación de las realciones
    private List<Cliente> clientes;
    private List<Servicio> servicios;
    private UsuarioMensajero usuario;

    public Mensajero() { } // Constructor vacío

    // Constructor con argumentos
    public Mensajero(int id, String name, String email, String direccion, int telefono) {
        this.identificacion = id;
        this.nombre = name;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        clientes = new ArrayList<>();
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void addSercivio(Servicio servicio) {
        servicios.add(servicio);
    }

    public UsuarioMensajero getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioMensajero usuario) {
        this.usuario = usuario;
    }
}
