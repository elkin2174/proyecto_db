package Interfaz;

import Controladores.LoginControlador;
import Modelo.UsuarioCliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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

    public Login(){
        super("Login");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bntSingInClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginControlador.validateLogin(txClient, psClient, clientErrorMsg)) {
                    dispose();
                    IClient.main(new String[]{});
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
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
}

