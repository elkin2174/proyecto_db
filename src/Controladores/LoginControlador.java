package Controladores;

import AccesoADatos.UsuarioClienteDAO;
import Interfaz.IClient;
import Modelo.Cliente;
import Modelo.UsuarioCliente;

import javax.swing.*;

public class LoginControlador {

    // Retorna un booleano de si login se hizo correctamente y modifica el mensaje de error
    public static boolean validateLogin(JTextField username, JPasswordField password, JLabel clientErrorMsg) {
        clientErrorMsg.setText("");
        String userStr = username.getText();
        String passStr = String.valueOf(password.getPassword());
        if (userStr.isEmpty() || passStr.isEmpty()) {
            clientErrorMsg.setText("No pueden haber campos vacíos");
            return false;
        }
        UsuarioClienteDAO dao = new UsuarioClienteDAO();
        UsuarioCliente user = dao.selectById(userStr);
        if (user == null) {
            clientErrorMsg.setText("El usuario no existe");
            return false;
        };
        if (!user.getPassword().equals(passStr)) {
            clientErrorMsg.setText("Contraseña incorrecta");
            return false;
        }
        //Aquí se debe guardar el usuario en un estado de la aplicación
        return true;
    }
}
