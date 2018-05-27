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
public class Clock extends JPanel
{
    private int x;
    private int y;
    private Image clock;
    
    public Clock(int xVal, int yVal){
        x = xVal;
        y = yVal;
        ImageIcon iih = new ImageIcon("img/Clock.png");
        clock = iih.getImage();
    }

    public void draw(Graphics page){
        page.drawImage(clock,x,y,this);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
