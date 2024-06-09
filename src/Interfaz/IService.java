package Interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.*;

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
    private JButton requiredButton;
    private JButton pickedUpByCourierButton;
    private JButton deliveredButton;

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
                Path targetDirectory = Paths.get("src/data");
                String newNameImage = "nuvonombre" + ".png";
                if(jf.showOpenDialog(getThis()) == JFileChooser.APPROVE_OPTION){
                    Path sourceFile = Paths.get(jf.getSelectedFile().toString());
                    try {
                        // Verificar si el archivo fuente es un archivo y no un directorio
                        if (!Files.isRegularFile(sourceFile)) {
                            return;
                        }
                        // Crear el directorio de destino si no existe
                        if (Files.notExists(targetDirectory)) {
                            Files.createDirectories(targetDirectory);
                        }
                        // Construir la ruta destino del archivo
                        Path targetFile = targetDirectory.resolve(newNameImage);
                        // Mover el archivo al directorio destino
                        Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        requiredButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        pickedUpByCourierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deliveredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
