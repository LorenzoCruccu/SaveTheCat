package ViewController;

import Model.Ultimate;

import javax.swing.SwingUtilities;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphKeyListener implements KeyListener {
    private StagePanel sp;
    private Ultimate u;
    
    public GraphKeyListener(StagePanel sp)
    {
        this.sp=sp;
        u=sp.getU();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='u' && u.isUltiCarica() && sp.isGiocoIniziato())
        {
            sp.lanciaUlti();
        }
        else if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(!sp.isGiocoIniziato())
            {
                sp.iniziaGioco();
            }
            else if(sp.isGattoPreso())
            {
                sp.getDp().addPunti(sp.getPunti());
                Window w= SwingUtilities.getWindowAncestor(sp); //getta il frame del panel
                w.dispose();
                sp.chiudiFinestra();
                Menu m=new Menu(sp.getDp());
                m.setVisible(true);
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            if(sp.isGiocoPausa())
                sp.riassumi();
            else
                sp.pausa();
        }
        else if(e.getKeyCode()==KeyEvent.VK_F11)
        {
            sp.schermoIntero();
        }
        else if(e.getKeyCode()==KeyEvent.VK_R)
        {
            sp.restart();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
