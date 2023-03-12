/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brick_breaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;






/**
 *
 * @author shabaka computers
 */

public class GamePlay extends JPanel implements KeyListener,ActionListener {

    private boolean play=false;
    private int score=0;
    private int totalBricks=21;
    private Timer time;
    private int delay=8;
    
    private int playerX=310;
    private int ballPosX=120;
    private int ballPosY=350;
    
    private int ballXDir=-1;
    private int ballYDir=-3;
    
    private MapGenerator mapObj;
    
    public GamePlay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        mapObj=new MapGenerator(3,7);
        time=new Timer(delay,this);
        time.start();
        
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        mapObj.draw((Graphics2D) g);
        
       
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif",Font.BOLD,25));
         g.drawString("SCORE "+score,540,30);
        
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(683, 0, 3, 592);
        
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550, 100, 8);
        
        g.setColor(Color.YELLOW);
        g.fillOval(ballPosX, ballPosY, 20, 20);
        
        if(ballPosY>570)
        {
            play=false;
            
             g.setColor(Color.WHITE);
            g.drawRect(200, 250, 300, 100);
            g.setFont(new Font("serif",Font.BOLD,40));
             g.setColor(Color.RED);
            g.drawString("GAME OVER", 220, 290);
            g.setColor(Color.WHITE);
            g.drawString("SCORE: "+score, 250, 330);
            
            g.setFont(new Font("serif",Font.PLAIN,25));
            g.drawString("Press ENTER to restart", 220, 390);
            
            
           
        }
        
        if(totalBricks==0)
        {
            play=false;
             g.setColor(Color.YELLOW);
            g.drawRect(200, 250, 300, 100);
            g.setFont(new Font("serif",Font.BOLD,40));
             g.setColor(Color.GREEN);
            g.drawString("YOU WON", 250, 290);
            g.setColor(Color.WHITE);
            g.drawString("SCORE: "+score, 250, 330);
            
            g.setFont(new Font("serif",Font.PLAIN,25));
            g.drawString("Press ENTER to restart", 220, 390);
        }
        
        //g.dispose();
        
        
        
        
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
      
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
           if(playerX>=580)
                playerX=580;
            else
                moveRight();
        }
       if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(playerX<18)
                playerX=18;
            else
                moveLeft();
        }
       
       if(e.getKeyCode() == KeyEvent.VK_ENTER)
       {
           if(!play)
           {
                play=true;
                playerX=310;
                ballPosX=120;
                ballPosY=350;
                ballXDir=-1;
                ballYDir=-3;
                mapObj=new MapGenerator(3,7);
                score=0;
                totalBricks=21;
                
                repaint();
                
                
           }
       }
    }
    public void moveRight()
    {
        play=true;
        playerX+=20;
    }
    
    public void moveLeft()
    {
        play=true;
        playerX-=20;
    }
    
   

    @Override
    public void keyReleased(KeyEvent e) {
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
       
       if(play)
       {
           if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,100,8)))
               ballYDir=-ballYDir;
           
         A: 
       for(int i=0;i<mapObj.map.length;i++)
            {
               for(int j=0;j<mapObj.map[0].length;j++)
               {
                   if(mapObj.map[i][j]>0)
                   {
                        int brickX=j*mapObj.brickWidth+80;
                        int brickY=i*mapObj.brickHeight+50;
                        int brickWidth=mapObj.brickWidth;
                        int brickHeight=mapObj.brickHeight;
                      
                         
                         Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                         Rectangle ballRect=new Rectangle(ballPosX,ballPosY,20,20);
                         Rectangle brickRect=rect;
                         
                    
                        
                        if(ballRect.intersects(brickRect))
                         {
                             mapObj.setBrickValue(0,i,j);
                             totalBricks--;
                             score+=5;
                             
                             if(ballPosX+19<=brickRect.x||ballPosX+1>=brickRect.x+brickRect.width)
                                 ballXDir=-ballXDir;
                             else
                                 ballYDir=-ballYDir;
                             
                             
                             break A;
                             
                             
                         }
                  }  
               }
           }
          
           
           
           ballPosX+=ballXDir;
           ballPosY+=ballYDir;
           
           if(ballPosX<0)
               ballXDir=-ballXDir;
           if(ballPosX>668)
               ballXDir=-ballXDir;
           if(ballPosY<0)
               ballYDir=-ballYDir;
           
       }
       repaint();
       
      
    }

    
   

    
    
   
   
    
    
}
