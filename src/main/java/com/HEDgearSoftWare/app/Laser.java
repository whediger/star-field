package com.HEDgearSoftWare.app;

import java.awt.*;
import java.util.Random;
import java.io.*;
import sun.audio.*;
import javax.sound.sampled.*;

public class Laser {

    private int x;
    private int originX;
    private int y;
    private int originY;
    private int startX;
    private int startY;
    private final int laserSpeed = 15;
    private Color color = Color.GREEN;
    private Graphics g;
    private static final int PARTICLE_COUNT = 1000;
    private Particle[] laser = new Particle[PARTICLE_COUNT];
    private static final int LASER_LENGTH = 100;
    private static final int LASER_DENSITY = (int)(PARTICLE_COUNT/LASER_LENGTH);
    private static final int LASER_WIDTH = 30;

    //audio vars +===}========>
    Clip laserClip;
    FloatControl volume;
    AudioInputStream ain;

    //methods +===}========>
    public int getStartY(){
      return startY;
    }

    public int getStartX() {
      return startX;
    }

    public int getLength() {
      return LASER_LENGTH;
    }

    public void fire(int startX, int startY){
      this.startX = startX;
      this.originX = startX;
      this.startY = startY + laserSpeed;
      this.originY = startY;
      move();
      startAudio();
    }

    public void draw(Graphics g){
      for (int i = 0; i < laser.length; i++) {
        g.setColor(laser[i].color);
        g.drawOval(laser[i].x, laser[i].y,
                  laser[i].size, laser[i].size);
      };
    }

    public void move(){
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
          x = startX;
          color = new Color(255, 240, 240);
        } else {
          x = startX + ((rand.nextInt(halfWidth-halfCount)) * sign);
        }
        //startY - length, creates laser from start point at ship upward;
        y = startY - ycount - 1;
        Particle p = new Particle(x, y, 1, color);
        laser[i] = p;
        halfCount++;
        if(i % LASER_DENSITY == 0)
           ycount++;
        if(halfCount == halfWidth) halfCount = 0;
      }
      startY += -laserSpeed;
    }

    private void startAudio(){
      try {
        ain = AudioSystem.getAudioInputStream(
    new File("target/classes/com/HEDgearSoftWare/app/resources/audio/soundFX/superLaser.wav"));
        laserClip = AudioSystem.getClip();
        laserClip.open(ain);
        volume = (FloatControl) laserClip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-15.0f);
        laserClip.start();
      } catch(LineUnavailableException exc){
        System.out.println(exc);
      } catch(FileNotFoundException exc){
        System.out.println("Error: Laser sound file not found  - " + exc);
      } catch(IOException exc){
        System.out.println(exc);
      } catch (UnsupportedAudioFileException exc){
        System.out.println(exc);
      }
    }

    public void stopAudio(){
      laserClip.stop();
    }

    public int getParticleLength(){
      return PARTICLE_COUNT;
    }

    public Particle getParticle(int n){
      return laser[n];
    }
}
