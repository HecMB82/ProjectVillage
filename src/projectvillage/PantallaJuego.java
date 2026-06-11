package projectvillage;

/**
 *
 * @author Hector
 */
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class PantallaJuego extends JPanel {
    
    private int jugadorX = 700;
    private int jugadorY = 580;
    private Image fondo;
    private Image jugadorFrente;
    private Image jugadorEspalda;
    private Image jugadorDerecha;
    private Image jugadorIzquierda;
    private Image jugadorActual;
    private Image abuelaLeonila;
    private Image abuelaLeonila2;
    private int contadorAnimacion = 0;
    private long ultimoMiau = 0;
    private Image bocadilloLeonila;
    private Image bocadilloAdriana;
    private Image bocadilloActual;
    private String textoBocadillo = "";
    private String[] lineasBocadillo;
    private int bocadilloX = 0;
    private int bocadilloY = 0;
    private long tiempoBocadillo = 0;
    private int textoOffsetX = 35;
    private int textoOffsetY = 45;
    private Image abuelaAdriana;
    private Image abuelaAdriana2;
    private Image pajaroArriba;
    private Image pajaroAbajo;
    private int pajaroX = -50;
    private int pajaroY = 80;
    private Image humo;
    private int humoX = 117;
    private int humoY1 = 200;
    private int humoY2 = 220;
    private int humoY3 = 280;
    private int humoY4 = 300;
    private Image gato1;
    private Image gato2;
    private Image gato3;
    private Image gato4;
    private boolean mostrarColisiones = false;
    private Rectangle zonaLeonila = new Rectangle(450, 530, 50, 30);
    private Rectangle zonaAdriana = new Rectangle(520, 500, 80, 50);
    private Rectangle zonaGato = new Rectangle(160, 580, 60, 30);
    private Clip musicaJuego;
    private FloatControl volumenJuego;
    private long tiempoMiau = 0;
    private boolean miauActivo = false;
    private Image teclaE;

    private Polygon zonaJugable = new Polygon(
        new int[]{50, 230, 580, 630, 760, 780, 810, 1220, 1220, 50},
        new int[]{650, 580, 530, 440, 440, 450, 450, 580, 680, 680},
        10
    );

    public PantallaJuego(Ventana ventana) {

        setBackground(new Color(120, 180, 120));
        
        fondo = new ImageIcon(
        "src/projectvillage/imagenes/fondo_juego.png"
        ).getImage();
        
        jugadorFrente = new ImageIcon("src/projectvillage/imagenes/hec_frente.png").getImage();
        jugadorEspalda = new ImageIcon("src/projectvillage/imagenes/hec_espalda.png").getImage();
        jugadorDerecha = new ImageIcon("src/projectvillage/imagenes/hec_der.png").getImage();
        jugadorIzquierda = new ImageIcon("src/projectvillage/imagenes/hec_izq.png").getImage();

        jugadorActual = jugadorFrente;
        
        abuelaLeonila = new ImageIcon(
        "src/projectvillage/imagenes/abu_leo.png"
        ).getImage();
        
        abuelaLeonila2 = new ImageIcon("src/projectvillage/imagenes/abu_leo_ojos.png"
        ).getImage();        
        
        abuelaAdriana = new ImageIcon(
        "src/projectvillage/imagenes/abu_adri.png"
        ).getImage();
        
        abuelaAdriana2 = new ImageIcon(
        "src/projectvillage/imagenes/abu_adri_ojos.png"
        ).getImage();
        
        pajaroArriba = new ImageIcon(
        "src/projectvillage/imagenes/alas_arriba.png"
        ).getImage();

        pajaroAbajo = new ImageIcon(
        "src/projectvillage/imagenes/alas_abajo.png"
        ).getImage();
        
        humo = new ImageIcon(
        "src/projectvillage/imagenes/humo.png"
        ).getImage();
        
        teclaE = new ImageIcon(
            "src/projectvillage/imagenes/E.png"
        ).getImage();
        
        gato1 = new ImageIcon("src/projectvillage/imagenes/gato_1.png").getImage();
        gato2 = new ImageIcon("src/projectvillage/imagenes/gato_2.png").getImage();
        gato3 = new ImageIcon("src/projectvillage/imagenes/gato_3.png").getImage();
        gato4 = new ImageIcon("src/projectvillage/imagenes/gato_4.png").getImage();
        
        bocadilloLeonila = new ImageIcon(
            "src/projectvillage/imagenes/bocadillo_der.png"
        ).getImage();

        bocadilloAdriana = new ImageIcon(
            "src/projectvillage/imagenes/bocadillo_izq.png"
        ).getImage();
        
        Timer timer = new Timer(400, e -> {
            contadorAnimacion++;
            
            pajaroX += 6;

            if (pajaroX > getWidth() + 50) {
                pajaroX = -50;
            }
            
            humoY1 -= 2;
            humoY2 -= 2;
            humoY3 -= 2;
            humoY4 -= 2;

            if (humoY1 < 100) humoY1 = 260;
            if (humoY2 < 100) humoY2 = 260;
            if (humoY3 < 100) humoY3 = 260;
            if (humoY4 < 100) humoY4 = 260;
            
            repaint();
        });

        timer.start();
        
        setFocusable(true);

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {

                int velocidad = 10;
                
                int anteriorX = jugadorX;
                int anteriorY = jugadorY;

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_D) {
                    jugadorX += velocidad;
                    jugadorActual = jugadorDerecha;
                }

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_A) {
                    jugadorX -= velocidad;
                    jugadorActual = jugadorIzquierda;
                }

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_S) {
                    jugadorY += velocidad;
                    jugadorActual = jugadorFrente;
                }

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_W) {
                    jugadorY -= velocidad;
                    jugadorActual = jugadorEspalda;
                }
                
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_E) {
                    comprobarInteraccion();
                }
                
                int piesX = jugadorX + 32;
                int piesY = jugadorY + 95;

                if (!zonaJugable.contains(piesX, piesY)) {
                    jugadorX = anteriorX;
                    jugadorY = anteriorY;
                }

                repaint();
            }
        });

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(
        fondo,
        0,
        0,
        getWidth(),
        getHeight(),
        this
        );
        
        long ahora = System.currentTimeMillis();

        boolean bocadilloActivo =
                !textoBocadillo.equals("")
                && ahora - tiempoBocadillo < 3000;

        boolean sonidoGatoActivo =
                miauActivo
                && ahora - tiempoMiau < 1500;
        
        if (miauActivo && ahora - tiempoMiau >= 1500) {
            miauActivo = false;
        }
        
        Image gatoActual;

        int frameGato = (contadorAnimacion / 2) % 6;

        switch (frameGato) {
            case 0:
                gatoActual = gato1;
                break;
            case 1:
                gatoActual = gato2;
                break;
            case 2:
                gatoActual = gato3;
                break;
            case 3:
                gatoActual = gato4;
                break;
            case 4:
                gatoActual = gato3;
                break;
            default:
                gatoActual = gato2;
                break;
        }

        g.drawImage(
            gatoActual,
            160,
            325,
            60,
            30,
            this
        );
        
        if (estaCerca(zonaGato) && !sonidoGatoActivo) {

            g.drawImage(
                teclaE,
                190,
                290,
                30,
                30,
                this
            );
        }
        
        Image abuelaAdrianaActual;

        if ((contadorAnimacion + 3) % 22 == 0) {
            abuelaAdrianaActual = abuelaAdriana2; // ojos cerrados
        } else {
            abuelaAdrianaActual = abuelaAdriana; // ojos abiertos
        }

        g.drawImage(
            abuelaAdrianaActual,
            500,
            420,
            70,
            110,
            this
        );
        
        if (estaCerca(zonaAdriana) && !bocadilloActivo) {

            g.drawImage(
                teclaE,
                520,
                380,
                30,
                30,
                this
            );
        }
        
        Image abuelaLeonilaActual;

        if (contadorAnimacion % 10 == 0) {
            abuelaLeonilaActual = abuelaLeonila2;   // ojos cerrados
        } else {
            abuelaLeonilaActual = abuelaLeonila;    // ojos abiertos
        }

        g.drawImage(
            abuelaLeonilaActual,
            450,
            400,
            60,
            140,
            this
        ); 
        
        if (estaCerca(zonaLeonila) && !bocadilloActivo) {

            g.drawImage(
                teclaE,
                465,
                365,
                30,
                30,
                this
            );
        }
        
        if (mostrarColisiones) {
            g.setColor(new Color(0, 255, 0, 80));

            g.fillPolygon(zonaJugable);

            g.setColor(Color.GREEN);
            g.drawPolygon(zonaJugable);
        }
        
        if (mostrarColisiones) {
            g.setColor(new Color(255, 0, 0, 90));

            g.fillRect(zonaLeonila.x, zonaLeonila.y, zonaLeonila.width, zonaLeonila.height);
            g.fillRect(zonaAdriana.x, zonaAdriana.y, zonaAdriana.width, zonaAdriana.height);
            g.fillRect(zonaGato.x, zonaGato.y, zonaGato.width, zonaGato.height);

            g.setColor(Color.RED);

            g.drawRect(zonaLeonila.x, zonaLeonila.y, zonaLeonila.width, zonaLeonila.height);
            g.drawRect(zonaAdriana.x, zonaAdriana.y, zonaAdriana.width, zonaAdriana.height);
            g.drawRect(zonaGato.x, zonaGato.y, zonaGato.width, zonaGato.height);
        }

        double escala;

        if (jugadorY > 360) {
            // Zona cercana: cambia poco
            escala = 1.15 + ((jugadorY - 520) / 80.0) * 0.35;
        } else {
            // Zona lejana: disminuye más rápido
            escala = 0.45 + ((jugadorY - 360) / 4.0) * 0.20;
        }
        
        if (escala < 0.35) {
            escala = 0.35;
        }

        int anchoJugador = (int)(64 * escala);
        int altoJugador = (int)(100 * escala);

        int jugadorDibujadoX = jugadorX - (anchoJugador - 64) / 2;
        int jugadorDibujadoY = jugadorY - (altoJugador - 100);

        g.drawImage(
            jugadorActual,
            jugadorDibujadoX,
            jugadorDibujadoY,
            anchoJugador,
            altoJugador,
            this
        );
        
        Image pajaroActual;

        if (contadorAnimacion % 2 == 0) {
            pajaroActual = pajaroArriba;
        } else {
            pajaroActual = pajaroAbajo;
        }
        
        g.drawImage(
            pajaroActual,
            pajaroX,
            pajaroY,
            25,
            15,
            this
        );
        
        int humoInicio = 260;

        int tam1 = 20 + (humoInicio - humoY1) / 3;
        int tam2 = 20 + (humoInicio - humoY2) / 3;
        int tam3 = 20 + (humoInicio - humoY3) / 3;
        int tam4 = 20 + (humoInicio - humoY4) / 3;

        g.drawImage(humo, humoX, humoY1, tam1, tam1, this);
        g.drawImage(humo, humoX + 5, humoY2, tam2, tam2, this);
        g.drawImage(humo, humoX - 4, humoY3, tam3, tam3, this);
        g.drawImage(humo, humoX + 8, humoY4, tam4, tam4, this);
        
        if (!textoBocadillo.equals("")) {            

            if (ahora - tiempoBocadillo < 3000) {

                g.drawImage(
                bocadilloActual,
                bocadilloX,
                bocadilloY,
                380,
                110,
                this
            );

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 14));

            for (int i = 0; i < lineasBocadillo.length; i++) {
                g.drawString(
                    lineasBocadillo[i],
                    bocadilloX + textoOffsetX,
                    bocadilloY + textoOffsetY + (i * 20)
                );
            }

            } else {
                textoBocadillo = "";
            }
        }
    }
    
    private void comprobarInteraccion() {

        if (estaCerca(zonaLeonila)) {

            mostrarBocadillo(
                new String[]{
                    "Ponte una rebequita,",
                    "que empieza a refrescar."
                },
                90,
                310,
                bocadilloLeonila,
                90,
                35
            );

        } else if (estaCerca(zonaAdriana)) {

            mostrarBocadillo(
                new String[]{
                    "¿Vienes a casa a cenar?",
                    "Tengo huevos fritos con patatas."
                },
                550,
                330,
                bocadilloAdriana,
                60,
                35
            );

        } else if (estaCerca(zonaGato)) {

            long tiempoActual = System.currentTimeMillis();

            if (tiempoActual - ultimoMiau > 1500) {

                reproducirSonido(
                    "src/projectvillage/sonidos/miau.wav"
                );

                ultimoMiau = tiempoActual;
                tiempoMiau = tiempoActual;
                miauActivo = true;
            }
        }
    }
    
    private void mostrarBocadillo(
            String[] lineas,
            int x,
            int y,
            Image bocadillo,
            int offsetX,
            int offsetY) {

        lineasBocadillo = lineas;
        textoBocadillo = "activo";
        bocadilloX = x;
        bocadilloY = y;
        bocadilloActual = bocadillo;
        textoOffsetX = offsetX;
        textoOffsetY = offsetY;
        tiempoBocadillo = System.currentTimeMillis();

        repaint();
    }

    private boolean estaCerca(Rectangle zona) {

        Rectangle piesJugador = new Rectangle(
            jugadorX + 20,
            jugadorY + 85,
            24,
            15
        );

        return piesJugador.intersects(zona);
    }
    
    private void reproducirSonido(String ruta) {

        try {

            File archivo = new File(ruta);

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(archivo);

            Clip clip = AudioSystem.getClip();

            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void reproducirMusicaJuego() {
        try {
            File archivo = new File("src/projectvillage/sonidos/ambiente.wav");

            AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);

            musicaJuego = AudioSystem.getClip();
            musicaJuego.open(audio);

            volumenJuego = (FloatControl) musicaJuego.getControl(
                    FloatControl.Type.MASTER_GAIN
            );

            volumenJuego.setValue(-5.0f); // volumen bajo

            musicaJuego.loop(Clip.LOOP_CONTINUOUSLY);
            musicaJuego.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void detenerMusicaJuego() {
        if (musicaJuego != null) {
            musicaJuego.stop();
            musicaJuego.close();
        }
    }
    
    public void activarTeclado() {
        requestFocusInWindow();
    }
}