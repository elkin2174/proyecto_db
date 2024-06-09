package Modelo;

public class Sucursal {
    private int numSucursal;
    private String nombre;
    private String direccion;
    private String telefono;

    // Representación de las relaciones
    private Cliente cliente;

    public Sucursal() { } // Constructor vacío

    // Constructor con argumentos
    public Sucursal(int numSucursal, String nombre, String direccion, String telefono, Cliente cliente) {
        this.cliente = cliente;
        this.numSucursal = numSucursal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }


    public int getNumSucursal() {
        return numSucursal;
    }

    public void setNumSucursal(int numSucursal) {
        this.numSucursal = numSucursal;
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
}
