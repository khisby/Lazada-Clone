/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Whoami
 */
public class TransaksiModel {

    private ArrayList<KeranjangItemModel> keranjangItem;
    private String bankTransfer;
    private String norekTransfer;
    private String namaTransfer;
    private String username;
    private String sendAddress;
    private int statusOrder;
    private String courier;

    public TransaksiModel(ArrayList keranjangItem, String username) {
        this.keranjangItem = keranjangItem;
        this.username = username;
        this.sendAddress = sendAddress;
        this.statusOrder = 1;
        this.courier = courier;
    }

    public ArrayList<KeranjangItemModel> getKeranjangItem() {
        return keranjangItem;
    }

    public void setKeranjangItem(ArrayList<KeranjangItemModel> keranjangItem) {
        this.keranjangItem = keranjangItem;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public int getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(int statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getBankTransfer() {
        return bankTransfer;
    }

    public void setBankTransfer(String bankTransfer) {
        this.bankTransfer = bankTransfer;
    }

    public String getNorekTransfer() {
        return norekTransfer;
    }

    public void setNorekTransfer(String norekTransfer) {
        this.norekTransfer = norekTransfer;
    }

    public String getNamaTransfer() {
        return namaTransfer;
    }

    public void setNamaTransfer(String namaTransfer) {
        this.namaTransfer = namaTransfer;
    }

}
