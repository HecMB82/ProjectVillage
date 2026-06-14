package projectvillage;

/**
 *
 * @author Hector
 */

import java.awt.Image;

public class Animacion {

    private Image[] frames;
    private int velocidad;

    public Animacion(Image[] frames, int velocidad) {
        this.frames = frames;
        this.velocidad = velocidad;
    }

    public Image getFrame(int contadorAnimacion) {
        int indice = (contadorAnimacion / velocidad) % frames.length;
        return frames[indice];
    }
}
