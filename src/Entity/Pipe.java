/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Asus
 */
public class Pipe extends Rectangle{
    Color color;

    public Pipe() {
        super();
        color= Color.BLACK;
    }

    public Pipe(Color color, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = color;
    }
    public void drawPipe(Graphics g){
        
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
    }

    
    
}
