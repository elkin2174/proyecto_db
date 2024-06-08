package AccesoADatos;

import Modelo.Estado;
import Modelo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    private static final String INSERT_SQL = "INSERT INTO estado (fecha, estado_actual, foto, codigo_servicio) VALUES (?, ?, ?, ?)";
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
            stmt.setString(2, estado.getEstadoActual());
            stmt.setString(4, estado.getServicio().getCodigo()+"");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estado selectById(String codigoServicio, String estadoActual) {
        Estado estado = null;
        Servicio serv = new Servicio();
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setString(1, codigoServicio);
            stmt.setString(2, estadoActual);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estado = new Estado();
                    estado.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                    estado.setEstadoActual(rs.getString("estado_actual"));
                    serv.setCodigo(Integer.parseInt(rs.getString("codigo_servicio")));
                    estado.setServicio(serv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }

    public List<Estado> selectAll() {
        List<Estado> estados = new ArrayList<>();
        Servicio serv = new Servicio();
        try (Connection conn = dbConnection.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                estado.setEstadoActual(rs.getString("estado_actual"));
                serv.setCodigo(Integer.parseInt(rs.getString("codigo_servicio")));
                estado.setServicio(serv);
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

