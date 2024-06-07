package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.time.*;
import java.util.List;

public class Servicio {
    private int codigo;
    private int numPaquetes;
    private String origen;
    private String destino;
    private String tipoTransporte;
    private String descripcion;
    private String ciudad;
    private LocalDateTime fechaSolicitud;

    // Representación de las relaciones
    private Mensajero mensajero;
    private UsuarioCliente cliente;
    private List<Estado> estados;


    public Servicio() {
        this.estados = new ArrayList<>();
    } // Constructor vacío

    // Constructor con argumentos
    public Servicio(int cod, int numPaq, String org, String dest, String tipoTrans, String desc, String ciu, Mensajero men, UsuarioCliente user) {
        this.codigo = cod;
        this.numPaquetes = numPaq;
        this.origen = org;
        this.destino = dest;
        this.tipoTransporte = tipoTrans;
        this.descripcion = desc;
        this.ciudad = ciu;
        this.mensajero = men;
        this.cliente = user;
        this.fechaSolicitud = LocalDateTime.now();
        this.estados = new ArrayList<>();
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumPaquetes() {
        return numPaquetes;
    }

    public void setNumPaquetes(int numPaquetes) {
        this.numPaquetes = numPaquetes;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Mensajero getMensajero() {
        return mensajero;
    }

    public void setMensajero(Mensajero mensajero) {
        this.mensajero = mensajero;
    }

    public UsuarioCliente getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioCliente cliente) {
        this.cliente = cliente;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public void addEstado(Estado estado) {
        estados.add(estado);
    }
}
