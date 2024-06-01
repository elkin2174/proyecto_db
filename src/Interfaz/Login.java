package Interfaz;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
    private JButton bntCancelDelivery;

    public Login(){
        super("Login");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);





    }

    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
}

