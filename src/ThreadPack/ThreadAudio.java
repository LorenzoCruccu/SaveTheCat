package ThreadPack;

import Model.AudioMorte;
import Model.AudioSottofondo;
import ViewController.StagePanel;

import static java.lang.Thread.sleep;

public class ThreadAudio implements Runnable{
    private StagePanel sp;
    private boolean musicaOn;

    public ThreadAudio(StagePanel sp)
    {
        this.sp=sp;
        musicaOn=sp.isMusicaOn();
    }
    @Override
    public void run()
    {
        AudioSottofondo as=new AudioSottofondo();
        as.play();
        int uccisioni=sp.getNemiciUccisi();
        while (!sp.isGattoPreso() && !sp.isFinestraChiusa())
        {
            if(uccisioni!=sp.getNemiciUccisi())
            {
                if(sp.isVoceOn())
                    AudioMorte.play();
                uccisioni=sp.getNemiciUccisi();
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(musicaOn!=sp.isMusicaOn() || as.terminato())
            {
                musicaOn=sp.isMusicaOn();
                if(musicaOn)
                    as.play();
                else if(!as.terminato())
                    as.termina();
            }
        }
        while (!sp.isFinestraChiusa())
        {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        as.termina();
    }
}
