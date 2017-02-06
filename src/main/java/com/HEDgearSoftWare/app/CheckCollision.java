package com.HEDgearSoftWare.app;

import java.awt.Graphics;

public class CheckCollision {


  public void checkPanel(GamePanel panel) {

    if(!panel.enemyShip.getIsHit()){
      boolean hit = panel.ship.checkLaserHit(panel.enemyShip.getX(),
          (panel.enemyShip.getX()
          + panel.enemyShip.getShipWidth()),
          panel.enemyShip.getY(),
          (panel.enemyShip.getY() + panel.enemyShip.getShipHeight()));
      if(hit) {
        panel.enemyShip.hit((panel.enemyShip.getX() + (panel.enemyShip.getShipWidth()/2)),
                            (panel.enemyShip.getY() + (panel.enemyShip.getShipHeight())/2));
      }
    }
  }
}
