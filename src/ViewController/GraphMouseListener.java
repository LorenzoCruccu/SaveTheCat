package ViewController;

import Model.Character;
import Model.Sparo;
import ThreadPack.ThreadSparo;

import javax.swing.SwingUtilities;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GraphMouseListener implements MouseListener {
    private StagePanel sp;
    private ArrayList<Character> rv;

    public GraphMouseListener(StagePanel sp)
    {
        this.sp=sp;
        rv=sp.getRv();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(SwingUtilities.isLeftMouseButton(e))
        {
            int x1 = e.getX();
            int y1 = e.getY();
            Sparo s=new Sparo(x1*1280/sp.getxPixPanel(), y1*720/sp.getyPixPanel());
            sp.getSpr().add(s);
            Thread tspr=new Thread(new ThreadSparo(sp, s));
            tspr.start();
            int differenzaX;
            int differenzaY;
            if(sp.isGiocoPausa())
            {
                differenzaX=x1-1000*sp.getxPixPanel()/1280;
                differenzaY=y1-600*sp.getyPixPanel()/720;
                if(differenzaX>=0 && differenzaX<250*sp.getxPixPanel()/1280 &&
                        differenzaY>=0 && differenzaY<100*sp.getyPixPanel()/720)
                {
                    sp.getDp().addPunti(sp.getPunti());
                    Window w= SwingUtilities.getWindowAncestor(sp); //getta il frame del panel
                    w.dispose();
                    sp.chiudiFinestra();
                    Menu m=new Menu(sp.getDp());
                    m.setVisible(true);
                }

                differenzaX=x1-140*sp.getxPixPanel()/1280;
                differenzaY=y1-260*sp.getyPixPanel()/720;
                if(differenzaX>=0 && differenzaX<200*sp.getxPixPanel()/1280 &&
                   differenzaY>=0 && differenzaY<200*sp.getyPixPanel()/720)
                {
                    sp.restart();
                }
                differenzaX=x1-900*sp.getxPixPanel()/1280;
                differenzaY=y1-160*sp.getyPixPanel()/720;
                if(differenzaX>=0 && differenzaX<200*sp.getxPixPanel()/1280 &&
                        differenzaY>=0 && differenzaY<200*sp.getyPixPanel()/720)
                {
                    sp.modificaMusica();
                }
                differenzaX=x1-900*sp.getxPixPanel()/1280;
                differenzaY=y1-360*sp.getyPixPanel()/720;
                if(differenzaX>=0 && differenzaX<200*sp.getxPixPanel()/1280 &&
                        differenzaY>=0 && differenzaY<200*sp.getyPixPanel()/720)
                {
                    sp.modificaAudio();
                }
            }
            boolean continua = true;
            Character r;
            for (int i = 0; i < rv.size() && continua && !sp.isGiocoPausa(); i++) {
                r = rv.get(i);
                differenzaX = x1 - sp.coordinatePixNemico(r).x;
                differenzaY = y1 - sp.coordinatePixNemico(r).y;
                if (!sp.isGiocoPausa() && differenzaX < 100 * sp.getSize().width / 1280 && differenzaX >= 0 &&
                        differenzaY < 150 * sp.getSize().height / 720 && differenzaY >= 0) {
                    r.diminuisciVita(sp.getM().getDanno());
                    if (r.getVita() <= 0) {
                        sp.eliminaNemico(i);
                    }
                    continua = false;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }
}
