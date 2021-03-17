/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Asus
 */
public class Bird extends Rectangle {
    String image;

    public Bird(String image, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.image = image;
    }
    public void DrawBird(Graphics g){
        
        ImageIcon icon= new ImageIcon(image);
        
        Image image=icon.getImage();
        
        
        g.drawImage(image, x, y,null);
//          g.setColor(Color.red);
//          g.fillRect(x, y, width, height);
    }
            
}
