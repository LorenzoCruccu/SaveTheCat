package Model;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Random;

public class AudioMorte {
    private static Clip clip;
    public static void play() {
        try {
            Random r=new Random();
            int n=r.nextInt(1);
            File musicPath = new File("musica/morte"+(n+1)+".wav");

            if(musicPath.exists()){
                //AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(musicPath));
                clip.start();
            }
            else{
                System.out.print("Non riesco a trovare il path del file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
