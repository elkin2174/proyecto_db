package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IUser extends JFrame{
    private JButton activeServicesButtom;
    private JPanel panel1;
    private JButton addNewServicesButton;
    private JButton previousServicesButton;
    private JPanel cardPanel;
    private JPanel pActiveServices;
    private JPanel pAddNewServices;
    private JPanel pPreviousServices;
    private JTextField txtOriginCity;
    private JTextField txtDestinationCity;
    private JSpinner spNumberPackages;
    private JSpinner spTotalWeight;
    private JButton addServiceButton;
    private JButton cancelButton;
    private JScrollPane spActiveServices;
    private JScrollPane spPreviousServices;
    private JTextArea txtDescription;
    private CardLayout cardLayout;

    final static String CARD1 = "card1";
    final static String CARD2 = "card2";
    final static String CARD3 = "card3";

    public IUser(){
        super("User Interfaz");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);



        cardLayout = (CardLayout) cardPanel.getLayout();
        pActiveServices.setLayout(new BoxLayout(pActiveServices, BoxLayout.Y_AXIS));
        pPreviousServices.setLayout(new BoxLayout(pPreviousServices, BoxLayout.Y_AXIS));
        addPanelActiveServices();
        addPanelActiveServices();
        addPanelActiveServices();
        addPanelPreviousServices();
        addPanelPreviousServices();

        spActiveServices.setViewportView(pActiveServices);
        spPreviousServices.setViewportView(pPreviousServices);
        cardLayout = (CardLayout) cardPanel.getLayout();


        activeServicesButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,CARD1);
            }
        });
        addNewServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,CARD2);
            }
        });
        previousServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,CARD3);
            }
        });
        addServiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDestinationCity.setText("");
                txtOriginCity.setText("");
                spNumberPackages.setValue(0);
                spTotalWeight.setValue(0);
            }
        });
    }
    private void addPanelActiveServices() {
        JPanel jp = new IPServices("xd","xd","xd","xd","xd","xd","xd").getPanel1();
        pActiveServices.add(jp);
    }
    private void addPanelPreviousServices(){
        JPanel jp = new IPServices("Hola","Prueba","xd","xd","xd","xd","xd").getPanel1();
        pPreviousServices.add(jp);
    }
    public static void main(String[] args) {
        IUser iUser = new IUser();
        iUser.setVisible(true);
    }
}
