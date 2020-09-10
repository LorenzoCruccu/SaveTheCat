/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Toolkit;
import java.awt.Image;
import java.util.Random;

/**
 *
 * @author utente
 */
public class Character {
    private int xPix, yPix;
    private int puntoSpawn, posOrz=0, posVer=0;
    private Toolkit toolkit;
    private Image img;
    private Image barraVita;
    private int velocità=5;
    private int vitaMax=10, vita=10;
    private Random rand;
    private boolean morto;
    private boolean tipo=true;//true=rova false=iacono
    
    public Character(){
        rand = new Random();
        morto=false;
        toolkit= Toolkit.getDefaultToolkit();
        img = toolkit.getImage(getClass().getResource("/Immagini/rova/rova1.png"));
        barraVita=toolkit.getImage(getClass().getResource("/Immagini/barraVita/vita10.png"));
        posCasuale();
    }

    public Character(int velocità, int vita, boolean tipo){
        rand = new Random();
        this.tipo=tipo;
        morto=false;
        toolkit= Toolkit.getDefaultToolkit();
        if(tipo) {
            img = toolkit.getImage(getClass().getResource("/Immagini/rova/rova1.png"));
        }
        else {
            img = toolkit.getImage(getClass().getResource("/Immagini/rova/iacono1.png"));
            vita*=1.5;
            velocità/=1.5;
        }
        this.vitaMax=vita;
        this.vita=vita;
        this.velocità=velocità;
        barraVita=toolkit.getImage(getClass().getResource("/Immagini/barraVita/vita10.png"));
        posCasuale();
    }

    public int posCasuale()
    {
        switch (rand.nextInt(14))
        {
            case 0:
            case 1:
            case 2:
                puntoSpawn=0;
                break;
            case 3:
            case 4:
            case 5:
                puntoSpawn=1;
                break;
            case 6:
                puntoSpawn=2;
                velocità/=2;
                if(velocità==0)
                    velocità=1;
                if(tipo)
                    img = toolkit.getImage(getClass().getResource("/Immagini/rova/rova2.png"));
                else
                    img = toolkit.getImage(getClass().getResource("/Immagini/rova/iacono1.png"));
                break;
            case 7:
            case 8:
            case 9:
                puntoSpawn=3;
                break;
            case 11:
            case 12:
            case 13:
                puntoSpawn=4;
        }
        return puntoSpawn;
    }

    public void destra()
    {
        posOrz++;
    }

    public void sinistra()
    {
        posOrz--;
    }

    public void giu()
    {
        posVer++;
    }

    public void su()
    {
        posVer--;
    }

    public void diminuisciVita(int danno)
    {

        if(vita<danno)
            vita=0;
        else
            vita-=danno;
        Toolkit toolkit= Toolkit.getDefaultToolkit();
        barraVita= toolkit.getImage(getClass().getResource("/Immagini/barraVita/vita"+(vita*10/vitaMax)+".png"));
    }
    //GETTER E SETTER
    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public boolean isMorto() {
        return morto;
    }

    public void setMorto(boolean morto) {
        this.morto = morto;
    }

    public Image getBarraVita() {
        return barraVita;
    }

    public void setBarraVita(Image barraVita) {
        this.barraVita = barraVita;
    }

    public int getVitaMax() {
        return vitaMax;
    }

    public void setVitaMax(int vitaMax) {
        this.vitaMax = vitaMax;
    }

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public int getVelocità() {
        return velocità;
    }

    public void setVelocità(int velocità) {
        this.velocità = velocità;
    }

    public int getPuntoSpawn() {
        return puntoSpawn;
    }

    public void setPuntoSpawn(int puntoSpawn) {
        this.puntoSpawn = puntoSpawn;
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

    public int getPosOrz() {
        return posOrz;
    }

    public void setPosOrz(int posOrz) {
        this.posOrz = posOrz;
    }

    public int getPosVer() {
        return posVer;
    }

    public void setPosVer(int posVer) {
        this.posVer = posVer;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
