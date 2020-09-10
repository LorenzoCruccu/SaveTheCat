package ViewController;

import Model.Character;
import Model.Coordinate;
import Model.DatiProgresso;
import Model.Gatto;
import Model.Mirino;
import Model.PunteggioLivello;
import Model.Sparo;
import Model.Ultimate;

import ThreadPack.ThreadAudio;
import ThreadPack.ThreadMirino;
import ThreadPack.ThreadSpawner;
import ThreadPack.ThreadUlti;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.LinkedList;


public class StagePanel extends JPanel {
    private DatiProgresso dp;
    private StageFrame sf;
    private Toolkit toolkit;
    private Image sfondo;
    private Image alberoColle, alberoSud, alberiNord;
    private Image gameoverImg;
    private Image livelloImg;
    private Image pressEnter;
    private Image pausa;
    private Image filtroPausa;
    private Image restartImg, musicaImg, voceImg, exitImg;
    private Gatto gt;
    private Thread tSpawn, tUlti, taudio;
    private ArrayList<Character> rv;
    private ArrayList<Thread> trv;
    private LinkedList<Sparo> spr;
    private int xPixPanel, yPixPanel;
    private Mirino m;
    private int coordX, coordY;//variabile di supporto
    private boolean gattoPreso, giocoIniziato, giocoPausa, finestraChiusa, musicaOn, voceOn;
    private PunteggioLivello pl;
    private Ultimate u;
    private int nemiciUccisi;

    public StagePanel(DatiProgresso dp)
    {
        setDoubleBuffered(true);
        this.dp=dp;
        sf=new StageFrame(this,true);
        sf.setVisible(true);
        setPreferredSize(new Dimension(1280,720));
        gattoPreso=false;
        giocoIniziato=false;
        giocoPausa=false;
        finestraChiusa=false;
        musicaOn=true;
        voceOn =true;
        nemiciUccisi=0;
        toolkit= Toolkit.getDefaultToolkit();
        pressEnter= toolkit.getImage(getClass().getResource("/Immagini/pressEnter/pressEnterStart.png"));
        sfondo= toolkit.getImage(getClass().getResource("/Immagini/sfondi/sfondo.png"));
        alberoColle = toolkit.getImage(getClass().getResource("/Immagini/sfondi/alberoColle.png"));
        alberoSud = toolkit.getImage(getClass().getResource("/Immagini/sfondi/alberoSud.png"));
        alberiNord= toolkit.getImage(getClass().getResource("/Immagini/sfondi/alberiNord.png"));
        gameoverImg=toolkit.getImage(getClass().getResource("/Immagini/gameover/gameover.jpg"));
        livelloImg=toolkit.getImage(getClass().getResource("/Immagini/livello/level.png"));
        pausa=toolkit.getImage(getClass().getResource("/Immagini/pausa/pausa.png"));
        filtroPausa=toolkit.getImage(getClass().getResource("/Immagini/pausa/filtro.png"));
        restartImg=toolkit.getImage(getClass().getResource("/Immagini/icone/restart.png"));
        musicaImg=toolkit.getImage(getClass().getResource("/Immagini/icone/musica.png"));
        voceImg=toolkit.getImage(getClass().getResource("/Immagini/icone/voce.png"));
        exitImg=toolkit.getImage(getClass().getResource("/Immagini/icone/exit.png"));

        m=new Mirino(dp);
        Thread tm=new Thread(new ThreadMirino(this, m));
        tm.start();
        gt=new Gatto();
        rv=new ArrayList<>();
        trv=new ArrayList<>();
        spr=new LinkedList<>();
        pl=new PunteggioLivello(dp);
        u=new Ultimate();
        taudio=new Thread(new ThreadAudio(this));
        tSpawn =new Thread(new ThreadSpawner(this));
        addMouseListener(new GraphMouseListener(this));
        addMouseMotionListener(new GraphMouseMotionListener());
        addKeyListener(new GraphKeyListener(this));
        setFocusable(true);
    }

    public Coordinate coordinatePixGatto()
    {
        switch (gt.getPos())
        {
            case 0:
                return new Coordinate(1150*getSize().width/1280, 70*getSize().height/720);
        }
        return new Coordinate(0,0);
    }

