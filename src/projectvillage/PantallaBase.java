package projectvillage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class PantallaBase extends JPanel {

    protected Ventana ventana;
    protected Jugador jugador;
    protected Image fondo;
    protected Polygon zonaJugable;

    protected ArrayList<SalidaMapa> salidas = new ArrayList<>();

    protected Image flechaSalida;
    protected Image teclaE;

    public PantallaBase(Ventana ventana, Jugador jugador, String rutaFondo) {
        this.ventana = ventana;
        this.jugador = jugador;

        fondo = new ImageIcon(rutaFondo).getImage();

        flechaSalida = new ImageIcon(
                "src/projectvillage/imagenes/IR.png"
        ).getImage();

        teclaE = new ImageIcon(
                "src/projectvillage/imagenes/E.png"
        ).getImage();

        setFocusable(true);
        configurarMovimiento();
    }

    protected void configurarMovimiento() {

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {

                int velocidad = 10;

                int anteriorX = jugador.getX();
                int anteriorY = jugador.getY();

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_D) {
                    jugador.setX(jugador.getX() + velocidad);
                    jugador.mirarDerecha();
                }

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_A) {
                    jugador.setX(jugador.getX() - velocidad);
                    jugador.mirarIzquierda();
                }

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_S) {
                    jugador.setY(jugador.getY() + velocidad);
                    jugador.mirarFrente();
                }

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_W) {
                    jugador.setY(jugador.getY() - velocidad);
                    jugador.mirarEspalda();
                }

                int piesX = jugador.getX() + 32;
                int piesY = jugador.getY() + 95;

                if (zonaJugable != null && !zonaJugable.contains(piesX, piesY)) {
                    jugador.setX(anteriorX);
                    jugador.setY(anteriorY);
                }

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_E) {
                    comprobarInteraccion();
                }

                repaint();
            }
        });
    }

    protected void comprobarInteraccion() {

        for (SalidaMapa salida : salidas) {

            if (estaCerca(salida.getZona())) {

                jugador.setX(salida.getDestinoX());
                jugador.setY(salida.getDestinoY());
                jugador.mirarFrente();

                ventana.mostrarPantalla(salida.getDestino());
                return;
            }
        }
    }

    protected boolean estaCerca(Rectangle zona) {

        Rectangle piesJugador = new Rectangle(
                jugador.getX() + 20,
                jugador.getY() + 85,
                24,
                15
        );

        return piesJugador.intersects(zona);
    }

    protected void dibujarFondo(Graphics g) {
        g.drawImage(
                fondo,
                0,
                0,
                getWidth(),
                getHeight(),
                this
        );
    }

    protected void dibujarSalidas(Graphics g) {

        for (SalidaMapa salida : salidas) {

            if (estaCerca(salida.getZona())) {

                g.drawImage(
                        flechaSalida,
                        salida.getFlechaX(),
                        salida.getFlechaY(),
                        70,
                        70,
                        this
                );

                g.drawImage(
                        teclaE,
                        salida.getFlechaX() + 20,
                        salida.getFlechaY() - 5,
                        30,
                        30,
                        this
                );
            }
        }
    }
    
    public void activarTeclado() {
        SwingUtilities.invokeLater(() -> {
            requestFocusInWindow();
        });
    }
}
