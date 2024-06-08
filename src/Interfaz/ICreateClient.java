package Interfaz;

import Controlador.ICreateClientC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ICreateClient extends JFrame {
    private JPanel panel1;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtCountry;
    private JTextField txtCity;
    private JTextField txtEmail;
    private JButton signUpButton;
    private JButton cancelButton;
    private JTextField txtId;
    private JComboBox comboBox1;
    private JLabel lNit;
    private JTextField txtTel;
    private JTextField txtAddress;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtCPassword;

    public ICreateClient(){
        super("Create Users");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ICreateClientC.createClient(txtId, comboBox1, txtFirstName, txtLastName, txtEmail, txtCity, txtAddress, txtTel, txtPassword, txtCPassword, txtUsername)) {
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

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem().toString().equals("Person")){
                    lNit.setText("ID");
                } else{
                    lNit.setText("NIT");
                };
            }
        });
    }

    public static void main(String[] args) {

        ICreateClient iCreateClient = new ICreateClient();
        iCreateClient.setVisible(true);

    }
}
