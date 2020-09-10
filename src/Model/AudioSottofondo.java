package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Random;

public class AudioSottofondo {
    private Clip clip;

    public void play() {
        try {
            Random r=new Random();
            int n=r.nextInt(6);
            File musicPath = new File("musica/sottofondo"+(n+1)+".wav");
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else{
                System.out.print("Non riesco a trovare il path del file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void termina()
    {
        clip.stop();
    }

    public boolean terminato()
    {
        if(clip.isRunning())
            return false;

        return true;
    }
}
