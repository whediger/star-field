package com.HEDgearSoftWare.app;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Random;
import javax.swing.JPanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class GamePanel extends JPanel {

  public static final int WIDTH = (int)(640*1.5);
  public static final int HEIGHT = (int)(480*1.5);
  public Stars[] stars = new Stars[50];
  public Ship ship;

  public GamePanel(){
    super();

    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setFocusable(true);
    requestFocus();
    for(int i = 0; i < stars.length; i++)
      stars[i] = new Stars(WIDTH, HEIGHT);

    ship = new Ship();
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    draw(g);
  }

  private void draw(Graphics g){
      drawStars(g);
      drawShip(g);
      Toolkit.getDefaultToolkit().sync();
  }

  private void drawShip(Graphics g){
    if(ship != null) g.drawImage(ship.getShip(), (WIDTH/2 - 25), (HEIGHT - 100), 50, 50, null);
  }

  private void drawStars(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    for (int i = 0; i < stars.length; i++) {
      g.setColor(stars[i].getColor());
      g.drawOval(stars[i].getX(), stars[i].getY(), stars[i].getDiameter(), stars[i].getDiameter());
      if(stars[i].getDiameter() >= 1) g.fillOval(stars[i].getX(), stars[i].getY(), stars[i].getDiameter(), stars[i].getDiameter());
    }
  }

  public void moveStars(PlayerInput pi){
    for (int i = 0; i < stars.length; i++) {
      int speed;
      if (stars[i].getDiameter() == 0) speed = 1;
      else speed = stars[i].getDiameter();

      //handle user input
      if (pi == pi.RIGHT){
        stars[i].setX(stars[i].getX() - speed);
      }

      if (pi == pi.LEFT){
        stars[i].setX(stars[i].getX() + speed);
      }


      //handle stars going off bottom
      stars[i].setY(stars[i].getY() + speed);
      //going off bottom
      if(stars[i].getY() >= HEIGHT){
        stars[i] = new Stars(WIDTH, HEIGHT);
        stars[i].setY(0);
      }
      //going off the sides
      if(stars[i].getX() < -stars[i].getDiameter())
        stars[i].setX(WIDTH + stars[i].getDiameter());
      if(stars[i].getX() > stars[i].getDiameter() + WIDTH)
        stars[i].setX(-stars[i].getDiameter());

    }
  }
}
