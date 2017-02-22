package com.HEDgearSoftWare.app;

import java.awt.Graphics;

public class CheckCollision {

  boolean hit = false;

  public void checkPanel(GamePanel panel) {
    if (panel.isEnemies){
      for (int i = 0; i < panel.enemyShips.length; i++) {
        checkLaserHitEnemy(panel.enemyShips[i], panel.ship);
        if(hit) {
            panel.enemyShips[i].hit();
        }
      }
    }
  }


  private void checkLaserHitEnemy(EnemyShip enemy, Ship ship){
    if(!enemy.getIsHit()){
      hit = ship.checkLaserHit(enemy.getX(),
          (enemy.getX()
          + enemy.getShipWidth()),
          enemy.getY(),
          (enemy.getY() + enemy.getShipHeight()));
    }
  }

}
