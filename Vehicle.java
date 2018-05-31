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
    Image car1,car2,car3,carL1,carL2,carL3;
    Image hitCar1,hitCar2,hitCar3,hitCarL1,hitCarL2,hitCarL3;
    int carType;
    
    public Vehicle( int xLoc, int yLoc, boolean goingRight )
    {
        x = xLoc;
        y = yLoc;
        v = 7;
        hit = false;
        getNewColor();
        assignImages();
        if(!goingRight){
            v*=-1;
        }
    }
    
    public void assignImages(){
        ImageIcon iih = new ImageIcon("img/Car1.png");
        car1 = iih.getImage();
        iih = new ImageIcon("img/CarL1.png");
        carL1 = iih.getImage();
        iih = new ImageIcon("img/HitCar1.png");
        hitCar1 = iih.getImage();
        iih = new ImageIcon("img/HitCarL1.png");
        hitCarL1 = iih.getImage();
        iih = new ImageIcon("img/Car2.png");
        car2 = iih.getImage();
        iih = new ImageIcon("img/CarL2.png");
        carL2 = iih.getImage();
        iih = new ImageIcon("img/HitCar2.png");
        hitCar2 = iih.getImage();
        iih = new ImageIcon("img/HitCarL2.png");
        hitCarL2 = iih.getImage();
        iih = new ImageIcon("img/Car3.png");
        car3 = iih.getImage();
        iih = new ImageIcon("img/CarL3.png");
        carL3 = iih.getImage();
        iih = new ImageIcon("img/HitCar3.png");
        hitCar3 = iih.getImage();
        iih = new ImageIcon("img/HitCarL3.png");
        hitCarL3 = iih.getImage();
    }
    
    
    public void getNewColor(){
        carType = (int)(Math.random()*3)+1;
    }
    
    public void move(){
        x += v;
        if (x >= 1100){
            x = -V_LENGTH;
            hit = false;
            getNewColor();
        }
        else if(x <= -100){
            x = 1000 + V_LENGTH;
            hit = false;
            getNewColor();
        }
    }
    
    public void reset(boolean goingRight){
        v = 7;
        if(!goingRight){
            v*=-1;
        }
        hit = false;
        getNewColor();
    }
    
    public void slowDown(){
        v = (int)(v * 0.6);
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
        if(carType == 1){
            if(v>=0){
                page.drawImage(car1,x,y,this);
            }
            else{
                page.drawImage(carL1,x,y,this);
            }
        }
        else if(carType == 2){
            if(v>=0){
                page.drawImage(car2,x,y,this);
            }
            else{
                page.drawImage(carL2,x,y,this);
            }
        }
        else{
            if(v>=0){
                page.drawImage(car3,x,y,this);
            }
            else{
                page.drawImage(carL3,x,y,this);
            }
        }
    }
    
    public void drawHitCar(Graphics page){
        if(carType == 1){
            if(v>=0){
                page.drawImage(hitCar1,x,y,this);
            }
            else{
                page.drawImage(hitCarL1,x,y,this);
            }
        }
        else if(carType == 2){
            if(v>=0){
                page.drawImage(hitCar2,x,y,this);
            }
            else{
                page.drawImage(hitCarL2,x,y,this);
            }
        }
        else{
            if(v>=0){
                page.drawImage(hitCar3,x,y,this);
            }
            else{
                page.drawImage(hitCarL3,x,y,this);
            }
        }
    }
    
    
    public void speedUp(int speed, boolean change){

        if(v<0){
            v-=speed;
        }
        else{
            v+=speed;
        }
        if(change){
            v*=-1;
        }
        //getNewColor();
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
    
    public int getSpeed(){
        return v;
    }
    
    public boolean isHit(){
        return hit;
    }
}