package AccesoADatos;

import Modelo.Mensajero;
import Modelo.UsuarioMensajero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioMensajeroDAO {
    private static final String INSERT_SQL = "INSERT INTO usuario_mensajero (login, contrasena, id_mensajero) VALUES (?, ?, ?)";
    private static final String SELECT_BY_LOGIN_SQL = "SELECT * FROM usuario_mensajero WHERE login = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM usuario_mensajero";
    private static final String UPDATE_SQL = "UPDATE usuario_mensajero SET contrasena = ?, id_mensajero = ? WHERE login = ?";
    private static final String DELETE_SQL = "DELETE FROM usuario_mensajero WHERE login = ?";

    private DBConnection dbConnection;

    public UsuarioMensajeroDAO() {
        this.dbConnection = new DBConnection();
    }

    public int insert(UsuarioMensajero usuarioMensajero) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, usuarioMensajero.getLogin());
            stmt.setString(2, usuarioMensajero.getPassword());
            stmt.setString(3, usuarioMensajero.getMensajero().getIdentificacion());
            stmt.executeUpdate();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());
            return 1;
        }
    }

    public UsuarioMensajero selectByLogin(String login) {
        UsuarioMensajero usuarioMensajero = null;
        Mensajero mensajero = new Mensajero();
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_LOGIN_SQL)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioMensajero = new UsuarioMensajero();
                    usuarioMensajero.setLogin(rs.getString("login"));
                    usuarioMensajero.setPassword(rs.getString("contraseña"));
                    mensajero.setIdentificacion(rs.getString("id_mensajero"));
                    usuarioMensajero.setMensajero(mensajero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioMensajero;
    }

    public List<UsuarioMensajero> selectAll() {
        List<UsuarioMensajero> usuariosMensajero = new ArrayList<>();
        Mensajero mensajero = new Mensajero();
        try (Connection conn = dbConnection.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                UsuarioMensajero usuarioMensajero = new UsuarioMensajero();
                usuarioMensajero.setLogin(rs.getString("login"));
                usuarioMensajero.setPassword(rs.getString("contraseña"));
                mensajero.setIdentificacion(rs.getString("id_mensajero"));
                usuarioMensajero.setMensajero(mensajero);
                usuariosMensajero.add(usuarioMensajero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuariosMensajero;
    }

    public void update(UsuarioMensajero usuarioMensajero) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, usuarioMensajero.getPassword());
            stmt.setString(2, usuarioMensajero.getMensajero().getIdentificacion());
            stmt.setString(3, usuarioMensajero.getLogin());
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

