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
public class Coin extends JPanel
{
    private int x;
    private int y;
    private Image coin = null;
    public Coin(int xVal, int yVal){
        x = xVal;
        y = yVal;
        ImageIcon iih = new ImageIcon("img/Coin.png");
        coin = iih.getImage();
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    

    public void draw(Graphics page)
    {
        page.drawImage(coin,x,y,this);
    }
}
