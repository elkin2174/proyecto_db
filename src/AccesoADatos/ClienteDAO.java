package AccesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.*;

public class ClienteDAO {
    private static final String INSERT_SQL = "INSERT INTO cliente (identificacion, tipo_cliente, nombre, email, ciudad, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM cliente WHERE identificacion = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM cliente";
    private static final String SELECT_DELIVERYS_SQL = "SELECT * FROM mensajero JOIN asociado ON mensajero.identificacion=asociado.id_mensajero AND asociado.id_cliente = ?";
    private static final String SELECT_SUCURSALES_SQL = "SELECT * FROM sucursal WHERE id_cliente = ?";
    private static final String SELECT_USERS_SQL = "SELECT * FROM usuario_cliente WHERE id_cliente = ?";
    private static final String UPDATE_SQL = "UPDATE cliente SET tipo_cliente = ?, nombre = ?, email = ?, ciudad = ?, direccion = ?, telefono = ? WHERE identificacion = ?";
    private static final String DELETE_SQL = "DELETE FROM cliente WHERE identificacion = ?";
    private static final String INSERT_MENSAJERO_SQL = "INSERT INTO asociado (id_cliente, id_mensajero) VALUES (?,?)";
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
                    cliente.setMensajeros(selectMensajerosAsociados(cliente.getIdentificacion()));
                    cliente.setSucursales(selectSucursales(cliente.getIdentificacion()));
                    cliente.setUsuarios(selectUsuarios(cliente.getIdentificacion()));
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
                cliente.setMensajeros(selectMensajerosAsociados(cliente.getIdentificacion()));
                cliente.setSucursales(selectSucursales(cliente.getIdentificacion()));
                cliente.setUsuarios(selectUsuarios(cliente.getIdentificacion()));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<Mensajero> selectMensajerosAsociados(String id_cliente) {
        List<Mensajero> mensajeros = new ArrayList<>();
        MensajeroDAO mensajeroDAO = new MensajeroDAO();

        try (Connection conn = conexion.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_DELIVERYS_SQL)) {

            stmt.setString(1, id_cliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Mensajero mensajero = new Mensajero();
                    mensajero.setIdentificacion(rs.getString("identificacion"));
                    mensajero.setNombre(rs.getString("nombre"));
                    mensajero.setEmail(rs.getString("email"));
                    mensajero.setDireccion(rs.getString("direccion"));
                    mensajero.setTelefono(rs.getString("telefono"));
                    mensajero.setServicios(mensajeroDAO.selectServicios(mensajero.getIdentificacion()));
                    mensajeros.add(mensajero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensajeros;
    }

    public List<Sucursal> selectSucursales(String id_cliente) {
        List<Sucursal> sucursales = new ArrayList<>();

        try (Connection conn = conexion.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_SUCURSALES_SQL)) {

            stmt.setString(1, id_cliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sucursal sucursal = new Sucursal();
                    sucursal.setNumSucursal(rs.getInt("no_sucursal"));
                    sucursal.setNombre(rs.getString("nombre"));
                    sucursal.setDireccion(rs.getString("direccion"));
                    sucursal.setTelefono(rs.getString("telefono"));
                    sucursales.add(sucursal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sucursales;
    }

    public List<UsuarioCliente> selectUsuarios(String id_cliente) {
        List<UsuarioCliente> usuarios = new ArrayList<>();
        UsuarioClienteDAO usuarioClienteDAO = new UsuarioClienteDAO();

        try (Connection conn = conexion.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_USERS_SQL)) {

            stmt.setString(1, id_cliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UsuarioCliente usuarioCliente = new UsuarioCliente();
                    usuarioCliente.setLogin(rs.getString("login"));
                    usuarioCliente.setPassword(rs.getString("contrasena"));
                    usuarioCliente.setDireccion(rs.getString("direccion"));
                    usuarioCliente.setEmail(rs.getString("email"));
                    usuarioCliente.setTelefono(rs.getString("telefono"));
                    usuarioCliente.setServiciosSolicitados(usuarioClienteDAO.selectAllServices(usuarioCliente.getLogin()));
                    usuarios.add(usuarioCliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
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
    public boolean updateMensajeros(Cliente cliente, Mensajero mensajero){
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_MENSAJERO_SQL)) {

            stmt.setString(1, cliente.getIdentificacion());
            stmt.setString(2, mensajero.getIdentificacion());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());
            return false;
        }

    }
}

