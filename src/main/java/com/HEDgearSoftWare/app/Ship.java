package com.HEDgearSoftWare.app;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Ship {

  BufferedImage ship[] = new BufferedImage[11];
  private int currentShip = 6;
  private int counter = 0;
  private int x;
  private int y;

  Ship(){

    x = (ScreenSize.WIDTH.getValue()/2 -25);
    y = (ScreenSize.HEIGHT.getValue() - 100);


    int shipNo = -5;
    for (int i = 0; i <= 10; i++) {
      try {
        ship[i] = ImageIO.read(this.getClass().getResource("resources/Ship/ship" + shipNo + ".png"));
      } catch (IOException exc){
        System.out.println("Error loading ship array - " + exc);
      }
      shipNo++;
    }
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public BufferedImage getShip(){
    return ship[currentShip];
  }

  public void moveShipRight(){
    counter++;
    if (counter == 50){
      counter = 0;
      if(currentShip < 10){
        currentShip++; }
    }
  }

  public void moveShipLeft(){
    counter++;
    if (counter == 50){
      counter = 0;
      if(currentShip > 0){
        currentShip--; }
    }
  }

  public void moveToCenter(){
    counter++;
    if (counter == 50){
      counter = 0;
      if(currentShip < 6) currentShip++;
      if(currentShip > 6) currentShip--;}
  }
}