    public Coordinate coordinatePixNemico(Character r)
    {
        switch (r.getPuntoSpawn())
        {
            case 0:
                return new Coordinate((-100+r.getPosOrz()*r.getVelocità())*getSize().width/1280, (300+r.getPosVer()*r.getVelocità())*getSize().height/720);
            case 1://y: 300 580 100 x:950 680
                return new Coordinate((825+r.getPosOrz()*r.getVelocità())*getSize().width/1280, (300+r.getPosVer()*r.getVelocità())*getSize().height/720);
            case 2:
                return new Coordinate((890+r.getPosOrz()*r.getVelocità())*getSize().width/1280, (-150+r.getPosVer()*r.getVelocità())*getSize().height/720);
            case 3:
                return new Coordinate((400+r.getPosOrz()*r.getVelocità())*getSize().width/1280, (535+r.getPosVer()*r.getVelocità())*getSize().height/720);
            case 4:
                return new Coordinate((150+r.getPosOrz()*r.getVelocità())*getSize().width/1280, (60+r.getPosVer()*r.getVelocità())*getSize().height/720);
        }
        return new Coordinate(0,0);
    }

    public void iniziaGioco()
    {
        if(!giocoIniziato)
        {
            tSpawn.start();
            giocoIniziato=true;
            if(!taudio.isAlive())
                taudio.start();
        }
    }

    public void pausa()
    {
        if(giocoIniziato && !gattoPreso && !giocoPausa)
        {
            giocoPausa=true;

        }
    }

    public void riassumi()
    {
        if(giocoPausa && giocoIniziato && !gattoPreso)
        {
            giocoPausa=false;
        }
    }

    public void eliminaNemico(int i)
    {
        removeAll();
        Character r=rv.get(i);
        pl.nemicoUcciso(r.isTipo());
        r.setMorto(true);
        rv.remove(i);
        trv.remove(i);
        if(!u.isUltiAttivata())
            u.incrementaBarra();
        nemiciUccisi++;
    }

    public void lanciaUlti()
    {
        if(u.isUltiCarica() && !gattoPreso)
        {
            u.setRiempimento(0);
            tUlti=new Thread(new ThreadUlti(this));
            tUlti.start();

        }
    }

    public void gameOver()
    {
        gattoPreso=true;
        pressEnter=toolkit.getImage(getClass().getResource("/Immagini/pressEnter/pressEnterExit.png"));
        m.setDanno(0);
    }

    public void restart()
    {
        if(gattoPreso || giocoPausa)
        {
            finestraChiusa=true;
            for(int i=0; i<rv.size(); i++)
            {
                rv.get(i).setMorto(true);
            }
            StagePanel newSp=new StagePanel(dp);
            sf.dispose();
        }
    }

    
    public void schermoIntero()
    {
        sf.dispose();

        if(sf.isUndecorated())
            sf=new StageFrame(this, false);
        else
            sf=new StageFrame(this, true);

        sf.setVisible(true);
    }

    public void chiudiFinestra()
    {
        finestraChiusa=true;
        for(int i=0; i<rv.size(); i++)
        {
            rv.get(i).setMorto(true);
        }
        sf.dispose();
    }
    
    public void modificaMusica()
    {
        if(musicaOn) {
            musicaImg = toolkit.getImage(getClass().getResource("/Immagini/icone/musicaNo.png"));
            musicaOn=false;
        }
        else
        {
            musicaImg = toolkit.getImage(getClass().getResource("/Immagini/icone/musica.png"));
            musicaOn=true;
        }
    }

