package projectvillage;

/**
 *
 * @author Hector
 */

import javax.swing.*;
import java.awt.*;

public class Pantalla2 extends PantallaBase {
    
    private SalidaMapa salidaPantalla1;
    private Image flechaSalida;
    private Image teclaE;

    
    public Pantalla2(Ventana ventana, Jugador jugador) {

        super(
            ventana,
            jugador,
            "src/projectvillage/imagenes/Pantalla2.png"
        );
        
        configurarMovimiento();
        
        salidaPantalla1 = new SalidaMapa(
            new Rectangle(600, 650, 120, 40),
            "juego",
            620,
            560,
            700,
            440
        );
        
        salidas.add(salidaPantalla1);

        flechaSalida = new ImageIcon(
            "src/projectvillage/imagenes/IR.png"
        ).getImage();

        teclaE = new ImageIcon(
            "src/projectvillage/imagenes/E.png"
        ).getImage();

        configurarMovimiento();
        
                addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_E) {
                    comprobarInteraccion();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        dibujarFondo(g);
        dibujarSalidas(g);

        g.drawImage(
            jugador.getImagenActual(),
            jugador.getX(),
            jugador.getY(),
            64,
            100,
            this
        );        
        
    }
        
}