package lazada;

import java.awt.*;
import javax.swing.*;
import utils.DecorationComponent;
import auth.Login;
import dashboard.Dashboard;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.EtchedBorder;
import utils.InComponent;

public class Main extends JFrame implements InComponent {
    private JPanel panel;
    private JProgressBar loading;
    private BufferedImage logo;
    private Graphics2D g2d;
    private  JLabel label1;
    
    public Main() {
        super("Dashboard - Lazada");

        init();
        component();
        actionListener();
    }
    
    @Override
    public void init(){
       
    }
    
    @Override
    public void component(){
        setSize(1000,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated (true);
        setResizable(false);
        setVisible (true);
        
        loading = new JProgressBar(0, 100);
        loading.setValue(0);
        loading.setPreferredSize( new Dimension (300, 30));
        loading.setStringPainted(true);
        loading.setSize(new Dimension(800, 50));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel, BorderLayout.CENTER);

        
        try {
            logo = ImageIO.read(new File("src/assets/logo.png"));
            logo = imageResize(logo,400,400);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        JLabel label = new JLabel("Product by");
        label.setBounds(450, 30, 300, 30);
        label.setFont(new Font("Verdana", Font.BOLD, 14));
        panel.add(label);
        JLabel icon = new JLabel(new ImageIcon(logo));
        icon.setBounds(200, -50, 600, 600);
        panel.add(icon);
        panel.setPreferredSize(new Dimension(1000, 500));
        
        label1 = new JLabel();
        label1.setBounds(420, 460, 300, 30);
        label1.setFont(new Font("Verdana", Font.BOLD, 14));
        panel.add(label1);
        
        add(loading, BorderLayout.SOUTH);

        ProgressStatusUpdate.start();
        ProgressUpdate.start();
        pack();
    }
    
    @Override
    public void actionListener(){
     
    }
    
    Thread ProgressUpdate = new Thread() {
        @Override
        public void run() {
                int time = 100;

                for(int i=0;i <= time; i++) {
                  try {
                        Thread.sleep(100);
                      } catch (Exception ex) {

                      }
                  loading.setValue(i);
                }
               
                new Login();
                dispose();
        }
    };
    
    Thread ProgressStatusUpdate = new Thread() {
        @Override
        public void run() {
                String[] text = {"Memuat Aplikasi...","Menyiapkan GUI...","Memuat proses...","Mengecek Proses...","Mengecek Server...","Memastikan kesiapan...","Memastikan Aplikasi...","THIS STATUS IS CENCORED","Membuka Aplikasi..."};
                for (int i = 0; i < text.length; i++) {
                    label1.setText(text[i]);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
    };

    private static BufferedImage imageResize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
   
}