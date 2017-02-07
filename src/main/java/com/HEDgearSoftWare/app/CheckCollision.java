package com.HEDgearSoftWare.app;

import java.awt.Graphics;

public class CheckCollision {


  public void checkPanel(GamePanel panel) {
    if (panel.isEnemies)
      for (int i = 0; i < panel.enemyShips.length; i++) {
        if(!panel.enemyShips[i].getIsHit()){
          boolean hit = panel.ship.checkLaserHit(panel.enemyShips[i].getX(),
              (panel.enemyShips[i].getX()
              + panel.enemyShips[i].getShipWidth()),
              panel.enemyShips[i].getY(),
              (panel.enemyShips[i].getY() + panel.enemyShips[i].getShipHeight()));
          if(hit) {
            panel.enemyShips[i].hit();
          }
        }
      }
  }
}
