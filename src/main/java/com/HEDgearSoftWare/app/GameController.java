package com.HEDgearSoftWare.app;


public class GameController {

  private GamePanel gamePanel;
  private GameListener gameListener;
  private CheckCollision checkCollision;
  private String input;

  GameController(GamePanel gp){
    gamePanel = gp;
    gameListener = new GameListener(gp);
    checkCollision = new CheckCollision();
    runGame();
  }

  // the game loop   +===}========>
  private void runGame(){
    while(true){

      gamePanel.moveStars(gameListener.getInput());
      gamePanel.repaint();
      checkCollision.checkPanel(gamePanel);
      try{ Thread.sleep(15); }
      catch(InterruptedException exc) {
        System.out.println("Error: " + exc);
      }
    }
  }

}
