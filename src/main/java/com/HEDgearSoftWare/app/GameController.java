package com.HEDgearSoftWare.app;


public class GameController {

  private GamePanel gamePanel;
  private GameListener gameListener;

  GameController(GamePanel gp){
    gamePanel = gp;
    gameListener = new GameListener(gp);
    runGame();
  }

  private void runGame(){

    while(true){

        gamePanel.moveStars();
        gamePanel.repaint();
        try{ Thread.sleep(15); }
        catch(InterruptedException exc) {
          System.out.println("Error: " + exc);
        }
    }

  }

}
