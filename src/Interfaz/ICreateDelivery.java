package Interfaz;

import Controlador.ICreateDeliveryC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ICreateDelivery extends JFrame {
    private JLabel lNit;
    private JTextField txtId;
    private JTextField txtFirstName;
    private JButton signUpButton;
    private JButton cancelButton;
    private JTextField txtLastName;
    private JTextField txtTel;
    private JTextField txtAddress;
    private JTextField txtEmail;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtCPassword;
    private JPanel panel1;

    public ICreateDelivery() {
        super("Create Delivery");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ICreateDeliveryC.createDelivery(txtId,txtFirstName, txtLastName, txtEmail,txtAddress, txtTel, txtPassword, txtCPassword, txtUsername)) {
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
        ICreateDelivery icd = new ICreateDelivery();
        icd.setVisible(true);
    }
}
