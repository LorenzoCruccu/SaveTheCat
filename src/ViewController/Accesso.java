package ViewController;

import Model.DatiProgresso;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Accesso extends JFrame implements ActionListener, KeyListener {
    private JPanel p;
    private JLabel username, password;
    private JTextField userFiedl;
    private JPasswordField passwordField;
    private JButton access, register, importa, esporta;

    public Accesso()
    {
        super("ACCESS");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width =(int) screenSize.getWidth();
        int height =(int) screenSize.getHeight();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(440*width/1280,290*height/720,400*width/1280,133*height/720);

        Font f=new Font("Arial", Font.PLAIN, 25);

        p=new JPanel(new GridLayout(4, 2));
        add(p);

        username=new JLabel("Username: ");
        username.setHorizontalAlignment(SwingConstants.RIGHT);
        username.setFont(f);
        p.add(username);

        userFiedl=new JTextField();
        userFiedl.setFont(f);
        userFiedl.addKeyListener(this);
        userFiedl.setFocusable(true);
        p.add(userFiedl);

        password=new JLabel("Password: ");
        password.setHorizontalAlignment(SwingConstants.RIGHT);
        password.setFont(f);
        p.add(password);

        passwordField=new JPasswordField();
        passwordField.setFont(f);
        passwordField.setFocusable(true);
        passwordField.addKeyListener(this);
        p.add(passwordField);

        access=new JButton("Login");
        access.setFont(f);
        access.addActionListener(this);
        p.add(access);

        register=new JButton("Register");
        register.setFont(f);
        register.addActionListener(this);
        p.add(register);

        importa=new JButton("Import");
        importa.setFont(f);
        importa.addActionListener(this);
        p.add(importa);

        esporta=new JButton("Export");
        esporta.setFont(f);
        esporta.addActionListener(this);
        p.add(esporta);

        p.addKeyListener(this);
        p.setFocusable(true);
    }

    private void login()
    {
        DatiProgresso dp;
        File f = new File("salvataggi/"+userFiedl.getText()+".dat");
        if(f.exists() && !f.isDirectory()) {
            FileInputStream flusso= null;
            ObjectInputStream leggiObj=null;
            try {
                flusso = new FileInputStream(f.getPath());
                leggiObj = new ObjectInputStream (flusso);
                dp=(DatiProgresso) leggiObj.readObject();
                if(dp.getPassword().equals(String.valueOf(passwordField.getPassword())))
                {
                    Menu m=new Menu(dp);
                    m.setVisible(true);
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Incorrect password", "Error" ,JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Non riesco ad aprire il file");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Username incorrect", "Error" ,JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(access))
        {
            login();
        }

        else if(e.getSource().equals(register))
        {
            File f = new File("salvataggi/"+userFiedl.getText()+".dat");
            if(f.exists() && !f.isDirectory())
            {
                JOptionPane.showMessageDialog(this, "This user already exists.", "Error" ,JOptionPane.ERROR_MESSAGE);
            }
            else if(String.valueOf(passwordField.getPassword()).equals("") || userFiedl.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "The user or the password field can't be empty.", "Error" ,JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                DatiProgresso dp = new DatiProgresso();
                dp.setNome(userFiedl.getText());
                dp.setPassword(String.valueOf(passwordField.getPassword()));
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("salvataggi/" + userFiedl.getText() + ".dat");
                    ObjectOutputStream salvaObj = new ObjectOutputStream(fos);
                    salvaObj.writeObject(dp);
                    salvaObj.flush();
                    fos.close();
                    JOptionPane.showMessageDialog(this, "Account created successfully!");
                } catch (FileNotFoundException e1) {
                    System.out.println("Non riesco a aprire il file in scrittura1");
                } catch (IOException e2) {
                    System.out.println("Non riesco a aprire il file in scrittura");
                }
            }

        }

        else if(e.getSource().equals(importa))
        {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("dat files", "dat");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File f = new File(chooser.getSelectedFile().getPath());
                if(f.exists() && !f.isDirectory()) {
                    FileInputStream flusso= null;
                    ObjectInputStream leggiObj=null;
                    try {
                        flusso = new FileInputStream(chooser.getSelectedFile().getPath());
                        leggiObj = new ObjectInputStream(flusso);
                        Object o=leggiObj.readObject();
                        leggiObj.close();
                        flusso.close();
                        if (o.getClass().equals(DatiProgresso.class)) {
                            DatiProgresso dp = (DatiProgresso) o;
                            int sovrascrivere=JOptionPane.YES_OPTION;
                            if(new File("salvataggi/" + dp.getNome() + ".dat").exists())
                            {
                                sovrascrivere=JOptionPane.showConfirmDialog(this,"This user is already registered, do you want to override it?");
                            }
                            if(sovrascrivere==JOptionPane.YES_OPTION) {
                                FileOutputStream fop = new FileOutputStream("salvataggi/" + dp.getNome() + ".dat");
                                ObjectOutputStream scriviObj = new ObjectOutputStream(fop);
                                scriviObj.writeObject(dp);
                                scriviObj.flush();
                                fop.close();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(this, "Unacceptable file", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch (HeadlessException ex) {
                        System.out.println("HeadlessException");
                        ex.printStackTrace();
                    }
                    catch (FileNotFoundException ex) {
                        System.out.println("FileNotFoundException");
                        ex.printStackTrace();
                    }
                    catch (IOException ex) {
                        System.out.println("IOException");
                        ex.printStackTrace();
                    }
                    catch (ClassNotFoundException ex) {
                        System.out.println("ClassNotFoundException");
                        ex.printStackTrace();
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Unacceptable file", "Error" ,JOptionPane.ERROR_MESSAGE);
            }
        }

        else if(e.getSource().equals(esporta))
        {
            final File folder = new File("salvataggi/");

            List result = new List();

            search(".*\\.dat", folder, result);

            ArrayList<DatiProgresso> utenti= new ArrayList<>();
            Object[] possibilities = new Object[result.getItemCount()];
            for(int i=0; i<result.getItemCount(); i++)
            {
                try {
                    FileInputStream f=new FileInputStream(result.getItem(i));
                    ObjectInputStream fIN=new ObjectInputStream(f);
                    DatiProgresso dp=(DatiProgresso) fIN.readObject();
                    utenti.add(dp);
                    f.close();
                    possibilities[i]=dp.getNome();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            String s = (String)JOptionPane.showInputDialog(
                    this,
                    "Select the user to export\n",
                    "Export",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    0);

            if(s!=null)
            {
                DatiProgresso dp=new DatiProgresso();
                for(int i=0; i<utenti.size(); i++)
                {
                    if(utenti.get(i).getNome().equals(s))
                    {
                        dp=utenti.get(i);
                        break;
                    }
                }
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                int returnVal = chooser.showOpenDialog(this);
                if(returnVal == JFileChooser.APPROVE_OPTION)
                {
                    try {
                        FileOutputStream fOUT = new FileOutputStream(chooser.getSelectedFile().getPath()+"\\"+dp.getNome()+".dat");
                        ObjectOutputStream oOUT = new ObjectOutputStream(fOUT);
                        oOUT.writeObject(dp);
                        oOUT.flush();
                        fOUT.close();
                    }
                    catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public void search(final String pattern, final File folder, List result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) {
        Accesso a=new Accesso();
        a.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            login();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

