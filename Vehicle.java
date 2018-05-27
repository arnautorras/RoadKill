import java.awt.*;//must be imported to use Graphics and Color
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


import java.awt.Graphics;
import java.awt.Image;


import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Vehicle extends JFrame
{
    private int x;                  // x position
    private int y;                  // y position
    private int v;                 // x velocity
    private boolean hit;
    public static final int V_LENGTH = 100;
    public static final int V_WIDTH = 50;
    Image car;
    Image carL;
    Image hitCar;
    Image hitCarL;
    
    public Vehicle( int xLoc, int yLoc, boolean goingRight )
    {
        x = xLoc;
        y = yLoc;
        v = 7;
        hit = false;
        ImageIcon iih = new ImageIcon("img/Car.png");
        car = iih.getImage();
        iih = new ImageIcon("img/CarL.png");
        carL = iih.getImage();
        iih = new ImageIcon("img/HitCar.png");
        hitCar = iih.getImage();
        iih = new ImageIcon("img/HitCarL.png");
        hitCarL = iih.getImage();
        if(!goingRight){
            v*=-1;
            //turn();
        }
    }
    
    public void move(){
        x += v;
        if (x >= 1100){
            x = -V_LENGTH;
            hit = false;
        }
        else if(x <= -100){
            x = 1000 + V_LENGTH;
            hit = false;
        }
    }
    
    public void reset(boolean goingRight){
        v = 7;
        if(!goingRight){
            v*=-1;
        }
        hit = false;
    }
    
    
    public void draw( Graphics page )
    {
        if (hit){
            drawHitCar(page);
        }else{
            drawCar(page);
        }
    }
    
    public void drawCar(Graphics page){
        if(v>=0){
            page.drawImage(car,x,y,this);
        }
        else{
            page.drawImage(carL,x,y,this);
        }
    }
    
    public void drawHitCar(Graphics page){
        if(v>=0){
            page.drawImage(hitCar,x,y,this);
        }
        else{
            page.drawImage(hitCarL,x,y,this);
        }
    }
    
    
    public void speedUp(int speed, boolean change){
        //car.setToRotation(Math.toRadians(0));
        //hitCar.setToRotation(Math.toRadians(0));
        if(v<0){
            v-=speed;
        }
        else{
            v+=speed;
        }
        if(change){
            v*=-1;
            //();
        }
    }
    
    public boolean touches(int xVal, int yVal, boolean gameOver){
        if ((xVal  - x) > -50 && (xVal - x) < 100){
            if (!gameOver){
                hit = true;
            }
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean isHit(){
        return hit;
    }
}