/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadPack;

import Model.Character;
import ViewController.StagePanel;

import java.util.Random;

/**
 *
 * @author utente
 */
public class ThreadCharacter implements Runnable {

    private Character r;
    private StagePanel sp;
    private int spawn;

    public ThreadCharacter(Character r, StagePanel sp) {
        this.r = r;
        this.sp = sp;
        spawn = r.getPuntoSpawn();
    }

    @Override
    public void run() {
        switch (spawn) {
            ///////////////////////////////////////////////////////BOSCO
            case 0:
                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.destra();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((-100 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 670 * sp.getSize().width / 1280);

                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.su();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((300 + r.getPosVer() * r.getVelocità()) * sp.getSize().height / 720) > 60 * sp.getSize().height / 720);

                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.destra();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((-100 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 1080 * sp.getSize().width / 1280);
                break;
            ///////////////////////////////////////////////////////COLLINA
            case 1:
                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.destra();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((825 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 950 * sp.getSize().width / 1280);

                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.giu();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((300 + r.getPosVer() * r.getVelocità()) * sp.getSize().height / 720) < 580 * sp.getSize().height / 720);

                ///////////////////////////////////////////////////////SINISTRA
                if (new Random().nextInt(2) == 0) {
                    do {
                        if (sp.isGattoPreso() || r.isMorto()) {
                            break;
                        }
                        r.sinistra();
                        do {
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while (sp.isGiocoPausa());
                        //sp.repaint();
                    } while (((825 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) > 670 * sp.getSize().width / 1280);

                    do {
                        if (sp.isGattoPreso() || r.isMorto()) {
                            break;
                        }
                        r.su();
                        do {
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while (sp.isGiocoPausa());
                        //sp.repaint();
                    } while (((300 + r.getPosVer() * r.getVelocità()) * sp.getSize().height / 720) > 60 * sp.getSize().height / 720);

                    do {
                        if (sp.isGattoPreso() || r.isMorto()) {
                            break;
                        }
                        r.destra();
                        do {
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while (sp.isGiocoPausa());
                        //sp.repaint();
                    } while (((825 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 1080 * sp.getSize().width / 1280);

                } ///////////////////////////////////////////////////////DESTRA
                else {
                    do {
                        if (sp.isGattoPreso() || r.isMorto()) {
                            break;
                        }
                        r.destra();
                        do {
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while (sp.isGiocoPausa());
                        //sp.repaint();
                    } while (((845 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 1300 * sp.getSize().width / 1280);

                    do {
                        if (sp.isGattoPreso() || r.isMorto()) {
                            break;
                        }
                        r.su();
                        do {
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while (sp.isGiocoPausa());
                        //sp.repaint();
                    } while (((300 + r.getPosVer() * r.getVelocità()) * sp.getSize().height / 720) > 200 * sp.getSize().height / 720);

                    do {
                        if (sp.isGattoPreso() || r.isMorto()) {
                            break;
                        }
                        r.sinistra();
                        do {
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while (sp.isGiocoPausa());
                        //sp.repaint();
                    } while (((825 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) > 950 * sp.getSize().width / 1280);

                    do {
                        if (sp.isGattoPreso() || r.isMorto()) {
                            break;
                        }
                        r.su();
                        do {
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while (sp.isGiocoPausa());
                        //sp.repaint();
                    } while (((300 + r.getPosVer() * r.getVelocità()) * sp.getSize().height / 720) > 60 * sp.getSize().height / 720);

                    do {
                        if (sp.isGattoPreso() || r.isMorto()) {
                            break;
                        }
                        r.destra();
                        do {
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while (sp.isGiocoPausa());
                        //sp.repaint();
                    } while (((765 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 1020 * sp.getSize().width / 1280);
                    //950 -> 890
                }
                break;
            ///////////////////////////////////////////////////////LAGO
            case 2:
                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.giu();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((-150 + r.getPosVer() * r.getVelocità()) * sp.getSize().height / 720) < 60 * sp.getSize().height / 720);

                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.destra();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((890 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 1080 * sp.getSize().width / 1280);
                break;
            ///////////////////////////////////////////////////////ALBERO SUD
            case 3:
                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.destra();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((400 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 670 * sp.getSize().width / 1280);

                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.su();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((535 + r.getPosVer() * r.getVelocità()) * sp.getSize().height / 720) > 60 * sp.getSize().height / 720);

                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.destra();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((400 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 1080 * sp.getSize().width / 1280);
                break;
            ///////////////////////////////////////////////////////ALBERI NORD
            case 4:
                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.destra();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((150 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 670 * sp.getSize().width / 1280);

                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.su();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((60 + r.getPosVer() * r.getVelocità()) * sp.getSize().height / 720) > 60 * sp.getSize().height / 720);

                do {
                    if (sp.isGattoPreso() || r.isMorto()) {
                        break;
                    }
                    r.destra();
                    do {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (sp.isGiocoPausa());
                    //sp.repaint();
                } while (((150 + r.getPosOrz() * r.getVelocità()) * sp.getSize().width / 1280) < 1080 * sp.getSize().width / 1280);
                break;
        }
        if (!r.isMorto()) {
            sp.gameOver();
        }

    }
}
