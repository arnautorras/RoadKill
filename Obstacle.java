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

public class Obstacle extends JPanel
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private Image bush;
    private Image rock;
    private boolean isBush;
    public Obstacle(int xVal, int yVal)
    {
        x = xVal;
        y = yVal;
        ImageIcon iih = new ImageIcon("img/Bush.png");
        bush = iih.getImage();
        iih = new ImageIcon("img/Rock.png");
        rock = iih.getImage();
        isBush = (int)(Math.random()+0.5)==1;
    }
    
    public void draw( Graphics page )
    {
        if(isBush){
            page.drawImage(bush,x,y,this);
        }
        else{
            page.drawImage(rock,x,y,this);
        }
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
