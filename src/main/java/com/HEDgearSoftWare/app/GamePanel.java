package com.HEDgearSoftWare.app;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.util.*;
import java.util.Random;
import javax.swing.JPanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class GamePanel extends JPanel {

  public Stars[] stars = new Stars[50];
  public Ship ship;
  Laser laser;

  public GamePanel(){
    super();

    setPreferredSize(new Dimension(ScreenSize.WIDTH.getValue(),
                                    ScreenSize.HEIGHT.getValue()));
    setFocusable(true);
    requestFocus();
    for(int i = 0; i < stars.length; i++)
      stars[i] = new Stars(ScreenSize.WIDTH.getValue(), ScreenSize.HEIGHT.getValue());

    ship = new Ship();
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    draw(g);
  }

  private boolean laserFired = false;
  private boolean laserMoving = false;
  private int startX, startY, originX, originY;

  private void draw(Graphics g){
    drawStars(g);
    drawShip(g);
    if(laserFired && !laserMoving) drawLaser(g);
    if(laserFired && laserMoving) moveLaser(g);

    Toolkit.getDefaultToolkit().sync();
  }

  private void drawLaser(Graphics g){
    startX = ship.getX() + ship.getShipWidth()/2;
    startY = ship.getY();
    originY = ship.getX();
    laserMoving = true;
  }

  private void moveLaser(Graphics g){
    startY += -15;
    if (startY <= 0) laserFired = laserMoving = false;
    laser = new Laser(startX, startY, originY);
    for (int i = 0; i < laser.getParticleLength(); i++) {
      g.setColor(laser.getParticle(i).color);
      g.drawOval(laser.getParticle(i).x, laser.getParticle(i).y,
                laser.getParticle(i).size, laser.getParticle(i).size);
    };
  }

  private void drawShip(Graphics g){
    if(ship != null) g.drawImage(ship.getShip(), ship.getX(),
                ship.getY(), ship.getShipWidth(), ship.getShipHeight(), null);
  }

  private void drawStars(Graphics g) {
    g.setColor(new Color(0, 4, 44));
    g.fillRect(0, 0, ScreenSize.WIDTH.getValue(), ScreenSize.HEIGHT.getValue());
    for (int i = 0; i < stars.length; i++) {
      g.setColor(stars[i].getColor());
      g.drawOval(stars[i].getX(), stars[i].getY(),
                 stars[i].getDiameter(), stars[i].getDiameter());
      if(stars[i].getDiameter() >= 1) g.fillOval(stars[i].getX(),
         stars[i].getY(), stars[i].getDiameter(), stars[i].getDiameter());
    }
  }

  public void move(Set pi){
    for (int i = 0; i < stars.length; i++) {
      int speed;
      if (stars[i].getDiameter() == 0) speed = 1;
      else speed = stars[i].getDiameter();

      //  Player Input  +===}========>
      if (pi.contains(PlayerInput.RIGHT) && pi.contains(PlayerInput.UP)){
        ship.moveShipRight();
        ship.moveShipRight();
        ship.moveShipUp();
        stars[i].setX(stars[i].getX() - speed);
      } else if (pi.contains(PlayerInput.RIGHT) && pi.contains(PlayerInput.DOWN)){
        ship.moveShipRight();
        ship.moveShipRight();
        ship.moveShipDown();
        stars[i].setX(stars[i].getX() - speed);
      } else if (pi.contains(PlayerInput.RIGHT)){
        ship.moveShipRight();
        stars[i].setX(stars[i].getX() - speed);
      }

      if (pi.contains(PlayerInput.LEFT) && pi.contains(PlayerInput.UP)){
        ship.moveShipLeft();
        ship.moveShipLeft();
        ship.moveShipUp();
        stars[i].setX(stars[i].getX() + speed);
      } else if (pi.contains(PlayerInput.LEFT) && pi.contains(PlayerInput.DOWN)){
        ship.moveShipLeft();
        ship.moveShipLeft();
        ship.moveShipDown();
        stars[i].setX(stars[i].getX() + speed);
      } else if (pi.contains(PlayerInput.LEFT)){
        ship.moveShipLeft();
        stars[i].setX(stars[i].getX() + speed);
      }

      if (pi.contains(PlayerInput.UP)){
        ship.moveShipUp();
      }

      if (pi.contains(PlayerInput.DOWN)){
        ship.moveShipDown();
      }

      if (pi.contains(PlayerInput.NONE)){
        ship.moveToCenter();
      }

      // Fire Laser  +===}========>

      if(pi.contains(PlayerInput.FIRE)) laserFired = true;

      // Background movement (passive movement)  +===}========>

      //handle stars going off bottom
      stars[i].setY(stars[i].getY() + speed);
      //going off bottom
      if(stars[i].getY() >= ScreenSize.HEIGHT.getValue()){
        stars[i] = new Stars(ScreenSize.WIDTH.getValue(), ScreenSize.HEIGHT.getValue());
        stars[i].setY(0);
      }
      //going off the sides
      if(stars[i].getX() < -stars[i].getDiameter())
        stars[i].setX(ScreenSize.WIDTH.getValue() + stars[i].getDiameter());
      if(stars[i].getX() > stars[i].getDiameter() + ScreenSize.WIDTH.getValue())
        stars[i].setX(-stars[i].getDiameter());

    }
  }
}
