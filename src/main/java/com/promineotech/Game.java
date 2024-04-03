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
  private static Random random = new Random();
  private static final int defaultCanvasWidth = 800;
  private static final int defaultCanvasHeight = 600;
  private static final int defaultSpriteSize = 32;

  List<Sprite> sprites = new ArrayList<>();
  
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
    frame.setSize(getDefaultCanvasWidth(), getDefaultCanvasHeight());
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
    g.setColor(Color.GRAY);
    g.drawRect(0, 0, getDefaultCanvasWidth(), getDefaultCanvasHeight());

    for(Sprite sprite : sprites) {
      sprite.render(g);
    }
  }

  /**
   * Retrieve the default sprite size for the game.
   * @return The default sprite size.
   */
  public static int getDefaultSpriteSize() {
    return defaultSpriteSize;
  }

  /**
   * Retrieve the default canvas width for the application.
   * @return The default width of the canvas.
   */
  public static int getDefaultCanvasWidth() {
    return defaultCanvasWidth;
  }

  /**
   * Retrieve the default canvas width for the application.
   * @return The default height of the canvas.
   */
  public static int getDefaultCanvasHeight() {
    return defaultCanvasHeight;
  }
  
  /**
   * Returns the next pseudorandom, uniformly distributed int value from this random number generator's sequence. 
   * The general contract of nextInt is that one int value is pseudorandomly generated and returned. 
   * All 232 possible int values are produced with (approximately) equal probability.
   * @return the next pseudorandom, uniformly distributed int value from this random number generator's sequence
   */
  public static int getRandomInt() {
    return random.nextInt();
  }  

  /**
   * Returns a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive), 
   * drawn from this random number generator's sequence. The general contract of nextInt is that one int 
   * value in the specified range is pseudorandomly generated and returned. All bound possible int values are 
   * produced with (approximately) equal probability.
   * @param range The upper bound (exclusive). Must be positive.
   * @return the next pseudorandom, uniformly distributed int value between zero (inclusive) and bound (exclusive) from this random number generator's sequence
   */
  public static int getRandomInt(int bound) {
    return random.nextInt(bound);
  }  
}
