package AccesoADatos;

import Modelo.Mensajero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensajeroDAO {
    private static final String INSERT_SQL = "INSERT INTO mensajero (identificacion, nombre, email, direccion, telefono) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM mensajero WHERE identificacion = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM mensajero";
    private static final String UPDATE_SQL = "UPDATE mensajero SET nombre = ?, email = ?, direccion = ?, telefono = ? WHERE identificacion = ?";
    private static final String DELETE_SQL = "DELETE FROM mensajero WHERE identificacion = ?";

    private DBConnection dbConnection;

    public MensajeroDAO() {
        this.dbConnection = new DBConnection();
    }

    public void insert(Mensajero mensajero) {
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, mensajero.getIdentificacion()+"");
            stmt.setString(2, mensajero.getNombre());
            stmt.setString(3, mensajero.getEmail());
            stmt.setString(4, mensajero.getDireccion());
            stmt.setString(5, mensajero.getTelefono()+"");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
                mensajeros.add(mensajero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensajeros;
    }

    public void update(Mensajero mensajero) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, mensajero.getNombre());
            stmt.setString(2, mensajero.getEmail());
            stmt.setString(3, mensajero.getDireccion());
            stmt.setString(4, mensajero.getTelefono()+"");
            stmt.setString(5, mensajero.getIdentificacion()+"");
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

