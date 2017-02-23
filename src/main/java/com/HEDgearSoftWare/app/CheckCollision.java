package com.HEDgearSoftWare.app;

import java.awt.Graphics;

public class CheckCollision {

  public void checkPanel(GamePanel panel) {
    if (panel.isEnemies){
      for (int i = 0; i < panel.enemyShips.length; i++) {
        checkLaserHitEnemy(panel.enemyShips[i], panel.ship);
        checkPlayerShipHitEnemy(panel.enemyShips[i], panel.ship);
      }
    }
  }

  private void checkPlayerShipHitEnemy(EnemyShip enemy, Ship ship) {
    int handicap = -20; //scale to cause collision inside the image size
    if(!enemy.getIsHit()) {
      if
      (
        (enemy.getX() + enemy.getShipWidth() + handicap >= ship.getX()
        && enemy.getX() <= ship.getX() + ship.getShipWidth() + handicap)
        &&
        (enemy.getY() + enemy.getShipHeight() + handicap >= ship.getY()
        && enemy.getY() < ship.getY() + ship.getShipHeight() + handicap)
      )
      {
      enemy.hit();
      ship.hit();
      }
    }
  }

  private void checkLaserHitEnemy(EnemyShip enemy, Ship ship) {
    if(!enemy.getIsHit()){
      if(ship.checkLaserHit(enemy.getX(),
          (enemy.getX()
          + enemy.getShipWidth()),
          enemy.getY(),
          (enemy.getY() + enemy.getShipHeight())))
        enemy.hit();
    }
  }

}
