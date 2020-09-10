package ThreadPack;

import Model.Mirino;
import ViewController.GraphMouseMotionListener;
import ViewController.StagePanel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import static java.lang.Thread.sleep;

public class ThreadMirino implements Runnable {
    private Point p;
    private StagePanel sp;
    private Mirino m;

    public ThreadMirino(StagePanel sp, Mirino m)
    {
        p = GraphMouseMotionListener.getMousePos();
        this.sp=sp;
        this.m=m;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    @Override
    public void run() {
        do
        {
            p= GraphMouseMotionListener.getMousePos();
            m.setxPix(p.x-40);
            m.setyPix(p.y-40);
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //sp.repaint(new Rectangle(m.getxPix(), m.getyPix(), 300,200));
            sp.repaint();
            sp.revalidate();
        }while (sp.isVisible());
    }
}
