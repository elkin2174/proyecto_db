package Interfaz;


import Controlador.CreateUserClientC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ICreateUserClient  extends JFrame {


    private JTextField txtUsername;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JButton createNewUserButton;
    private JButton cancelButton;
    private JPanel panel1;
    private JPasswordField txtPassword;
    private JPasswordField txtCPassword;
    private JTextField txtTel;

    public ICreateUserClient() {
        super("Create Users");
        setContentPane(panel1);
        setSize(450, 350);
        setLocationRelativeTo(null);
        createNewUserButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (CreateUserClientC.createUserClient(txtEmail, txtAddress, txtTel,txtUsername, txtPassword, txtPassword)) {
                    dispose();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        ICreateUserClient iCreateUserClient = new ICreateUserClient();
        iCreateUserClient.setVisible(true);
    }
}
