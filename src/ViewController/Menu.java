package ViewController;

import Model.DatiProgresso;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Menu extends JPanel  implements ActionListener {
    private DatiProgresso dp;
    private JFrame frame;
    private JPanel buttonPanel;
    private JLabel titolo;
    private JButton play, store, exit;
    private Toolkit toolkit;
    private Image gattoImg, sfondo;
    private int xPanel, yPanel;

    public Menu(DatiProgresso dp)
    {
        this.dp=dp;
        frame=new JFrame("MENU");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        toolkit= Toolkit.getDefaultToolkit();
        Random r=new Random();
        sfondo=toolkit.getImage(getClass().getResource("/Immagini/sfondiMenu/sfondoMenu.png"));
        gattoImg =toolkit.getImage(getClass().getResource("/Immagini/sfondiMenu/gatto"+(r.nextInt(12)+1)+".png"));


        Dimension screenSize = toolkit.getDefaultToolkit().getScreenSize();
        int width =(int) screenSize.getWidth();
        int height =(int) screenSize.getHeight();
        frame.setBounds(440*width/1280,210*height/720,400*width/1280, 300*height/720);
        frame.add(this);
        setLayout(null);
        frame.setVisible(true);

        //TITOLO
        titolo=new JLabel("SAVE THE CAT!");
        titolo.setForeground(Color.RED);
        titolo.setFont(new Font("Tempus Sans ITC", 1, 30));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titolo);

        //BUTTONPANEL
        buttonPanel =new JPanel(new GridLayout(3,1, 0, 40));
        buttonPanel.setBounds(100*xPanel/400, 100*yPanel/300, 200*xPanel/400, 180*yPanel/300);
        buttonPanel.setOpaque(false);
        add(buttonPanel);

        //PLAY BUTTON
        play=new JButton("PLAY!");
        play.setBackground(Color.GREEN);
        play.setFont(new Font("Tempus Sans ITC", 1, 24));
        play.addActionListener(this);
        buttonPanel.add(play);

        //STORE BUTTON
        store=new JButton("STORE");
        store.setBackground(Color.YELLOW);
        store.setFont(new Font("Tempus Sans ITC", 1, 24));
        store.addActionListener(this);
        buttonPanel.add(store);

        //EXIT BUTTON
        exit=new JButton("SAVE AND EXIT");
        exit.setBackground(Color.WHITE);
        exit.setFont(new Font("Tempus Sans ITC", 1, 24));
        exit.addActionListener(this);
        buttonPanel.add(exit);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        xPanel=getSize().width;
        yPanel=getSize().height;

        Graphics2D g2=(Graphics2D)g;
        //SFONDO
        g2.drawImage(sfondo, 0,0, xPanel, yPanel, this);
        g2.drawImage(gattoImg, -xPanel/4,0, xPanel, xPanel, this);

        //TITOLO
        titolo.setBounds(100*xPanel/400, 10*yPanel/300, 200*xPanel/400, 50*yPanel/300);

        //INFO ACCOUNT
        g2.setFont(new Font("Arial", Font.BOLD, 10*xPanel/400));
        g2.drawString("Name: "+dp.getNome()+"   Score: "+dp.getPunti(), 5*xPanel/400, 15*yPanel/300);

        //buttonPanel
        buttonPanel.setBounds(100*xPanel/400, 100*yPanel/300, 200*xPanel/400, 180*yPanel/300);
        buttonPanel.setLayout(new GridLayout(3,1, 0, 15*yPanel/300));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(play))
        {
            StagePanel sp=new StagePanel(dp);
            frame.dispose();
        }
        else if(e.getSource().equals(store))
        {
            frame.dispose();
            StoreView sv=new StoreView(dp);
            sv.setVisible(true);
        }
        else if(e.getSource().equals(exit))
        {
            FileOutputStream fos=null;

            try {
                 fos = new FileOutputStream("salvataggi/"+dp.getNome()+".dat");
                 ObjectOutputStream salvaObj=new ObjectOutputStream(fos);
                 salvaObj.writeObject(dp);
                 salvaObj.flush();
                 fos.close();
            }
            catch (FileNotFoundException e1)
            {
                System.out.println("Non riesco a aprire il file in scrittura");
            }
            catch (IOException e2)
            {
                System.out.println("Non riesco a aprire il file in scrittura");
            }
            System.exit(0);
        }
    }
}
