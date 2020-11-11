package FileManager;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * @author AlfonsoAndrés
 */
public class SoundResource implements Resource<Clip> {

    public static final int OGG = 0, WAV = 1;

    @Override
    public Clip load(String s, int type) {
        if (type == WAV)
            return FileManager.getInstance().loadClipJar(s);
        Clip clip = null;
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(getClass().getResource(s));
            AudioInputStream din;
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    false,
                    false);
            // Get AudioInputStream that will be decoded by underlying VorbisSPI
            din = AudioSystem.getAudioInputStream(decodedFormat, in);
            // Play now !
            // rawPlay(decodedFormat, din);
            // in.close();

            clip = AudioSystem.getClip();
            clip.open(din);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.err.println(ex.getMessage());
        }
        return clip;
    }

}