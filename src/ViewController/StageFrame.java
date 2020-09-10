/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author utente
 */
public class StageFrame extends JFrame{
    private StagePanel p;
    
    public StageFrame(StagePanel p, boolean schermoIntero){
        super("SAVE THE CAT!");
        ImageIcon img = new ImageIcon("src/Immagini/gatto/gatto1.png");
        setIconImage(img.getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(schermoIntero);
        setSize(new Dimension(1280, 720));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(p);
        //CODICE PER NASCONDERE CURSORE MOUSE
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        getContentPane().setCursor(blankCursor);
    }
}
