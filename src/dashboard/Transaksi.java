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
public class Transaksi extends JPanel implements InComponent {

    private ArrayList<TransaksiModel> transaksi;
    private JPanel topPanel, panelScroll, topLeftPanel, topRightPanel;
    private JPanel sPanel,bayarPanel,kirimPanel,statusPanel;
    private JButton btnCheckout;
    private DecorationComponent dComponent;
    private Color cPrimary;
    private JTextArea isi;
    private JLabel kirim,bayar,status,lkirimke,lpengiriman,ltotal,linfo;
    private JTextField kirimke;
    private JComboBox menuPengiriman;

    public Transaksi(ArrayList transaksi) {
        this.transaksi = transaksi;
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
        bayarPanel = new JPanel();
        kirimPanel = new JPanel();
        statusPanel = new JPanel();
        sPanel = new JPanel();
        kirimke = new JTextField();
        lkirimke = new JLabel("Kirim Ke :");
        lpengiriman = new JLabel("Jasa pengiriman : ");
        ltotal = new JLabel("Total Bayar");
        linfo = new JLabel("Transfer sejumlah uang ke rekening 0721382 a/n Khisby Al Ghofari");
        menuPengiriman = new JComboBox();

        dComponent = new DecorationComponent();
        cPrimary = new Color(26, 188, 156);
    }

    @Override
    public void component() {
        
        menuPengiriman.addItem("JNE (1000)");
        menuPengiriman.addItem("JNE YES (2000)");
        menuPengiriman.addItem("TIKI (1500)");

        sPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        sPanel.setPreferredSize(new Dimension(1135, 665));

        topPanel.setLayout(new GridLayout(0, 2));
        topPanel.add(topLeftPanel);
        topPanel.add(topRightPanel);

        kirim = new JLabel("Pengiriman > ");
        bayar = new JLabel("Bayar > ");
        status = new JLabel("Status > ");

        topLeftPanel.add(kirim);
        topLeftPanel.add(bayar);
        topLeftPanel.add(status);

        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        isi = new JTextArea();
        isi.setPreferredSize(new Dimension(1135, 200));
        
        kirimPanel.setPreferredSize(new Dimension(1100, 400));
        lkirimke.setPreferredSize(new Dimension(1100, 40));
        kirimke.setPreferredSize(new Dimension(1100, 40));
        lpengiriman.setPreferredSize(new Dimension(1100, 40));
        ltotal.setPreferredSize(new Dimension(1100, 40));
        linfo.setPreferredSize(new Dimension(1100, 40));
        menuPengiriman.setPreferredSize(new Dimension(1100, 40));

        kirimPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        kirimPanel.add(lkirimke);
        kirimPanel.add(kirimke);
        kirimPanel.add(lpengiriman);
        kirimPanel.add(menuPengiriman);
        kirimPanel.add(new JButton("Lihat Total Bayar"));
        kirimPanel.add(ltotal);
        kirimPanel.add(linfo);
        
        
        sPanel.add(isi);
        sPanel.add(kirimPanel);
        

        add(topPanel, BorderLayout.NORTH);
        add(sPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionListener() {

    }

    public void reData() {
        isi.setText("");
        for (int i = 0; i < transaksi.get(0).getKeranjangItem().size(); i++) {
            isi.append(" - " + transaksi.get(0).getKeranjangItem().get(i).getProductName() + " (beli " + transaksi.get(0).getKeranjangItem().get(i).getJumlahBeli() + " biji )" + "\n");
        }
        
        if(transaksi.get(0).getStatusOrder() == 1){
            kirim.setForeground(cPrimary);
        }else if(transaksi.get(0).getStatusOrder() == 2){
            bayar.setForeground(cPrimary);
        }else if(transaksi.get(0).getStatusOrder() == 3){
            status.setForeground(cPrimary);
        }
//        System.out.println(transaksi.get(0).getKeranjangItem().size());
//        for (int i = 0; i < transaksi.size(); i++) {
//            System.out.println(transaksi.get(i).getUsername());
//            System.out.println("i = " + i);
//        }
    }

}
