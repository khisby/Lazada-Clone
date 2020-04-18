package dashboard;

import auth.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.management.timer.Timer.ONE_SECOND;
import javax.swing.*;
import model.KeranjangItemModel;
import model.Product;
import model.TransaksiModel;
import utils.DecorationComponent; 
import utils.InComponent;

public class Dashboard extends JFrame implements InComponent {

    private String username;
    private JPanel topPanel, leftPanel, topLeftPanel, topRightPanel,leftTopPanel, leftBottomPanel,contentPanel;
    private JButton btnLogout,btnDashboard, btnStore, btnAdministrator, btnKeranjang, btnTransaksi, btnHistory;
    private Color cPrimary, cSecondary, cTertiary, cTransparent;
    private DecorationComponent dComponent;
    private JLabel waktu,garisHorizontal,garisVertical,menu, judulApp,copyright, nama;
    private BufferedImage logo;
    private final SimpleDateFormat clockFormat = new SimpleDateFormat("EEEEE, dd MMMMM yyyy H:mm:ss");
    private Statistik statistik;
    private Store store;
    private Keranjang keranjang;
    private Transaksi transaksi;
    private History history;
    private Administrator administrator;
    private ArrayList<KeranjangItemModel> productsItem,keranjangItem;
    private ArrayList<TransaksiModel> transaksiItem;
    
    public void buttonInit(){
        btnLogout = new JButton(new ImageIcon("src/assets/logout.png"));
        btnDashboard = new JButton(new ImageIcon("src/assets/dashboard.png"));
        btnStore = new JButton(new ImageIcon("src/assets/store.png"));
        btnAdministrator = new JButton(new ImageIcon("src/assets/admin.png")); 
        btnKeranjang = new JButton(new ImageIcon("src/assets/keranjang.png"));
        btnTransaksi = new JButton(new ImageIcon("src/assets/transaksi.png"));
        btnHistory = new JButton(new ImageIcon("src/assets/history.png"));

        dComponent.setButtonIconDecor(btnLogout);
        dComponent.setButtonIconDecor(btnDashboard);
        dComponent.setButtonIconDecor(btnStore);
        dComponent.setButtonIconDecor(btnAdministrator);
        dComponent.setButtonIconDecor(btnKeranjang);
        dComponent.setButtonIconDecor(btnTransaksi);
        dComponent.setButtonIconDecor(btnHistory);
    }

    public Dashboard(String username) throws FileNotFoundException {
        super("Dashboard - Lazada");
        
        productsItem = new ArrayList<>();
        keranjangItem = new ArrayList<>();
        transaksiItem = new ArrayList<>();
        
        Scanner scan = new Scanner(new File("src/assets/product.txt"));
//        System.out.println(scan.nextLine() + "\n" +  scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine());
        while (scan.hasNext()) {
            String gambar = scan.nextLine();
            String nama = scan.nextLine();
            String harga = scan.nextLine();
            String stock = scan.nextLine();
            String deskripsi = scan.nextLine();
            String kategori = scan.nextLine();
            String penjual = scan.nextLine();
            
            
            productsItem.add(new KeranjangItemModel(gambar, nama, Integer.parseInt(harga), Integer.parseInt(stock), deskripsi, kategori, penjual));
            
        }
        
//        productsItem.add(new Product("src/assets/logout.png","Barang1", 1000, 10, "Barang original 1", "Kategori1", "Khisoft Official"));
//        productsItem.add(new Product("src/assets/logout.png","Barang2", 2000, 20, "Barang original 2", "Kategori2", "Khisoft Official"));
//        productsItem.add(new Product("src/assets/logout.png","Barang3", 3000, 30, "Barang original 3", "Kategori3", "Khisoft Official"));
//        productsItem.add(new Product("src/assets/logout.png","Barang4", 4000, 40, "Barang original 4", "Kategori4", "Khisoft Official"));
        
        this.username = username;

        init();
        component();
        actionListener();
    }

