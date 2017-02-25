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
    private int y;
    private int originX;
    private int originY;
    private int startX;
    private int startY;
    private final int starSpeed = 2;
    private Color color;
    private Graphics g;
    private static final int PARTICLE_COUNT = 400;
    private Particle[] star = new Particle[PARTICLE_COUNT];
    private static final int STAR_LENGTH = 40;
    private static final int STAR_DENSITY = (int)(PARTICLE_COUNT/STAR_LENGTH);
    private int shipWidth;
    Random rand = new Random();
    File audioFile;

    //audio vars +===}========>
    Clip starClip;
    FloatControl volume;
    AudioInputStream audioHead;
    AudioInputStream audioBody;
    AudioInputStream audioTail;
    int randHeadNum;
    int randBodyNum;
    int randTailNum;

    public StarBurst(int sw){
      shipWidth = sw;
      randHeadNum = rand.nextInt(9);
      audioFile = new File("target/classes/com/HEDgearSoftWare/app/resources/audio/soundFX/Laser-Lab/Heads/HEAD000"+randHeadNum+".wav");
      try {
        audioHead = AudioSystem.getAudioInputStream(audioFile);
        starClip = AudioSystem.getClip();
        starClip.open(audioHead); //TODO this is causing a memory leak
        volume = (FloatControl) starClip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-10.0f);
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

    //methods +===}========>
    public void move(){

      int xPosy = 0;
      int yPosy = 0;
      int randFlip = 1;
      int radius = (int)shipWidth/2;
      int middleR = (int) radius / 2;
      double rads = 0;
      int rdn = 0;
      int ptclCnt = 0;
      int lineCount = 4; //number of lines in the burst
      int radShift = rand.nextInt(360 / (lineCount * 2));
      int lineShift = 360 / (lineCount * 2);
      int degree1 = 0 + radShift;
      int degree2 = 180 + radShift;
      int burst = (int)PARTICLE_COUNT / lineCount;
      int middleD = (int)burst / 2;
      int outterD = (int)burst / 2;
      color = new Color(255, 255, 255);
      int randMidR;
      int randOutR;
      Particle p;

      for (int i = 0; i < lineCount; i++) {
        randMidR = rand.nextInt(middleR) + 1;
        randOutR = rand.nextInt(radius) + 1;
        degree1 += (lineShift * i);
        degree2 += (lineShift * i);
        for (int j = 0; j < burst; j++) {
          randFlip = rand.nextInt(2);
          if (randFlip == 1) {
            rads = Math.toRadians(degree1);
          } else {
            rads = Math.toRadians(degree2);
          }
          if (j < middleD) {
            color = new Color(100, 100, 255);
            rdn = rand.nextInt(randMidR);
            xPosy = Math.round((float) (startX + Math.cos(rads) * rdn));
            yPosy = Math.round((float) (startY + Math.sin(rads) * rdn));
          } else {
            color = new Color(100, 255, 100);
            rdn = rand.nextInt(randOutR);
            xPosy = Math.round((float) (startX + Math.cos(rads) * rdn));
            yPosy = Math.round((float) (startY + Math.sin(rads) * rdn));
          }
          p = new Particle(xPosy, yPosy, 1, color);
          star[ptclCnt] = p;
          ptclCnt++;
        }
      }
      startY += starSpeed;
    }

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

    private void startAudio(){
      starClip.start();
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
