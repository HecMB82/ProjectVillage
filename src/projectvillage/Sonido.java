package projectvillage;

/**
 *
 * @author Hector
 */

import javax.sound.sampled.*;
import java.io.File;

public class Sonido {

    public static void reproducirSonido(String ruta) {
        try {
            File archivo = new File(ruta);
            AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);

            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Clip reproducirMusica(String ruta, float volumen) {
        try {
            File archivo = new File(ruta);
            AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);

            Clip clip = AudioSystem.getClip();
            clip.open(audio);

            FloatControl controlVolumen =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            controlVolumen.setValue(volumen);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            return clip;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void detenerMusica(Clip clip) {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}