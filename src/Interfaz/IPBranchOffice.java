package Interfaz;

import javax.swing.*;
import java.awt.*;

public class IPBranchOffice extends JFrame {
    private JPanel panel1;
    private JLabel lbNameOffice;
    private JLabel lbAddress;
    private JLabel lbPhoneNumber;

    public IPBranchOffice(String  lbName, String  lbAddres, String lbPhoneNumbe) {
        lbNameOffice.setText(lbName);
        lbAddress.setText(lbAddres);
        lbPhoneNumber.setText(lbPhoneNumbe);
    }

    public JPanel getPanel(){
        return panel1;
    }
}
