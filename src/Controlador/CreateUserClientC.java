package Controlador;

import AccesoADatos.ClienteDAO;
import AccesoADatos.UsuarioClienteDAO;
import Modelo.Cliente;
import Modelo.UsuarioCliente;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUserClientC {
    public static UsuarioClienteDAO usuarioClienteDAO = new UsuarioClienteDAO();
    public static UsuarioCliente usuarioClienteContext = LoginControlador.getUsuarioContext();

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean createUserClient(JTextField tfEmail, JTextField tfAddress, JTextField tfUsername, JPasswordField tfPassword,  JPasswordField tfCPassword) {
        // Obtener toda la información de los campos
        String email = tfEmail.getText();
        String direccion = tfAddress.getText();
        String password = String.valueOf(tfPassword.getPassword());
        String CPassword = String.valueOf(tfCPassword.getPassword());
        String username = tfUsername.getText();

        // Verificaciones
        if (!isValidEmail(email)) {
            tfEmail.setText("Email inválido");
            return false;
        }
        if (!password.equals(CPassword)) {
            tfCPassword.setText("Contrasenas no coinciden");
            return false;
        }
        else {
            // Crear usuario
            Cliente cliente = usuarioClienteContext.getCliente();
            UsuarioCliente user = new UsuarioCliente(username, password, direccion, email, "", cliente);
            if (usuarioClienteDAO.insert(user) == 0) {
                JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");

                // Vaciar campos
                tfEmail.setText("");
                tfAddress.setText("");
                tfPassword.setText("");
                tfCPassword.setText("");
                tfUsername.setText("");

                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Username ya está en uso, debe utilizar uno nuevo");
                return false;
            }
        }
    }
}
