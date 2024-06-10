package Interfaz;

import AccesoADatos.EstadoDAO;
import Controlador.IDeliveryC;
import Controlador.IServiceC;
import Controlador.LoginControlador;
import Modelo.Servicio;
import Modelo.UsuarioMensajero;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IDelivery extends JFrame {
    private JButton activeServicesButton;
    private JButton previousServicesButton;
    private JPanel cardPanel;
    private JScrollPane spActiveServices;
    private JScrollPane spPreviousServices;
    private JPanel panel1;
    private JPanel pActiveServices;
    private JPanel pPreviousServices;
    private JMenu jMenu;
    private JMenuBar jManuBar;
    private JMenuItem jmIAsociateClient;
    private JTable table1;
    private JScrollPane associtedClient;
    private JButton logOutButton;
    private CardLayout cardLayout;

    final static String CARD1 = "card1";
    final static String CARD2 = "card2";
    private DefaultTableModel model;
    private UsuarioMensajero usuarioContext;
    private IDeliveryC controlador;
    public IDelivery() {
        super("Client Interfaz");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);
        controlador = new IDeliveryC();

        usuarioContext = LoginControlador.getMensajeroContext();

        // Crear un DefaultTableModel y añadir las columnas
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        table1.setModel(model);

        TableColumnModel columnModel = table1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        columnModel.getColumn(0).setCellRenderer(centerRenderer);

        cardLayout = (CardLayout) cardPanel.getLayout();

        pActiveServices.setLayout(new BoxLayout(pActiveServices, BoxLayout.Y_AXIS));
        pPreviousServices.setLayout(new BoxLayout(pPreviousServices, BoxLayout.Y_AXIS));
        spPreviousServices.setViewportView(pPreviousServices);
        spActiveServices.setViewportView(pActiveServices);
        addPanelServices(usuarioContext);

        activeServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,CARD1);
                addPanelServices(usuarioContext);
                addPanelServices(usuarioContext);
            }
        });
        previousServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,CARD2);
                addPanelServices(usuarioContext);
            }
        });
        jmIAsociateClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cargarAsociateCliente(model);
                cardLayout.show(cardPanel,"card3");
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginControlador.setMensajeroContext(null);
                Login.main(new String[]{});
            }
        });
    }
    private void addPanelServices(UsuarioMensajero mensajeroContex) {
        IServiceC iServiceC = new IServiceC();
        pActiveServices.removeAll();
        pPreviousServices.removeAll();
        for(Servicio servicio : mensajeroContex.getMensajero().getServicios()){
            JPanel jp = new IPServices(iServiceC.getEstadoActual(servicio).getEstadoActual(),
                    Integer.toString(servicio.getCodigo()),
                    mensajeroContex.getMensajero().getIdentificacion(),
                    servicio.getCiudad(), servicio.getDestino(),
                    servicio.getCliente().getTelefono(), servicio.getCliente().getEmail()).getPanel1();
            if(!iServiceC.getEstadoActual(servicio).getEstadoActual().equals(EstadoDAO.DELIVERED)) {
                pActiveServices.add(jp);
            }else{
                pPreviousServices.add(jp);
            }
        }
    }

    public static void main(String[] args) {
        IDelivery id = new IDelivery();
        id.setVisible(true);
    }
}
