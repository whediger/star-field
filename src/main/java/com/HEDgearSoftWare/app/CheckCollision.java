package com.HEDgearSoftWare.app;

import java.awt.Graphics;

public class CheckCollision {


    public void checkPanel(GamePanel panel) {

      boolean hit = false;
      // hit = panel.ship.checkLaserHit(200, 250, 100, 200);
      hit = panel.ship.checkLaserHit(panel.enemyShip.getX(),
          (panel.enemyShip.getX()
          + panel.enemyShip.getShipWidth()),
          panel.enemyShip.getY(),
          (panel.enemyShip.getY() + panel.enemyShip.getShipHeight()));

      if(hit) System.out.println("laser hit enemyShip!!!!!!!!!!!!!!!!!!!!");
    }

}
