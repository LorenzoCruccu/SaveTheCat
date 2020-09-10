package Model;

import java.awt.Image;
import java.awt.Toolkit;

public class Ultimate {
    private Toolkit toolkit;
    private Image barraVuota;
    private Image riempimentoImg;
    private Image animzioneGatto;
    private Image filtro;
    private int riempimento, contatoreAnimazione;
    private boolean ultiAttivata;

    public Ultimate()
    {
        riempimento=0;
        contatoreAnimazione=0;
        toolkit= Toolkit.getDefaultToolkit();
        barraVuota= toolkit.getImage(getClass().getResource("/Immagini/ulti/barraVuota.png"));
    }
    //BARRA
    public void incrementaBarra()
    {
        riempimento+=5;
        if(riempimento>100)
            riempimento=100;
    }

    public void impostaBarra(int n)
    {
        if(n>=0 && n<=100)
            riempimento=n;
    }

    public Image getRiempimentoImg() {
        riempimentoImg= toolkit.getImage(getClass().getResource("/Immagini/ulti/caricamento/"+((riempimento*20/100)+1)+".png"));
        return riempimentoImg;
    }

    public boolean isUltiCarica()
    {
        return riempimento == 100;
    }

    public boolean isUltiAttivata() {
        return ultiAttivata;
    }

    public void setUltiAttivata(boolean ultiAttivata) {
        this.ultiAttivata = ultiAttivata;
    }

    public void setRiempimentoImg(Image riempimentoImg) {
        this.riempimentoImg = riempimentoImg;
    }

    public Image getBarraVuota() {
        return barraVuota;
    }

    public void setBarraVuota(Image barraVuota) {
        this.barraVuota = barraVuota;
    }

    public int getRiempimento() {
        return riempimento;
    }

    public void setRiempimento(int riempimento) {
        this.riempimento = riempimento;
    }


    //ANIMAZIONE
    public Image getFrame()
    {
        return animzioneGatto;
    }

    public Image getFiltro()
    {
        return filtro;
    }

    public boolean incrementaAnimazioneGatto()
    {
        animzioneGatto=toolkit.getImage(getClass().getResource("/Immagini/ulti/frames/"+contatoreAnimazione+".png"));
        filtro=toolkit.getImage(getClass().getResource("/Immagini/ulti/framesFiltro/"+contatoreAnimazione+".png"));
        contatoreAnimazione++;
        if(contatoreAnimazione>=24) {
            contatoreAnimazione=0;
            return false;
        }
        return true;
    }
}
