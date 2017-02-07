package com.HEDgearSoftWare.app;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class EnemyShip {

  BufferedImage enemyShip[] = new BufferedImage[1];
  private int x;
  private int y;
  private int shipSpeed;
  private int shipWidth;
  private int shipHeight;
  private boolean hit;
  private boolean destroyed;
  Damage damage;

  EnemyShip(int x, int y) {
    this.x = x;
    this.y = y;
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

  public int getShipWidth() {
    return shipWidth;
  }

  public int getShipHeight() {
    return shipHeight;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean getIsHit(){
    return hit;
  }

  public BufferedImage getShip() {
    return enemyShip[0];
  }

  public void hit(){
    damage.create(x, y);
    hit = true;
    destroyed = true;
  }

  public void draw(Graphics g) {
    if(!destroyed){
      if(enemyShip[0] != null) {
        g.drawImage(getShip(), x, y, shipWidth, shipHeight, null);
      }
    } else {
      //destroy ship here!!!
    }
    if(hit) damage.draw(g);
  }
}
