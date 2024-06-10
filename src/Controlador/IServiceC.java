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
import java.util.Random;

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
            servicio.getEstados().add(estado);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        LoginControlador.getUsuarioContext().getServiciosSolicitados().add(servicio);


        JOptionPane.showMessageDialog(null, "Servicio creado correctamente ");
        origen.setText("");
        destini.setText("");
        numPackage.setValue(0);
        weightPackage.setValue(0);
        descripcion.setText("");
    }

    private Mensajero getMensajero() {

        Random random = new Random();
        try {
            int indiceAleatorio = random.nextInt(usuarioContext.getCliente().getMensajeros().size());
            return usuarioContext.getCliente().getMensajeros().get(indiceAleatorio);
        }catch (IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null,"No hay mensajeros asociados");
            return null;
        }catch (IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,"No hay mensajeros asociados");
            return null;
        }

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

    public Servicio getServiceById(String id, JLabel lbCodigo, JLabel lbOrigin,JLabel lbDestination, JLabel lbRequeseDate,
                               JLabel tipoTranporte, JLabel numPackage,JLabel description, JLabel status, JLabel statusDate,
                               JLabel img) {
        ServicioDAO servicioDAO = new ServicioDAO();
        Servicio servicio = servicioDAO.selectByIdClient(id);
        lbCodigo.setText(Integer.toString(servicio.getCodigo()));
        lbOrigin.setText(servicio.getOrigen());
        lbDestination.setText(servicio.getDestino());
        lbRequeseDate.setText(servicio.getFechaSolicitud().toString());
        tipoTranporte.setText(servicio.getTipoTransporte());
        numPackage.setText(Integer.toString(servicio.getNumPaquetes()));
        description.setText(servicio.getDescripcion());
        status.setText(getEstadoActual(servicio).getEstadoActual());
        statusDate.setText(getEstadoActual(servicio).getFecha().toString());

        return servicio;
//      ImageIcon imageIcon = new ImageIcon("src/data/" + getEstadoActual(servicio).ge);
    }

    public void selectStatus(Servicio servicio, JLabel status,JLabel statusDate, JLabel img, int indexStatus){
        try {
            statusDate.setText(servicio.getEstados().get(indexStatus).getFecha().toString());
            status.setText(servicio.getEstados().get(indexStatus).getEstadoActual());
        }catch (IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "Se encuentra en un estado anterior");
        }

        //img.setText(servicio.getEstados().get(indexStatus));
    }

    public Estado getEstadoActual(Servicio servicio){
        return  servicio.getEstados().get(servicio.getEstados().size()-1);
    }

    public boolean validationFinish(Servicio servicio){
        if(servicio.getEstados().size() == 3){
            return true;
        }
        return false;
    }

    public void changeStatus(Servicio servicio) {
        EstadoDAO estadoDAO = new EstadoDAO();
        if(servicio.getEstados().size() == 1){
            Estado estado = new Estado(EstadoDAO.PICKEDUP,servicio);
            estadoDAO.insert(estado);
            servicio.addEstado(estado);
        }else if(servicio.getEstados().size() == 2){
            Estado estado = new Estado(EstadoDAO.DELIVERED,servicio);
            estadoDAO.insert(estado);
            servicio.addEstado(estado);
        }

    }
}
