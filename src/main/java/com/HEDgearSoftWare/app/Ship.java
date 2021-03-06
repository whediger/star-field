package com.HEDgearSoftWare.app;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Ship {

  BufferedImage ship[] = new BufferedImage[11];
  private int numOfLives;
  private int points;
  private int currentShip = 6;
  private int counter = 0;
  private int x;
  private int y;
  private int shipSpeed;
  private int shipWidth;
  private int shipHeight;
  Damage damage;
  private boolean hit = false;
  private boolean destroyed = false;
  private int destroyedShipDelay;

  Laser laser;
  private boolean laserFired;
  private boolean laserMoving;

  Ship(){
    numOfLives = 4;
    points = 0;
    destroyedShipDelay = 0;

    laser  = new Laser();

    int shipNo = -5;
    shipSpeed = 5;
    shipWidth = shipHeight = 50;
    x = (ScreenSize.WIDTH.getValue()/2 -(shipWidth/2));
    y = (ScreenSize.HEIGHT.getValue() - 100);
    damage = new Damage(shipWidth, shipHeight);

    for (int i = 0; i <= 10; i++) {
      try {
        ship[i] = ImageIO.read(this.getClass().getResource("resources/Ship/ship" + shipNo + ".png"));
      } catch (IOException exc){
        System.out.println("Error loading ship array - " + exc);
      }
      shipNo++;
    }
  }

  public boolean isDestroyed() {
    return destroyed;
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

  public BufferedImage getShip(){
    return ship[currentShip];
  }

  public void hit(){
    damage.create(x, y);
    hit = true;
    destroyed = true;
  }

  public void draw(Graphics g){
    if(numOfLives > 0){
      int startL = ScreenSize.WIDTH.getValue();
      int posYL = ScreenSize.HEIGHT.getValue() - 30;
      for (int i = numOfLives; i > 1; i--) {
        g.drawImage(ship[5], startL-(30*i), posYL, 20, 20, null);
      }
    }
    if(laserFired && laserMoving)
      moveLaser(g);
    if(laserFired && !laserMoving)
      fireLaser();
    if(!destroyed){
      if(ship != null) g.drawImage(getShip(), x,
                  y, shipWidth, shipHeight, null);
    } else {
      destroyedShipDelay++;
      if (destroyedShipDelay >= 1000){
        x = (ScreenSize.WIDTH.getValue()/2 -(shipWidth/2));
        y = (ScreenSize.HEIGHT.getValue() - 100);
        hit = false;
        destroyed = false;
        destroyedShipDelay = 0;
        numOfLives--;
      }
    }
    if(hit) damage.draw(g);
  }

  public void fireLaser(){
    if(!destroyed){
      if(!laserFired){
        laser = new Laser();
        laser.fire((x + (shipWidth/2)), y + 15);
        laserFired = true;
        laserMoving = true;
      }
    }
  }

  public void moveLaser(Graphics g){
    laser.move();
    if (laser.getStartY() <= 0) laserFired = laserMoving = false;
    laser.draw(g);
  }

  public boolean checkLaserHit(int x1In, int x2In, int y1In, int y2In) {
    for (int i = 0; i < laser.getLength(); i++) {
      if (x1In <= laser.getStartX()
      && laser.getStartX() <= x2In
      && y1In <= laser.getStartY()
      && (laser.getStartY() - (laser.getLength()/2)) <= y2In
      && laserMoving) {
        laserFired = laserMoving = false;
        return true;
      }
    }
    return false;
  }

  public int getPoints() {
    return points;
  }

  public void addPoints(int p) {
    this.points += p;
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
