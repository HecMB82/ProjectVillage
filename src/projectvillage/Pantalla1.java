package projectvillage;

/**
 *
 * @author Hector
 */
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.Clip;

public class Pantalla1 extends PantallaBase {
        
    private NPC leonila;
    private NPC adriana;    
    private int contadorAnimacion = 0;
    private long ultimoMiau = 0;
    private Dialogo dialogo;
    private Image bocadilloLeonila;
    private Image bocadilloAdriana;
    private Animacion animacionGato;
    private Animacion animacionPajaro;    
    private int pajaroX = -50;
    private int pajaroY = 80;
    private Image humo;
    private int humoX = 117;
    private int humoY1 = 200;
    private int humoY2 = 220;
    private int humoY3 = 280;
    private int humoY4 = 300;    
    private boolean mostrarColisiones = true;
    private Rectangle zonaLeonila = new Rectangle(450, 530, 50, 30);
    private Rectangle zonaAdriana = new Rectangle(520, 500, 80, 50);
    private Rectangle zonaGato = new Rectangle(160, 580, 60, 30);
    private Clip musicaJuego;    
    private long tiempoMiau = 0;
    private boolean miauActivo = false;
    private Image teclaE;    
    private Image flechaSalida;
    private SalidaMapa salidaPantalla2;

    private Polygon zonaJugable = new Polygon(
        new int[]{50, 230, 580, 630, 760, 780, 810, 1220, 1220, 50},
        new int[]{650, 580, 530, 440, 440, 450, 450, 580, 680, 680},
        10
    );

