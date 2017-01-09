package com.HEDgearSoftWare.app;

import java.util.Random;

public class ThemeMusic implements Runnable {

  AudioFilePlayer player;
  Random rand = new Random();

  public ThemeMusic(){
    player = new AudioFilePlayer();
  }

  @Override
  public void run(){
    int songnum = rand.nextInt(4);

    if(songnum == 0)
      player.play("target/classes/com/HEDgearSoftWare/app/resources/audio/sountracks/soundtrack-"+songnum+".mp3");
    else
      player.play("target/classes/com/HEDgearSoftWare/app/resources/audio/sountracks/soundtrack-"+rand.nextInt(4)+".ogg");
    run();
  }
}
