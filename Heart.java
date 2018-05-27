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

public class Heart extends JPanel
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private Image full;
    private Image empty;
    private boolean isEmpty;

    public Heart(int xVal, int yVal)
    {
        x = xVal;
        y = yVal;
        ImageIcon iih = new ImageIcon("img/Heart.png");
        full = iih.getImage();
        iih = new ImageIcon("img/EmptyHeart.png");
        empty = iih.getImage();
        isEmpty = false;
    }

    public void draw(Graphics page)
    {
        if(isEmpty){
            page.drawImage(empty,x,y,this);
        }
        else{
            page.drawImage(full,x,y,this);
        }
    }
    
    public void fill(){
        isEmpty = false;
    }
    
    public void empty(){
        isEmpty = true;
    }
    
    public boolean isEmpty(){
        return isEmpty;
    }
}
