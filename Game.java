import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

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

public class Game extends JPanel implements KeyListener
{
    private final int WIDTH;
    private final int HEIGHT;
    private Player player;
    
    private Road [] roads;
    
    private Obstacle [] obstacles;
    
    private Heart[] hearts;
    
    private boolean gameOver;
    
    private Image grass;
    
    //constructor - sets the initial conditions for this Game object
    public Game(int width, int height)
    {
        //make a panel with dimensions width by height with a black background
        WIDTH = width;
        HEIGHT = height;
        ImageIcon iih = new ImageIcon("img/Grass.png");
        grass = iih.getImage();
        gameOver = false;
        roads = new Road[3];
        obstacles = new Obstacle[40];
        hearts = new Heart[3];
        this.setLayout( null );//Don't change
        this.setBackground( Color.GREEN.darker() );
        this.setPreferredSize( new Dimension(width, height) );//Don't change
        player = new Player();
        //initialize the instance variables
        int loc = 100;
        for(int i = 0; i < 3; i++){
            roads[i] = new Road (loc ); //change these numbers and see what happens
            loc+=250;
        }
        loc = 800;
        for(int i = 0; i < 3; i++){
            hearts[i] = new Heart(loc, 0);
            loc+=50;
        }
        
        for(int i = 0; i < obstacles.length; i++){
            obstacles[i] = new Obstacle(1010,1010); //Extras are left outside of screen
        }
        obstacles = mixedUp(obstacles);
        this.addKeyListener(this);//allows the program to respond to key presses - Don't change

        this.setFocusable(true);
        
        
    }
    
    public Obstacle[] mixedUp(Obstacle[] list){
        int count = 0;
        int y = 50;
        while(y < 700){
            if(!onRoad(y)){
                for(int i = 0; i < 4; i++){
                    int x = (int)(Math.random()*20)*50;
                    if(!occupied(x,y)){
                        list[count] = new Obstacle(x,y);
                    }
                    count++;
                }
            }
            y += 50;
        }
        return list;
    }

    //This is the method that runs the game
    public void playGame(){
        boolean done = false;
        while( !done )
        {
            moveCars();
            try
            {
                Thread.sleep( 25 );//pause for 25 milliseconds
            }catch( InterruptedException ex ){}
            this.repaint();//redraw the screen with the updated locations; calls paintComponent below
            if (player.getY() <= -50){
                levelUp();
            }
        }
    }
    
    public void newGame(){
        obstacles = mixedUp(obstacles);
        player.reset();
        for(int j = 0; j < 3; j++){
            roads[j].resetCars();
            roads[j].getCoinHeartOrClock();
            hearts[j].fill();
        }
        gameOver = false;
    }
    
    public void levelUp(){
        player.resetLocation();
        player.levelUp();
        for(int i = 0; i < 3; i++){
            roads[i].speedUpCars(); //change these numbers and see what happens
            roads[i].getCoinHeartOrClock();
        }
        obstacles = mixedUp(obstacles);
    }
    
    public void moveCars(){
        for(int j = 0; j < 3; j++){
            roads[j].moveCars();
        }
        checkIfKilled();
    }

    //Precondition: executed when repaint() or paintImmediately is called
    //Postcondition: the screen has been updated with current player location
    public void paintComponent( Graphics page )
    {
        super.paintComponent( page );
        drawGrass(page);
        //Detemines which layers are on top of others
        for(int j = 0; j < obstacles.length; j++){
            obstacles[j].draw(page);
        }
        for(int j = 0; j < 3; j++){
            roads[j].draw( page );
        }
        player.draw(page);
        for(int j = 0; j < 3; j++){
            roads[j].drawCoin( page );
            roads[j].drawHeart( page );
            roads[j].drawClock( page );
        }
        for(int j = 0; j < 3; j++){
            roads[j].drawCars(page);
        }
        for(int j = 0; j < 3; j++){
            hearts[j].draw(page);
        }
    }
    
