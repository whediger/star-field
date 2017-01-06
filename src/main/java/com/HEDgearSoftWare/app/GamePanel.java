package com.HEDgearSoftWare.app;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

  public static final int WIDTH = 640;
  public static final int HEIGHT = 480;

  public GamePanel(){
    super();

    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setFocusable(true);
    requestFocus();
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    Font myFont = new Font(Font.MONOSPACED, Font.BOLD, 16);
    g.setColor(Color.RED);
    g.setFont(myFont);
    g.drawString("Hello sanity", 10, 20);
    g.setColor(Color.BLUE);
    g.drawLine(20, 20, 630, 470);
    g.drawLine((WIDTH/2), 0, (WIDTH/2), HEIGHT);
  }

}
