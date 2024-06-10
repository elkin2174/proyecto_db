package AccesoADatos;

import Modelo.Cliente;
import Modelo.Servicio;
import Modelo.UsuarioCliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioClienteDAO {
    private static final String INSERT_SQL = "INSERT INTO usuario_cliente (login, contrasena, direccion, email, telefono, id_cliente) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM usuario_cliente WHERE login = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM usuario_cliente";
    private static final String SELECT_SERVICES_SQL = "SELECT * FROM servicio WHERE id_usuario = ?";
    private static final String UPDATE_SQL = "UPDATE usuario_cliente SET contrasena = ?, direccion = ?, email = ?, telefono = ?, id_cliente = ? WHERE login = ?";
    private static final String DELETE_SQL = "DELETE FROM usuario_cliente WHERE login = ?";

    private DBConnection dbConnection;

    public UsuarioClienteDAO() {
        this.dbConnection = new DBConnection();
    }

    public int insert(UsuarioCliente usuarioCliente) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, usuarioCliente.getLogin());
            stmt.setString(2, usuarioCliente.getPassword());
            stmt.setString(3, usuarioCliente.getDireccion());
            stmt.setString(4, usuarioCliente.getEmail());
            stmt.setString(5, usuarioCliente.getTelefono());
            stmt.setString(6, usuarioCliente.getCliente().getIdentificacion());
            stmt.executeUpdate();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());
            return 1;
        }
    }

    public UsuarioCliente selectById(String login) {
        UsuarioCliente usuarioCliente = new UsuarioCliente();
        ClienteDAO clienteDAO = new ClienteDAO();

        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioCliente.setLogin(rs.getString("login"));
                    usuarioCliente.setPassword(rs.getString("contrasena"));
                    usuarioCliente.setDireccion(rs.getString("direccion"));
                    usuarioCliente.setEmail(rs.getString("email"));
                    usuarioCliente.setTelefono(rs.getString("telefono"));
                    usuarioCliente.setCliente(clienteDAO.selectById(rs.getString("id_cliente")));
                    usuarioCliente.setServiciosSolicitados(selectAllServices(usuarioCliente.getLogin()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioCliente;
    }

    public List<UsuarioCliente> selectAll() {
        List<UsuarioCliente> usuariosCliente = new ArrayList<>();
        ClienteDAO clienteDAO = new ClienteDAO();

        try (Connection conn = dbConnection.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                UsuarioCliente usuarioCliente = new UsuarioCliente();
                usuarioCliente.setLogin(rs.getString("login"));
                usuarioCliente.setPassword(rs.getString("contraseña"));
                usuarioCliente.setDireccion(rs.getString("direccion"));
                usuarioCliente.setEmail(rs.getString("email"));
                usuarioCliente.setTelefono(rs.getString("telefono"));
                usuarioCliente.setCliente(clienteDAO.selectById(rs.getString("id_cliente")));
                usuarioCliente.setServiciosSolicitados(selectAllServices(usuarioCliente.getLogin()));
                usuariosCliente.add(usuarioCliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuariosCliente;
    }

    public List<Servicio> selectAllServices(String id_usuario) {
        List<Servicio> servicios = new ArrayList<>();
        ServicioDAO servicioDAO = new ServicioDAO();
        MensajeroDAO mensajeroDAO = new MensajeroDAO();

        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_SERVICES_SQL)) {

            stmt.setString(1, id_usuario);
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
                    servicio.setEstados(servicioDAO.selectAllStates(servicio.getCodigo()));
                    servicio.setMensajero(mensajeroDAO.selectById(rs.getString("id_mensajero")));
                    servicios.add(servicio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicios;
    }


    public void update(UsuarioCliente usuarioCliente) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, usuarioCliente.getPassword());
            stmt.setString(2, usuarioCliente.getDireccion());
            stmt.setString(3, usuarioCliente.getEmail());
            stmt.setString(4, usuarioCliente.getTelefono());
            stmt.setString(5, usuarioCliente.getCliente().getIdentificacion());
            stmt.setString(6, usuarioCliente.getLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String login) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setString(1, login);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

