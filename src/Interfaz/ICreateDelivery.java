package Interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    private JTextField txtPassword;
    private JTextField txtCPassword;
    private JPanel panel1;

    public ICreateDelivery() {
        super("Create Delivery");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
