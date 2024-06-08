package AccesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Cliente;
import org.postgresql.util.PSQLException;

public class ClienteDAO {
    private static final String INSERT_SQL = "INSERT INTO cliente (identificacion, tipo_cliente, nombre, email, ciudad, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM cliente WHERE identificacion = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM cliente";
    private static final String UPDATE_SQL = "UPDATE cliente SET tipo_cliente = ?, nombre = ?, email = ?, ciudad = ?, direccion = ?, telefono = ? WHERE identificacion = ?";
    private static final String DELETE_SQL = "DELETE FROM cliente WHERE identificacion = ?";

    DBConnection conexion;

    public ClienteDAO() {
        conexion = new DBConnection();
    }

    /**
     * Insertar cliente en la base de datos
     *
     * @param cliente
     */
    public int insert(Cliente cliente) {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            System.out.println("Id: " + cliente.getIdentificacion() + "\n" +
            "TipoCliente: " + cliente.getTipoCliente() + "\n" +
            "Nombre: " + cliente.getNombre() + "\n" +
            "Email: " + cliente.getEmail() + "\n" +
            "Ciudad: ");

            stmt.setString(1, cliente.getIdentificacion());
            stmt.setString(2, cliente.getTipoCliente());
            stmt.setString(3, cliente.getNombre());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getCiudad());
            stmt.setString(6, cliente.getDireccion());
            stmt.setString(7, cliente.getTelefono());
            stmt.executeUpdate();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());
            return 1;
        }
    }

    /**
     * Seleccionar una cliente de la base de datos con su identificación
     * @param identificacion
     * @return
     */
    public Cliente selectById(String identificacion) {
        Cliente cliente = null;
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setString(1, identificacion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdentificacion(rs.getString("identificacion"));
                    cliente.setTipoCliente(rs.getString("tipo_cliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setCiudad(rs.getString("ciudad"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public List<Cliente> selectAll() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdentificacion(rs.getString("identificacion"));
                cliente.setTipoCliente(rs.getString("tipo_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void update(Cliente cliente) {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, cliente.getTipoCliente());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCiudad());
            stmt.setString(5, cliente.getDireccion());
            stmt.setString(6, cliente.getTelefono());
            stmt.setString(7, cliente.getIdentificacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String identificacion) {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setString(1, identificacion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

