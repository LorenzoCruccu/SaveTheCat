/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

/**
 *
 * @author utente
 */
public class Gatto{
    private int xPix, yPix;
    private int pos;
    private Image img;
    private Random rand;
    private boolean preso=false;

    public Gatto() {
        rand=new Random();
        posCasuale();
        Toolkit toolkit= Toolkit.getDefaultToolkit();
        img = toolkit.getImage(getClass().getResource("/Immagini/gatto/gatto"+(1+rand.nextInt(2))+".png"));
    }

    public int posCasuale()
    {
        switch (rand.nextInt(1)){
            case 0:
                pos =0;
                return 0;

        }
        return 0;
    }

    //GETTER E SETTER
    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public boolean isPreso() {
        return preso;
    }

    public void setPreso(boolean preso) {
        this.preso = preso;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
