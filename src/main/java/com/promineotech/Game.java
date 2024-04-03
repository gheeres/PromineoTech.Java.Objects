package com.promineotech;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel
{
  public static final int width = 800;
  public static final int height = 600;
  public static final int defaultSpriteSize = 32;
  List<Sprite> sprites = new ArrayList<>();
  Random random = new Random();
  
  void start() {
    
    
    while(true) {
      try {
        for(Sprite sprite : sprites) {
          if (sprite.getClass() == MoveableSprite.class) {
            ((MoveableSprite) sprite).move();
          }
        }
        
        // render
        repaint();
        
        Thread.sleep(10);
      } catch (InterruptedException e) {
      }
    }
  }
  
  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("My Game");
    frame.setLayout(new BorderLayout());
    frame.setSize(width, height);
    frame.setResizable(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Game game = new Game();
    frame.getContentPane().add(game, BorderLayout.CENTER);
    frame.setVisible(true);
    game.start();
  }
  
  @Override
  public void paint(Graphics g) {
    //super.paint(g);
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());

    for(Sprite sprite : sprites) {
      sprite.render(g);
    }
  }  
}
