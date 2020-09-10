package ViewController;

import Model.DatiProgresso;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class MiriniPanel extends JPanel {
    private DatiProgresso dp;
    private StoreView st;
    private JButton b[];
    private JLabel info[];
    private Image miriniImg[], sfondoMirini;
    private Toolkit tk;
    private int xOriginale, yOriginale;
    private int xPix, yPix;

    public MiriniPanel(StoreView st, DatiProgresso dp ,int xOriginale, int yOriginale)
    {
        this.dp=dp;
        this.st=st;
        this.xOriginale=xOriginale;
        this.yOriginale=yOriginale;
        setPreferredSize(new Dimension(xOriginale,yOriginale));

        tk = Toolkit.getDefaultToolkit();

        sfondoMirini=tk.getImage(getClass().getResource("/Immagini/store/sfondoStore2.jpg"));

        miriniImg=new Image[15];
        for(int i=0; i<miriniImg.length; i++)
        {
            miriniImg[i]=tk.getImage(getClass().getResource("/Immagini/mirini/mirino"+(i+1)+".png"));
        }

        info=new JLabel[15];
        b = new JButton[15];
        for (int i = 0; i < b.length; i++) {
            b[i] = new JButton();
            info[i]=new JLabel();
            info[i].setText("<html>PRICE: "+(dp.getPrezzoMirino(i))+"<br>DAMAGE: "+(dp.getDannoMirino(i))+"</html>");
            if (dp.mirinoSelezionato() == i) {
                b[i].setText("Selected");
                b[i].setBackground(Color.GREEN);
            } else if (dp.mirinoDisponibile(i)) {
                b[i].setText("Select");
                b[i].setBackground(Color.LIGHT_GRAY);
            } else {
                b[i].setText("Buy");
                b[i].setBackground(Color.YELLOW);
            }
            add(b[i]);
            add(info[i]);
            b[i].addActionListener(st);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(sfondoMirini, 0, 0, xPix, yPix, this);

        //MIRINI
        for(int i=0; i<miriniImg.length; i++)
        {
            g2.drawImage(miriniImg[i], 50+(240*i) * xPix / (xOriginale), 10 * yPix / (yOriginale), 100 * xPix / (xOriginale), 100 * yPix / (yOriginale), this);
        }

        //PULSANTI
        for(int i=0; i<b.length; i++)
        {
            b[i].setBounds((50+(240*i)) * xPix / (xOriginale), 130 * yPix / (yOriginale), 100 * xPix / (xOriginale), 40 * yPix / (yOriginale));
            info[i].setBounds((50+(240*i)) * xPix / (xOriginale), 170 * yPix / (yOriginale), 100 * xPix / (xOriginale), 70 * yPix / (yOriginale));
        }
    }

    public JButton[] getB() {
        return b;
    }

    public void setB(JButton[] b) {
        this.b = b;
    }

    public void setDimensioni(int xPix, int yPix)
    {
        this.xPix=xPix;
        this.yPix=yPix;
        setPreferredSize(new Dimension(xPix, yPix));
        repaint();
    }
}
