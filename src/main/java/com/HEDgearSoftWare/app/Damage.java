package com.HEDgearSoftWare.app;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

class Damage {

  int x;
  int y;
  int damageCount;
  BufferedImage boom[] = new BufferedImage[90];

  public Damage(int x, int y){
      this.x = x;
      this.y = y;
      this.damageCount = 0;

      System.out.println("position x: " + this.x);
      System.out.println("position y: " + this.y);
      try {
        for (int i = 1; i < 90; i++) {
            boom[i] = ImageIO.read(this.getClass().getResource("resources/damageImages/explosion1_" + i + ".png"));
        }
      } catch (IOException exc){
        System.out.println("Error loading explosion image - " + exc);
      }
  }

  public BufferedImage getBoom() {
    ++damageCount;
    if(damageCount == 90) damageCount = 0;
    return boom[damageCount];
  }

  public void draw(Graphics g){
    g.drawImage(getBoom(), x, y, 64, 48, null);
  }
}
