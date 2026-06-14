package projectvillage;

/**
 *
 * @author Hector
 */

import java.awt.*;

public class Dialogo {

    private String[] lineas;
    private Image bocadillo;

    private int x;
    private int y;
    private int offsetX;
    private int offsetY;

    private long tiempoInicio;
    private int duracion = 3000;

    private boolean activo = false;

    public void mostrar(String[] lineas, int x, int y, Image bocadillo, int offsetX, int offsetY) {
        this.lineas = lineas;
        this.x = x;
        this.y = y;
        this.bocadillo = bocadillo;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.tiempoInicio = System.currentTimeMillis();
        this.activo = true;
    }

    public boolean estaActivo() {
        if (!activo) {
            return false;
        }

        if (System.currentTimeMillis() - tiempoInicio >= duracion) {
            activo = false;
        }

        return activo;
    }

    public void dibujar(Graphics g) {
        if (!estaActivo()) {
            return;
        }

        g.drawImage(bocadillo, x, y, 380, 110, null);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));

        for (int i = 0; i < lineas.length; i++) {
            g.drawString(
                lineas[i],
                x + offsetX,
                y + offsetY + (i * 20)
            );
        }
    }
}
