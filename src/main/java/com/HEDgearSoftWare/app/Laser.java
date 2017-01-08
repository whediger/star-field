package com.HEDgearSoftWare.app;

import java.awt.*;
import java.util.Random;


public class Laser {

    private int x;
    private int y;
    private int startX;
    private int startY;
    private int life = 1;
    private Color color = Color.GREEN;
    private Graphics g;
    private static final int PARTICLE_COUNT = 1000;
    private Particle[] laser = new Particle[PARTICLE_COUNT];
    private static final int LASER_LENGTH = 100;
    private static final int LASER_DENSITY = (int)(PARTICLE_COUNT/LASER_LENGTH);
    private static final int LASER_WIDTH = 30;

    public Laser(int startX, int startY){
      this.startX = startX;
      this.startY = startY;
      init();
    }

    public void init(){
      Random rand = new Random();
      int ycount = 0;
      int halfWidth = (int)LASER_WIDTH/2;
      int halfCount = 0;
      // System.out.println(PARTICLE_COUNT);
      for (int i = 0; i < PARTICLE_COUNT; i++) {
        int sign = -1;
        color = new Color(rand.nextInt(255), 0, 0);
        if (rand.nextInt(10) % 2 == 0) sign = 1;
        if(halfCount == 0){
          System.out.println("hhalf count 0");
          x = startX;
          color = new Color(255, 240, 240);
        } else {
          x = startX + ((rand.nextInt(halfWidth-halfCount)) * sign);
        }
        System.out.println("x" + x + " density: " + LASER_DENSITY + " halfCount: " + halfCount);
        System.out.println((int)halfWidth);
        //startY - length, creates laser from start point upward;
        // System.out.println("i: " +i+ " x: " +x+ " y: " +y+ " color: " + color);
        y = startY - ycount - 1;
        Particle p = new Particle(x, y, 1, color);
        laser[i] = p;
        halfCount++;
        if(i % LASER_DENSITY == 0)
           ycount++;
        if(halfCount == halfWidth) halfCount = 0;
      }
    }

    public int getParticleLength(){
      return PARTICLE_COUNT;
    }

    public Particle getParticle(int n){
      return laser[n];
    }
}
