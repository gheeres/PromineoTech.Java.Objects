package com.promineotech;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Sprite {
  protected int x;
  protected int y;
  private int size = 32;
  private String iconName;
  private BufferedImage icon;
  
  public Sprite(String iconName, int x, int y) {
    this.x = x;
    this.y = y;
    if (iconName != null) {
      try {
        this.iconName = iconName;
        icon = ImageIO.read(getClass().getClassLoader().getResource(String.format("images/%s", iconName)));
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }
  
  /**
   * Retrieves the bounding rectangle for the sprite.
   * @returns {Object} The bounding x1,y1;x2,y2 rectangle.
   */
  public Rectangle getBoundingRectangle() {
    return new Rectangle(this.x - (this.size / 2),
                         this.y - (this.size / 2),
                         this.x + (this.size / 2),
                         this.y + (this.size / 2));
  }
  
  /**
   * Checks to see if the currect sprite collides or interects the bounding rectangle.
   * @param {Integer} size The size of the current sprite.
   * @param {Integer} x1 The upper left bounding x coordinate.
   * @param {Integer} y1 The upper left bounding u coordinate.
   * @param {Integer} x2 The lower right bounding x coordinate.
   * @param {Integer} y2 The lower right bounding y coordinate.
   * @returns {Boolean} True if the sprite intersects, false if othewise.
   */
  public boolean intersectsWith(int x1, int y1, int x2, int y2) {
    Rectangle self = this.getBoundingRectangle();
    return ((self.x1 < x2) && (self.x2 > x1) &&
            (self.y1 < y2) && (self.y2 > y1));
  }
  
  /**
   * Gets the collection of sprites and are colliding or intersecting with the current sprite.
   * @param {Array.Sprite} sprites The collection of sprites to inspect.
   * @returns The collection of colliding sprites.
   */
  List<Sprite> getCollided(Sprite[] sprites) {
    Rectangle boundingRectangle = this.getBoundingRectangle();
    
    List<Sprite> collided = new ArrayList<>();
    for(Sprite sprite : sprites) {
      if ((sprite != this) && (sprite != null) &&  
          (sprite.intersectsWith(boundingRectangle.x1, boundingRectangle.y1,
                                 boundingRectangle.x2, boundingRectangle.y2))) {
        collided.add(sprite);
      }
    }
    return collided;
  }
  
  /**
   * A custom method to define what happens when the Sprite interacts with another Sprite.
   * @param thing The Sprite being interacted with.
   */
  public void interactWith(Sprite thing) {
  }
  
  /**
   * Checks to see if the specified item can be interacted with.
   * @param {Sprite} thing The thing to check for interaction.
   * @returns {Boolean} True if the object can interact with the thing.
   */
  public boolean canInteractWith(Sprite thing) {
    return false;
  }

  @Override
  public String toString() {
    return String.format("%s; x: %d, y: %d", iconName, x, y);
  }
  
  /**
   * Draws or renders the sprite to the specified graphics instance.
   * @param graphics The current graphics instance.
   */
  public void render(Graphics graphics) {
    if (icon != null) {
      System.out.println(this.toString());
      graphics.drawImage(icon, x, y, size, size, null);
    } 
  }
}
