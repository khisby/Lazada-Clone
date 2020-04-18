/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

/**
 *
 * @author Whoami
 */
public class DecorationComponent {
    public void setTextFieldDecor(JTextField tf){
        tf.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(236, 240, 241)));
        tf.setFont(new Font("Roboto",Font.PLAIN, 15));
        tf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tf.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(26, 188, 156)));
            }

            @Override
            public void focusLost(FocusEvent e) {
                tf.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(236, 240, 241)));
            }
        });
    }
    
    public void setButtonDecor(JButton btn){
        btn.setUI(new ButtonUI());
    }
    
    public void setButtonIconDecor(JButton button){
            button.setUI(new ButtonIconUI());
    }
    
    public void setLabelDecor(JLabel l){
        l.setFont(new Font("Roboto", Font.PLAIN, 15));
    }
}
