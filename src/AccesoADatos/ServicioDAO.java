package AccesoADatos;

import Modelo.Servicio;
import Modelo.UsuarioCliente;
import Modelo.UsuarioMensajero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {
    private static final String INSERT_SQL = "INSERT INTO servicio (codigo, numero_paquetes, origen, destino, tipo_transporte, descripcion, ciudad, fecha_solicitud, id_mensajero, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM servicio WHERE codigo = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM servicio";
    private static final String UPDATE_SQL = "UPDATE servicio SET numero_paquetes = ?, origen = ?, destino = ?, tipo_transporte = ?, descripcion = ?, ciudad = ?, fecha_solicitud = ?, id_mensajero = ?, id_usuario = ? WHERE codigo = ?";
    private static final String DELETE_SQL = "DELETE FROM servicio WHERE codigo = ?";

    private DBConnection dbConnection;

    public ServicioDAO() {
        this.dbConnection = new DBConnection();
    }

    public void insert(Servicio servicio) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, servicio.getCodigo()+"");
            stmt.setInt(2, servicio.getNumPaquetes());
            stmt.setString(3, servicio.getOrigen());
            stmt.setString(4, servicio.getDestino());
            stmt.setString(5, servicio.getTipoTransporte());
            stmt.setString(6, servicio.getDescripcion());
            stmt.setString(7, servicio.getCiudad());
            stmt.setTimestamp(8, Timestamp.valueOf(servicio.getFechaSolicitud()));
            stmt.setString(9, servicio.getMensajero().getIdentificacion()+"");
            stmt.setString(10, servicio.getCliente().getLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Servicio selectById(String codigo) {
        Servicio servicio = null;
        UsuarioCliente usuario = new UsuarioCliente();
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setString(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    servicio = new Servicio();
                    servicio.setCodigo(Integer.parseInt(rs.getString("codigo")));
                    servicio.setNumPaquetes(rs.getInt("numero_paquetes"));
                    servicio.setOrigen(rs.getString("origen"));
                    servicio.setDestino(rs.getString("destino"));
                    servicio.setTipoTransporte(rs.getString("tipo_transporte"));
                    servicio.setDescripcion(rs.getString("descripcion"));
                    servicio.setCiudad(rs.getString("ciudad"));
                    servicio.setFechaSolicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicio;
    }

    public List<Servicio> selectAll() {
        List<Servicio> servicios = new ArrayList<>();
        try (Connection conn = dbConnection.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setCodigo(Integer.parseInt(rs.getString("codigo")));
                servicio.setNumPaquetes(rs.getInt("numero_paquetes"));
                servicio.setOrigen(rs.getString("origen"));
                servicio.setDestino(rs.getString("destino"));
                servicio.setTipoTransporte(rs.getString("tipo_transporte"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setCiudad(rs.getString("ciudad"));
                servicio.setFechaSolicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
                servicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicios;
    }

    public void update(Servicio servicio) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setInt(1, servicio.getNumPaquetes());
            stmt.setString(2, servicio.getOrigen());
            stmt.setString(3, servicio.getDestino());
            stmt.setString(4, servicio.getTipoTransporte());
            stmt.setString(5, servicio.getDescripcion());
            stmt.setString(6, servicio.getCiudad());
            stmt.setTimestamp(7, Timestamp.valueOf(servicio.getFechaSolicitud()));
            stmt.setString(8, servicio.getMensajero().getIdentificacion()+"");
            stmt.setString(9, servicio.getCliente().getLogin());
            stmt.setString(10, servicio.getCodigo()+"");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String codigo) {
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
