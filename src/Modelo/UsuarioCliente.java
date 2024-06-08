package Modelo;

import java.util.ArrayList;
import java.util.List;

public class UsuarioCliente {
    private String login;
    private String password;
    private String direccion;
    private String email;
    private String telefono;

    // Representación de las relaciones
    private Cliente cliente;
    private List<Servicio> serviciosSolicitados;

    public UsuarioCliente() {
        this.serviciosSolicitados = new ArrayList<>();
    } // Constructor vacío

    // Constructor con argumentos
    public UsuarioCliente(String login, String password, String direccion, String email, String telefono, Cliente cliente) {
        this.login = login;
        this.password = password;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.cliente = cliente;
        this.serviciosSolicitados = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Servicio> getServiciosSolicitados() {
        return serviciosSolicitados;
    }

    public void setServiciosSolicitados(List<Servicio> serviciosSolicitados) {
        this.serviciosSolicitados = serviciosSolicitados;
    }

    public void addServicio(Servicio servicio) {
        serviciosSolicitados.add(servicio);
    }
}
