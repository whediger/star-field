package com.HEDgearSoftWare.app;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Game {

  public static void main(String args[])
  {
    GamePanel gamePanel = new GamePanel();

    JFrame window = new JFrame("bitsNpieces graphics");
    window.setContentPane(gamePanel);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.pack();
    window.setLocationRelativeTo(null);
    window.setVisible(true);

    GameController gameController = new GameController(gamePanel);
  }
}
