/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Asus
 */
public class Pipe_Double {
    private static final int DISTANCE_MIN=150;
    private static final int DISTANCE_MAX=200;
    private static final int HEIGHT_MIN=300;
    private static final int HEIGHT_MAX=500;
    private Pipe up;
    private Pipe down;
    static int x=450;
    static Random dis= new Random(System.currentTimeMillis());
    public static void resetStart(){
        x=450;
    }
    public Pipe_Double(Color color,int width,int HEIGHT, Random r) {
        
        int heightUp=r.nextInt((HEIGHT_MAX-HEIGHT_MIN)+1)+HEIGHT_MIN;
        
        up=new Pipe(color,x,0, width,heightUp);
        
        //System.out.println("start x: "+x);
        int startHeightDown=r.nextInt((heightUp+DISTANCE_MAX)-(heightUp+DISTANCE_MIN))+(heightUp+DISTANCE_MIN);
        //System.out.println(startHeightDown-heightUp);
        down= new Pipe(color,x,startHeightDown, width,900);
        
        int distance=dis.nextInt((340-280)+1)+280;
        x+=distance;
       // System.out.println("--distance: "+distance);
        //setDistance(x);
    }
    
    public void drawPipes(Graphics g){
       up.drawPipe(g);
       down.drawPipe(g);
        
    }
    public Pipe getUp() {
        return up;
    }

    public void setUp(Pipe up) {
        this.up = up;
    }

    public Pipe getDown() {
        return down;
    }

    public void setDown(Pipe down) {
        this.down = down;
    }
    
    
    
}
