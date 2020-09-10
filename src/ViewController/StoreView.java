/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.DatiProgresso;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author loryc
 */
public class StoreView extends JPanel implements ActionListener {

    private DatiProgresso dp;
    private JFrame jf;
    private JScrollPane miriniScrollPane;
    private MiriniPanel mp;
    private Image storeImg, sfondo1;
    private Toolkit tk;
    private int xOriginale, yOriginale;
    private JButton b[], chiudi;
    private int punti, apg;
    private Image img1, img2, img3, img4, img5, img6, img7, img8, img9, img0;
    private String sPunti;

    public StoreView(DatiProgresso dp) {
        this.dp=dp;
        jf = new JFrame("STORE");
        jf.setVisible(true);
        tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getDefaultToolkit().getScreenSize();
        int width =(int) screenSize.getWidth();
        int height =(int) screenSize.getHeight();
        xOriginale = 700;
        yOriginale = 400;
        jf.setBounds((width-xOriginale)/2, (height-yOriginale)/2, xOriginale, yOriginale);
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jf.add(this);

        /*miriniImg=new Image[3];
        for(int i=0; i<miriniImg.length; i++)
        {
            miriniImg[i]=tk.getImage(getClass().getResource("/Immagini/mirini/mirino"+(i+1)+".png"));
        }*/
        storeImg = tk.getImage(getClass().getResource("/Immagini/store/store.png"));
        img0 = tk.getImage(getClass().getResource("/Immagini/punteggio/0.png"));
        img1 = tk.getImage(getClass().getResource("/Immagini/punteggio/1.png"));
        img2 = tk.getImage(getClass().getResource("/Immagini/punteggio/2.png"));
        img3 = tk.getImage(getClass().getResource("/Immagini/punteggio/3.png"));
        img4 = tk.getImage(getClass().getResource("/Immagini/punteggio/4.png"));
        img5 = tk.getImage(getClass().getResource("/Immagini/punteggio/5.png"));
        img6 = tk.getImage(getClass().getResource("/Immagini/punteggio/6.png"));
        img7 = tk.getImage(getClass().getResource("/Immagini/punteggio/7.png"));
        img8 = tk.getImage(getClass().getResource("/Immagini/punteggio/8.png"));
        img9 = tk.getImage(getClass().getResource("/Immagini/punteggio/9.png"));
        sfondo1=tk.getImage(getClass().getResource("/Immagini/store/sfondoStore1.jpg"));
        punti = dp.getPunti();
        sPunti = String.valueOf(punti);

        mp=new MiriniPanel(this, dp,3560, 250);
        miriniScrollPane =new JScrollPane(mp);
        miriniScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
        miriniScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
        add(miriniScrollPane);
        miriniScrollPane.setBounds(0, 140, xOriginale, 260);


        b=mp.getB();
        chiudi = new JButton();
        chiudi.setBackground(Color.yellow);
        try {
            Image img = ImageIO.read(getClass().getResource("/Immagini/store/backImg.png"));
            chiudi.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println("Can't find image of button");
        }
        chiudi.addActionListener(this);
        add(chiudi);

        repaint();
    }

    public Image getLivelloImg(int i) {
        apg = Integer.valueOf(String.valueOf(sPunti.charAt(i)));
        switch (apg) {
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xPix = getSize().width;
        int yPix = getSize().height;

        Graphics2D g2 = (Graphics2D) g;
        //SFONDO
        g2.drawImage(sfondo1, 0, 0, xPix, yPix, this);

        //SCRITTA STORE
        g2.drawImage(storeImg, 5 * xPix / (xOriginale), 5 * yPix / (yOriginale), 300 * xPix / (xOriginale), 70 * yPix / (yOriginale), this);

        //SCROLLPANEL
        mp.setDimensioni(3560*xPix/xOriginale, 250*yPix/yOriginale);
        miriniScrollPane.setBounds(0, 140*yPix/yOriginale, xPix, 260*yPix/yOriginale);

        //BOTTONE CHIUDI
        chiudi.setBounds(5 * xPix / (xOriginale), 80 * yPix / (yOriginale), 100 * xPix / (xOriginale), 40 * yPix / (yOriginale));

        //PUNTI
        for (int i = 0; i < sPunti.length(); i++) {
            g2.drawImage(getLivelloImg(i), (300+(56*i)) * xPix / (xOriginale), 5 * yPix / (yOriginale), 66 * xPix / (xOriginale), 55 * yPix / (yOriginale), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton)
        {
            if(Arrays.asList(b).contains(e.getSource()))
            {
                for(int i=0; i<b.length ; i++)
                {
                    if(b[i].equals(e.getSource()))
                    {
                        if (dp.mirinoDisponibile(i))
                        {
                            dp.setMirino(i);
                        }
                        else
                        {
                            int costo=dp.getPrezzoMirino(i);
                            if (punti >= costo)
                            {
                                dp.setMirinoDisponibile(i);
                                b[i].setText("Select");
                                b[i].setBackground(Color.LIGHT_GRAY);
                                punti -= costo;
                                sPunti = String.valueOf(punti);
                                dp.setPunti(punti);
                            }
                        }
                    }
                    else if(dp.mirinoDisponibile(i))
                    {
                        b[i].setBackground(Color.LIGHT_GRAY);
                        if (b[i].getText().equals("Selected"))
                        {
                            b[i].setText("Select");
                        }
                    }
                }
                b[dp.mirinoSelezionato()].setBackground(Color.GREEN);
                b[dp.mirinoSelezionato()].setText("Selected");
            }

            else if (e.getSource().equals(chiudi))
            {
                jf.dispose();
                Menu m=new Menu(dp);
                m.setVisible(true);
            }
        }
        repaint();
    }

    public Image getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(Image storeImg) {
        this.storeImg = storeImg;
    }

    public Image getImg1() {
        return img1;
    }

    public void setImg1(Image img1) {
        this.img1 = img1;
    }

    public Image getImg2() {
        return img2;
    }

    public void setImg2(Image img2) {
        this.img2 = img2;
    }

    public Image getImg3() {
        return img3;
    }

    public void setImg3(Image img3) {
        this.img3 = img3;
    }

    public Image getImg4() {
        return img4;
    }

    public void setImg4(Image img4) {
        this.img4 = img4;
    }

    public Image getImg5() {
        return img5;
    }

    public void setImg5(Image img5) {
        this.img5 = img5;
    }

    public Image getImg6() {
        return img6;
    }

    public void setImg6(Image img6) {
        this.img6 = img6;
    }

    public Image getImg7() {
        return img7;
    }

    public void setImg7(Image img7) {
        this.img7 = img7;
    }

    public Image getImg8() {
        return img8;
    }

    public void setImg8(Image img8) {
        this.img8 = img8;
    }

    public Image getImg9() {
        return img9;
    }

    public void setImg9(Image img9) {
        this.img9 = img9;
    }

    public Image getImg0() {
        return img0;
    }

    public void setImg0(Image img0) {
        this.img0 = img0;
    }

}
