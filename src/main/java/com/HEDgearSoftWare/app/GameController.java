package com.HEDgearSoftWare.app;


public class GameController {

  private GamePanel gamePanel;

  GameController(GamePanel gamePanel){
    this.gamePanel = gamePanel;
    runGame();
  }

  private void runGame(){

    while(true){
        gamePanel.moveStars();
        gamePanel.repaint();
        try{ Thread.sleep(5); }
        catch(InterruptedException exc) {
          System.out.println("Error: " + exc);
        }
    }

  }

}
