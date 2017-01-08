package com.HEDgearSoftWare.app;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.util.*;

public class GameListener implements KeyListener {

  GamePanel gamePanel;
  private int key;
  private Set<PlayerInput> input;

  GameListener(GamePanel gp){
      gamePanel = gp;
      gamePanel.addKeyListener(this);
      input = new HashSet<PlayerInput>();
  }

  public Set getInput(){
    return input;
  }

  @Override
  public void keyPressed(KeyEvent e){
    //39 is right arrow
    //37 is left arrow
    //38 is up arrow
    //40 is down arrow
    //70 is 'F'
    key = e.getKeyCode();
    //Printline for dev use: retrieving key codes
    // System.out.println("key pressed: " + e.getKeyCode());
    if(input.contains(PlayerInput.NONE)) input.remove(PlayerInput.NONE);
    if (key == 39) input.add(PlayerInput.RIGHT);
    if (key == 37) input.add(PlayerInput.LEFT);
    if (key == 38) input.add(PlayerInput.UP);
    if (key == 40) input.add(PlayerInput.DOWN);
    if (key == 70) input.add(PlayerInput.FIRE);
  }

  @Override
  public void keyReleased(KeyEvent e){
    key = e.getKeyCode();
    if (key == 39) input.remove(PlayerInput.RIGHT);
    if (key == 37) input.remove(PlayerInput.LEFT);
    if (key == 38) input.remove(PlayerInput.UP);
    if (key == 40) input.remove(PlayerInput.DOWN);
    if (key == 70) input.remove(PlayerInput.FIRE);
    if (input.isEmpty()) input.add(PlayerInput.NONE);
    //TODO: if hashset is empty give value PlayerInput.NONE
  }

  @Override
  public void keyTyped(KeyEvent e) {}
}
