package ThreadPack;

import Model.Sparo;
import ViewController.StagePanel;

import static java.lang.Thread.sleep;

public class ThreadSparo implements Runnable{
    private StagePanel sp;
    private Sparo s;

    public ThreadSparo(StagePanel sp, Sparo s)
    {
        this.s=s;
    }


    @Override
    public void run()
    {
        do {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (s.incrementaAnimazioneSparo());
    }
}
