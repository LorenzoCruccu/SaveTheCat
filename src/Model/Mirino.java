package Model;


import java.awt.Image;
import java.awt.Toolkit;

public class Mirino {

    private DatiProgresso dp;
    private int xPix = 0, yPix = 0;
    private int danno;
    private Image img;

    public Mirino(DatiProgresso dp) {
        this.dp=dp;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        img=toolkit.getImage(getClass().getResource("/Immagini/mirini/mirino"+(dp.getDp().mirinoSelezionato()+1)+".png"));
        danno=dp.getDannoMirino(dp.mirinoSelezionato());
    }

    //GETTER E SETTER
    public int getDanno() {
        return danno;
    }

    public void setDanno(int danno) {
        this.danno = danno;
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

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
