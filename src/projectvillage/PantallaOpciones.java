package projectvillage;

/**
 *
 * @author Hector
 */

import javax.swing.*;
import java.awt.*;

public class PantallaOpciones extends JPanel {

    public PantallaOpciones(Ventana ventana) {
        setLayout(new BorderLayout());
        setBackground(new Color(230, 220, 190));

        JLabel titulo = new JLabel("Opciones", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 60));

        JButton volver = crearBotonImagen(
        "src/projectvillage/imagenes/Volver.png",
        "src/projectvillage/imagenes/Volver_hover.png"
        );
        volver.addActionListener(e -> ventana.mostrarPantalla("titulo"));

        add(titulo, BorderLayout.CENTER);
        add(volver, BorderLayout.SOUTH);
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