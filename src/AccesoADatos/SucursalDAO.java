package AccesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;
import Modelo.Sucursal;

public class SucursalDAO {
    private static final String INSERT_SQL = "INSERT INTO sucursal (id_cliente, nombre, direccion, telefono) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM sucursal WHERE id_cliente = ? AND no_sucursal = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM sucursal";
    private static final String SELECT_ALL_FROM_CLIENT = "SELECT * FROM sucursal WHERE id_cliente = ?";
    private static final String UPDATE_SQL = "UPDATE sucursal SET nombre = ?, direccion = ?, telefono = ? WHERE id_cliente = ? AND no_sucursal = ?";
    private static final String DELETE_SQL = "DELETE FROM sucursal WHERE id_cliente = ? AND no_sucursal = ?";

    private DBConnection conexion;

    public SucursalDAO() {
        conexion = new DBConnection();
    }

    public boolean insert(Sucursal sucursal) {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, sucursal.getCliente().getIdentificacion());
            stmt.setString(2, sucursal.getNombre());
            stmt.setString(3, sucursal.getDireccion());
            stmt.setString(4, sucursal.getTelefono());
            stmt.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public List<Sucursal> selectFromClient(Cliente cliente) {
        List<Sucursal> sucursales = new ArrayList<>();

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_FROM_CLIENT)) {
            stmt.setString(1, cliente.getIdentificacion());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sucursal sucursal = new Sucursal();
                    sucursal.setNumSucursal(rs.getInt("no_sucursal"));
                    sucursal.setNombre(rs.getString("nombre"));
                    sucursal.setDireccion(rs.getString("direccion"));
                    sucursal.setTelefono(rs.getString("telefono"));
                    sucursal.setCliente(cliente);
                    sucursales.add(sucursal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sucursales;
    }

    public Sucursal selectById(String idCliente, int noSucursal) {
        Sucursal sucursal = new Sucursal();
        ClienteDAO clienteDAO = new ClienteDAO();

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setString(1, idCliente);
            stmt.setInt(2, noSucursal);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    sucursal.setNumSucursal(rs.getInt("no_sucursal"));
                    sucursal.setNombre(rs.getString("nombre"));
                    sucursal.setDireccion(rs.getString("direccion"));
                    sucursal.setTelefono(rs.getString("telefono"));
                    sucursal.setCliente(clienteDAO.selectById(rs.getString("id_cliente")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sucursal;
    }

    public List<Sucursal> selectAll() {
        List<Sucursal> sucursales = new ArrayList<>();
        ClienteDAO clienteDAO = new ClienteDAO();

        try (Connection conn = conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                sucursal.setNumSucursal(rs.getInt("num_sucursal"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setDireccion(rs.getString("direccion"));
                sucursal.setTelefono(rs.getString("telefono"));
                sucursal.setCliente(clienteDAO.selectById(rs.getString("id_cliente")));
                sucursales.add(sucursal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sucursales;
    }

    public void update(Sucursal sucursal) {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, sucursal.getNombre());
            stmt.setString(2, sucursal.getDireccion());
            stmt.setString(3, sucursal.getTelefono());
            stmt.setString(4, sucursal.getCliente().getIdentificacion());
            stmt.setInt(5, sucursal.getNumSucursal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String idCliente, int noSucursal) {
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setString(1, idCliente);
            stmt.setInt(2, noSucursal);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

