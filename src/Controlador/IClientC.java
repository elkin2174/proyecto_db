package Controlador;

import AccesoADatos.ClienteDAO;
import AccesoADatos.MensajeroDAO;
import Modelo.Cliente;
import Modelo.Mensajero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collection;
import java.util.List;

public class IClientC {

    private Cliente clienteContext;


    public void addAssociate(JComboBox cbMensajero){
        MensajeroDAO mensajeroDAO = new MensajeroDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        Mensajero mensajero = mensajeroDAO.selectById(cbMensajero.getSelectedItem().toString().split(" - ")[0]);
        if(clienteDAO.updateMensajeros(clienteContext, mensajero)) {
            JOptionPane.showMessageDialog(null, "Creado correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Hubo un error en la creacion");
        };
    }
    public void cargarMensajeros(Cliente cliente, DefaultTableModel model){
        ClienteDAO clienteDAO = new ClienteDAO();

    }


}
