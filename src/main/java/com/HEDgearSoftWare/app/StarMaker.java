package com.HEDgearSoftWare.app;

import java.awt.*;
import java.awt.Dimension;
import java.util.Random;

public class StarMaker {
  private int x;
  private int y;
  private int radius;
  private Color randomColor;

  StarMaker(int width, int height) {
    Random rand = new Random();

    randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

    x = Math.abs(rand.nextInt() % width);
    y = Math.abs(rand.nextInt() % height);
    radius = rand.nextInt(2);
    if (rand.nextInt(100) > 75) radius = rand.nextInt(5);
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public int getRadius(){
    return radius;
  }

  public Color getColor(){
    return randomColor;
  }


}
