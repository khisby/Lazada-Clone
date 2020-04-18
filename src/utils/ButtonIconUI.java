/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javafx.scene.effect.DropShadow;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Whoami
 */
public class ButtonIconUI extends BasicButtonUI {
   
    @Override
    public void installUI (JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        
        button.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 0, 0)));
        
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 0, 0)));
//                button.setBorder(BorderFactory.createMatteBorder(6, 0, 0, 6, new Color(0, 0, 0, 0)));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBorder(BorderFactory.createMatteBorder(6, 3, 0, 3, new Color(0, 0, 0, 0)));
//                button.setBorder(BorderFactory.createMatteBorder(6, 0, 0, 6, new Color(0, 0, 0, 0)));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 0, 0)));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 0, 0)));
//                button.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 6, new Color(0, 0, 0, 0)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 0, 0)));
            }
        });

    }
}