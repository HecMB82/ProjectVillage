package projectvillage;

/**
 *
 * @author Hector
 */
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

public class PantallaTitulo extends JPanel {
    
    private Image fondo;    
    private Clip clip;
    private float opacidadTitulo = 0f;
    private JPanel panelSuperior;
    private JPanel panelInferior;

    public PantallaTitulo(Ventana ventana) {

        setLayout(new BorderLayout());
        fondo = new ImageIcon(
        "src/projectvillage/imagenes/fondo_titulo.png"
        ).getImage();

        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon iconoOriginal = new ImageIcon(
            "src/projectvillage/imagenes/logo.png"
        );

        Image imagenEscalada = iconoOriginal.getImage()
                .getScaledInstance(512, 341, Image.SCALE_SMOOTH);

        ImageIcon imagenLogo = new ImageIcon(imagenEscalada);

        logo.setIcon(imagenLogo);

        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new GridLayout(4, 1, 0, 0));

        JButton btnEmpezar = crearBotonImagen("src/projectvillage/imagenes/Empezar.png", "src/projectvillage/imagenes/Empezar_hover.png");
        JButton btnOpciones = crearBotonImagen("src/projectvillage/imagenes/Opciones.png","src/projectvillage/imagenes/Opciones_hover.png");
        JButton btnCreditos = crearBotonImagen("src/projectvillage/imagenes/Creditos.png", "src/projectvillage/imagenes/Creditos_hover.png");
        JButton btnSalir = crearBotonImagen("src/projectvillage/imagenes/Salir.png", "src/projectvillage/imagenes/Salir_hover.png");

        btnEmpezar.addActionListener(e -> ventana.mostrarPantalla("juego"));
        btnOpciones.addActionListener(e -> ventana.mostrarPantalla("opciones"));
        btnCreditos.addActionListener(e -> ventana.mostrarPantalla("creditos"));
        btnSalir.addActionListener(e -> System.exit(0));

        panelBotones.add(btnEmpezar);
        panelBotones.add(btnOpciones);
        panelBotones.add(btnCreditos);
        panelBotones.add(btnSalir);

        
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setOpaque(false);
        

        logo.setAlignmentX(0.10f);
        

        panelSuperior.add(Box.createVerticalGlue());
        panelSuperior.add(logo);        
        panelSuperior.add(Box.createVerticalGlue());

        add(panelSuperior, BorderLayout.CENTER);
        
        panelInferior = new JPanel();
        panelInferior.setOpaque(false);
        panelInferior.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 0));
        
        panelInferior.setBorder(
        BorderFactory.createEmptyBorder(0, 0, 20, 0)
        );

        panelInferior.add(panelBotones);

        add(panelInferior, BorderLayout.SOUTH);
        
        panelSuperior.setVisible(false);
        panelInferior.setVisible(false);
    }
    
    public void iniciarEntradaTitulo() {

        panelSuperior.setVisible(false);
        panelInferior.setVisible(false);

        opacidadTitulo = 0f;

        Timer fade = new Timer(40, e -> {

            opacidadTitulo += 0.04f;

            if (opacidadTitulo >= 1f) {
                opacidadTitulo = 1f;

                panelSuperior.setVisible(true);
                panelInferior.setVisible(true);

                ((Timer) e.getSource()).stop();
            }

            repaint();
        });

        fade.start();
    }
    
    public void reproducirMusicaTitulo() {
        try {
            File archivo = new File("src/projectvillage/sonidos/titulo.wav");

            AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);

            clip = AudioSystem.getClip();
            clip.open(audio);

            FloatControl volumen =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            volumen.setValue(-40.0f);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            Timer fadeAudio = new Timer(100, e -> {

                float valor = volumen.getValue();

                if (valor < -15.0f) {
                    volumen.setValue(valor + 2.0f);
                } else {
                    volumen.setValue(-15.0f);
                    ((Timer) e.getSource()).stop();
                }
            });

            fadeAudio.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detenerMusicaTitulo() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
    
    public void iniciarFadeIn() {

        opacidadTitulo = 0f;

        Timer fade = new Timer(40, e -> {

            opacidadTitulo += 0.04f;

            if (opacidadTitulo >= 1f) {
                opacidadTitulo = 1f;
                ((Timer) e.getSource()).stop();
            }

            repaint();
        });

        fade.start();
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

        if (opacidadTitulo < 1f) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setComposite(
                    AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER,
                            1f - opacidadTitulo
                    )
            );

            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private JButton crearBotonImagen(String rutaNormal, String rutaHover) {

        ImageIcon iconoNormal = new ImageIcon(
                new ImageIcon(rutaNormal).getImage()
                        .getScaledInstance(220, 60, Image.SCALE_SMOOTH)
        );

        ImageIcon iconoHover = new ImageIcon(
                new ImageIcon(rutaHover).getImage()
                        .getScaledInstance(220, 60, Image.SCALE_SMOOTH)
        );

        JButton boton = new JButton(iconoNormal);

        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setIcon(iconoHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setIcon(iconoNormal);
            }
        });

        return boton;
    }
}