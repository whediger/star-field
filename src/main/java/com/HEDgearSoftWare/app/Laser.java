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
    private int life = 1;
    private Color color = Color.GREEN;
    private Graphics g;
    private static final int PARTICLE_COUNT = 1000;
    private Particle[] laser = new Particle[PARTICLE_COUNT];
    private static final int LASER_LENGTH = 100;
    private static final int LASER_DENSITY = (int)(PARTICLE_COUNT/LASER_LENGTH);
    private static final int LASER_WIDTH = 30;

    public Laser(int startX, int startY, int originY){
      this.startX = startX;
      this.originX = originX;
      this.startY = startY;
      this.originY = originY;
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
          x = startX;
          color = new Color(255, 240, 240);
        } else {
          x = startX + ((rand.nextInt(halfWidth-halfCount)) * sign);
        }
        //startY - length, creates laser from start point upward;
        y = startY - ycount - 1;
        Particle p = new Particle(x, y, 1, color);
        laser[i] = p;
        halfCount++;
        if(i % LASER_DENSITY == 0)
           ycount++;
        if(halfCount == halfWidth) halfCount = 0;
      }
    }

    public static void playAudio(){
      try {
        // InputStream in = new FileInputStream("target/classes/com/HEDgearSoftWare/app/resources/audio/soundFX/superLaser.wav");
        AudioInputStream ain = AudioSystem.getAudioInputStream(
    new File("target/classes/com/HEDgearSoftWare/app/resources/audio/soundFX/superLaser.wav"));
        Clip laserClip = AudioSystem.getClip();
        laserClip.open(ain);
        FloatControl volume = (FloatControl) laserClip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-10.0f);
        laserClip.start();
      } catch(FileNotFoundException exc){
        System.out.println("Error: Laser sound file not found  - " + exc);
      } catch(IOException exc) {
        System.out.println("Error: IO Exception - " + exc);
      } catch (UnsupportedAudioFileException exc){
        System.out.println(exc);
      } catch(LineUnavailableException exc){
        System.out.println(exc);
      }
    }

    public int getParticleLength(){
      return PARTICLE_COUNT;
    }

    public Particle getParticle(int n){
      return laser[n];
    }
}
