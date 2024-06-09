package Interfaz;

import Controlador.LoginControlador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login  extends JFrame{
    private JPanel panel1;
    private JTextField txtDelivery;
    private JPasswordField psDelivery;
    private JTabbedPane clientTab;
    private JTextField txClient;
    private JPasswordField psClient;
    private JPanel pClient;
    private JPanel pDelivery;
    private JButton bntSingInClient;
    private JButton bntCancelClient;
    private JButton bntSingDelivery;
    private JButton bntExit;
    private JButton bntSingUpClient;
    private JButton bntSingUpDelivery;
    private JLabel clientErrorMsg;
    private JLabel deliveryErrorMsg;

    public Login() {
        super("Login");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bntSingInClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginControlador.validateLoginClient(txClient, psClient, clientErrorMsg)) {
                    dispose();
                    IClient.main(new String[]{});
                }
            }
        });

        bntSingDelivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginControlador.validateLoginMensajero(txtDelivery, psDelivery, deliveryErrorMsg)) {
                    dispose();
                    IDelivery.main(new String[]{});
                }
            }
        });

        bntSingUpClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ICreateClient.main(new String[]{});
            }
        });

        bntCancelClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bntSingUpDelivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {ICreateDelivery.main(new String[]{});
            }
        });
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
}

