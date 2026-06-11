package projectvillage;

/**
 *
 * @author Hector
 */
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class PantallaCreditos extends JPanel {
    
    private Clip clip;

    public PantallaCreditos(Ventana ventana) {
        setLayout(new BorderLayout());
        
        JLabel skyline = new JLabel();
        skyline.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon iconoSkyline = new ImageIcon(
            "src/projectvillage/imagenes/skyline.png"
        );

        Image skylineEscalado = iconoSkyline.getImage()
                .getScaledInstance(1000, 120, Image.SCALE_SMOOTH);

        skyline.setIcon(new ImageIcon(skylineEscalado));

        JLabel creditos = new JLabel(
                "<html><center>" +
                "<span style='font-size:36px'><b>Créditos</b></span><br><br>" +
                "<span style='font-size:24px'>Programador:</span><br>" +
                "<span style='font-size:20px'>Hector Julián Mateos Bravo</span><br><br>" +
                "<span style='font-size:24px'>Arte original:</span><br>" +
                "<span style='font-size:20px'>Jose Antonio Mateos Carretero</span><br><br>" +
                "<span style='font-size:24px'><b>Música original:</b></span><br>" +
                "<span style='font-size:20px'>Tarasca del Duero</span><br><br>" +
                "<span style='font-size:24px'><b>Cantantes:</b></span><br>" +
                "<span style='font-size:20px'>Susana Bravo Cabezas</span><br>" +
                "<span style='font-size:20px'>Placidia Bravo Cabezas</span><br><br>" +
                "</center></html>",
                SwingConstants.CENTER
        );
        
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);

        skyline.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditos.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelCentral.add(Box.createVerticalStrut(20));
        panelCentral.add(skyline);
        panelCentral.add(Box.createVerticalStrut(20));
        panelCentral.add(creditos);

        JButton volver = crearBotonImagen(
        "src/projectvillage/imagenes/Volver.png",
        "src/projectvillage/imagenes/Volver_hover.png"
        );

        volver.addActionListener(e -> ventana.mostrarPantalla("titulo"));

        add(panelCentral, BorderLayout.CENTER);
        add(volver, BorderLayout.SOUTH);      
    }
    
    public void reproducirAudioCreditos() {
    try {

        File archivo = new File("src/projectvillage/sonidos/creditos.wav");

        AudioInputStream audio =
                AudioSystem.getAudioInputStream(archivo);

        clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
    
    public void detenerAudioCreditos() {

        if (clip != null) {

            clip.stop();
            clip.close();

        }

    }
    
    private JButton crearBotonImagen(String rutaNormal, String rutaHover) {

        ImageIcon iconoNormal = new ImageIcon(
                new ImageIcon(rutaNormal).getImage()
                        .getScaledInstance(220, 60, Image.SCALE_SMOOTH)
        );

        ImageIcon iconoHover = new ImageIcon(
                new ImageIcon(rutaHover).getImage()
                        .getScaledInstance(220, 60, Image.SCALE_SMOOTH)
        );

        JButton boton = new JButton(iconoNormal);

        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setIcon(iconoHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setIcon(iconoNormal);
            }
        });

        return boton;
    }
}