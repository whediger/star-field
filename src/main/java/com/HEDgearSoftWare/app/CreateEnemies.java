package com.HEDgearSoftWare.app;

class CreateEnemies implements Runnable {

  private EnemyShip[] enemyShips = new EnemyShip[9];

  public void run() {
    int marginL = 250;
    for (int i = 0; i < 9; i++) {
      int xspot;
      int yspot;
      if (i < 5) {
        if(i == 0) xspot = marginL;
        else xspot = (marginL + (100 * i));
        yspot = 100;
      } else {
        xspot = (marginL + 50 + ((i - 5) * 100));
        yspot = 200;
      }
      enemyShips[i] = new EnemyShip(xspot, yspot);
    }
  }

  public EnemyShip[] getShips() {
    return enemyShips;
  }
}
