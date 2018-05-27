import java.awt.*;
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

import java.awt.geom.AffineTransform;

public class Road extends JPanel
{
    private int y; // y position
    private boolean goingRight = false;
    private Vehicle[] vehicles= new Vehicle[3];
    private Coin coin;
    private Life life;
    private Clock clock;
    private Image road;

    public Road(int yLoc ){
        y = yLoc;
        goingRight = random();
        ImageIcon iih = new ImageIcon("img/Road.png");
        road = iih.getImage();
        int loc = (int)(Math.random()*200)/100*100;
        for(int i = 0; i < 3; i++){
            vehicles[i] = new Vehicle ( loc, y, goingRight); //change these numbers and see what happens
            loc+=380;
        }
        getCoinHeartOrClock();
    }
    
    public void getCoinHeartOrClock(){
        if((int)(Math.random()*5) == 1){
            coin = new Coin((int)(Math.random()*20)*50,y);
        }
        else if((int)(Math.random()*10) == 1){
            life = new Life((int)(Math.random()*20)*50,y);
        }
        else if((int)(Math.random()*1) == 0){
            clock = new Clock((int)(Math.random()*20)*50,y);
        }
    }
    
    public boolean hasCoin(){
        return coin != null;
    }
    
    public boolean hasHeart(){
        return life != null;
    }
    
    public boolean hasClock(){
        return clock != null;
    }
    
    public int getCoinX(){
        return coin.getX();
    }
    
    public int getCoinY(){
        return coin.getY();
    }
    
    public int getHeartX(){
        return life.getX();
    }
    
    public int getHeartY(){
        return life.getY();
    }
    
    public int getClockX(){
        return clock.getX();
    }
    
    public int getClockY(){
        return clock.getY();
    }
    
    public boolean random(){
        return(int)(Math.random()+0.5) == 1;
    }
    
    public void removeCoin(){
        coin = null;
    }
    
    public void removeHeart(){
        life = null;
    }
    
    public void removeClock(){
        clock = null;
    }

    public void drawCars(Graphics page){
        for(int i = 0; i < 3; i++){
            vehicles[i].draw(page);
        }
    }
    
    public void drawCoin(Graphics page){
        if(coin != null){
            coin.draw(page);
        }
    }
    
    public void drawHeart(Graphics page){
        if(life != null){
            life.draw(page);
        }
    }
    
    public void drawClock(Graphics page){
        if(clock != null){
            clock.draw(page);
        }
    }
    
    public boolean touchesCar(int xVal, int yVal, boolean gameOver){
        for(int i = 0; i < 3; i++){
            if (vehicles[i].touches(xVal,yVal, gameOver)){
                return true;
            }
        }
        return false;
    }
    
    public void moveCars(){
        for(int i = 0; i < 3; i++){
            vehicles[i].move();
        }
    }
    
    public void resetCars(){
        boolean goingRight = random();
        for(int i = 0; i < 3; i++){
            vehicles[i].reset(goingRight);
        }
    }
    
    public void speedUpCars(){
        int speed = (int)(Math.random()*4)+1;
        boolean change = random();
        for(int i = 0; i < 3; i++){
            vehicles[i].speedUp(speed, change);
        }
    }
    
    public void draw( Graphics page )
    {
        for(int i = 0; i < 1000; i+=50){
            page.drawImage(road,i,y,this);
        }
        
        
    }
}
