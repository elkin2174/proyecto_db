package Interfaz;

import javax.swing.*;
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
    private CardLayout cardLayout;

    final static String CARD1 = "card1";
    final static String CARD2 = "card2";
    public IDelivery() {
        super("Client Interfaz");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);

        cardLayout = (CardLayout) cardPanel.getLayout();
        pActiveServices.setLayout(new BoxLayout(pActiveServices, BoxLayout.Y_AXIS));
        addPanelServices();
        addPanelServices();
        addPanelServices();
        addPanelServices();
        spActiveServices.setViewportView(pActiveServices);

        activeServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {cardLayout.show(cardPanel,CARD1);}
        });
        previousServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {cardLayout.show(cardPanel,CARD2);}
        });

    }
    public void addPanelServices(){
        JPanel jp = new IPServices("xd","xd","xd","xd","xd","xd","xd").getPanel1();
        pActiveServices.add(jp);
    }

    public static void main(String[] args) {
        IDelivery id = new IDelivery();
        id.setVisible(true);
    }
}
