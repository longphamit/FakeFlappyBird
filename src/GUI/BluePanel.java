/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Bird;
import Entity.Pipe;
import Entity.Pipe_Double;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class BluePanel extends javax.swing.JPanel {

    /**
     * Creates new form BluePanel
     */
    static int WIDTH;
    static int HEIGHT;
    static final int NUM_REC=4;
    Vector<Pipe_Double> pipes;
    Graphics g;
    boolean isPlaying=false;
    Bird bird;
    Thread t1;
    Thread tCheck;
    Thread tBird;
    Random r= new Random(System.currentTimeMillis());
    ControlPanel controlPanel;
    public BluePanel(ControlPanel controlPanel) {
        this.controlPanel=controlPanel;
        this.setSize(1500,1500);
        WIDTH=this.getWidth();
        HEIGHT=this.getHeight();
        init();
        createBird();
        initComponents();
        startGame(this.isPlaying);
        
        
    }
    public void startGame(boolean isPlaying){
        if(isPlaying==true){
            Pipe_Double.resetStart();
            init();
            controlPanel.setPoint(0);
            createBird();
            thread();
            t1.start();
            tBird.start();
            tCheck.start();
        }
    }
    public void init(){
        pipes= new  Vector<>();
        for(int i=0;i<5;i++){
            Pipe_Double pipe_Double= new Pipe_Double(Color.GREEN,50,HEIGHT,r);
            pipes.add(pipe_Double);
        }
//        pipes[0] =new Pipe(Color.GREEN,200,0,100,150);
//        pipes[1] =new Pipe(Color.GREEN,((WIDTH+200)/2),0,100,150);
//        pipes[2] =new Pipe(Color.GREEN,WIDTH,0,100,150);
//        pipes[3] =new Pipe(Color.GREEN,200,450,100,600);
//        pipes[4] =new Pipe(Color.GREEN,((WIDTH+200)/2),450,100,600);
//        pipes[5] =new Pipe(Color.GREEN,WIDTH,450,100,600);
        
    }
    public void createBird(){
        bird= new Bird("imageBird2.png",100,300,50,50);
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        WIDTH=this.getWidth();
        HEIGHT=this.getHeight();
        super.paintComponent(g);
        this.setBackground(new Color(51,204,255));//To change body of generated methods, choose Tools | Templates.
        drawPipes(g);//To change body of generated methods, choose Tools | Templates.
        drawBird(g);
    }
    public void drawBird(Graphics g){
        bird.DrawBird(g);
    }
   
    public void drawPipes(Graphics g){
        Iterator<Pipe_Double> iter=pipes.iterator();
        while(iter.hasNext()){
            Pipe_Double p=iter.next();
            p.drawPipes(g);
        }
    }
    // tao thread de dich chuyen cac pipe ve ben trai
    
    private void thread(){
        t1= new Thread(new Runnable() {
        @Override
        public void run() {
            while(isPlaying){
                for(int i=0;i<pipes.size();i++){
                    pipes.get(i).getUp().x=pipes.get(i).getUp().x-1;
                    pipes.get(i).getDown().x=pipes.get(i).getDown().x-1;
                    
                }
                repaint();// ham nay goi ham paint trong ham paint co ham ve lai==> ve lai theo x moi
                // neu pipe dau tien ben trai sat man hinh thi ve lai
                
                for (ListIterator <Pipe_Double> iterator = pipes.listIterator(); iterator.hasNext(); ) {
                Pipe_Double p = iterator.next();
                    if(p.getUp().x+p.getUp().width==0){
                        Pipe_Double pN= new Pipe_Double(Color.GREEN,50, HEIGHT, r);
                        //p.setDistance(50);
                        pN.getUp().x=WIDTH;
                        pN.getDown().x=WIDTH;
                        
                        iterator.remove();
                        iterator.add(pN);
                        
                    }
                    if(p.getUp().intersects(bird)||p.getDown().intersects(bird)){
                       
                        isPlaying=false;  
                    }
                    if(bird.x==p.getUp().x+p.getUp().width){
                        controlPanel.setPoint(controlPanel.getPoint()+1);
                    }
                }
//                for(int i=0;i<pipes.size();i++){
//                    if(pipes.get(i).getUp().x+pipes.get(i).getUp().width==0){
//                        Pipe_Double p= new Pipe_Double(Color.GREEN,50, HEIGHT, r);
//                        //p.setDistance(50);
//                        p.getUp().x=WIDTH;
//                        p.getDown().x=WIDTH;
//                        pipes.add(p);
//                        pipes.remove(i);
//                        
//                    }
//                    if(pipes.get(i).getUp().intersects(bird)||pipes.get(i).getDown().intersects(bird)){
//                       
//                        isPlaying=false;  
//                    }
//                    if(bird.x==pipes.get(i).getUp().x+pipes.get(i).getUp().width){
//                        controlPanel.setPoint(controlPanel.getPoint()+1);
//                    }
//                }
                try{
                    Thread.sleep(5);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(bird.y<0||bird.y>HEIGHT){
                    isPlaying=false;
                }
            }
        }
    });
        tCheck= new Thread(new Runnable() {
        boolean check=true;
        @Override
        
        public void run() {
            while(check){
                if(isPlaying==false){
                    check=false;
                    JOptionPane.showMessageDialog(null,"Lose");
                    break;
                }
                
                try{
                    Thread.sleep(1);
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        }
    });
    tBird= new Thread(new Runnable() {
        @Override
        public void run() {
            while(isPlaying){
                bird.y=bird.y+2;
                try{
                    Thread.sleep(9);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_UP&&isPlaying==true){
            bird.y=bird.y-60;
        }
    }//GEN-LAST:event_formKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
