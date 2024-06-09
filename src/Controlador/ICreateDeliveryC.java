package Controlador;

import AccesoADatos.*;
import Modelo.Mensajero;
import Modelo.UsuarioMensajero;

import javax.swing.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ICreateDeliveryC {

    public static MensajeroDAO mensajeroDAO = new MensajeroDAO();
    public static UsuarioMensajeroDAO usuarioMensajeroDAO = new UsuarioMensajeroDAO();

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

    public static boolean createDelivery(JTextField tfId, JTextField tfNombre, JTextField tfApellido, JTextField tfEmail, JTextField tfDireccion, JTextField tfTelefono, JPasswordField tfPassword, JPasswordField tfCPassword, JTextField tfUsername) {
        // Obtener toda la información de los campos
        String id = tfId.getText();
        String nombre = tfNombre.getText() + tfApellido.getText();
        String email = tfEmail.getText();
        String direccion = tfDireccion.getText();
        String telefono = tfTelefono.getText();
        String password = String.valueOf(tfPassword.getPassword());
        String CPassword = String.valueOf(tfCPassword.getPassword());
        String username = tfUsername.getText();

        // Vaciar campos
        tfId.setText("");
        tfNombre.setText("");
        tfApellido.setText("");
        tfEmail.setText("");
        tfDireccion.setText("");
        tfTelefono.setText("");
        tfPassword.setText("");
        tfCPassword.setText("");
        tfUsername.setText("");

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
            // Insertar mensajero
            Mensajero mensajero = new Mensajero(id, nombre, email, direccion, telefono);
            if (mensajeroDAO.insert(mensajero)==0) {
                JOptionPane.showMessageDialog(null, "Mensajero creado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe un mensajero con esa identificación");
                return false;
            }

            //Insertar usuario
            UsuarioMensajero user = new UsuarioMensajero(username, password, mensajero);
            if (usuarioMensajeroDAO.insert(user)==1) {
                JOptionPane.showMessageDialog(null, "Username ya existe, debe crear uno nuevo");
                return false;
            }

            return true;
        }
    }

}
