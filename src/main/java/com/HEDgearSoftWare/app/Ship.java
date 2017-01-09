package com.HEDgearSoftWare.app;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Ship {

  BufferedImage ship[] = new BufferedImage[11];
  private int currentShip = 6;
  private int counter = 0;
  private int x;
  private int y;
  private int shipSpeed;
  private int shipWidth;
  private int shipHeight;

  Laser laser;
  private boolean laserFired;
  private boolean laserMoving;

  Ship(){

    laser  = new Laser();

    x = (ScreenSize.WIDTH.getValue()/2 -25);
    y = (ScreenSize.HEIGHT.getValue() - 100);
    int shipNo = -5;
    shipSpeed = 5;
    shipWidth = shipHeight = 50;

    for (int i = 0; i <= 10; i++) {
      try {
        ship[i] = ImageIO.read(this.getClass().getResource("resources/Ship/ship" + shipNo + ".png"));
      } catch (IOException exc){
        System.out.println("Error loading ship array - " + exc);
      }
      shipNo++;
    }
  }

  public int getShipWidth(){
    return shipWidth;
  }

  public int getShipHeight(){
    return shipHeight;
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public boolean getLaserFired(){
    return laserFired;
  }

  public boolean getLaserMoving(){
    return laserMoving;
  }

  public void setLaserFired(boolean b){
    laserFired = b;
  }

  public void setLaserMoving(boolean b){
    laserMoving = b;
  }

  public BufferedImage getShip(){
    return ship[currentShip];
  }

  public void fireLaser(){
    laser.fire((x + (shipWidth/2)), y + 15);
    laserFired = true;
    laserMoving = true;
  }

  public void moveLaser(Graphics g){
    laser.move();
    if (laser.getStartY() <= 0) laserFired = laserMoving = false;
      for (int i = 0; i < laser.getParticleLength(); i++) {
        g.setColor(laser.getParticle(i).color);
        g.drawOval(laser.getParticle(i).x, laser.getParticle(i).y,
                  laser.getParticle(i).size, laser.getParticle(i).size);
      };

  }

  public void moveShipRight(){
    counter++;
    if (counter == 50){
      counter = 0;
      if (x < ScreenSize.WIDTH.getValue() - shipWidth)
        x += shipSpeed;
      if(currentShip < 10) //animates ship
        currentShip++;
    }
  }

  public void moveShipLeft(){
    counter++;
    if (counter == 50){
      counter = 0;
      if(x > 0)
        x -= shipSpeed;
      if(currentShip > 0) //animates ship
        currentShip--;
    }
  }

  public void moveShipUp(){
    counter++;
    if (counter == 50){
      counter = 0;
      if(y > 200)
        y -= shipSpeed;
    }
  }

  public void moveShipDown(){
    counter++;
    if (counter == 50){
      counter = 0;
      if (y < ScreenSize.HEIGHT.getValue() - shipHeight)
        y += shipSpeed;
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
