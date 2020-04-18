package auth;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import utils.DecorationComponent;
import dashboard.Dashboard;
import utils.InComponent;

public class Login extends JFrame implements InComponent {
    private JButton btnLogin, btnRegister, btnExit;
    private JLabel lUsername, lPassword, contentLogo;
    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private JPanel pLogin, pLogo;
    private BufferedImage logo;
    private Graphics2D g2d;
    private Color cPrimary,cSecondary, cTertiary;
    private DecorationComponent dComponent;
    
    public Login() {
        super("Login - Lazada");
        
        init();
        component();
        actionListener();
        
//        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
//        setExtendedState(getExtendedState() | JFrame.NORMAL);
//        setState(Frame.NORMAL);
        
    }
    
    @Override
    public void init(){
        dComponent = new DecorationComponent();
        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");
        btnExit = new JButton("Exit");
        lUsername = new JLabel("Username");
        lPassword = new JLabel("Password");
        tfUsername = new JTextField();
        tfPassword = new JPasswordField();
        pLogin = new JPanel();
        pLogo = new JPanel();
        cPrimary = new Color(26, 188, 156);
        cSecondary = new Color(236, 240, 241);
        cTertiary = new Color(52, 73, 94);
        
        try {
            logo = ImageIO.read(new File("src/assets/logo.png"));
            logo = imageResize(logo,300,300);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void component(){
        setSize(1000,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
        
        contentLogo = new JLabel(new ImageIcon(logo));
        contentLogo.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        pLogo.add(contentLogo);
        
        
        pLogin.setLayout(null);
        dComponent.setLabelDecor(lUsername);
        dComponent.setLabelDecor(lPassword);
        dComponent.setTextFieldDecor(tfUsername);
        dComponent.setTextFieldDecor(tfPassword);
        dComponent.setButtonDecor(btnLogin);
        dComponent.setButtonDecor(btnRegister);
        dComponent.setButtonDecor(btnExit);
        
        int top = 130;
        int left = 0;
        
        lUsername.setBounds(left,top,150,30);
        tfUsername.setBounds(left,top+30,350,30);
        lPassword.setBounds(left,top+30+50,150,30);
        tfPassword.setBounds(left,top+30+50+30,350,30);
        
        btnLogin.setBounds(left, top+30+50+30+50, 100, 40);
        btnLogin.setForeground(cSecondary);
        btnLogin.setBackground(cPrimary);
        
        btnRegister.setBounds(left+120, top+30+50+30+50, 100, 40);
        btnRegister.setForeground(cSecondary);
        btnRegister.setBackground(cTertiary);
        btnExit.setBounds(400, 0, 100, 40);
        btnExit.setForeground(cSecondary);
        btnExit.setBackground(Color.RED);
        
        pLogin.add(lUsername);
        pLogin.add(lPassword);
        pLogin.add(tfUsername);
        pLogin.add(tfPassword);
        pLogin.add(btnLogin);
        pLogin.add(btnRegister);
        pLogin.add(btnExit);
        
         
        
        pLogo.setPreferredSize(new Dimension(500,500));
        pLogin.setPreferredSize(new Dimension(500,500));
        
        add(pLogo,BorderLayout.LINE_START);
        add(pLogin,BorderLayout.CENTER);
        
        pack();
    }
    
    @Override
    public void actionListener(){
        tfPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    private static BufferedImage imageResize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
    
    private void register(){
        try {
            File file = new File("src/assets/akun.txt");
            Scanner scan = new Scanner(file);

            FileWriter filewrite = new FileWriter(file, true);

            String dbUsername = "";
            String dbPassword = "";
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            boolean terdaftar = false;
            
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(this,"Username dan password harus di isi!","Kesalahan",JOptionPane.ERROR_MESSAGE);
            }else{
                while (scan.hasNext()) {
                    dbUsername = scan.nextLine();
                    dbPassword = scan.nextLine();
                    if (username.equals(dbUsername)) {
                        JOptionPane.showMessageDialog(this,"Akun sudah terdaftar!","Kesalahan",JOptionPane.ERROR_MESSAGE);
                        tfUsername.setText("");
                        tfPassword.setText("");
                        tfUsername.requestFocus();
                        terdaftar = true;
                        break;
                    }
                }
                
                if(terdaftar == false){
                    filewrite.write(username + "\r\n" + password + "\r\n");
                    filewrite.close();
                    JOptionPane.showMessageDialog(this,"Akun berhasil terdaftar.","Sukses",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (IOException d) {
            d.printStackTrace();
        }
    }
    
    private void login(){
        try {
             File file = new File("src/assets/akun.txt");
            Scanner scan = new Scanner(file);;

            FileWriter filewrite = new FileWriter(file, true);

            String dbUsername = "";
            String dbPassword = "";
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            boolean terdaftar = false;
            
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(this,"Username dan password harus di isi!","Kesalahan",JOptionPane.ERROR_MESSAGE);
            }else{
                while (scan.hasNext()) {
                    dbUsername = scan.nextLine();
                    dbPassword = scan.nextLine();
                    if (username.equals(dbUsername) && password.equals(dbPassword)) {
                        tfUsername.setText("");
                        tfPassword.setText("");
                        terdaftar = true;
                        break;
                    }
                }
                
                if(terdaftar == true){
                    JOptionPane.showMessageDialog(this,"Berhasil Login.","Sukses",JOptionPane.INFORMATION_MESSAGE);
                    new Dashboard(username);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(this,"Username atau Password salah","Kesalahan",JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException d) {
            d.printStackTrace();
        }
    }
    


}