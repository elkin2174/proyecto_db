package Interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IPServices extends JPanel{
    private JLabel lbClient;
    private JLabel lbCity;
    private JLabel lbId;
    private JLabel lbAddress;
    private JLabel lbTel;
    private JLabel lbMail;
    private JButton viewDetailsButton;
    private JPanel panel1;
    private JLabel lbSatus;


    public IPServices(String Status, String serviceId, String cliente, String city, String address, String Tel,String email ) {
        lbSatus.setText(Status);
        lbId.setText(serviceId);
        lbClient.setText(cliente);
        lbCity.setText(city);
        lbAddress.setText(address);
        lbTel.setText(Tel);
        lbMail.setText(email);
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IService.main(new String[]{});
            }
        });
    }
    public JPanel getPanel1() {
        return panel1;
    }
}
