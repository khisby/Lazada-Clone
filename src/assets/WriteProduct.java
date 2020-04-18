/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Whoami
 */
public class WriteProduct {

    public static void main(String[] args) throws IOException {
        File file = new File("src/assets/product.txt");
        Scanner scan;
        FileWriter filewrite;
        String arrData[][] = {
            {"src/assets/logout.png", "Asus X441MA-GA011T - Intel Celeron N4000 - RAM 4GB - 1TB - 14\" - Windows 10 – Black", "4000000", "10", "Processor: Intel Celeron N4000 Sistem Operasi: Windows 10 Home Memori: 4GB Display: 14.0' (16:9) LED backlit HD (1366x768) 60Hz Glare Panel with 45% NTSC Storage Hard Drives: 1TB 5400RPM SATA HDD Baterai: 3 Cells 36 Whrs Battery Dimensions: 34.8 x 24.2 x 2.76 cm (WxDxH) Weight: 1.7 kg with Battery", "Laptop", "Khisoft"},
            {"src/assets/logout.png", "laptop nya ", "29000000", "10", "sadasdPa asd asdnel with 45% NTSC Storage Hard Drives: 1TB 5400RPM SATA HDD Baterai: 3 Cells 36 Whrs Battery Dimensions: 34.8 x 24.2 x 2.76 cm (WxDxH) Weight: 1.7 kg with Battery", "Laptop", "Khisoft"}
        };  
        filewrite = new FileWriter(file, true);
        scan = new Scanner(file);
        
        clear();

        filewrite.write("");
        for (int i = 0; i < arrData.length; i++) {
            for (int j = 0; j < arrData[i].length; j++) {
                filewrite.write(arrData[i][j] + "\r\n");    
            }
        }
        filewrite.close();
    }

    private static void clear() throws IOException {
        FileWriter fwOb = new FileWriter("src/assets/product.txt", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
}
