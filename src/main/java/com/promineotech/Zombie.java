package com.promineotech;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a flesh eating monster than can infect / convert others.
 */
public class Zombie extends Person {
  private static List<String> availableIcons = new ArrayList<>(
    List.of("icons8-zombie-96.png")
  );

  public Zombie(String name) {
    super(name, getRandomIcon());
    this.setSpeed(Math.max(5, this.getSpeed() / 2));
  }

  public Zombie(String name, String iconName, int x, int y, int speed, int direction)
  {
    super(name, iconName, x, y, speed, direction);
  }
  
  /**
   * Kills the zombie. Leaving the corpse being which can still infect other
   * players.
   * @param weapon The weapon that was used to kill the zombie.
   */
  public void kill(Weapon weapon) {
    this.setSpeed(0);
  }  

  /**
   * Attempts to infect the specified person.
   * @param {Person} person The person being infected.
   * @returns {Boolean} true if infected, false if otherwise.
   */
  public void infect(Person person) {
    Zombie zombie = new Zombie(person.getName(), this.getIcon(), person.x, person.y, 
                               Game.getRandomInt(person.getSpeed() / 2),
                               Game.getRandomInt(360));
    System.out.printf("INFECTED! %s%n", zombie.toString());
    Game.removeSprite(person);
    Game.addSprite(zombie);    
  }
  
  /**
   * Interacts with the specified item.
   * @param {Sprite} thing 
   */
  public void interactWith(Sprite thing) {
    if (thing != null) {
      if ((! (thing instanceof Zombie)) && 
          (thing instanceof Person)) {
        ((Person) thing).fight(this);
      }
    }
  }

  /**
   * Checks to see if the specified item can be interacted with.
   * @param {Sprite} thing The thing to check for interaction.
   * @returns {Boolean} True if the object can interact with the thing.
   */
  public boolean canInteractWith(Sprite thing) {
    if (thing != null) {
      return ((! (thing instanceof Zombie)) &&
              (thing instanceof Person));
    }
    return false;
  }
  
  /**
   * Retrieves a random weapon icon.
   * @return The random weapon icon/resource.
   */
  protected static String getRandomIcon() {
    if (availableIcons != null) {
      return availableIcons.get(Game.getRandomInt(availableIcons.size()));
    }
    return null;
  } 
}
