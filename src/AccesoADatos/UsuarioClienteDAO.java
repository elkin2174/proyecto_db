package AccesoADatos;

import Modelo.UsuarioCliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioClienteDAO {
    private static final String INSERT_SQL = "INSERT INTO usuario_cliente (login, contraseña, direccion, email, telefono, id_cliente) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM usuario_cliente WHERE login = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM usuario_cliente";
    private static final String UPDATE_SQL = "UPDATE usuario_cliente SET contraseña = ?, direccion = ?, email = ?, telefono = ?, id_cliente = ? WHERE login = ?";
    private static final String DELETE_SQL = "DELETE FROM usuario_cliente WHERE login = ?";

    private DBConnection dbConnection;

    public UsuarioClienteDAO() {
        this.dbConnection = new DBConnection();
    }

    public void insert(UsuarioCliente usuarioCliente) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, usuarioCliente.getLogin());
            stmt.setString(2, usuarioCliente.getPassword());
            stmt.setString(3, usuarioCliente.getDireccion());
            stmt.setString(4, usuarioCliente.getEmail());
            stmt.setString(5, usuarioCliente.getTelefono()+"");
            stmt.setString(6, usuarioCliente.getCliente().getIdentificacion()+"");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UsuarioCliente selectById(String login) {
        UsuarioCliente usuarioCliente = null;
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioCliente = new UsuarioCliente();
                    usuarioCliente.setLogin(rs.getString("login"));
                    usuarioCliente.setPassword(rs.getString("contraseña"));
                    usuarioCliente.setDireccion(rs.getString("direccion"));
                    usuarioCliente.setEmail(rs.getString("email"));
                    usuarioCliente.setTelefono(Integer.parseInt(rs.getString("telefono")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioCliente;
    }

    public List<UsuarioCliente> selectAll() {
        List<UsuarioCliente> usuariosCliente = new ArrayList<>();
        try (Connection conn = dbConnection.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                UsuarioCliente usuarioCliente = new UsuarioCliente();
                usuarioCliente.setLogin(rs.getString("login"));
                usuarioCliente.setPassword(rs.getString("contraseña"));
                usuarioCliente.setDireccion(rs.getString("direccion"));
                usuarioCliente.setEmail(rs.getString("email"));
                usuarioCliente.setTelefono(Integer.parseInt(rs.getString("telefono")));
                usuariosCliente.add(usuarioCliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuariosCliente;
    }

    public void update(UsuarioCliente usuarioCliente) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, usuarioCliente.getPassword());
            stmt.setString(2, usuarioCliente.getDireccion());
            stmt.setString(3, usuarioCliente.getEmail());
            stmt.setString(4, usuarioCliente.getTelefono()+"");
            stmt.setString(5, usuarioCliente.getCliente().getIdentificacion()+"");
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

