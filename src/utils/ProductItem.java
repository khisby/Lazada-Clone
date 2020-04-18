/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import auth.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.KeranjangItemModel;
import model.Product;

/**
 *
 * @author Whoami
 */
public class ProductItem extends JPanel implements InComponent {

    private ArrayList<Product> keranjang;
    private KeranjangItemModel product;
    private JPanel leftPanel, rightPanel;
    private JTextArea deskripsi;
    private JLabel judul;
    private JButton btnAddKeranjang;
    private DecorationComponent dComponent;
    private Color cPrimary, cSecondary, cTertiary, cTransparent;
    private BufferedImage logo;

    public ProductItem(KeranjangItemModel product, ArrayList keranjang) {
        this.product = product;
        this.keranjang = keranjang;

        setPreferredSize(new Dimension(1100, 215));
        setBorder(BorderFactory.createMatteBorder(0, 0, 15, 0, new Color(255, 255, 255)));
        setLayout(new FlowLayout(FlowLayout.LEFT));

        init();
        component();
        actionListener();
    }

    @Override
    public void init() {
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        deskripsi = new JTextArea(2, 0);
        judul = new JLabel();
        if (product.getStock() <= 0) {
            btnAddKeranjang = new JButton("STOCK HABIS");
        } else {
            btnAddKeranjang = new JButton("+ Keranjang (IDR. " + product.getPrice()+ ")" );
        }
        dComponent = new DecorationComponent();
        cPrimary = new Color(26, 188, 156);
        cSecondary = new Color(236, 240, 241);
        cTertiary = new Color(52, 73, 94);
        cTransparent = new Color(0, 0, 0, 0);

        try {
            logo = ImageIO.read(new File(product.getImage()));
            logo = imageResize(logo, 190, 190);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        leftPanel.setPreferredSize(new Dimension(190, 190));
        leftPanel.add(new JLabel(new ImageIcon(logo)));

        rightPanel.setPreferredSize(new Dimension(910, 190));
        rightPanel.setLayout(new GridLayout(0, 1));

        judul.setText(product.getProductName());
        judul.setFont(new Font("Raleway", Font.BOLD, 25));
        judul.setForeground(Color.BLACK);
        deskripsi.setText(product.getDescription());
        deskripsi.setEditable(false);
        deskripsi.setLineWrap(true);
        
        if (product.getStock() <= 0) {
            btnAddKeranjang.setEnabled(false);
            btnAddKeranjang.setBackground(cSecondary);
            btnAddKeranjang.setForeground(Color.BLACK);
        }else{
            btnAddKeranjang.setBackground(cPrimary);
            btnAddKeranjang.setForeground(Color.WHITE);
        }
        
        btnAddKeranjang.setSize(new Dimension(100, 50));
        dComponent.setButtonDecor(btnAddKeranjang);

        rightPanel.add(judul);
        rightPanel.add(new JLabel("Kategori : " + product.getCategory()));
        rightPanel.add(new JLabel("Penjual : " + product.getSeller()));
        rightPanel.add(new JScrollPane(deskripsi));
        rightPanel.add(btnAddKeranjang);

        add(leftPanel);
        add(rightPanel);
    }

    @Override
    public void actionListener() {
        if (product.getStock() <= 0) {
            btnAddKeranjang.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnAddKeranjang.setEnabled(false);
                }
            
            });
        } else {
            btnAddKeranjang.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean sudahAda = false;

                    for (int i = 0; i < keranjang.size(); i++) {
                        if (keranjang.get(i).equals(product)) {
                            sudahAda = true;

                        }
                    }

                    if (sudahAda == false) {
                        keranjang.add(product);
                        JOptionPane.showMessageDialog(null, "Product \"" + product.getProductName() + "\" Berhasil ditambahkan ke Keranjang", "Info", JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(null, "Anda sudah menambahkan product tersebut! silahkan ubah jumlah pesanan di menu keranjang", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
        }
    }

}
