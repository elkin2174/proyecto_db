package AccesoADatos;

import Modelo.Estado;
import Modelo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class EstadoDAO {
    public static final String REQUIERED = "Requiered";
    public static final String PICKEDUP = "Pick by Delivery";
    public static final String DELIVERED = "Deliverd";

    private static final String INSERT_SQL = "INSERT INTO estado (fecha,codigo_servicio,estado_actual, foto) VALUES (?,?, ?,?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM estado WHERE codigo_servicio = ? AND estado_actual = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM estado";
    private static final String DELETE_SQL = "DELETE FROM estado WHERE codigo_servicio = ? AND estado_actual = ?";

    private DBConnection dbConnection;

    public EstadoDAO() {
        dbConnection = new DBConnection();
    }

    public void insert(Estado estado) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setTimestamp(1, Timestamp.valueOf(estado.getFecha()));
            stmt.setInt(2, estado.getServicio().getCodigo());
            stmt.setString(3, estado.getEstadoActual());
            stmt.setString(4,"Imagen");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estado selectById(String codigoServicio, String estadoActual) {
        Estado estado = null;
        ServicioDAO servicioDAO = new ServicioDAO();

        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setString(1, codigoServicio);
            stmt.setString(2, estadoActual);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estado = new Estado();
                    estado.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                    estado.setEstadoActual(rs.getString("estado_actual"));
                    estado.setServicio(servicioDAO.selectById(rs.getString("codigo_servicio")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }

    public List<Estado> selectAll() {
        List<Estado> estados = new ArrayList<>();
        ServicioDAO servicioDAO = new ServicioDAO();
        try (Connection conn = dbConnection.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                estado.setEstadoActual(rs.getString("estado_actual"));
                estado.setServicio(servicioDAO.selectById(rs.getString("codigo_servicio")));
                estados.add(estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estados;
    }

    public void delete(String codigoServicio, String estadoActual) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setString(1, codigoServicio);
            stmt.setString(2, estadoActual);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

