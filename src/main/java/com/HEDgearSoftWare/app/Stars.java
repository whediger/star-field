package com.HEDgearSoftWare.app;

import java.awt.*;
import java.awt.Dimension;
import java.util.Random;

public class Stars {
  private int x;
  private int y;
  private int diameter;
  private Color randomColor;

  Stars(int width, int height) {
    Random rand = new Random();

    randomColor = new Color(255 - rand.nextInt(50), 255 - rand.nextInt(50), 255 - rand.nextInt(50));

    diameter = rand.nextInt(2) + 1;
    if (rand.nextInt(80) > 50) diameter = rand.nextInt(10);

    x = rand.nextInt(width + (diameter * 2)) - diameter;
    y = Math.abs(rand.nextInt() % height);

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

  public int getDiameter(){
    return diameter;
  }

  public Color getColor(){
    return randomColor;
  }


}
