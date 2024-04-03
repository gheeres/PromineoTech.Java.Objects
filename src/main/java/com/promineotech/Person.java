package com.promineotech;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents a player in the game.
 */
public class Person extends MoveableSprite {
  private static List<String> availableIcons = new ArrayList<>(
    List.of("icons8-person-96.png", "icons8-person-artist-96.png", "icons8-person-astronaut-96.png", 
            "icons8-person-factory-worker-96.png", "icons8-person-farmer-96.png", "icons8-person-firefighter-96.png", 
            "icons8-person-health-worker-96.png", "icons8-person-mechanic-96.png", "icons8-person-pilot-96.png",
            "icons8-person-scientist-96.png", "icons8-person-singer-96.png", "icons8-person-student-96.png",
            "icons8-woman-farmer-96.png", "icons8-older-person-96.png")
  );
  
  private String name;
  private Queue<Weapon> weapons = new LinkedList<Weapon>();
  
  public Person(String name) {
    this(name, getRandomIcon());
    this.name = name;
  }
  
  public Person(String name, String iconName) {
    super(iconName);
    this.name = name;
  }  
  
  public Person(String name, String iconName, int x, int y, int speed, int direction)
  {
    super(iconName, x, y, speed, direction);
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  /**
   * Picks up the specified weapon and adds to inventory.
   */
  public void pickup(Weapon weapon) {
    if (weapon != null) {
      weapons.add(weapon);
      Game.removeSprite(weapon);
    }
  }

  public void fight(Zombie zombie) {
    if (zombie != null) {
      if (weapons.size() > 0) {
        Weapon weapon = weapons.poll();
        if (weapon != null) {
          zombie.kill(weapon);
        }
      }
      zombie.infect(this);
    }
  }

  /**
   * Interacts with the specified item.
   * @param {Sprite} thing 
   * @return The collection of changes for sprites, if any.
   */
  @Override
  public void interactWith(Sprite thing) {
    if (thing != null) {
      // Pickup
      if (thing instanceof Weapon) {
        this.pickup((Weapon) thing);
      }
      // Fight!
      else if (thing instanceof Zombie) {
        this.fight((Zombie) thing);
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
      return ((thing instanceof Zombie) ||
              (thing instanceof Weapon));
    }
    return false;
  }

  @Override
  public String toString() {
    if (weapons.size() > 0) {
      StringBuilder weaponCollection = new StringBuilder();
      for(Weapon weapon: weapons) {
        if (weaponCollection.length() > 0) {
          weaponCollection.append(",");
        }
        weaponCollection.append(weapon.toString());
      }
      return String.format("%s Weapons(%d):[ %s ]; x: %d, y: %d", 
                           name, weapons.size(), weaponCollection, x, y);
    }
    return String.format("%s; x: %d, y: %d", name, x, y);
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
