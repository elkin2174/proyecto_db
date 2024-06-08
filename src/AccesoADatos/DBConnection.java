package AccesoADatos;

import java.sql.*;

public class DBConnection {
    String url, usuario, password;
    Connection conexion = null;

    public DBConnection() {
        url = "jdbc:postgresql://localhost:5432/proyecto_bd";
        usuario = "juan";
        password = "12345";
    }

    public Connection openConnection() {
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión exitosa");
            return conexion;
        } catch (SQLException e) {
            System.out.println("No se pudo establecer la conexión");
            e.printStackTrace();
            return null;
        }
    }

    public Connection getConnection(){
        if (conexion == null) return this.openConnection();
        else return conexion;
    }

    public void closeConnection(Connection c) {
        try {
            if (conexion != null ) c.close();
        } catch (SQLException e) {
            System.out.println("No se pudo cerrar la conexión");
        }
    }

    public static void main(String[] arg) {
        DBConnection conexionBd = new DBConnection();
        Connection c = conexionBd.openConnection();
        conexionBd.closeConnection(c);
    }
}
