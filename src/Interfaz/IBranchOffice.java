package Interfaz;

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
        for (int i = 0; i < 30 ; i++) {
            addBranchOffice();

        }

        spBranchOfficce.setViewportView(pBranchOffice);

        cardLayout = (CardLayout) cardPanel.getLayout();

        branchOfficesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"card1");
            }
        });
        addNewBranchOfficeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"card2");
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
    public void addBranchOffice(){
        JPanel jp = new IPBranchOffice("xd","xd","xd").getPanel();
        pBranchOffice.add(jp);
    }


    public static void main(String[] args) {
        IBranchOffice iBranchOffice = new IBranchOffice();
        iBranchOffice.setVisible(true);
    }
}
