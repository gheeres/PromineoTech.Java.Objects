package com.promineotech;

import java.util.ArrayList;
import java.util.List;

public class Weapon extends Sprite {
  private static List<String> availableWeapons = new ArrayList<>(
    List.of("icons8-badminton-96.png", "icons8-bomb-96.png", "icons8-carpentry-saw-96.png", 
            "icons8-dagger-96.png", "icons8-fire-96.png", "icons8-firecracker-96.png", 
            "icons8-pistol-96.png", "icons8-rock-96.png", "icons8-sword-96.png")
  );
  
  public Weapon() {
    super(getRandomIcon(), Game.getRandomInt(Game.getDefaultCanvasWidth()), Game.getRandomInt(Game.getDefaultCanvasHeight()));
  }

  public Weapon(String iconName) {
    super(iconName, Game.getRandomInt(Game.getDefaultCanvasWidth()), Game.getRandomInt(Game.getDefaultCanvasHeight()));
  }
  
  public Weapon(String iconName, int x, int y) {
    super(iconName, x, y);
  }

  /**
   * Retrieves a random weapon icon.
   * @return The random weapon icon/resource.
   */
  protected static String getRandomIcon() {
    if (availableWeapons != null) {
      return availableWeapons.get(Game.getRandomInt(availableWeapons.size()));
    }
    return null;
  }
}
