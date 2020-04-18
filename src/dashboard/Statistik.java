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
import javax.swing.*;
import utils.InComponent;

/**
 *
 * @author Whoami
 */
public class Statistik extends JPanel implements InComponent{
    
    public Statistik() {
        setLayout(new GridLayout(1, 0, 50, 0));
        init();
        component();
//        actionListener();
    }

    @Override
    public void init() {
            
    }

    @Override
    public void component() {
        add(new JLabel("Jumlah Barang : 100"));
        add(new JLabel("Jumlah Terjual: 0"));
        add(new JLabel("Jumlah Kategori: 5"));
        add(new JLabel("Jumlah Jasa Pengiriman: 2"));
        add(new JLabel("Jumlah Paket Pengiriman: 8"));
    }

    @Override
    public void actionListener() {
        
    }
    
   
}
