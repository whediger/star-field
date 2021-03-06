package com.HEDgearSoftWare.app;



public class GameController {

  private GamePanel gamePanel;
  private GameListener gameListener;
  private CheckCollision checkCollision;
  private String input;

  GameController(GamePanel gp){
    (new Thread(new ThemeMusic())).start();
    gamePanel = gp;
    gameListener = new GameListener(gp);
    checkCollision = new CheckCollision();
    runGame();
  }

  // the game loop   +===}========>
  private void runGame(){
    while(true){
      gamePanel.move(gameListener.getInput());
      gamePanel.moveEnemies();
      gamePanel.repaint();
      checkCollision.checkPanel(gamePanel);
      try{ Thread.sleep(15); }
      catch(InterruptedException exc) {
        System.out.println("Error sleeping in controller: " + exc);
      }
    }
  }
}