    public void modificaAudio()
    {
        if(voceOn) {
            voceImg = toolkit.getImage(getClass().getResource("/Immagini/icone/voceNo.png"));
            voceOn =false;
        }
        else
        {
            voceImg = toolkit.getImage(getClass().getResource("/Immagini/icone/voce.png"));
            voceOn=true;
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        xPixPanel=this.getSize().width;
        yPixPanel=this.getSize().height;
        g.setFont(new Font("Tempus Sans ITC", 1, 15*yPixPanel/720));

        Graphics2D g2= (Graphics2D) g;
        g2.setColor(Color.WHITE);

        //SFONDO
        g2.drawImage(sfondo, 0, 0, xPixPanel, yPixPanel, this);

        if(giocoIniziato){
            //GATTO
            gt.setxPix(75 * xPixPanel / 1280);
            gt.setyPix(75 * yPixPanel / 720);
            g2.drawImage(gt.getImg(), coordinatePixGatto().x, coordinatePixGatto().y, gt.getxPix(), gt.getyPix(), this);
            //ROVA
            Character r;
            for (int i = 0; i < rv.size(); i++) {
                r = rv.get(i);
                r.setxPix(150 * xPixPanel / 1920);
                r.setyPix(225 * yPixPanel / 1080);
                coordX = coordinatePixNemico(r).x;
                coordY = coordinatePixNemico(r).y;
                g2.drawImage(r.getImg(), coordX, coordY, r.getxPix(), r.getyPix(), this);
                g2.drawImage(r.getBarraVita(), coordX + (15 * xPixPanel / 1280), coordY - (20 * yPixPanel / 720), 75 * xPixPanel / 1280, 15 * yPixPanel / 720, this);
                g2.drawString(+(r.getVita())+"/"+(r.getVitaMax()), coordX + (15 * xPixPanel / 1280),coordY - (25 * yPixPanel / 720));
            }
            //ALBERO
            g2.drawImage(alberoColle, 0, 0, xPixPanel, yPixPanel, this);
            g2.drawImage(alberoSud, 0, 0, xPixPanel, yPixPanel, this);
            g2.drawImage(alberiNord, 0, 0, xPixPanel, yPixPanel, this);

            //PUNTEGGIO
            for (int i = 0; i < pl.getsPunti().length(); i++) {
                g2.drawImage(pl.getPuntiImg(i), (10 + 50 * i) * xPixPanel / 1280, 10 * yPixPanel / 720, 60 * xPixPanel / 1280, 50 * yPixPanel / 720, this);
            }

            //LIVELLO
            g2.drawImage(livelloImg, 10 * xPixPanel / 1280, 650 * yPixPanel / 720, 300 * xPixPanel / 1280, 50 * yPixPanel / 720, this);
            for (int i = 0; i < pl.getsLivello().length(); i++) {
                g2.drawImage(pl.getLivelloImg(i), (310 + i * 50) * xPixPanel / 1280, 650 * yPixPanel / 720, 60 * xPixPanel / 1280, 50 * yPixPanel / 720, this);
            }

            //ULTI
            if (u.isUltiAttivata() && !gattoPreso) {
                g2.drawImage(u.getFrame(), 340 * xPixPanel / 1280, 60 * yPixPanel / 720, 600 * xPixPanel / 1280, 600 * yPixPanel / 720, this);
            }

            //PAUSA
            if(giocoPausa)
            {
                g2.drawImage(u.getRiempimentoImg(), 945 * xPixPanel / 1280, 10 * yPixPanel / 720, 336 * xPixPanel / 1280, 35 * yPixPanel / 720, this);
                g2.drawImage(u.getBarraVuota(), 945 * xPixPanel / 1280, 10 * yPixPanel / 720, 334 * xPixPanel / 1280, 33 * yPixPanel / 720, this);
                g2.drawImage(filtroPausa, 0, 0, xPixPanel, yPixPanel, this);
                g2.drawImage(pausa, 380*xPixPanel/1280, 100*yPixPanel/720, 520*xPixPanel/1280, 520*yPixPanel/720, this);
                g2.drawImage(restartImg, 140*xPixPanel/1280, 260*yPixPanel/720, 200*xPixPanel/1280, 200*yPixPanel/720, this);
                g2.drawImage(musicaImg, 900*xPixPanel/1280, 160*yPixPanel/720, 200*xPixPanel/1280, 200*yPixPanel/720, this);
                g2.drawImage(voceImg, 900*xPixPanel/1280, 360*yPixPanel/720, 200*xPixPanel/1280, 200*yPixPanel/720, this);
                g2.drawImage(exitImg, 1000*xPixPanel/1280, 600*yPixPanel/720, 250*xPixPanel/1280, 100*yPixPanel/720, this);
            }

            //GAMEOVER
            if (gattoPreso) {
                g2.drawImage(gameoverImg, 340 * xPixPanel / 1280, 200 *yPixPanel / 720, 600 * xPixPanel / 1280, 300 *yPixPanel / 720, this);

                g2.drawImage(pressEnter, 465*xPixPanel/1280, 600*yPixPanel/720, 350*xPixPanel/1280, 66*yPixPanel/720, this);
            }

            //SPARI
            Sparo s;
            for(int i=0; i<spr.size(); i++)
            {
                s=spr.get(i);
                g2.drawImage(s.getSparoImg(), (s.getxPix()-15)*xPixPanel/1280, (s.getyPix()-15)*yPixPanel/720, 30*xPixPanel/1280, 30*yPixPanel/720, this);
                if(s.isAnimazioneTerminata()) {
                    spr.remove(i);
                    i--;
                }
            }

            //MIRINO
            g2.drawImage(m.getImg(), m.getxPix(), m.getyPix(), 80, 80, this);

            if(!giocoPausa) {
                g2.drawImage(u.getRiempimentoImg(), 945 * xPixPanel / 1280, 10 * yPixPanel / 720, 336 * xPixPanel / 1280, 35 * yPixPanel / 720, this);
                g2.drawImage(u.getBarraVuota(), 945 * xPixPanel / 1280, 10 * yPixPanel / 720, 334 * xPixPanel / 1280, 33 * yPixPanel / 720, this);
                //ULTI
                if (u.isUltiAttivata() && !gattoPreso) {
                    g2.drawImage(u.getFiltro(), 0, 0, xPixPanel, yPixPanel, this);
                }
            }
        }
        else
        {
            //PRESS ENTER TO START
            g2.drawImage(pressEnter, 465*xPixPanel/1280, 600*yPixPanel/720, 350*xPixPanel/1280, 66*yPixPanel/720, this);

            //MIRINO
            g2.drawImage(m.getImg(), m.getxPix(), m.getyPix(), 80, 80, this);

            //SPARI
            Sparo s;
            for(int i=0; i<spr.size(); i++)
            {
                s=spr.get(i);
                g2.drawImage(s.getSparoImg(), (s.getxPix()-15)*xPixPanel/1280, (s.getyPix()-15)*yPixPanel/720, 30*xPixPanel/1280, 30*yPixPanel/720, this);
                if(s.isAnimazioneTerminata()) {
                    spr.remove(i);
                    i--;
                }
            }
        }
    }


    //GETTER E SETTER
    public DatiProgresso getDp() {
        return dp;
    }

    public void setDp(DatiProgresso dp) {
        this.dp = dp;
    }

    public int getPunti()
    {
        return pl.getPunti();
    }
    
    public boolean isVoceOn() {
        return voceOn;
    }

    public boolean isMusicaOn() {
        return musicaOn;
    }

    public void setMusicaOn(boolean musicaOn) {
        this.musicaOn = musicaOn;
    }

    public void setFinestraChiusa(boolean finestraChiusa) {
        this.finestraChiusa = finestraChiusa;
    }

    public boolean isFinestraChiusa() {
        return finestraChiusa;
    }
    
    

    public int getNemiciUccisi() {
        return nemiciUccisi;
    }

    public LinkedList<Sparo> getSpr() {
        return spr;
    }

    public void setSpr(LinkedList<Sparo> spr) {
        this.spr = spr;
    }

    public Thread gettUlti() {
        return tUlti;
    }

    public void settUlti(Thread tUlti) {
        this.tUlti = tUlti;
    }

    
    public boolean isGiocoPausa() {
        return giocoPausa;
    }

    public boolean isGiocoIniziato() {
        return giocoIniziato;
    }

    public void setGiocoIniziato(boolean giocoIniziato) {
        this.giocoIniziato = giocoIniziato;
    }

    public Ultimate getU() {
        return u;
    }

    public PunteggioLivello getPl() {
        return pl;
    }

    public Mirino getM() {
        return m;
    }

    public void setM(Mirino m) {
        this.m = m;
    }

    public boolean isGattoPreso() {
        return gattoPreso;
    }

    public void setGattoPreso(boolean gattoPreso) {
        this.gattoPreso = gattoPreso;
    }

    public ArrayList<Thread> getTrv() {
        return trv;
    }

    public void setTrv(ArrayList<Thread> trv) {
        this.trv = trv;
    }

    public Image getSfondo() {
        return sfondo;
    }

    public void setSfondo(Image sfondo) {
        this.sfondo = sfondo;
    }

    public Gatto getGt() {
        return gt;
    }

    public void setGt(Gatto gt) {
        this.gt = gt;
    }

    public ArrayList<Character> getRv() {
        return rv;
    }

    public void setRv(ArrayList<Character> rv) {
        this.rv = rv;
    }

    public int getxPixPanel() {
        return xPixPanel;
    }

    public void setxPixPanel(int xPixPanel) {
        this.xPixPanel = xPixPanel;
    }

    public int getyPixPanel() {
        return yPixPanel;
    }

    public void setyPixPanel(int yPixPanel) {
        this.yPixPanel = yPixPanel;
    }
}
