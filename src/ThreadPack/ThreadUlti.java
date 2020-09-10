package ThreadPack;

import Model.Character;
import Model.Ultimate;
import ViewController.StagePanel;

import static java.lang.Thread.sleep;

public class ThreadUlti implements Runnable{
    private StagePanel sp;
    private Ultimate u;
    private Character r;

    public ThreadUlti(StagePanel sp)
    {
        this.sp=sp;
        u=sp.getU();
    }



    @Override
    public void run() {
        //VECCHIO CICLO PIU' PRECISO MA IMPOSSIBILE METTERLO IN PAUSA
        /*for (long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos( 3); stop > System.nanoTime() || tRimasto>0; ) {
            u.setUltiAttivata(true);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(sp.isGiocoPausa()) {
                do {
                    try {
                        sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //t+=0.1;

                } while (sp.isGiocoPausa());
            }
            for (int i = 0; i < sp.getRv().size(); i++) {
                r = sp.getRv().get(i);
                r.diminuisciVita(sp.getM().getDanno());
                if (r.getVita() <= 0) {
                    sp.eliminaNemico(i);
                }
            }
            u.incrementaAnimazioneGatto();
            u.setUltiAttivata(false);
            tRimasto--;
        }*/
        //CICLO CHE SI PUO' METTERE IN PAUSA MA NON MOLTO PRECISO
        float tTrascorso=0;
        do {
            u.setUltiAttivata(true);
            try {
                sleep(50);
                tTrascorso += 0.05;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (sp.isGiocoPausa()) {
                try {
                    sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < sp.getRv().size(); i++) {
                r = sp.getRv().get(i);
                r.diminuisciVita(sp.getM().getDanno());
                if (r.getVita() <= 0) {
                    sp.eliminaNemico(i);
                }
            }
            u.incrementaAnimazioneGatto();
            u.setUltiAttivata(false);
        }while (tTrascorso<3);
    }
}