    public Pantalla1(Ventana ventana, Jugador jugador) {
        
        super(
            ventana,
            jugador,
            "src/projectvillage/imagenes/fondo_juego.png"
        );
        
        this.ventana = ventana;
        
        salidaPantalla2 = new SalidaMapa(
            new Rectangle(650, 430, 120, 40),
            "pantalla2",
            670,
            340,
            650,
            600
        );
        
        salidas.add(salidaPantalla2);
        
        flechaSalida = new ImageIcon(
        "src/projectvillage/imagenes/IR.png"
        ).getImage();
        
        dialogo = new Dialogo();
        
        leonila = new NPC(
            450,
            400,
            new ImageIcon(
                "src/projectvillage/imagenes/abu_leo.png"
            ).getImage(),
            new ImageIcon(
                "src/projectvillage/imagenes/abu_leo_ojos.png"
            ).getImage()
        );

        adriana = new NPC(
            500,
            420,
            new ImageIcon(
                "src/projectvillage/imagenes/abu_adri.png"
            ).getImage(),
            new ImageIcon(
                "src/projectvillage/imagenes/abu_adri_ojos.png"
            ).getImage()
        );
        
        Image pajaroArriba = new ImageIcon(
                "src/projectvillage/imagenes/alas_arriba.png"
        ).getImage();

        Image pajaroAbajo = new ImageIcon(
                "src/projectvillage/imagenes/alas_abajo.png"
        ).getImage();

        animacionPajaro = new Animacion(
                new Image[]{pajaroArriba, pajaroAbajo},
                1
        );
        
        humo = new ImageIcon(
        "src/projectvillage/imagenes/humo.png"
        ).getImage();
        
        teclaE = new ImageIcon(
            "src/projectvillage/imagenes/E.png"
        ).getImage();
        
        Image gato1 = new ImageIcon("src/projectvillage/imagenes/gato_1.png").getImage();
        Image gato2 = new ImageIcon("src/projectvillage/imagenes/gato_2.png").getImage();
        Image gato3 = new ImageIcon("src/projectvillage/imagenes/gato_3.png").getImage();
        Image gato4 = new ImageIcon("src/projectvillage/imagenes/gato_4.png").getImage();

        animacionGato = new Animacion(
            new Image[]{gato1, gato2, gato3, gato4, gato3, gato2},
            2
        );
        
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

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        dibujarFondo(g);
        
        long ahora = System.currentTimeMillis();

        boolean bocadilloActivo = dialogo.estaActivo();

        boolean sonidoGatoActivo =
                miauActivo
                && ahora - tiempoMiau < 1500;
        
        if (miauActivo && ahora - tiempoMiau >= 1500) {
            miauActivo = false;
        }
        
        if (estaCerca(salidaPantalla2.getZona())) {

            g.drawImage(
                flechaSalida,
                salidaPantalla2.getFlechaX(),
                salidaPantalla2.getFlechaY(),
                70,
                70,
                this
            );
            
             g.drawImage(
                teclaE,
                690,
                335,
                30,
                30,
                this
            );
        }
        
        Image gatoActual = animacionGato.getFrame(contadorAnimacion);

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
            abuelaAdrianaActual = adriana.getImagenAlternativa();
        } else {
            abuelaAdrianaActual = adriana.getImagen();
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
            abuelaLeonilaActual = leonila.getImagenAlternativa();
        } else {
            abuelaLeonilaActual = leonila.getImagen();
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
            
            g.setColor(Color.YELLOW);

            Rectangle zona = salidaPantalla2.getZona();

            g.fillRect(
                zona.x,
                zona.y,
                zona.width,
                zona.height
            );
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

        if (jugador.getY() > 360) {
            // Zona cercana: cambia poco
            escala = 1.15 + ((jugador.getY() - 520) / 80.0) * 0.35;
        } else {
            // Zona lejana: disminuye más rápido
            escala = 0.45 + ((jugador.getY() - 360) / 4.0) * 0.20;
        }
        
        if (escala < 0.35) {
            escala = 0.35;
        }

        int anchoJugador = (int)(64 * escala);
        int altoJugador = (int)(100 * escala);

        int jugadorDibujadoX = jugador.getX() - (anchoJugador - 64) / 2;
        int jugadorDibujadoY = jugador.getY() - (altoJugador - 100);

        g.drawImage(
            jugador.getImagenActual(),
            jugadorDibujadoX,
            jugadorDibujadoY,
            anchoJugador,
            altoJugador,
            this
        );
        
        Image pajaroActual = animacionPajaro.getFrame(contadorAnimacion);
        
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
        
        dialogo.dibujar(g);
        dibujarSalidas(g);
    }
    
    @Override
    protected void comprobarInteraccion() {

        if (estaCerca(zonaLeonila)) {

            dialogo.mostrar(
                new String[]{
                    "Abuela Leonila:",
                    "Ponte una rebequita,",
                    "que empieza a refrescar."
                },
                90,
                310,
                bocadilloLeonila,
                95,
                25
            );

        } else if (estaCerca(zonaAdriana)) {

            dialogo.mostrar(
                new String[]{
                    "Abuela Adriana:",
                    "¿Vienes a casa a cenar?",
                    "Tengo huevos fritos con patatas."
                },
                550,
                330,
                bocadilloAdriana,
                65,
                25
            );       

        } else if (estaCerca(zonaGato)) {

            long tiempoActual = System.currentTimeMillis();

            if (tiempoActual - ultimoMiau > 1500) {

                Sonido.reproducirSonido(
                    "src/projectvillage/sonidos/miau.wav"
                );

                ultimoMiau = tiempoActual;
                tiempoMiau = tiempoActual;
                miauActivo = true;
            }
        } else {
            
            super.comprobarInteraccion();
        }       
        
    }       
    
    public void reproducirMusicaJuego() {
        musicaJuego = Sonido.reproducirMusica(
                "src/projectvillage/sonidos/ambiente.wav",
                -5.0f
        );
    }
    
    public void detenerMusicaJuego() {
        Sonido.detenerMusica(musicaJuego);
        musicaJuego = null;
    }
    
    public void activarTeclado() {
        requestFocusInWindow();
    }
}