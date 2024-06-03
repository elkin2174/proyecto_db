package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Predicate;

public class IClient extends JFrame{
    private JButton activeServicesButton;
    private JPanel panel1;
    private JButton addNewServiceButton;
    private JButton previousServicesButton;
    private JPanel cardPanel;
    private JPanel pActiveServices;
    private JPanel pAddNewService;
    private JPanel pPreviousServices;
    private CardLayout cardLayout;

    final static String CARD1 = "card1";
    final static String CARD2 = "card2";
    final static String CARD3 = "card3";

    public IClient(){
        super("Create Users");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);


        cardPanel.add(pActiveServices,CARD1);
        cardPanel.add(pAddNewService,CARD2);
        cardPanel.add(pPreviousServices,CARD3);
        cardLayout = (CardLayout) cardPanel.getLayout();


        activeServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,CARD1);
            }
        });
        addNewServiceButton.addActionListener(new ActionListener() {
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
    }

    public static void main(String[] args) {
        IClient iUser = new IClient();
        iUser.setVisible(true);
    }
}
