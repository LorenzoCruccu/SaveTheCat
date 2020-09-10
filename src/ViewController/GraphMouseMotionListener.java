package ViewController;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class GraphMouseMotionListener implements MouseMotionListener {
    private static Point mousePos=new Point();

    public static Point getMousePos()
    {
        return mousePos;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePos=e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos=e.getPoint();
    }
}