    public void drawGrass(Graphics page){
        for(int y = 0; y < HEIGHT; y+=50){
            for(int x = 0; x < WIDTH; x+=50){
                page.drawImage(grass,x,y,this);
            }
        }
    }

    //not used but must be present
    public void keyReleased( KeyEvent event )
    {  
    }
    
    //tells the program what to do when keys are pressed
    public void keyPressed( KeyEvent event )
    {
        if(!gameOver){
            if( event.getKeyCode() == KeyEvent.VK_RIGHT )
            {
                if(!occupied(player.getX() + 50, player.getY())){
                    player.moveRight();
                }
            }
            else if( event.getKeyCode() == KeyEvent.VK_LEFT )
            {
                if(!occupied(player.getX() - 50, player.getY())){
                    player.moveLeft();
                }
            }
            else if( event.getKeyCode() == KeyEvent.VK_UP )
            {
                if(!occupied(player.getX(), player.getY() - 50)){
                    player.moveDown();
                    player.addPoints(1);
                }
            }
            else if( event.getKeyCode() == KeyEvent.VK_DOWN )
            {
                if(player.getY() < 750 ){
                    if(!occupied(player.getX(), player.getY() + 50)){
                            player.moveUp();
                            player.addPoints(-1);
                    }
                }
            }
            if(coinPresent(player.getX(), player.getY())){
                player.addPoints(3);
            }
            else if(heartPresent(player.getX(), player.getY())){
                player.addLives(1);
                addHeart();
            }
            else if(clockPresent(player.getX(), player.getY())){
                slowDownCars();
            }
        }
        else{
            if( event.getKeyCode() == KeyEvent.VK_ENTER ){
                newGame();
            }
        }
    }
    
    public void slowDownCars(){
        
    }
    
    public boolean coinPresent(int x, int y){
        for(int j = 0; j < 3; j++){
            if(roads[j].hasCoin()){
                if(roads[j].getCoinX() == x && roads[j].getCoinY() == y){
                    roads[j].removeCoin();
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean heartPresent(int x, int y){
        for(int j = 0; j < 3; j++){
            if(roads[j].hasHeart()){
                if(roads[j].getHeartX() == x && roads[j].getHeartY() == y){
                    roads[j].removeHeart();
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean clockPresent(int x, int y){
        for(int j = 0; j < 3; j++){
            if(roads[j].hasClock()){
                if(roads[j].getClockX() == x && roads[j].getClockY() == y){
                    roads[j].removeClock();
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean occupied(int x, int y){
        for(int i = 0; i < obstacles.length; i++){
            if (obstacles[i].getX() == x && obstacles[i].getY() == y){
                return true;
            }
        }
        return false;
    }
    
    public void checkIfKilled(){
        int py = player.getY();
        if(py == 100){
            if(roads[0].touchesCar(player.getX(),py, gameOver)){
                if (!gameOver){
                    subtractLife();
                    player.resetLocation();
                }
            }
        }
        else if(py == 350){
            if(roads[1].touchesCar(player.getX(),py, gameOver)){
                if (!gameOver){
                    subtractLife();
                    player.resetLocation();
                }
            }
        }
        else if (py == 600){
            if(roads[2].touchesCar(player.getX(),py, gameOver)){
                if (!gameOver){
                    subtractLife();
                    player.resetLocation();
                }
            }
        }
        if (player.getLives() == 0){
            endGame();
        }
    }
    
    public void subtractLife(){
        player.subtractLife();
        int count = 2;
        while(count >= 0){
            if(!hearts[count].isEmpty()){
                hearts[count].empty();
                count = -1;
            }
            count--;
        }
    }
    
    public void addHeart(){
        int count = 0;
        while(count < 3){
            if(hearts[count].isEmpty()){
                hearts[count].fill();
                count = 4;
            }
            count++;
        }
    }
    
    public boolean onRoad(int y){
        if(y == 100 || y == 350 || y == 600){
            return true;
        }
        return false;
    }
    
    public void endGame(){
        gameOver = true;
        player.die();
    }
    //not used but must be present
    public void keyTyped( KeyEvent event )
    {
    }
}