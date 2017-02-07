package com.HEDgearSoftWare.app;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.util.*;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

  Stars stars[] = new Stars[50];
  public Ship ship;
  public EnemyShip[] enemyShips = new EnemyShip[9];
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
    int marginL = 250;
    for (int i = 0; i < 9; i++) {
      int xspot;
      int yspot;
      if (i < 5) {
        if(i == 0) xspot = marginL;
        else xspot = (marginL + (100 * i));
        yspot = 100;
      } else {
        xspot = (marginL + 50 + ((i - 5) * 100));
        yspot = 200;
      }
      enemyShips[i] = new EnemyShip(xspot, yspot);
    }

  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    draw(g);
  }

  private void draw(Graphics g){
    drawStars(g);
    ship.draw(g);
    for (int i = 0; i < enemyShips.length; i++) {
      enemyShips[i].draw(g);
    }


    Toolkit.getDefaultToolkit().sync();
  }

  private void drawStars(Graphics g) {
    g.setColor(new Color(0, 4, 20));
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

      //  Player Input  +===]========>
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

      //
      // Background movement (passive movement)  +===]========>
      //

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

    // Fire Laser  +===]========>

    if(pi.contains(PlayerInput.FIRE))
      ship.fireLaser();
  }
}
