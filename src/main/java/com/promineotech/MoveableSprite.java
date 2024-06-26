package com.promineotech;

/**
 * Represents a sprite than can move or change position over time.
 */
public class MoveableSprite extends Sprite {
  private int modifierX = 1;
  private int modifierY = 1;
  
  private int speed;
  private int direction;
  
  public MoveableSprite(String iconName) {
    this(iconName, Game.getRandomInt(Game.getDefaultCanvasWidth()), Game.getRandomInt(Game.getDefaultCanvasHeight()));
  }
  
  public MoveableSprite(String iconName, int x, int y) {
    this(iconName, x, y, Math.max(1, Game.getRandomInt(10)), Game.getRandomInt(360));
  }

  public MoveableSprite(String iconName, int x, int y, int speed, int direction) {
    super(iconName, x, y);

    this.speed = speed;
    this.direction = direction;
  }
  
  /**
   * Retrieves the current speed of the sprite. How far it moves in a straight line.
   * @returns The current speed.
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Changes the speed of the specified sprite.
   * @param speed The new speed.
   */
  public void setSpeed(int speed) {
    if (speed >= 0) {
      this.speed = speed;
    }
  }
  
  /**
   * Stops the sprites movement.
   */
  public void stop() {
    setSpeed(0);
  }
  
  /**
   * Changes the direction/angle that the specified sprite is moving.
   * @param direction The new angle / direction (0-360).
   */
  public void setDirection(int direction) {
    if (direction >= 0) {
      this.direction = direction;
    }
  }  
  /**
   * Moves the specified sprite based on speed and direction.
   */
  public void move(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Moves the specified sprite to the new position.
   * @param {Integer} x The optional new X coordinate of the sprite. If not specified, then will move based on speed and direction.
   * @param {Integer} y The optional new y coordinate of the sprite. If not specified, then will move based on speed and direction.
   */
  public void move() {
    double angle = this.direction * (Math.PI / 180);
    x = (int) Math.round((this.speed * this.modifierX) * Math.cos(angle) + this.x);
    y = (int) Math.round((this.speed * this.modifierY) * Math.sin(angle) + this.y);

    // At edge of screen? Bounce back...
    if ((x <= 0) || ((x + Game.getDefaultSpriteSize()) >= Game.getDefaultCanvasWidth())) {
      this.modifierX *= -1;
    }
    if ((y <= 0) || ((y + Game.getDefaultSpriteSize()) >= Game.getDefaultCanvasHeight())) {
      this.modifierY *= -1;
    }
  }    
  
  /**
   * Handles the collision between one or more items.
   * @param {Sprite} sprite The sprite that collided with another sprite.
   * @param {Array.Sprite} collided The collection of sprites collided with.
   */
  public void handleCollision(Iterable<Sprite> collided) {
    if (collided != null) {
      for(Sprite other : collided) {
        if (canInteractWith(other)) {
          interactWith(other);
        }
      }
    }
  }
}
