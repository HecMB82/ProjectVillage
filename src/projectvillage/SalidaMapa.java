package projectvillage;

/**
 *
 * @author Hector
 */

import java.awt.Image;
import java.awt.Rectangle;

public class SalidaMapa {

    private Rectangle zona;
    private String destino;

    private int flechaX;
    private int flechaY;
    
    private int destinoX;
    private int destinoY;

    public SalidaMapa(
            Rectangle zona,
            String destino,
            int flechaX,
            int flechaY,
            int destinoX,
            int destinoY){

        this.zona = zona;
        this.destino = destino;
        this.flechaX = flechaX;
        this.flechaY = flechaY;
        this.destinoX = destinoX;
        this.destinoY = destinoY;
    }

    public Rectangle getZona() {
        return zona;
    }

    public String getDestino() {
        return destino;
    }

    public int getFlechaX() {
        return flechaX;
    }

    public int getFlechaY() {
        return flechaY;
    }
    
    public int getDestinoX() {
        return destinoX;
    }

    public int getDestinoY() {
        return destinoY;
    }
}
