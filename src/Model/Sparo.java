package Model;

import java.awt.Image;
import java.awt.Toolkit;

public class Sparo {
    private int stadio;
    private int xPix, yPix;
    private Toolkit toolkit;
    private Image sparoImg;
    private boolean animazioneTerminata;

    public Sparo()
    {
        animazioneTerminata=false;
        stadio=1;
        toolkit= Toolkit.getDefaultToolkit();
        sparoImg=toolkit.getImage(getClass().getResource("/Immagini/sparo/sparo1.png"));
    }

    public Sparo(int xPix, int yPix)
    {
        animazioneTerminata=false;
        stadio=1;
        this.xPix=xPix;
        this.yPix=yPix;
        toolkit= Toolkit.getDefaultToolkit();
        sparoImg=toolkit.getImage(getClass().getResource("/Immagini/sparo/sparo1.png"));
    }

    public boolean incrementaAnimazioneSparo()
    {
        sparoImg=toolkit.getImage(getClass().getResource("/Immagini/sparo/sparo"+stadio+".png"));
        stadio++;
        if(stadio>=8) {
            stadio=1;
            animazioneTerminata=true;
            return false;
        }
        return true;
    }

    public boolean isAnimazioneTerminata()
    {
        return animazioneTerminata;
    }

    public int getStadio() {
        return stadio;
    }

    public void setStadio(int stadio) {
        this.stadio = stadio;
    }

    public Image getSparoImg()
    {
        return sparoImg;
    }

    public int getxPix() {
        return xPix;
    }

    public void setxPix(int xPix) {
        this.xPix = xPix;
    }

    public int getyPix() {
        return yPix;
    }

    public void setyPix(int yPix) {
        this.yPix = yPix;
    }
}
