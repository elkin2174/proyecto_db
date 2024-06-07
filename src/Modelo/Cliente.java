package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int identificacion;
    private String tipoCliente;
    private String nombre;
    private String email;
    private String ciudad;
    private String direccion;
    private int telefono;

    // Representación de las relaciones
    private List<Mensajero> mensajeros;
    private List<Sucursal> sucursales;
    private List<UsuarioCliente> usuarios;

    public Cliente() {
        mensajeros = new ArrayList<>();
        sucursales = new ArrayList<>();
        usuarios = new ArrayList<>();
    } // Constructor vacío

    // Constructor con argumentos
    public Cliente(int id, String tipoCliente, String nombre, String email, String ciudad, String direccion, int telefono) {
        this.identificacion = id;
        this.tipoCliente = tipoCliente;
        this.nombre = nombre;
        this.email = email;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        mensajeros = new ArrayList<>();
        sucursales = new ArrayList<>();
        usuarios = new ArrayList<>();
    }


    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }


    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    public List<Mensajero> getMensajeros() {
        return mensajeros;
    }

    public void setMensajeros(List<Mensajero> mensajeros) {
        this.mensajeros = mensajeros;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public List<UsuarioCliente> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioCliente> usuarios) {
        this.usuarios = usuarios;
    }

    public void addMensajero(Mensajero mensajero) {
        mensajeros.add(mensajero);
    }

    public void addSucursal(Sucursal sucursal) {
        sucursales.add(sucursal);
    }

    public void addUsuario(UsuarioCliente usuario) {
        usuarios.add(usuario);
    }
}
