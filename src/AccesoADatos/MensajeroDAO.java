package AccesoADatos;

import Modelo.Cliente;
import Modelo.Mensajero;
import Modelo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensajeroDAO {
    private static final String INSERT_SQL = "INSERT INTO mensajero (identificacion, nombre, email, direccion, telefono) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM mensajero WHERE identificacion = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM mensajero";
    private static final String SELECT_CLIENTS_SQL = "SELECT * FROM cliente JOIN asociado ON cliente.identificacion=asociado.id_cliente AND asociado.id_mensajero = ?";
    private static final String SELECT_SERVICES_SQL = "SELECT * FROM servicio WHERE id_mensajero = ?";
    private static final String UPDATE_SQL = "UPDATE mensajero SET nombre = ?, email = ?, direccion = ?, telefono = ? WHERE identificacion = ?";
    private static final String DELETE_SQL = "DELETE FROM mensajero WHERE identificacion = ?";

    DBConnection dbConnection;

    public MensajeroDAO() {
        this.dbConnection = new DBConnection();
    }

    public int insert(Mensajero mensajero) {
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, mensajero.getIdentificacion());
            stmt.setString(2, mensajero.getNombre());
            stmt.setString(3, mensajero.getEmail());
            stmt.setString(4, mensajero.getDireccion());
            stmt.setString(5, mensajero.getTelefono());
            stmt.executeUpdate();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());
            return 1;
        }
    }

    public Mensajero selectById(String identificacion) {
        Mensajero mensajero = null;
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setString(1, identificacion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mensajero = new Mensajero();
                    mensajero.setIdentificacion(rs.getString("identificacion"));
                    mensajero.setNombre(rs.getString("nombre"));
                    mensajero.setEmail(rs.getString("email"));
                    mensajero.setDireccion(rs.getString("direccion"));
                    mensajero.setTelefono(rs.getString("telefono"));
                    mensajero.setClientes(selectClientesAsociados(mensajero.getIdentificacion()));
                    mensajero.setServicios(selectServicios(mensajero.getIdentificacion()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensajero;
    }

    public List<Mensajero> selectAll() {
        List<Mensajero> mensajeros = new ArrayList<>();
        try (Connection conn = dbConnection.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                Mensajero mensajero = new Mensajero();
                mensajero.setIdentificacion(rs.getString("identificacion"));
                mensajero.setNombre(rs.getString("nombre"));
                mensajero.setEmail(rs.getString("email"));
                mensajero.setDireccion(rs.getString("direccion"));
                mensajero.setTelefono(rs.getString("telefono"));
                mensajero.setClientes(selectClientesAsociados(mensajero.getIdentificacion()));
                mensajero.setServicios(selectServicios(mensajero.getIdentificacion()));
                mensajeros.add(mensajero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensajeros;
    }

    public List<Cliente> selectClientesAsociados(String id_mensajero) {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CLIENTS_SQL)) {

            stmt.setString(1, id_mensajero);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdentificacion(rs.getString("identificacion"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setTipoCliente(rs.getString("tipo_cliente"));
                    cliente.setCiudad(rs.getString("ciudad"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefono(rs.getString("telefono"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<Servicio> selectServicios(String id_mensajero) {
        List<Servicio> servicios = new ArrayList<>();
        UsuarioClienteDAO usuarioClienteDAO = new UsuarioClienteDAO();
        ServicioDAO servicioDAO = new ServicioDAO();

        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_SERVICES_SQL)) {

            stmt.setString(1, id_mensajero);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Servicio servicio = new Servicio();
                    servicio.setCodigo(rs.getInt("codigo"));
                    servicio.setNumPaquetes(rs.getInt("numero_paquetes"));
                    servicio.setOrigen(rs.getString("origen"));
                    servicio.setDestino(rs.getString("destino"));
                    servicio.setTipoTransporte(rs.getString("tipo_transporte"));
                    servicio.setDescripcion(rs.getString("descripcion"));
                    servicio.setCiudad(rs.getString("ciudad"));
                    servicio.setFechaSolicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
                    servicio.setCliente(usuarioClienteDAO.selectById(rs.getString("id_usuario")));
                    servicio.setEstados(servicioDAO.selectAllStates(rs.getString("codigo")));
                    servicios.add(servicio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicios;
    }

    public void update(Mensajero mensajero) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, mensajero.getNombre());
            stmt.setString(2, mensajero.getEmail());
            stmt.setString(3, mensajero.getDireccion());
            stmt.setString(4, mensajero.getTelefono());
            stmt.setString(5, mensajero.getIdentificacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String identificacion) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setString(1, identificacion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

