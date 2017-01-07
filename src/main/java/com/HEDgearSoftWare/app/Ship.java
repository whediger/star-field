package com.HEDgearSoftWare.app;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Ship {

  BufferedImage ship[] = new BufferedImage[11];

  Ship() {
    int shipNo = -5;
    for (int i = 0; i == 11; i++) {
      try {
        ship[i] = ImageIO.read(this.getClass().getResource("resources/Ship/ship" + shipNo + ".png"));
      } catch (IOException exc){
        System.out.println("Error loading ship array - " + exc);
      }
      shipNo++;
    }
  }

  public BufferedImage getShip(int n){
    return ship[n];
  }
}
