package com.HEDgearSoftWare.app;
//this is an enemy weapon
//TODO reverse and turn into a starburst circular shap the width of the ship

import java.awt.*;
import java.util.Random;
import java.io.*;
import sun.audio.*;
import javax.sound.sampled.*;

public class StarBurst {

    private int x;
    private int originX;
    private int y;
    private int originY;
    private int startX;
    private int startY;
    private final int starSpeed = 2;
    private Color color = Color.GREEN;
    private Graphics g;
    private static final int PARTICLE_COUNT = 1000;
    private Particle[] star = new Particle[PARTICLE_COUNT];
    private static final int STAR_LENGTH = 40;
    private static final int STAR_DENSITY = (int)(PARTICLE_COUNT/STAR_LENGTH);
    private static final int STAR_WIDTH = 50;

    //audio vars +===}========>
    Clip starClip;
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
      return STAR_LENGTH;
    }

    public void fire(int startX, int startY){
      this.startX = startX;
      this.originX = startX;
      this.startY = startY + starSpeed;
      this.originY = startY;
      move();
      startAudio();
    }

    public void draw(Graphics g){
      for (int i = 0; i < star.length; i++) {
        g.setColor(star[i].color);
        g.drawOval(star[i].x, star[i].y,
                  star[i].size, star[i].size);
      };
    }

    public void move(){
      Random rand = new Random();
      int ycount = 0;
      int halfWidth = (int)STAR_WIDTH/2;
      int halfCount = 0;
      // System.out.println(PARTICLE_COUNT);
      for (int i = 0; i < PARTICLE_COUNT; i++) {
        int sign = -1;
        color = new Color(0, 0, rand.nextInt(255));
        if (rand.nextInt(10) % 2 == 0) sign = 1;
        if(halfCount == 0){
          x = startX;
          color = new Color(240, 240, 255);
        } else {
          x = startX + ((rand.nextInt(halfWidth-halfCount)) * sign);
        }
        //TODO turn this into an actual starBurst
        //startY - length, creates laser from start point at ship downward;
        y = startY + ycount + 1;
        Particle p = new Particle(x, y, 1, color);
        star[i] = p;
        halfCount++;
        if(i % STAR_DENSITY == 0)
           ycount++;
        if(halfCount == halfWidth) halfCount = 0;
      }
      startY += starSpeed;
    }

    private void startAudio(){
      try {
        ain = AudioSystem.getAudioInputStream(
    new File("target/classes/com/HEDgearSoftWare/app/resources/audio/soundFX/superLaser.wav"));
        starClip = AudioSystem.getClip();
        starClip.open(ain);
        volume = (FloatControl) starClip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-10.0f);
        starClip.start();
      } catch(LineUnavailableException exc){
        System.out.println(exc);
      } catch(FileNotFoundException exc){
        System.out.println("Error: Star sound file not found  - " + exc);
      } catch(IOException exc){
        System.out.println(exc);
      } catch (UnsupportedAudioFileException exc){
        System.out.println(exc);
      }
    }

    public void stopAudio(){
      starClip.stop();
    }

    public int getParticleLength(){
      return PARTICLE_COUNT;
    }

    public Particle getParticle(int n){
      return star[n];
    }
}
