package projectvillage;

/**
 *
 * @author Hector
 */

import javax.swing.*;
import java.awt.*;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

public class PantallaLogo extends JPanel {

    private Image logoEmpresa;
    private int opacidad = 255;

    public PantallaLogo(Ventana ventana) {

        setBackground(Color.WHITE);

        logoEmpresa = new ImageIcon(
            "src/projectvillage/imagenes/logo_empresa.png"
        ).getImage();

        Timer fade = new Timer(40, e -> {

            opacidad -= 5;

            if (opacidad <= 0) {
                ((Timer)e.getSource()).stop();
                ventana.mostrarPantalla("titulo");
            }

            repaint();
        });

        Timer timer = new Timer(4000, e -> {
            fade.start();
        });

        timer.setRepeats(false);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setComposite(
            AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER,
                opacidad / 255f
            )
        );

        g2.drawImage(
            logoEmpresa,
            390,
            210,
            500,
            300,
            this
        );
    }
}
