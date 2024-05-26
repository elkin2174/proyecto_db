package Modelo;

import java.util.Date;

public class Estado {
    private Date fecha;
    private String estadoActual;
    private Servicio servicio;

    public Estado(Date fecha, String estadoActual, Servicio servicio) {
        this.estadoActual = estadoActual;
        this.fecha = fecha;
        this.servicio = servicio;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
