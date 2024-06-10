package Controlador;

import Interfaz.IDelivery;
import Modelo.Cliente;
import Modelo.Mensajero;
import Modelo.UsuarioMensajero;

import javax.swing.table.DefaultTableModel;

public class IDeliveryC {
    private UsuarioMensajero mensajeroContex;
    public IDeliveryC(){
        mensajeroContex  = LoginControlador.getMensajeroContext();
    }

    public void cargarAsociateCliente(DefaultTableModel model){
        model.setRowCount(0);
        for (Cliente cliente : mensajeroContex.getMensajero().getClientes()){
            model.addRow(new Object[]{
                    cliente.getIdentificacion(),
                    cliente.getNombre()
            });
        }
    }
}
