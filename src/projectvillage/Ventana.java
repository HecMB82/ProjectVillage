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
    private PantallaLogo pantallaLogo;
    private PantallaTitulo pantallaTitulo;
    private PantallaOpciones pantallaOpciones;
    private PantallaCreditos pantallaCreditos;
    private Jugador jugador;
    private Pantalla1 pantalla1;
    private Pantalla2 pantalla2;

    public Ventana() {
        setTitle(" Un verano en el pueblo");
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        jugador = new Jugador();
        
        pantallaLogo = new PantallaLogo(this);
        contenedor.add(pantallaLogo, "logo");

        pantallaTitulo = new PantallaTitulo(this);
        contenedor.add(pantallaTitulo, "titulo");
        pantalla1 = new Pantalla1(this, jugador);
        contenedor.add(pantalla1, "juego");
        pantalla2 = new Pantalla2(this, jugador);
        contenedor.add(pantalla2, "pantalla2");
        pantallaOpciones = new PantallaOpciones(this);
        contenedor.add(pantallaOpciones, "opciones");
        pantallaCreditos = new PantallaCreditos(this);
        contenedor.add(pantallaCreditos, "creditos");

        add(contenedor);
        
        cardLayout.show(contenedor, "logo");
        setVisible(true);
    }

    public void mostrarPantalla(String nombre) {

        pantallaTitulo.detenerMusicaTitulo();
        pantallaCreditos.detenerAudioCreditos();
        pantalla1.detenerMusicaJuego();

        cardLayout.show(contenedor, nombre);
        
        if (nombre.equals("juego")) {
            pantalla1.activarTeclado();
            pantalla1.reproducirMusicaJuego();
        }
        
        if (nombre.equals("pantalla2")) {
            pantalla2.activarTeclado();
        }

        if (nombre.equals("titulo")) {
            pantallaTitulo.iniciarEntradaTitulo();
            pantallaTitulo.reproducirMusicaTitulo();
        }

        if (nombre.equals("creditos")) {
            pantallaCreditos.reproducirAudioCreditos();
        }
    }
    
}
