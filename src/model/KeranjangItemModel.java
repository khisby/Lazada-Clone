/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Whoami
 */
public class KeranjangItemModel extends Product{
    private int jumlahBeli = 1;

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }
    
    public KeranjangItemModel(String image, String productName, int price, int stock, String description, String category, String seller) {
        super(image, productName, price, stock, description, category, seller);
    }
    
    
    
    
}
