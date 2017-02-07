package com.HEDgearSoftWare.app;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.*;
import sun.audio.*;
import javax.sound.sampled.*;

class Damage {

  int x;
  int y;
  int shipWidth;
  int shipHeight;
  int damageCount;
  BufferedImage boom[] = new BufferedImage[90];
  boolean active;


  public Damage(int shipWidth, int shipHeight){
      this.x = x;
      this.y = y;
      this.shipWidth = shipWidth;
      this.shipHeight = shipHeight;
      this.damageCount = 0;
      this.active = true;

      try {
        for (int i = 1; i < 90; i++) {
            boom[i] = ImageIO.read(this.getClass().getResource("resources/damageImages/explosion1_" + i + ".png"));
        }
      } catch (IOException exc){
        System.out.println("Error loading explosion image - " + exc);
      }
  }

  public void create(int x, int y) {
    this.x = x;
    this.y = y;
    (new Thread(new DamageAudio())).start();
  }

  public BufferedImage getBoom() {
    ++damageCount;
    if(damageCount == 90) {
      active = false;
      damageCount = 0;
    }
    return boom[damageCount];
  }

  public void draw(Graphics g){
    if(active) g.drawImage(getBoom(), x, y, shipWidth, shipHeight, null);
  }
}
