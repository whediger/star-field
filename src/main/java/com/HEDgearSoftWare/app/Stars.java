package com.HEDgearSoftWare.app;

import java.awt.*;
import java.awt.Dimension;
import java.util.Random;

public class Stars {
  private int x;
  private int y;
  private int radius;
  private Color randomColor;

  Stars(int width, int height) {
    Random rand = new Random();

    randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

    x = Math.abs(rand.nextInt() % width);
    y = Math.abs(rand.nextInt() % height);
    radius = rand.nextInt(2);
    if (rand.nextInt(80) > 75) radius = rand.nextInt(5);
  }

  public int getX(){
    return x;
  }

  public void setX(int n){
    x = n;
  }

  public int getY(){
    return y;
  }

  public void setY(int n){
    y = n;
  }

  public int getRadius(){
    return radius;
  }

  public Color getColor(){
    return randomColor;
  }


}
