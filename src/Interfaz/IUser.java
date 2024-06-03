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
    private CardLayout cardLayout;

    final static String CARD1 = "card1";
    final static String CARD2 = "card2";
    final static String CARD3 = "card3";

    public IUser(){
        super("User Interfaz");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);


        cardPanel.add(pActiveServices,CARD1);
        cardPanel.add(pAddNewServices,CARD2);
        cardPanel.add(pPreviousServices,CARD3);
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
    }

    public static void main(String[] args) {
        IUser iUser = new IUser();
        iUser.setVisible(true);
    }
}
