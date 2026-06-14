package projectvillage;

/**
 *
 * @author Hector
 */

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class NPC {
    
    private int x;
    private int y;
    private Image imagen;
    private Image imagenAlternativa;

    private Rectangle zonaInteraccion;
    
    private NPC leonila;
    private NPC adriana;
    
    public NPC(
            
            int x,
            int y,
            Image imagen,
            Image imagenAlternativa
        ) {

        this.x = x;
        this.y = y;

        this.imagen = imagen;
        this.imagenAlternativa = imagenAlternativa;        
       
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagen() {
        return imagen;
    }

    public Image getImagenAlternativa() {
        return imagenAlternativa;
    }

}
