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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.KeranjangItemModel;
import model.Product;
import utils.ProductItem;
import utils.InComponent;
import utils.KeranjangItem;

/**
 *
 * @author Whoami
 */
public class Store extends JPanel implements InComponent{
    private ArrayList products,keranjang;
    private JPanel topPanel,panelScroll,topLeftPanel,topRightPanel;
    private JScrollPane sPanel;
    private JComboBox<String> kategori;
    private JTextField pencarian;
    
    public Store(ArrayList products,ArrayList keranjang){
        this.products = products;
        this.keranjang = keranjang;
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
        kategori = new JComboBox<String>();
        pencarian = new JTextField();
    }

    @Override
    public void component() {        
        panelScroll.setAutoscrolls(true);
        panelScroll.setLayout(new GridLayout(0, 1, 20, 20));
        
        reData();
        
        sPanel.setViewportView(panelScroll);
        sPanel.setPreferredSize(new Dimension(1135, 665));
        
        
        topPanel.setLayout(new GridLayout(0,2));
        topPanel.add(topLeftPanel);
        topPanel.add(topRightPanel);
        
        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        String[] kategoriArr = new String[] {"All", "Kategori 1", "Kategori 2", "Kategori 3", "Kategori 4", "Kategori 5", "Kategori 6", "Kategori 7", "Kategori 8", "Kategori 9"};
        for(String kat : kategoriArr){
            kategori.addItem(kat);
        }
        
        pencarian.setPreferredSize(new Dimension(200,30));
        
        topLeftPanel.add(new JLabel("Kategori : "));
        topLeftPanel.add(kategori);
        topRightPanel.add(new JLabel("Cari barang : "));
        topRightPanel.add(pencarian);
        add(topPanel, BorderLayout.NORTH);
        add(sPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionListener() {
        pencarian.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                typeOn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                typeOn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                typeOn();
            }
            
            public void typeOn(){
//                System.out.println(pencarian.getText().length());
                if(pencarian.getText().length() > 0){
                    panelScroll.removeAll();
                    for (int i = 0; i < products.size(); i++) {
                        KeranjangItemModel pr = (KeranjangItemModel) products.get(i);
//                        System.out.println(pr.getProductName());
                        if(pr.getProductName().toUpperCase().contains(pencarian.getText().toUpperCase())){
//                            System.out.println("Ketemu : " + pr.getProductName());
                            panelScroll.add(new ProductItem((KeranjangItemModel) pr,keranjang));
                        }
                    }
                }else{
                    reData();
                }                
            }
        });
    }
    
    public void reData(){
        panelScroll.removeAll();
        for (int i = 0; i < products.size(); i++) {
            panelScroll.add(new ProductItem((KeranjangItemModel)products.get(i),keranjang));
        }
    }
    
   
}

