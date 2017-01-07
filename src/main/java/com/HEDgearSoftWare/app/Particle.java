package com.HEDgearSoftWare.app;


import java.awt.Color;
import java.awt.Graphics;

public class Particle {

  private Graphics g;
  private int x;
  private int y;
  private int dx;
  private int dy;
  private int size;
  private int life;
  private Color color;

  public Particle(Graphics g, int x, int y, int dx, int dy, int size, int life, Color color){
    this.g = g;
    this.x = x;
    this.y = y;
    this.dx = dx;
    this.dy = dy;
    this.size = size;
    this.life = life;
    this.color = color;
  }

  public boolean update(){
    x += dx;
    y += dy;
    life--;
    if(life <= 0) return false;
    else return true;
  }

  public void render(Graphics g){
    Graphics2d g2d = (Graphics2d) g.create();

    g2d.setColor(color);
    g2d.fillRect(x-(size/2), y-(size/2), size, size);

    g2d.dispose();
  }
}
