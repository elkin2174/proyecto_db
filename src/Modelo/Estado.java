package Modelo;

import java.time.*;

public class Estado {
    public String getCodigo_estado() {
        return codigo_estado;
    }

    public void setCodigo_estado(String codigo_estado) {
        this.codigo_estado = codigo_estado;
    }

    private String codigo_estado;
    private LocalDateTime fecha;
    private String estadoActual;

    // Representación de relaciones
    private Servicio servicio;

    public Estado() { } // Constructor vacío

    // Constructor con argumentos
    public Estado(String estadoActual, Servicio servicio) {
        this.estadoActual = estadoActual;
        this.servicio = servicio;
        this.fecha = LocalDateTime.now();
    }
    public Estado(String codigo_estado,String estadoActual, Servicio servicio) {
        this.codigo_estado = codigo_estado;
        this.estadoActual = estadoActual;
        this.servicio = servicio;
        this.fecha = LocalDateTime.now();
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
