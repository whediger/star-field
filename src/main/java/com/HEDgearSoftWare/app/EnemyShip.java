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
  Damage damage;

  EnemyShip() {
    x = (int)(ScreenSize.WIDTH.getValue()/2);
    y = 100;
    shipWidth = 50;
    shipHeight = 75;

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

  public void hit(int x, int y){
    damage = new Damage((x - (shipWidth/2)), (y - (shipHeight/2)));
    hit = true;
  }

  public void draw(Graphics g) {
    if(enemyShip[0] != null) {
      g.drawImage(getShip(), x, y, shipWidth, shipHeight, null);
    }
    if(hit) damage.draw(g);
  }
}
