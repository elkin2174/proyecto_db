package AccesoADatos;

import Modelo.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoCliente {
    DBConnection conexionBD;

    public DaoCliente() {
        conexionBD = new DBConnection();
    }

    // CRUD

    // Create
    public int createCliente(Cliente c) {
        String query = "INSERT INTO cliente(identificacion, nombre, tipo_cliente, email, telefono, direccion, ciudad)" +
                "VALUES ('" + c.getIdentificacion() + "', '" + c.getNombre() + "', '" + c.getTipoCliente() + "', '" +
                c.getEmail() + "', '" + c.getTelefono() + "', '" + c.getDireccion() + "', '" + c.getCiudad() + "')";

        try {
            Connection connection = conexionBD.openConnection();
            Statement sentencia = connection.createStatement();
            int numFilas = sentencia.executeUpdate(query);
            connection.close();
            return numFilas;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Read
}
