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
  }

  public PlayerInput getInput(){
    return input;
  }

  @Override
  public void keyPressed(KeyEvent e){
    //39 is right arrow
    //37 is left arrow
    //38 is up arrow
    //40 is down arrow
    key = e.getKeyCode();
    //Printline for dev use: retrieving key codes
    // System.out.println("key pressed: " + e.getKeyCode());
    if (key == 39) input = PlayerInput.RIGHT;
    if (key == 37) input = PlayerInput.LEFT;
    if (key == 38) input = PlayerInput.UP;
    if (key == 40) input = PlayerInput.DOWN;
  }

  @Override
  public void keyReleased(KeyEvent e){
    input = PlayerInput.NONE;
  }

  @Override
  public void keyTyped(KeyEvent e) {}
}