    @Override
    public void init() {
        dComponent = new DecorationComponent();
        topPanel = new JPanel();
        topLeftPanel = new JPanel();
        topRightPanel = new JPanel();
        leftTopPanel = new JPanel();
        leftBottomPanel = new JPanel();
        leftPanel = new JPanel();
        contentPanel = new JPanel();
        cPrimary = new Color(26, 188, 156);
        cSecondary = new Color(236, 240, 241);
        cTertiary = new Color(52, 73, 94);
        cTransparent = new Color(0, 0, 0, 0);
        waktu = new JLabel("Waktunya");
        menu = new JLabel("Menu");
        garisHorizontal = new JLabel("|");
        garisVertical = new JLabel("-");
        judulApp = new JLabel("Lazada ");
        nama = new JLabel("Welcome, " + username);
        copyright = new JLabel("\u00a9 Khisoft 2018");
        
        statistik = new Statistik();
        store = new Store(productsItem,keranjangItem);
        keranjang = new Keranjang(keranjangItem,transaksiItem,this.username);
        transaksi = new Transaksi(transaksiItem);
        history = new History();
        administrator = new Administrator();
        
        
        try {
            logo = ImageIO.read(new File("src/assets/logoIcon.png"));
            logo = imageResize(logo,60,60);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        buttonInit();
    }
    
    private static BufferedImage imageResize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    @Override
    public void component() {
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setBackground(cSecondary);

        topPanel.setLayout(new GridLayout(1, 2));
        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topLeftPanel.setBackground(cPrimary);
        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.setBackground(cPrimary);
        leftBottomPanel.setBackground(cPrimary);
        
        topPanel.add(topLeftPanel);
        topPanel.add(topRightPanel);
        
        leftTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,30));
        leftTopPanel.setBackground(cTertiary);
        leftTopPanel.setPreferredSize(new Dimension(230, 200));
        
        Timer timer = new Timer((int) ONE_SECOND, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                waktu.setText(clockFormat.format(new Date()));
                waktu.repaint();
            }
        });
        waktu.setText(clockFormat.format(new Date()));
        waktu.setFont(new Font("Raleway", Font.BOLD, 25));
        waktu.setForeground(Color.WHITE);
        timer.start();
        garisHorizontal.setFont(new Font("Raleway", Font.PLAIN, 40));
        garisHorizontal.setForeground(Color.WHITE);
        garisVertical.setFont(new Font("Raleway", Font.PLAIN, 40));
        garisVertical.setForeground(Color.WHITE);
        menu.setFont(new Font("Raleway", Font.PLAIN, 20));
        menu.setForeground(Color.WHITE);
        judulApp.setFont(new Font("Raleway", Font.BOLD, 40));
        judulApp.setForeground(Color.WHITE);
        copyright.setFont(new Font("Raleway", Font.BOLD, 20));
        copyright.setForeground(Color.WHITE);
        nama.setFont(new Font("Raleway", Font.PLAIN, 25));
        nama.setForeground(Color.WHITE);
        
        topLeftPanel.add(new JLabel(new ImageIcon(logo)));
        topLeftPanel.add(judulApp);
        topLeftPanel.add(nama);
                
        topRightPanel.add(waktu);
        topRightPanel.add(garisHorizontal);
        topRightPanel.add(btnLogout);
        
        leftBottomPanel.add(copyright);
        
        leftTopPanel.add(btnDashboard);
        leftTopPanel.add(btnStore);
        leftTopPanel.add(btnKeranjang);
        leftTopPanel.add(btnTransaksi);
        leftTopPanel.add(btnHistory);
        leftTopPanel.add(btnAdministrator);
        
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(leftTopPanel, BorderLayout.CENTER);
        leftPanel.add(leftBottomPanel, BorderLayout.SOUTH);
        
        contentPanel.add(statistik);

        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

//        pack();

        setVisible(true);
    }

    @Override
    public void actionListener() {
        btnLogout.addActionListener((e) -> {
            dispose();
            new Login();
        });
        
        btnDashboard.addActionListener((e) -> {
            contentPanel.removeAll();
            contentPanel.add(statistik);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
        
        btnStore.addActionListener((e) -> {
            contentPanel.removeAll();
            contentPanel.add(store);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
         
        btnKeranjang.addActionListener((e) -> {
            keranjang.reData();
            contentPanel.removeAll();
            contentPanel.add(keranjang);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
        
        btnTransaksi.addActionListener((e) -> {
            contentPanel.removeAll();
            contentPanel.add(transaksi);
            contentPanel.revalidate();
            contentPanel.repaint();
            transaksi.reData();
        });
        
        btnHistory.addActionListener((e) -> {
            contentPanel.removeAll();
            contentPanel.add(history);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
        
        btnAdministrator.addActionListener((e) -> {
            contentPanel.removeAll();
            contentPanel.add(administrator);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
        
    }

}
