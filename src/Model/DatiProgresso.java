/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author loryc
 */
public class DatiProgresso implements Serializable {
    private String nome, password;
    private byte mirini[];
    private int livelloRaggiuntoMax; //record
    private int livelloCheckpoint;
    private int punti;
    private DatiProgresso dp;

    public DatiProgresso(int punti) {
        dp=this;
        livelloCheckpoint=1;
        livelloRaggiuntoMax=1;
        this.punti = punti;
        mirini = new byte[15];
        mirini[0] = 1;
        for (int i = 1; i < mirini.length; i++) {
            mirini[i] = -1;
        }
    }
    public DatiProgresso(){
        dp=this;
        livelloCheckpoint=1;
        livelloRaggiuntoMax=1;
        punti=0;
        mirini = new byte[15];
        mirini[0] = 1;
        for (int i = 1; i < mirini.length; i++) {
            mirini[i] = -1;
        }
    }

    public void importaSalvataggio(DatiProgresso dp)
    {
        mirini=dp.getMirini();
        livelloRaggiuntoMax=dp.getLivelloRaggiuntoMax();
        livelloCheckpoint=dp.getLivelloCheckpoint();
        punti=dp.getPunti();
    }

    public DatiProgresso getAccess()
    {
        return dp;
    }

    public int getLivelloRaggiuntoMax() {
        return livelloRaggiuntoMax;
    }

    public void setLivelloRaggiuntoMax(int livelloRaggiuntoMax) {
        this.livelloRaggiuntoMax = livelloRaggiuntoMax;
    }

    public int getLivelloCheckpoint() {
        return livelloCheckpoint;
    }

    public void setLivelloCheckpoint(int livelloCheckpoint) {
        this.livelloCheckpoint = livelloCheckpoint;
    }


    public void setMirinoDisponibile(int i) {
        mirini[i] = 0;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public void addPunti(int punti) {
        this.punti += punti;
    }

    public void setMirino(int i) {
        for (int j = 0; j < mirini.length; j++) {
            if (mirini[j] == 1) {
                mirini[j] = 0;
            }
        }
        mirini[i] = 1;
    }

    public int mirinoSelezionato() {
        for (int i = 0; i < mirini.length; i++) {
            if (mirini[i] == 1) {
                return i;
            }
        }
        return 0;
    }

    public boolean mirinoDisponibile(int i) {
        if (mirini[i] > -1) {
            return true;
        }
        return false;
    }

    public int getDannoMirino(int i)
    {
        if(i>=0 && i<15)
        {
            return (int) (3*(Math.pow(2,i)));
        }
        return 0;
    }

    public int getPrezzoMirino(int i)
    {
        if(i>0 && i<15)
        {
            return (int) (17500*(Math.pow(2, i-1)));
        }
        return 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getMirini() {
        return mirini;
    }

    public void setMirini(byte[] mirini) {
        this.mirini = mirini;
    }

    public int getPunti() {
        return punti;
    }

    public DatiProgresso getDp() {
        return dp;
    }

    public void setDp(DatiProgresso dp) {
        this.dp = dp;
    }
}
