import java.awt.*;//must be imported to use Graphics
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



public class Player extends JPanel
{
    private int x;
    private int y;
    private int points;
    private int maxPoints;
    private int level;
    private int highScore;
    private int lives;
    public static final int STEP = 50;
    public boolean dead;
    public static Font font;
    public static Font font1;
    Image aliveU;
    Image aliveR;
    Image aliveD;
    Image aliveL;
    Image killedU;
    Image killedR;
    Image killedD;
    Image killedL;
    int direction;
    AffineTransform affineTransform;
    private Sound step;
    public Player( )
    {
        x = 500;
        y = 750;
        level = 1;
        dead = false;
        font = new Font("Courier New", Font.BOLD, 35);
        font1 = new Font("Impact", Font.BOLD, 100);
        ImageIcon iih = new ImageIcon("img/RaccoonU.png");
        aliveU = iih.getImage();
        iih = new ImageIcon("img/RaccoonR.png");
        aliveR = iih.getImage();
        iih = new ImageIcon("img/RaccoonD.png");
        aliveD = iih.getImage();
        iih = new ImageIcon("img/RaccoonL.png");
        aliveL = iih.getImage();
        iih = new ImageIcon("img/KilledRaccoonU.png");
        killedU = iih.getImage();
        iih = new ImageIcon("img/KilledRaccoonR.png");
        killedR = iih.getImage();
        iih = new ImageIcon("img/KilledRaccoonD.png");
        killedD = iih.getImage();
        iih = new ImageIcon("img/KilledRaccoonL.png");
        killedL = iih.getImage();
        step = new Sound("sound/Step.wav");
        lives = 3;
        affineTransform = new AffineTransform();
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void moveRight()
    {
        if(x < 1000 - STEP){
            x = x + 50;
        }
        affineTransform.rotate(Math.toRadians(45), x, y);
        step.play();
        direction = 1;
    }
    
    public void moveLeft()
    {
        if(x > 0){
            x = x - 50;
        }
        step.play();
        direction = 3;
    }
    
    public void moveDown(){
        y -= 50;//Special case (in order to beat level)
        step.play();
        direction = 0;
    }
    
    public void moveUp(){
        y += 50; //Special case since it is checked in Game to to counts points
        step.play();
        direction = 2;
    }

    public void draw( Graphics page )
    {
        if(!dead){
            drawRaccoon(page);
            //page.drawImage(alive,x,y,this);
            page.setColor( Color.BLACK );
            page.setFont(font);
            page.drawString(String.format("%03d",maxPoints), 20, 30);
            page.drawString("Level: " + Integer.toString(level), 350, 30);
        }
        else{
            drawDeadRaccoon(page);
            //page.drawImage(killed,x,y,this);
            page.setColor( Color.RED);
            page.setFont(font);
            page.drawString(String.format("%03d",maxPoints), 20, 30);
            page.drawString("Level: " + Integer.toString(level), 350, 30);
            if(lives <= 0){
                page.setFont(font1);
                page.drawString("Game Over", 300, 320);
                page.setFont(font);
                page.setColor(Color.WHITE);
                if(highScore == maxPoints){
                    page.drawString("New High Score: "+ Integer.toString(highScore), 350, 700);
                }
                else{
                    page.drawString("High Score: "+ Integer.toString(highScore), 350, 700);
                    page.drawString("Your Score: "+ Integer.toString(maxPoints), 350, 750);
                }
            }
            page.setColor(Color.WHITE);
            page.setFont(font);
            page.drawString("Press Enter", 400, 350);
            
        }
    }
    
    public void drawRaccoon(Graphics page){
        if(direction == 0){
            page.drawImage(aliveU,x,y,this);
        }
        else if(direction == 1){
            page.drawImage(aliveR,x,y,this);
        }
        else if(direction == 2){
            page.drawImage(aliveD,x,y,this);
        }
        else if(direction == 3){
            page.drawImage(aliveL,x,y,this);
        }
    }
    
    public void drawDeadRaccoon(Graphics page){
        if(direction == 0){
            page.drawImage(killedU,x,y,this);
        }
        else if(direction == 1){
            page.drawImage(killedR,x,y,this);
        }
        else if(direction == 2){
            page.drawImage(killedD,x,y,this);
        }
        else if(direction == 3){
            page.drawImage(killedL,x,y,this);
        }
    }
    
    public void addPoints(int x){
        points+=x;
        if(points>maxPoints){
            maxPoints = points;
            if (maxPoints>highScore){
                highScore = maxPoints;
            }
        }
    }
    public void addLives(int x){
        if(lives < 3){
            lives+=x;
        }
    } 
    public void levelUp(){
        level++;
    }
    public void resetLocation(){
        if(!dead){
            x = 500;
            y = 750;
            direction = 0;
        }
    }
    public void subtractLife(){
        lives--;
        if (lives <= 0){
            dead = true;
        }
        
    }
    
    public void die(){
        dead = true;
    }

    public int getLives(){
        return lives;
    }
    public void reset(){
        dead = false;
        lives = 3;
        resetLocation();
        points = 0;
        maxPoints = 0;
        level = 1;
        direction = 0;
    }
}