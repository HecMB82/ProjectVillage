package projectvillage;

/**
 *
 * @author Hector
 */

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    private CardLayout cardLayout;
    private JPanel contenedor;
    private PantallaTitulo pantallaTitulo;
    private PantallaOpciones pantallaOpciones;
    private PantallaCreditos pantallaCreditos;
    private PantallaJuego pantallaJuego;

    public Ventana() {
        setTitle(" Un verano en el pueblo");
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        pantallaTitulo = new PantallaTitulo(this);
        contenedor.add(pantallaTitulo, "titulo");
        pantallaJuego = new PantallaJuego(this);
        contenedor.add(pantallaJuego, "juego");
        pantallaOpciones = new PantallaOpciones(this);
        contenedor.add(pantallaOpciones, "opciones");
        pantallaCreditos = new PantallaCreditos(this);
        contenedor.add(pantallaCreditos, "creditos");

        add(contenedor);
        
        pantallaTitulo.reproducirMusicaTitulo();

        setVisible(true);
    }

    public void mostrarPantalla(String nombre) {

        pantallaTitulo.detenerMusicaTitulo();
        pantallaCreditos.detenerAudioCreditos();
        pantallaJuego.detenerMusicaJuego();

        cardLayout.show(contenedor, nombre);
        
        if (nombre.equals("juego")) {
            pantallaJuego.activarTeclado();
            pantallaJuego.reproducirMusicaJuego();
        }

        if (nombre.equals("titulo")) {
            pantallaTitulo.reproducirMusicaTitulo();
        }

        if (nombre.equals("creditos")) {
            pantallaCreditos.reproducirAudioCreditos();
        }
    }
    
}
