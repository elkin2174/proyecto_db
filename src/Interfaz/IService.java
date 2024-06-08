package Interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IService extends JFrame {
    private JPanel panel1;
    private JLabel lbImage;
    private JLabel lbId;
    private JLabel lbOrigen;
    private JLabel lbDestination;
    private JLabel lbDateRequest;
    private JLabel lbTypeTransport;
    private JLabel lbNumberPackages;
    private JLabel lbDescription;
    private JLabel lbStatus;
    private JLabel lbStatusDate;
    private JButton changeStatusButton;

    public IService()  {
        super("Client Interfaz");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);
        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                jf.setMultiSelectionEnabled(false);
                if(jf.showOpenDialog(getThis()) == JFileChooser.APPROVE_OPTION){

                }
            }
        });
    }
    private IService getThis(){
        return this;
    }

    public static void main(String[] args) {
        IService iService = new IService();
        iService.setVisible(true);
    }
}
