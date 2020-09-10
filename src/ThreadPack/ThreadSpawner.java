package ThreadPack;

import Model.Character;
import ViewController.StagePanel;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class ThreadSpawner implements Runnable{
    private Random rand;
    private StagePanel sp;
    private ArrayList<Character> rv;
    private ArrayList<Thread> trv;
    private int livello;

    public ThreadSpawner(StagePanel sp)
    {
        rand=new Random();
        this.sp=sp;
        this.rv=sp.getRv();
        this.trv=sp.getTrv();
        livello=sp.getPl().getLivello();
    }



    @Override
    public void run() {
        int velocità = 4+(((livello-1)/5)), vita = 8+(((livello-1)/3)*2), nNemici = 5+(2*(livello-1));
        while (!sp.isGattoPreso()) {
            //spawn nemici
            for (int i = 0; i < nNemici && !sp.isGattoPreso() && !sp.isFinestraChiusa(); i++) {
                do {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while (sp.isGiocoPausa());
                Character r = new Character(velocità, vita, rand.nextBoolean());
                rv.add(r);
                Thread t = new Thread(new ThreadCharacter(r, sp));
                trv.add(t);
                t.start();
            }

            //attesa della morte dei nemici
            while (!sp.isGattoPreso() && rv.size() > 0) {
                try {
                    sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //aumento difficoltà livello
            if (!sp.isGattoPreso() && !sp.isFinestraChiusa()) {
                sp.getPl().aumentoLivello();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (livello % 5 == 0 && velocità <= 8)
                    velocità++;

                if (livello % 3 == 0)
                    vita += 2;
                nNemici += 2;
                livello++;
                if(sp.getDp().getLivelloRaggiuntoMax()<livello)
                    sp.getDp().setLivelloRaggiuntoMax(livello);
                if(sp.getDp().getLivelloCheckpoint()<livello && livello%10==0)
                    sp.getDp().setLivelloCheckpoint(livello);
            }
        }
    }
}
