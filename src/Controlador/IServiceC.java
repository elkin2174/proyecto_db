package Controlador;

import AccesoADatos.EstadoDAO;
import AccesoADatos.ServicioDAO;
import Interfaz.IService;
import Modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IServiceC {


    private UsuarioCliente usuarioContext;
    private UsuarioMensajero mensajeroContext;

    public IServiceC() {
        if(LoginControlador.getUsuarioContext() != null){
            usuarioContext = LoginControlador.getUsuarioContext();
        } else {
            mensajeroContext = LoginControlador.getMensajeroContext();
        }


    }
    public void addService(JTextField origen, JTextField destini, JSpinner numPackage, JSpinner weightPackage,
                           JTextArea descripcion){
        ServicioDAO servicioDAO = new ServicioDAO();
        Servicio servicio = new Servicio((ServicioDAO.codigo + 1),(int) numPackage.getValue(),
                origen.getText(),
                destini.getText(),
                getTipoTranporte((int) numPackage.getValue(), weightPackage),
                descripcion.getText(),
                origen.getText(),
                getMensajero(),
                usuarioContext
        );
        servicioDAO.insert(servicio);
        try {
            EstadoDAO estadoDAO = new EstadoDAO();
            Estado estado = new Estado(EstadoDAO.REQUIERED,servicio);
            estadoDAO.insert(estado);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }



        JOptionPane.showMessageDialog(null, "Servicio creado correctamente ");
        origen.setText("");
        destini.setText("");
        numPackage.setValue(0);
        weightPackage.setValue(0);
        descripcion.setText("");


    }

    private Mensajero getMensajero() {
        int random = (int) Math.floor(Math.random()*(usuarioContext.getCliente().getMensajeros().size())
                + usuarioContext.getCliente().getMensajeros().size() - 1 );
        System.out.println(random);
        return usuarioContext.getCliente().getMensajeros().get(random);
    }

    private String getTipoTranporte(int numberPackege, JSpinner weightPackage) {
        if ((1 <= numberPackege && numberPackege < 5) ||
                ((int)weightPackage.getValue() > 0 && (int)weightPackage.getValue() < 30)){
            return "Moto";
        }else if((5 <= numberPackege && numberPackege < 10) ||
                ((int)weightPackage.getValue() > 30 && (int)weightPackage.getValue() < 60)){
            return "Carro";
        } else if ((10 <= numberPackege) ||
                (int)weightPackage.getValue() > 60){
            return "Camion";
        }
        return "No Asignado";
    }

    public void getServiceById(String id, JLabel lbCodigo, JLabel lbOrigin,JLabel lbDestination, JLabel lbRequeseDate,
                               JLabel tipoTranporte, JLabel numPackage,JLabel description, JLabel status, JLabel statusDate,
                               JLabel img) {
        ServicioDAO servicioDAO = new ServicioDAO();
        Servicio servicio = servicioDAO.selectById(id);
        lbCodigo.setText(servicio.getOrigen());
        lbOrigin.setText(servicio.getDestino());
        lbDestination.setText(servicio.getFechaSolicitud().toString());
        tipoTranporte.setText(servicio.getTipoTransporte());
        numPackage.setText(Integer.toString(servicio.getNumPaquetes()));
        description.setText(servicio.getDescripcion());
        status.setText(getEstadoActual(servicio).getEstadoActual());
        statusDate.setText(getEstadoActual(servicio).getFecha().toString());


//      ImageIcon imageIcon = new ImageIcon("src/data/" + getEstadoActual(servicio).ge);
    }

    public void selectStatus(String id, JLabel statusDate, JLabel img, int indexStatus){
        ServicioDAO servicioDAO = new ServicioDAO();
        Servicio servicio = servicioDAO.selectById(id);
        statusDate.setText(servicio.getEstados().get(indexStatus).getEstadoActual());
        statusDate.setText(servicio.getEstados().get(indexStatus).getFecha().toString());
    }

    public Estado getEstadoActual(Servicio servicio){
        return  servicio.getEstados().get(servicio.getEstados().size()-1);
    }
}
