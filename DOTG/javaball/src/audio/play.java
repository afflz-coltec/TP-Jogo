package audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

/**
 *
 * @author Augusto
 */
public class play extends Object implements LineListener {

    File soundFile;

    JDialog playingDialog;

    Clip clip;

    public play() throws Exception {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        soundFile = chooser.getSelectedFile();
    }

    /**
     *
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException
     */
    public void soltaOSom () throws LineUnavailableException, UnsupportedAudioFileException, IOException{    
        Line.Info linfo = new Line.Info(Clip.class);
        Line line = AudioSystem.getLine(linfo);
        clip = (Clip) line;
        clip.addLineListener(this);
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        clip.open(ais);
        clip.start();
        
    }

    @Override
    public void update(LineEvent le) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
