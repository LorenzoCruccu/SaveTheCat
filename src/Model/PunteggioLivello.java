package Model;

import java.awt.Image;
import java.awt.Toolkit;

public class PunteggioLivello {
    private int punti, livello;
    private Image img1, img2, img3, img4, img5, img6, img7, img8, img9, img0;
    private int apg;
    private String sPunti, sLivello;
    private int puntiRova, puntiIacono;


    public PunteggioLivello(DatiProgresso dp)
    {
        punti=0;
        livello=dp.getLivelloCheckpoint();
        sLivello=String.valueOf(livello);
        sPunti ="0";
        puntiRova=100;
        puntiIacono=150;
        for(int i=1; i<livello; i++)
        {
            puntiIacono+=puntiIacono*5/100;
            puntiRova+=puntiRova*5/100;
            while (puntiIacono%5!=0)
                puntiIacono--;
            while (puntiRova%5!=0)
                puntiRova--;
        }
        Toolkit toolkit= Toolkit.getDefaultToolkit();
        img0= toolkit.getImage(getClass().getResource("/Immagini/punteggio/0.png"));
        img1= toolkit.getImage(getClass().getResource("/Immagini/punteggio/1.png"));
        img2= toolkit.getImage(getClass().getResource("/Immagini/punteggio/2.png"));
        img3= toolkit.getImage(getClass().getResource("/Immagini/punteggio/3.png"));
        img4= toolkit.getImage(getClass().getResource("/Immagini/punteggio/4.png"));
        img5= toolkit.getImage(getClass().getResource("/Immagini/punteggio/5.png"));
        img6= toolkit.getImage(getClass().getResource("/Immagini/punteggio/6.png"));
        img7= toolkit.getImage(getClass().getResource("/Immagini/punteggio/7.png"));
        img8= toolkit.getImage(getClass().getResource("/Immagini/punteggio/8.png"));
        img9= toolkit.getImage(getClass().getResource("/Immagini/punteggio/9.png"));
    }

    public void nemicoUcciso(boolean tipo)
    {
        if(tipo)
        {
            punti+=puntiRova;
        }
        else
        {
            punti+=puntiIacono;
        }
        sPunti =String.valueOf(punti);
    }

    public void aumentoLivello()
    {
        livello++;
        sLivello=String.valueOf(livello);
        puntiIacono+=puntiIacono*5/100;
        puntiRova+=puntiRova*5/100;
        while (puntiIacono%5!=0)
            puntiIacono--;
        while (puntiRova%5!=0)
            puntiRova--;
    }

    public int getCifra(int i)
    {
        apg=punti;
        for(int j=0; j<i && j<7; j++)
        {
            apg=apg/10;
        }
        return apg%10;
    }

    public Image getPuntiImg(int i)
    {
        apg=Integer.valueOf(String.valueOf(sPunti.charAt(i)));
        switch (apg)
        {
            case 0:
                return getImg0();
            case 1:
                return getImg1();
            case 2:
                return getImg2();
            case 3:
                return getImg3();
            case 4:
                return getImg4();
            case 5:
                return getImg5();
            case 6:
                return getImg6();
            case 7:
                return getImg7();
            case 8:
                return getImg8();
            case 9:
                return getImg9();
        }
        return getImg0();
    }

    public Image getLivelloImg(int i)
    {
        apg=Integer.valueOf(String.valueOf(sLivello.charAt(i)));
        switch (apg)
        {
            case 0:
                return getImg0();
            case 1:
                return getImg1();
            case 2:
                return getImg2();
            case 3:
                return getImg3();
            case 4:
                return getImg4();
            case 5:
                return getImg5();
            case 6:
                return getImg6();
            case 7:
                return getImg7();
            case 8:
                return getImg8();
            case 9:
                return getImg9();
        }
        return getImg0();
    }

    public int getPunti() {
        return punti;
    }

    public int getLivello() {
        return livello;
    }

    public String getsPunti() {
        return sPunti;
    }

    public String getsLivello() {
        return sLivello;
    }

    public Image getImg1() {
        return img1;
    }

    public Image getImg2() {
        return img2;
    }

    public Image getImg3() {
        return img3;
    }

    public Image getImg4() {
        return img4;
    }

    public Image getImg5() {
        return img5;
    }

    public Image getImg6() {
        return img6;
    }

    public Image getImg7() {
        return img7;
    }

    public Image getImg8() {
        return img8;
    }

    public Image getImg9() {
        return img9;
    }

    public Image getImg0() {
        return img0;
    }
}
