package Controlador;

import AccesoADatos.*;
import Modelo.Cliente;
import Modelo.UsuarioCliente;
//import org.postgresql.util.PSQLException;

import javax.swing.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class CreateClientC {

    public static ClienteDAO clienteDAO = new ClienteDAO();
    public static UsuarioClienteDAO usuarioClienteDAO = new UsuarioClienteDAO();

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\+57|0057)?\\s?(1\\s?\\d{7}|3\\d{2}\\s?\\d{7})$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean createClient(JTextField tfId, JComboBox cbTipoCliente, JTextField tfNombre, JTextField tfApellido, JTextField tfEmail, JTextField tfCiudad, JTextField tfDireccion, JTextField tfTelefono, JPasswordField tfPassword, JPasswordField tfCPassword, JTextField tfUsername) {
        // Obtener toda la información de los campos
        String id = tfId.getText();
        String tipoCliente = cbTipoCliente.getSelectedItem().toString();
        String nombre = tfNombre.getText() + tfApellido.getText();
        String email = tfEmail.getText();
        String ciudad = tfCiudad.getText();
        String direccion = tfDireccion.getText();
        String telefono = tfTelefono.getText();
        String password = String.valueOf(tfPassword.getPassword());
        String CPassword = String.valueOf(tfCPassword.getPassword());
        String username = tfUsername.getText();

        // Verificaciones
        if (id == "") {
            tfId.setText("Es obligatorio poner la identificación");
            return false;
        }
        if (!isValidEmail(email)) {
            tfEmail.setText("El email no corresponde a un formato de email válido");
            return false;
        }
        if (!isValidPhoneNumber(telefono)) {
            tfTelefono.setText("Número de teléfono inválido");
            return false;
        }
        if (!CPassword.equals(password)) {
            tfPassword.setText("Contrasenas no coinciden");
            return false;
        }
        else {
            // Insertar cliente
            Cliente cliente = new Cliente(id, tipoCliente, nombre, email, ciudad, direccion, telefono);
            if (clienteDAO.insert(cliente)==0) {
                JOptionPane.showMessageDialog(null, "Cliente creado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe un cliente con esa identificación");
                return false;
            }

            //Insertar usuario
            UsuarioCliente user = new UsuarioCliente(username, password, direccion, email, telefono, cliente);
            if (usuarioClienteDAO.insert(user)==1) {
                JOptionPane.showMessageDialog(null, "Username ya está en uso, debe crear uno nuevo");
                return false;
            }

            // Vaciar campos
            tfId.setText("");
            tfNombre.setText("");
            tfApellido.setText("");
            tfEmail.setText("");
            tfCiudad.setText("");
            tfDireccion.setText("");
            tfTelefono.setText("");
            tfPassword.setText("");
            tfCPassword.setText("");
            tfUsername.setText("");

            return true;
        }
    }
}
