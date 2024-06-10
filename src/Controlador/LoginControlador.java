package Controlador;

import AccesoADatos.UsuarioClienteDAO;
import AccesoADatos.UsuarioMensajeroDAO;
import Modelo.Cliente;
import Modelo.Servicio;
import Modelo.UsuarioCliente;
import Modelo.UsuarioMensajero;

import javax.swing.*;

public class LoginControlador {

    private static UsuarioCliente usuarioContext;
    private static UsuarioMensajero mensajeroContext;


    // Retorna un booleano de si login se hizo correctamente y modifica el mensaje de error
    public static boolean validateLoginClient(JTextField username, JPasswordField password, JLabel errorMsg) {
        errorMsg.setText("");
        String userStr = username.getText();
        String passStr = String.valueOf(password.getPassword());

        if (userStr.isEmpty() || passStr.isEmpty()) {
            errorMsg.setText("No pueden haber campos vacíos");
            return false;
        }

        UsuarioClienteDAO dao = new UsuarioClienteDAO();
        UsuarioCliente user = dao.selectById(userStr);

        //System.out.println(user.getLogin() + user.getPassword() + user.getDireccion());

        if (user.getLogin() == null) {
            errorMsg.setText("El usuario no existe");
            return false;
        }
        if (!user.getPassword().equals(passStr)) {
            errorMsg.setText("Contraseña incorrecta");
            return false;
        }

        //Aquí se debe guardar el usuario en un estado de la aplicación
        usuarioContext = user;
        return true;
    }


    // Retorna un booleano de si login se hizo correctamente y modifica el mensaje de error
    public static boolean validateLoginMensajero(JTextField username, JPasswordField password, JLabel errorMsg) {
        errorMsg.setText("");
        String userStr = username.getText();
        String passStr = String.valueOf(password.getPassword());

        if (userStr.isEmpty() || passStr.isEmpty()) {
            errorMsg.setText("No pueden haber campos vacíos");
            return false;
        }

        UsuarioMensajeroDAO dao = new UsuarioMensajeroDAO();
        UsuarioMensajero user = dao.selectByLogin(userStr);

        if (user == null) {
            errorMsg.setText("El usuario no existe");
            return false;
        }
        if (!user.getPassword().equals(passStr)) {
            errorMsg.setText("Contraseña incorrecta");
            return false;
        }

        //Aquí se debe guardar el usuario en un estado de la aplicación
        mensajeroContext = user;

        return true;
    }

    public static UsuarioCliente getUsuarioContext() {
        return usuarioContext;
    }

    public static void setUsuarioContext(UsuarioCliente usuarioContext) {
        LoginControlador.usuarioContext = usuarioContext;
    }

    public static UsuarioMensajero getMensajeroContext() {
        return mensajeroContext;
    }

    public static void setMensajeroContext(UsuarioMensajero mensajeroContext) {
        LoginControlador.mensajeroContext = mensajeroContext;
    }
}
