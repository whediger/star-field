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

  public GamePanel(){
    super();

    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setFocusable(true);
    requestFocus();
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    for (int i = 0; i < 8000; i++) {
    Random rand = new Random();
    Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    g.setColor(randomColor);

      int x = Math.abs(rand.nextInt() % WIDTH);
      int y = Math.abs(rand.nextInt() % HEIGHT);
      int r = rand.nextInt(2);
      if(i % 75 == 0) r = rand.nextInt(5);
      g.drawOval(x, y, r, r);
      if(r >= 1) g.fillOval(x, y, r, r);
    }
  }

}
