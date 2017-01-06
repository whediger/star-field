package com.HEDgearSoftWare.app;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Random;
import javax.swing.JPanel;



public class GamePanel extends JPanel {

  public static final int WIDTH = (int)(640*1.5);
  public static final int HEIGHT = (int)(480*1.5);
  public Stars[] stars = new Stars[8000];

  public GamePanel(){
    super();

    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setFocusable(true);
    requestFocus();
    for(int i = 0; i < 8000; i++)
      stars[i] = new Stars(WIDTH, HEIGHT);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    for (int i = 0; i < stars.length; i++) {
      g.setColor(stars[i].getColor());
      g.drawOval(stars[i].getX(), stars[i].getY(), stars[i].getRadius(), stars[i].getRadius());
      if(stars[i].getRadius() >= 1) g.fillOval(stars[i].getX(), stars[i].getY(), stars[i].getRadius(), stars[i].getRadius());
    }
  }
}
