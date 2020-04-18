/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.KeranjangItemModel;
import model.Product;
import model.TransaksiModel;
import utils.DecorationComponent;
import utils.InComponent;
import utils.KeranjangItem;
import utils.ProductItem;

/**
 *
 * @author Whoami
 */
public class Keranjang extends JPanel implements InComponent {

    private ArrayList<KeranjangItemModel> keranjang;
    private ArrayList transaksiItem;
    private JPanel topPanel, panelScroll, topLeftPanel, topRightPanel;
    private JScrollPane sPanel;
    private JButton btnCheckout;
    private DecorationComponent dComponent;
    private Color cPrimary;
    private String username;

    public Keranjang(ArrayList keranjang, ArrayList transaksi, String username) {
        this.keranjang = keranjang;
        this.transaksiItem = transaksi;
        this.username = username;
        setLayout(new BorderLayout());

        init();
        component();
        actionListener();
    }

    @Override
    public void init() {
        topPanel = new JPanel();
        panelScroll = new JPanel();
        topLeftPanel = new JPanel();
        topRightPanel = new JPanel();
        sPanel = new JScrollPane();

        dComponent = new DecorationComponent();
        btnCheckout = new JButton("Checkout");
        cPrimary = new Color(26, 188, 156);
    }

    @Override
    public void component() {
        panelScroll.setAutoscrolls(true);
        panelScroll.setLayout(new GridLayout(0, 1, 20, 20));
        reData();

        sPanel.setViewportView(panelScroll);
        sPanel.setPreferredSize(new Dimension(1135, 665));

        topPanel.setLayout(new GridLayout(0, 2));
        topPanel.add(topLeftPanel);
        topPanel.add(topRightPanel);

        btnCheckout.setPreferredSize(new Dimension(200, 40));
        btnCheckout.setBackground(cPrimary);
        btnCheckout.setForeground(Color.WHITE);
        dComponent.setButtonDecor(btnCheckout);
        topRightPanel.add(btnCheckout);

        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(topPanel, BorderLayout.NORTH);
        add(sPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionListener() {
        btnCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransaksiModel tm = new TransaksiModel(keranjang, username);
                transaksiItem.add(tm);
//                keranjang.clear();
                reData();
                JOptionPane.showMessageDialog(null,"Berhasil di tambhakan ke Transaksi","INFO",JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void reData() {
        panelScroll.removeAll();
        for (int i = 0; i < keranjang.size(); i++) {
            panelScroll.add(new KeranjangItem(keranjang.get(i)));
        }
    }

}
