package Controlador;

import AccesoADatos.SucursalDAO;
import Interfaz.IBranchOffice;
import Modelo.Cliente;
import Modelo.Sucursal;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchOfficeC {

    public static List<Sucursal> getBranchOffices() {
        if (LoginControlador.getUsuarioContext() == null) return new ArrayList<>();
        SucursalDAO dao = new SucursalDAO();
        return dao.selectFromClient(LoginControlador.getUsuarioContext().getCliente());
    }

    public static boolean createBranchOffice(JTextField name, JTextField address, JTextField phone) {
        if (LoginControlador.getUsuarioContext() == null) return false;
        String _name = name.getText();
        String _address = address.getText();
        String _phone = phone.getText();
        if (_name.isEmpty() || _address.isEmpty() || _phone.isEmpty()) return false;
        Cliente cliente = LoginControlador.getUsuarioContext().getCliente();
        SucursalDAO dao = new SucursalDAO();
        Sucursal sucursal = new Sucursal(0, _name, _address, _phone, cliente);
        return dao.insert(sucursal);
    }
}
