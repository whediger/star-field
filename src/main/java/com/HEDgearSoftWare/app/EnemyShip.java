package com.HEDgearSoftWare.app;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.Random;

public class EnemyShip {

  BufferedImage enemyShip[] = new BufferedImage[1];
  private int x;
  private int y;
  private int xPosy;
  private int yPosy;
  private int xDest;
  private int yDest;
  private double rads;
  private float radius;
  private int wobbleDir;
  private int degress;
  private boolean moving;
  private int shipSpeed;
  private int shipWidth;
  private int shipHeight;
  private boolean hit;
  private boolean destroyed;
  Damage damage;

  EnemyShip(int x, int y) {
    this.x = x;
    this.y = y;
    yPosy = -1;
    xDest = -1;
    Random rand = new Random();
    degress = rand.nextInt(360);
    if(rand.nextInt(2) == 1) wobbleDir = -1;
    else wobbleDir = 1;
    rads = Math.toRadians(degress - 90); //0 becomes the top
    radius = 2; //radius of ship wobble
    moving = true;
    shipWidth = 50;
    shipHeight = 75;
    destroyed = hit = false;
    damage = new Damage(shipWidth, shipHeight);

    try {
      enemyShip[0] = ImageIO.read(this.getClass().getResource("resources/badShips/drone.png"));
    } catch (IOException exc){
      System.out.println("Error loading enemy ship image - " + exc);
    }
  }

  public void setMoving(boolean m){
    moving = m;
  }

  public int getShipWidth() {
    return shipWidth;
  }

  public int getShipHeight() {
    return shipHeight;
  }

  public int getX() {
    return x;
  }

  public void setX(int x){
    this.x = x;
  }

  public void moveX(int x){
    this.x += x;
  }

  public int getXDest() {
    return xDest;
  }

  public void setXDest(int x) {
    xDest = x;
  }

  public int getY() {
    return y;
  }

  public void setY() {
    this.y = y;
  }

  public void moveY(int y){
    this.y += y;
  }

  public int getYDest() {
    return yDest;
  }

  public void setYDest(int y){
    yDest = y;
  }

  public boolean getIsHit(){
    return hit;
  }

  public boolean isDestroyed() {
    return destroyed;
  }

  public BufferedImage getShip() {
    return enemyShip[0];
  }

  public void moveShip(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void hit(){
    damage.create(x, y);
    hit = true;
    destroyed = true;
  }

  public void draw(Graphics g) {
    rads = Math.toRadians(degress - 90); //0 becomes the top
    xPosy = Math.round((float) (x + Math.cos(rads) * radius));
    yPosy = Math.round((float) (y + Math.sin(rads) * radius));
    if(degress == 360) degress = 0;
    degress += wobbleDir * 1; //multiplier to change the speed(1 = no change)
    if(!destroyed){
      if(enemyShip[0] != null) {
        if(!moving) g.drawImage(getShip(), xPosy, yPosy, shipWidth, shipHeight, null);
        else g.drawImage(getShip(), x, y, shipWidth, shipHeight, null);
      }
    }
    if(hit) damage.draw(g);
  }
}
