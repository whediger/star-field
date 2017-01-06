package com.HEDgearSoftWare.app;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

public class GameListener implements KeyListener {

  GamePanel gamePanel;
  private int key;
  PlayerInput input;

  GameListener(GamePanel gp){
      gamePanel = gp;
      gamePanel.addKeyListener(this);
      System.out.println(gamePanel.isFocusable());
  }

  public PlayerInput getInput(){
    return input;
  }

  @Override
  public void keyPressed(KeyEvent e){
    //39 is right
    //37 is left
    key = e.getKeyCode();
    // System.out.println("key pressed: " + e.getKeyCode());
    if (key == 39) input = PlayerInput.RIGHT;
    if (key == 37) input = PlayerInput.LEFT;
  }

  @Override
  public void keyReleased(KeyEvent e){}

  @Override
  public void keyTyped(KeyEvent e) {}
}
