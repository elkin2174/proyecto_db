package Interfaz;

import Controlador.BranchOfficeC;
import Modelo.Sucursal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IBranchOffice extends JFrame{
    private JPanel panel1;
    private JScrollPane spBranchOfficce;
    private JButton addNewBranchOfficeButton;
    private JButton branchOfficesButton;
    private JButton closeButton;
    private JPanel pAddNewOffice;
    private JPanel cardPanel;
    private JPanel pBranchOffice;
    private JTextField txtName;
    private JTextField txtAddres;
    private JTextField txtPhoneNumber;
    private JButton addButton;
    private JButton cancelButton;
    private CardLayout cardLayout;

    public IBranchOffice() {
        super("Client Interfaz");
        setContentPane(panel1);
        setSize(600, 400);
        setLocationRelativeTo(null);

        cardLayout = (CardLayout) cardPanel.getLayout();
        pBranchOffice.setLayout(new BoxLayout(pBranchOffice, BoxLayout.Y_AXIS));

        pBranchOffice.removeAll();
        for (Sucursal surcursal : BranchOfficeC.getBranchOffices()) {
            addBranchOffice(surcursal.getNombre(), surcursal.getDireccion(), surcursal.getTelefono());
        }

        spBranchOfficce.setViewportView(pBranchOffice);

        cardLayout = (CardLayout) cardPanel.getLayout();

        branchOfficesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pBranchOffice.removeAll();
                for (Sucursal surcursal : BranchOfficeC.getBranchOffices()) {
                    addBranchOffice(surcursal.getNombre(), surcursal.getDireccion(), surcursal.getTelefono());
                }
                cardLayout.first(cardPanel);
            }
        });
        addNewBranchOfficeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.last(cardPanel);
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean fue_exitoso = BranchOfficeC.createBranchOffice(txtName, txtAddres, txtPhoneNumber);
                if (!fue_exitoso) {
                    JOptionPane.showMessageDialog(null, "xd");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAddres.setText("");
                txtName.setText("");
                txtPhoneNumber.setText("");
            }
        });
    }

    public void addBranchOffice(String name, String address, String phone){
        JPanel jp = new IPBranchOffice(name, address, phone).getPanel();
        pBranchOffice.add(jp);
    }

    public static void main(String[] args) {
        IBranchOffice iBranchOffice = new IBranchOffice();
        iBranchOffice.setVisible(true);
    }
}
