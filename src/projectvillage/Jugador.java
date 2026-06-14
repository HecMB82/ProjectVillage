package projectvillage;

/**
 *
 * @author Hector
 */

import javax.swing.*;
import java.awt.*;

public class Jugador {

    private int x;
    private int y;

    private Image frente;
    private Image espalda;
    private Image derecha;
    private Image izquierda;

    private Image imagenActual;

    public Jugador() {

        x = 700;
        y = 580;

        frente = new ImageIcon(
            "src/projectvillage/imagenes/hec_frente.png"
        ).getImage();

        espalda = new ImageIcon(
            "src/projectvillage/imagenes/hec_espalda.png"
        ).getImage();

        derecha = new ImageIcon(
            "src/projectvillage/imagenes/hec_der.png"
        ).getImage();

        izquierda = new ImageIcon(
            "src/projectvillage/imagenes/hec_izq.png"
        ).getImage();

        imagenActual = frente;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImagenActual() {
        return imagenActual;
    }

    public void mirarFrente() {
        imagenActual = frente;
    }

    public void mirarEspalda() {
        imagenActual = espalda;
    }

    public void mirarDerecha() {
        imagenActual = derecha;
    }

    public void mirarIzquierda() {
        imagenActual = izquierda;
    }
}
